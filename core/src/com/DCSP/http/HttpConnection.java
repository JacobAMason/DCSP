/*
 * The MIT License
 *
 * Copyright 2015 Jacob Mason (jm2232).
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.DCSP.http;

import com.DCSP.game.GameRoot;
import com.DCSP.game.UserProfile;
import com.DCSP.screen.ChallengesScreen;
import com.DCSP.screen.GameMenuScreen;
import com.DCSP.screen.HighScoresScreen;
import com.DCSP.windows.MessageWindow;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpParametersUtils;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.OrderedMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Parsing JSON. Complicated stuff, dude.
 * https://stackoverflow.com/questions/27078410/libgdx-reading-from-json-file-to-arraylist
 * https://stackoverflow.com/questions/15278619/lib-gdx-json-serializationexception-and-missing-no-arg-constructor
 *
 * Using Render methods (like setting a screen) from a thread other than the
 * OpenGL thread: https://github.com/libgdx/libgdx/wiki/Threading
 *
 * @author Jacob Mason (jm2232)
 */
public class HttpConnection {

    GameRoot gameParent;
    MessageWindow messageWindow;
    
    final private String HOST = "http://jacobmason.net/aMAZE";

    public HttpConnection(GameRoot gameParent) {
        this.gameParent = gameParent;
        messageWindow = gameParent.getMessageWindow();
    }

    // Returns true/false depending on whether the login succeeded or failed.
    public void login(String username, String password, final Window successWindow) {
        Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.POST);
        request.setUrl(HOST + "/application/Login.php");

        Map parameters = new HashMap();
        parameters.put("username", username);
        parameters.put("password", password);

        request.setContent(HttpParametersUtils.convertHttpParameters(parameters));

        Gdx.net.sendHttpRequest(request, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                String response = httpResponse.getResultAsString();
                Json json = new Json();
                ObjectMap result = json.fromJson(ObjectMap.class, response);
                Gdx.app.log("HttpCon:Login", result.toString());

                if (result.get("result").equals("Fail")) {
                    successWindow.setVisible(true);
                } else if (result.get("result").equals("Success")) {

                    try {
                        gameParent.profile
                                = new UserProfile(Math.round(Float.valueOf(result.get("ID").toString())), // This is the stupidest conversion ever invented. Thanks Java.
                                        result.get("username").toString(),
                                        result.get("email").toString(),
                                        result.get("Name").toString());
                        gameParent.logIn();
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }

                    Gdx.app.postRunnable(new Runnable() {

                        @Override
                        public void run() {
                            getFriends(gameParent.profile.getID());
                            getScores(gameParent.profile.getID());
                            Gdx.input.setOnscreenKeyboardVisible(false);
                        }
                    });
                }
            }

            @Override
            public void failed(Throwable t) {
                Gdx.app.log("HttpCon:Login", "Connection Fail: " + t.toString());
                messageWindow.makeConnectionErrorWindow();
            }

