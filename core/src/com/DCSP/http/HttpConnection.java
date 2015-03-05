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
    public boolean login(String username, String password) {
        Net.HttpRequest request = new Net.HttpRequest(Net.HttpMethods.POST);
        request.setUrl("http://pluto.cse.msstate.edu/~dcsp01/application/Login.php");
        
        Map parameters = new HashMap();
        parameters.put("username", username);
        parameters.put("password", password);
        
        request.setContent(HttpParametersUtils.convertHttpParameters(parameters));
        
        String status = sendRequest(request);
        
        System.out.println(status);
        
        // TODO: This shouldn't return a constant "true". The result of the login should be parsed out or minimized.
        return true;
    }
    
    private String sendRequest(Net.HttpRequest request) {
        
        // Inner class is created to add the "status" string.
        // This is the only way to extract the status and handle it outside this function.
        class Listener implements Net.HttpResponseListener {
            public String status;
            
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                status = httpResponse.getResultAsString();
                // interpret response
            }

            @Override
            public void failed(Throwable t) {
                status = "failed";
                // Failed
            }

            @Override
            public void cancelled() {
                status = "cancelled";
                // Shouldn't really end up here.
            }
            
        }
        
        Listener listener = new Listener();

        // Cast the Listener as the original class because polymorphism
        Gdx.net.sendHttpRequest(request, (Net.HttpResponseListener)listener);
        
        return listener.status;
    }
}
