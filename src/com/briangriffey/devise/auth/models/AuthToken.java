package com.briangriffey.devise.auth.models;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: briangriffey
 * Date: 12/2/12
 * Time: 12:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class AuthToken implements Serializable {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
