package com.briangriffey.devise.auth;

import android.content.Context;
import com.briangriffey.utilities.loaders.JSONFormPostLoader;
import com.briangriffey.utilities.parsing.ObjectParser;

import java.util.HashMap;
import java.util.Map;

/**
 * class created by briangriffey
 */
public class AuthenticationLoader<AuthToken> extends JSONFormPostLoader<AuthToken> {

	//you'll have to change this to wherever your devise auth token service runs
    public static final String AUTH_URL = "http://localhost:3000/users/token.json";

    private static final String USERNAME_PARAM = "email";
    private static final String PASSWORD_PARAM = "password";

    public AuthenticationLoader(Context context, String email, String password) {
        super(context);
        setUrl(AUTH_URL);

        Map<String, String> params = new HashMap<String, String>();
        params.put(USERNAME_PARAM, email);
        params.put(PASSWORD_PARAM, password);

        setForm(params);

        AuthTokenParser parser = new AuthTokenParser();
        setParser((ObjectParser<AuthToken>)parser);
    }


}
