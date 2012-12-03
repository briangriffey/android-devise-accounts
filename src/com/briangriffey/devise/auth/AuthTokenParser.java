package com.briangriffey.devise.auth;

import com.briangriffey.devise.auth.models.AuthToken;
import com.briangriffey.utilities.parsing.ObjectParser;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * class created by briangriffey
 */
public class AuthTokenParser implements ObjectParser<AuthToken> {

    private static final String TOKEN_KEY = "token";

    @Override
    public AuthToken parse(InputStream stream) throws IOException {
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader buffReader = new BufferedReader(reader);

        try {
            String line = buffReader.readLine();
            JSONObject jsonObject = new JSONObject(line);

            AuthToken token = new AuthToken();
            token.setToken(jsonObject.getString(TOKEN_KEY));

            return token;

        } catch (JSONException e) {
            throw new IOException("couldn't parse input", e);
        }
    }
}
