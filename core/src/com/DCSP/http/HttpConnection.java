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
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Jacob Mason (jm2232)
 */
public class HttpConnection {
//    public static void main(String[] args) {
//        httpTest test = new httpTest();
//        test.makeRequest();
//    }
    
    // Returns true/false depending on whether the login succeeded or failed.
    public void login(String username, String password) {
        Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.POST);
        request.setUrl("http://pluto.cse.msstate.edu/~dcsp01/application/Login.php");
        
        Map parameters = new HashMap();
        parameters.put("username", username);
        parameters.put("password", password);
        
        request.setContent(HttpParametersUtils.convertHttpParameters(parameters));
        
        sendRequest(request, new loginCallback());
    }
    
    private class loginCallback implements Event {
        @Override
        public void handle(String status) {
            Gdx.app.log("HttpConnection", status);
        }
    }

    
    private void sendRequest(Net.HttpRequest request, Event callback) {
        
        // Inner class is created to add the "status" string.
        // This is the only way to extract the status and handle it outside this function.
        class Listener implements Net.HttpResponseListener {
            public String status;
            private final Event callback;

            public Listener(Event callback) {
                this.callback = callback;
            } 
            
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                this.status = httpResponse.getResultAsString();
                callback.handle(status);
                // interpret response
            }

            @Override
            public void failed(Throwable t) {
                this.status = "failed";
                // Failed
            }

            @Override
            public void cancelled() {
                this.status = "cancelled";
                // Shouldn't really end up here.
            }
        }
        
        Listener listener = new Listener(callback);

        Gdx.net.sendHttpRequest(request, listener);
    }
}
