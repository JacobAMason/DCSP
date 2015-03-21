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

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpParametersUtils;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ObjectMap;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Jacob Mason (jm2232)
 */
public class HttpConnection {
    
    // Returns true/false depending on whether the login succeeded or failed.
    public void login(String username, String password) {
        Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.POST);
        request.setUrl("http://pluto.cse.msstate.edu/~dcsp01/application/Login.php");
        
        Map parameters = new HashMap();
        parameters.put("username", username);
        parameters.put("password", password);
        
        request.setContent(HttpParametersUtils.convertHttpParameters(parameters));
        
        Gdx.net.sendHttpRequest(request, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                String response = httpResponse.getResultAsString();
                Gdx.app.log("HttpCon:Login", response);
                Json json = new Json();
                ObjectMap result = json.fromJson(ObjectMap.class, response);
                Gdx.app.log("HttpCon:Login", result.get("result").toString());
            }

            @Override
            public void failed(Throwable t) {
                Gdx.app.log("HttpCon:Login", "Connection Fail");
            }

            @Override
            public void cancelled() {
                Gdx.app.log("HttpCon:Login", "Cancel function called. What does this even do?");
            }
        });
    }
    
    
    public void register(String username, String password, String name, String email) {
        Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.POST);
        request.setUrl("http://pluto.cse.msstate.edu/~dcsp01/application/Register.php");
        
        Map parameters = new HashMap();
        parameters.put("username", username);
        parameters.put("password", password);
        parameters.put("name", name);
        parameters.put("email", email);
        
        request.setContent(HttpParametersUtils.convertHttpParameters(parameters));
        
        Gdx.net.sendHttpRequest(request, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                Gdx.app.log("HttpCon:Register", httpResponse.getResultAsString());
            }

            @Override
            public void failed(Throwable t) {
                Gdx.app.log("HttpCon:Register", "Connection Fail");
            }

            @Override
            public void cancelled() {
                Gdx.app.log("HttpCon:Register", "Cancel function called. What does this even do?");
            }
        });
    }
    
    
    public void sendScore(int ID, int level, int score) {
        Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.POST);
        request.setUrl("http://pluto.cse.msstate.edu/~dcsp01/application/Score.php");
        
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
            }

            @Override
            public void cancelled() {
                Gdx.app.log("HttpCon:sendScore", "Cancel function called. What does this even do?");
            }
        });
    }
    
    
    public void getHighScores() {
        Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.POST);
        request.setUrl("http://pluto.cse.msstate.edu/~dcsp01/application/HighScore.php");
        
        Gdx.net.sendHttpRequest(request, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                Gdx.app.log("HttpCon:getHighScores", httpResponse.getResultAsString());
            }

            @Override
            public void failed(Throwable t) {
                Gdx.app.log("HttpCon:getHighScores", "Connection Fail");
            }

            @Override
            public void cancelled() {
                Gdx.app.log("HttpCon:getHighScores", "Cancel function called. What does this even do?");
            }
        });
    }
    
    
    public void userLookup(String username) {
        Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.POST);
        request.setUrl("http://pluto.cse.msstate.edu/~dcsp01/application/userLookup.php");
        
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
            }

            @Override
            public void cancelled() {
                Gdx.app.log("HttpCon:userLookup", "Cancel function called. What does this even do?");
            }
        });
    }
    
    
    public void sendChallenge(int ID, int score, int level, long seed, int toID) {
        Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.POST);
        request.setUrl("http://pluto.cse.msstate.edu/~dcsp01/application/sendChallenge.php");
        
        Map parameters = new HashMap();
        parameters.put("ID", String.valueOf(ID));
        parameters.put("score", String.valueOf(score));
        parameters.put("level", String.valueOf(level));
        parameters.put("seed", String.valueOf(seed));
        parameters.put("toID", String.valueOf(toID));
        
        request.setContent(HttpParametersUtils.convertHttpParameters(parameters));
        
        Gdx.net.sendHttpRequest(request, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                Gdx.app.log("HttpCon:sendChallenge", httpResponse.getResultAsString());
            }

            @Override
            public void failed(Throwable t) {
                Gdx.app.log("HttpCon:sendChallenge", "Connection Fail");
            }

            @Override
            public void cancelled() {
                Gdx.app.log("HttpCon:sendChallenge", "Cancel function called. What does this even do?");
            }
        });
    }
}
