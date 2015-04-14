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
package com.DCSP.game;

import com.badlogic.gdx.utils.Array;
import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 *
 * @author Jacob Mason (jm2232)
 */
public class UserProfile {
    private int ID;
    private String username;
    private String email;
    private String name;
    private LinkedHashSet<String> friendsSet = new LinkedHashSet();
    private Array friendsArray = new Array();
    public HashMap<Integer, Double> scoresDict = new HashMap<Integer, Double>();  // level/score

    public UserProfile(int ID, String username, String email, String name) {
        this.ID = ID;
        this.username = username;
        this.email = email;
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }    

    public void setFriends(Array friendsStringArray) {
        this.friendsArray = friendsStringArray;
        System.out.println(this.friendsArray);
        for (Object friend : friendsStringArray) {
            this.friendsSet.add((String) friend);
        }
    }

    public Array getFriendsArray() {
        return friendsArray;
    }
}
