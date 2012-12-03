package com.briangriffey.devise.auth;

import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.briangriffey.devise.auth.models.AuthToken;
import com.example.R;
import com.google.common.base.Optional;

/**
 * class created by briangriffey
 */
public class AuthenticatorActivity extends AccountAuthenticatorActivity implements LoaderManager.LoaderCallbacks<Optional<AuthToken>> {

    private static final int AUTH_LOADER_ID = 0;

    private TextView mEmail;
    private TextView mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_account);

        mEmail = (TextView) findViewById(R.id.email);
        mPassword = (TextView) findViewById(R.id.password);

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Loader<Optional<AuthToken>> loader = getLoaderManager().initLoader(AUTH_LOADER_ID, null, AuthenticatorActivity.this);
                loader.forceLoad();
            }
        });
    }

    @Override
    public Loader<Optional<AuthToken>> onCreateLoader(int i, Bundle bundle) {
        return new AuthenticationLoader<AuthToken>(this, mEmail.getText().toString(), mPassword.getText().toString());
    }

    @Override
    public void onLoadFinished(Loader<Optional<AuthToken>> optionalLoader, Optional<AuthToken> authTokenOptional) {
        //if this is here then the auth succeeded
        if (authTokenOptional.isPresent()) {
           
            String username = mEmail.getText().toString();

            final Account account = new Account(username, DeviseAuthenticator.ACCOUNT_TYPE);
            AccountManager manager = AccountManager.get(this);
            manager.addAccountExplicitly(account, authTokenOptional.get().getToken(), null);
            
            Toast.makeText(this, "Logged in successfully", Toast.LENGTH_SHORT).show();

            final Intent intent = new Intent();
            intent.putExtra(AccountManager.KEY_ACCOUNT_NAME, username);
            intent.putExtra(AccountManager.KEY_ACCOUNT_TYPE, DeviseAuthenticator.ACCOUNT_TYPE);
            setAccountAuthenticatorResult(intent.getExtras());
            setResult(RESULT_OK, intent);
            finish();
        } else {
            //else then we didn't succeed, just show a toast for example
            Toast.makeText(this, "Login did not succeed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoaderReset(Loader<Optional<AuthToken>> optionalLoader) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