            @Override
            public void cancelled() {
                Gdx.app.log("HttpCon:Login", "Cancel function called. What does this even do?");
            }
        });
    }

    public void register(String username, String password, String name, String email,
            final Window connectionFailWindow) {
        Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.POST);
        request.setUrl(HOST + "/application/Register.php");

        Map parameters = new HashMap();
        parameters.put("username", username);
        parameters.put("password", password);
        parameters.put("name", name);
        parameters.put("email", email);

        request.setContent(HttpParametersUtils.convertHttpParameters(parameters));

        Gdx.net.sendHttpRequest(request, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                String response = httpResponse.getResultAsString();
                Gdx.app.log("HttpCon:Register", response);
                Json json = new Json();
                ObjectMap result = json.fromJson(ObjectMap.class, response);
                Gdx.app.log("HttpCon:Register", result.toString());

                if (result.get("result").equals("usernameInUse")) {
                    messageWindow.setTitle("Registration Error");
                    messageWindow.setText("This username is already taken.");
                } else if (result.get("result").equals("Fail")) {
                    messageWindow.setTitle("Registration Error");
                    messageWindow.setText("There was an issue registering.\n"
                            + "Please try again.");
                } else if (result.get("result").equals("Success")) {
                    messageWindow.setTitle("Registration Complete");
                    messageWindow.setText("You've successfully registered.");
                    Gdx.app.postRunnable(new Runnable() {

                        @Override
                        public void run() {
                            gameParent.setScreen(gameParent.mainMenuScreen);
                        }
                    });
                }

                messageWindow.update();
                messageWindow.setVisible(true);
            }

            @Override
            public void failed(Throwable t) {
                Gdx.app.log("HttpCon:Register", "Connection Fail");
                messageWindow.makeConnectionErrorWindow();
            }

            @Override
            public void cancelled() {
                Gdx.app.log("HttpCon:Register", "Cancel function called. What does this even do?");
            }
        });
    }

    public void sendScore(int ID, int level, double score) {
        Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.POST);
        request.setUrl(HOST + "/application/sendScores.php");

        Map parameters = new HashMap();
        parameters.put("ID", String.valueOf(ID));
        parameters.put("score", String.valueOf(score));
        parameters.put("level", String.valueOf(level));

        request.setContent(HttpParametersUtils.convertHttpParameters(parameters));

        Gdx.net.sendHttpRequest(request, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                Gdx.app.log("HttpCon:sendScore", httpResponse.getResultAsString());
            }

            @Override
            public void failed(Throwable t) {
                Gdx.app.log("HttpCon:sendScore", "Connection Fail");
                messageWindow.makeConnectionErrorWindow();
            }

            @Override
            public void cancelled() {
                Gdx.app.log("HttpCon:sendScore", "Cancel function called. What does this even do?");
            }
        });
    }

    public void getScores(int ID) {
        Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.POST);
        request.setUrl(HOST + "/application/getScores.php");

        Map parameters = new HashMap();
        parameters.put("ID", String.valueOf(ID));
        request.setContent(HttpParametersUtils.convertHttpParameters(parameters));

        Gdx.net.sendHttpRequest(request, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                String response = httpResponse.getResultAsString();
                Gdx.app.log("HttpCon:getScores", response);

                Json json = new Json();
                try {
                    ScoresResponse result = json.fromJson(ScoresResponse.class, response);
                    Gdx.app.log("HttpCon:getScores", result.scoreTupleArray.toString());

                    gameParent.profile.setScoresDict(result.scoreTupleArray);

                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }

            @Override
            public void failed(Throwable t) {
                Gdx.app.log("HttpCon:getScores", "Connection Fail");
                messageWindow.makeConnectionErrorWindow();
            }

            @Override
            public void cancelled() {
                Gdx.app.log("HttpCon:getScores", "Cancel function called. What does this even do?");
            }
        });
    }

    public void getHighScores() {
        Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.POST);
        request.setUrl(HOST + "/application/HighScore.php");

        Gdx.net.sendHttpRequest(request, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                String response = httpResponse.getResultAsString();
                Gdx.app.log("HttpCon:getHighScores", response);
                Json json = new Json();
                final OrderedMap result = json.fromJson(OrderedMap.class, response);
                Gdx.app.log("HttpCon:getHighScores", result.toString());

                if (result.remove("result").equals("Success")) {
                    Gdx.app.postRunnable(new Runnable() {
                        @Override
                        public void run() {
                            gameParent.setScreen(new HighScoresScreen(result));
                        }
                    });
                }
            }

            @Override
            public void failed(Throwable t) {
                Gdx.app.log("HttpCon:getHighScores", "Connection Fail");
                messageWindow.makeConnectionErrorWindow();
            }

            @Override
            public void cancelled() {
                Gdx.app.log("HttpCon:getHighScores", "Cancel function called. What does this even do?");
            }
        });
    }

    public void userLookup(String username) {
        Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.POST);
        request.setUrl(HOST + "/application/userLookup.php");

        Map parameters = new HashMap();
        parameters.put("username", String.valueOf(username));

        request.setContent(HttpParametersUtils.convertHttpParameters(parameters));

        Gdx.net.sendHttpRequest(request, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                Gdx.app.log("HttpCon:userLookup", httpResponse.getResultAsString());
            }

            @Override
            public void failed(Throwable t) {
                Gdx.app.log("HttpCon:userLookup", "Connection Fail");
                messageWindow.makeConnectionErrorWindow();
            }

            @Override
            public void cancelled() {
                Gdx.app.log("HttpCon:userLookup", "Cancel function called. What does this even do?");
            }
        });
    }

    public void IDLookup(int ID) {
        Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.POST);
        request.setUrl(HOST + "/application/addFriend.php");

        Map parameters = new HashMap();
        parameters.put("ID", String.valueOf(ID));

        request.setContent(HttpParametersUtils.convertHttpParameters(parameters));

        Gdx.net.sendHttpRequest(request, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                Gdx.app.log("HttpCon:IDLookup", httpResponse.getResultAsString());
            }

            @Override
            public void failed(Throwable t) {
                Gdx.app.log("HttpCon:IDLookup", "Connection Fail");
                messageWindow.makeConnectionErrorWindow();
            }

            @Override
            public void cancelled() {
                Gdx.app.log("HttpCon:IDLookup", "Cancel function called. What does this even do?");
            }
        });
    }

    public void sendChallenge(double score, int level, long seed, String toUsername) {
        Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.POST);
        request.setUrl(HOST + "/application/sendChallenge.php");

        Map parameters = new HashMap();
        parameters.put("ID", String.valueOf(gameParent.profile.getID()));
        parameters.put("score", String.valueOf(score));
        parameters.put("level", String.valueOf(level));
        parameters.put("seed", String.valueOf(seed));
        parameters.put("toUsername", toUsername);

        request.setContent(HttpParametersUtils.convertHttpParameters(parameters));

        Gdx.net.sendHttpRequest(request, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                Gdx.app.log("HttpCon:sendChallenge", httpResponse.getResultAsString());

                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        messageWindow.setTitle("Thanks");
                        messageWindow.setText("Your Challenge has been sent!");
                        messageWindow.update();
                        messageWindow.setVisible(true);
                        gameParent.setScreen(new GameMenuScreen());
                    }
                });
            }

            @Override
            public void failed(Throwable t) {
                Gdx.app.log("HttpCon:sendChallenge", "Connection Fail");
                messageWindow.makeConnectionErrorWindow();
            }

            @Override
            public void cancelled() {
                Gdx.app.log("HttpCon:sendChallenge", "Cancel function called. What does this even do?");
            }
        });
    }

    public void getChallenges(int ID) {
        Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.POST);
        request.setUrl(HOST + "/application/getChallenges.php");

        Map parameters = new HashMap();
        parameters.put("ID", String.valueOf(ID));

        request.setContent(HttpParametersUtils.convertHttpParameters(parameters));

        Gdx.net.sendHttpRequest(request, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                String response = httpResponse.getResultAsString();
                Gdx.app.log("HttpCon:getChallenges", response);
                Json json = new Json();
                try {
                    final ChallengesResponse results = json.fromJson(ChallengesResponse.class, response);
                    if (results.result.equals("Success")) {
                        Gdx.app.log("HttpCon:getChallenges", results.challengeResultsArray.toString());

                        Gdx.app.postRunnable(new Runnable() {
                            @Override
                            public void run() {
                                gameParent.setScreen(new ChallengesScreen(results));
                            }
                        });

                    } else {
                        Gdx.app.log("HttpCon:getChallenges", "No challenges found.");
                        messageWindow.setTitle("No Challenges");
                        messageWindow.setText("There are currently no challenges for you.");
                        messageWindow.update();
                        messageWindow.setVisible(true);
                    }
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }

            @Override
            public void failed(Throwable t) {
                Gdx.app.log("HttpCon:getChallenges", "Connection Fail");
                messageWindow.makeConnectionErrorWindow();
            }

            @Override
            public void cancelled() {
                Gdx.app.log("HttpCon:getChallenges", "Cancel function called. What does this even do?");
            }
        });
    }

    public void addFriend(int frienderID, String friendeeUsername, final List friendList) {

        // You can't friend yourself.
        if (friendeeUsername.toLowerCase().equals(gameParent.profile.getUsername().toLowerCase())) {
            Gdx.app.log("HttpCon:addFriend", "Can't add yourself");
            return;
        }

        Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.POST);
        request.setUrl(HOST + "/application/addFriend.php");

        Map parameters = new HashMap();
        parameters.put("friender", String.valueOf(frienderID));
        parameters.put("friendee", friendeeUsername);

        request.setContent(HttpParametersUtils.convertHttpParameters(parameters));

        Gdx.net.sendHttpRequest(request, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                Gdx.app.log("HttpCon:addFriend", httpResponse.getResultAsString());

                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                        getFriends(gameParent.profile.getID(), friendList);
                    }
                });
            }

            @Override
            public void failed(Throwable t) {
                Gdx.app.log("HttpCon:addFriend", "Connection Fail");
                messageWindow.makeConnectionErrorWindow();
            }

            @Override
            public void cancelled() {
                Gdx.app.log("HttpCon:addFriend", "Cancel function called. What does this even do?");
            }
        });
    }

    public void getFriends(int frienderID) {
        getFriends(frienderID, null);
    }

    public void getFriends(int frienderID, final List friendList) {
        Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.POST);
        request.setUrl(HOST + "/application/getFriends.php");

        Map parameters = new HashMap();
        parameters.put("friender", String.valueOf(frienderID));

        request.setContent(HttpParametersUtils.convertHttpParameters(parameters));

        Gdx.net.sendHttpRequest(request, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                String response = httpResponse.getResultAsString();
                Gdx.app.log("HttpCon:getFriends", response);
                Json json = new Json();
                try {
                    ObjectMap results = json.fromJson(ObjectMap.class, response);
                    if (results.get("result").equals("Success")) {
                        final Array friendResultsArray = (Array) results.get("friendResultsArray");
                        if (friendList != null) {
                            friendList.setItems(friendResultsArray);
                        }
                        gameParent.profile.setFriends(friendResultsArray);
                    } else {
                        Gdx.app.log("HttpCon:getFriends", "You have no friends.");
                    }
                } catch (Exception e) {
                    System.out.println(e.toString());
                }
            }

            @Override
            public void failed(Throwable t) {
                Gdx.app.log("HttpCon:getFriends", "Connection Fail");
                messageWindow.makeConnectionErrorWindow();
            }

            @Override
            public void cancelled() {
                Gdx.app.log("HttpCon:getFriends", "Cancel function called. What does this even do?");
            }
        });
    }
}
