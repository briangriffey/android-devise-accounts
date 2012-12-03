package com.briangriffey.devise.auth;

import android.accounts.*;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.example.R;

/**
 * created by briangriffey
 */
public class DeviseAuthenticator extends AbstractAccountAuthenticator {
	
	public static final String ACCOUNT_TYPE = "com.briangriffey.devise.auth";

    private Context mContext;


    public DeviseAuthenticator(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public Bundle editProperties(AccountAuthenticatorResponse accountAuthenticatorResponse, String s) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Bundle addAccount(AccountAuthenticatorResponse accountAuthenticatorResponse, String s, String s1, String[] strings, Bundle options) throws NetworkErrorException {
       Intent intent = new Intent(mContext, AuthenticatorActivity.class);
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, accountAuthenticatorResponse);
        final Bundle bundle = new Bundle();
        bundle.putParcelable(AccountManager.KEY_INTENT, intent);
        return bundle;
    }

    @Override
    public Bundle confirmCredentials(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, Bundle bundle) throws NetworkErrorException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * This is does nothing more than return the password from the account since that is where you should be storing the auth token.  You should never store the actual password inside of the password field of the accountmanager because it is not safe on rooted devices
     *
     * @param accountAuthenticatorResponse
     * @param account
     * @param type
     * @param bundle
     * @return
     * @throws NetworkErrorException
     */
    @Override
    public Bundle getAuthToken(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, String type, Bundle bundle) throws NetworkErrorException {

        if (type.equals(ACCOUNT_TYPE)) {
            final Bundle result = new Bundle();
            result.putString(AccountManager.KEY_ERROR_MESSAGE, "invalid authTokenType");
            return result;
        }

        AccountManager manager = AccountManager.get(mContext);
        String password = manager.getPassword(account);

        if (password != null) {
            Bundle returnBundle = new Bundle();
            returnBundle.putString(AccountManager.KEY_ACCOUNT_NAME, account.name);
            returnBundle.putString(AccountManager.KEY_AUTHTOKEN, password);
            returnBundle.putString(AccountManager.KEY_ACCOUNT_TYPE, ACCOUNT_TYPE);
            return returnBundle;
        }

        Intent intent = new Intent(mContext, AuthenticatorActivity.class);
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, accountAuthenticatorResponse);
        Bundle returnBundle = new Bundle();
        returnBundle.putParcelable(AccountManager.KEY_INTENT, intent);
        return returnBundle;
    }

    @Override
    public String getAuthTokenLabel(String s) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Bundle updateCredentials(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, String s, Bundle bundle) throws NetworkErrorException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Bundle hasFeatures(AccountAuthenticatorResponse accountAuthenticatorResponse, Account account, String[] strings) throws NetworkErrorException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
