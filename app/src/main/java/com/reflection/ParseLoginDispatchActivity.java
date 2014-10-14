package com.reflection;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import java.util.Arrays;
import java.util.List;


/**
 * Placeholder for ParseLoginActivity.  Will we be using this or rolling our own?
 */
public class ParseLoginDispatchActivity extends Activity {

    private final String TAG = ParseLoginDispatchActivity.class.getSimpleName();
    private Button fbLoginButton;
    private Button loginButton;
    private Button registerButton;
    private Dialog progressDialog;

    private EditText username;
    private EditText password;
    private String mUserEmail;
    private String mPassword;
    private TextView mErrorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText) findViewById(R.id.loginUsername);
        password = (EditText) findViewById(R.id.loginPassword);
        mErrorMessage = (TextView) findViewById(R.id.errorMessage);

        // Fetch Facebook user info if the session is active
        fbLoginButton = (Button) findViewById(R.id.fbLoginButton);
        fbLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFBLoginButtonClicked();
            }
        });

        registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                Intent i = new Intent(ParseLoginDispatchActivity.this,
                        RegisterNewAccountActivity.class);
                startActivity(i);
            }
        });

        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                onLoginButtonClicked();
            }
        });

        // Check if there is a currently logged in user
        // and they are linked to a Facebook account.
        ParseUser currentUser = ParseUser.getCurrentUser();
        if ((currentUser != null) && ParseFacebookUtils.isLinked(currentUser)) {
            // Go to the user info activity
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        //TODO: remove this debugging code set to true to skip login
        boolean debugMode = true;
        if (debugMode) {
            username.setText("demo@reflection.com");
            password.setText("demo");
            loginButton.callOnClick();
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i(TAG, "onActivityResult");
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.finishAuthentication(requestCode, resultCode, data);
    }

    /**
     * Handles regular Login
     */
    private void onLoginButtonClicked() {
        Log.i(TAG, "onLoginButtonClicked");
        ParseLoginDispatchActivity.this.progressDialog = ProgressDialog.show(
                ParseLoginDispatchActivity.this, "", "Logging in...", true);
        if (validateFields()) {
            mUserEmail = username.getText().toString();
            mPassword = password.getText().toString();
            if (TextUtils.isEmpty(mUserEmail) || TextUtils.isEmpty(mPassword)) {
                mErrorMessage
                        .setText("Please enter a valid username and password.");
                this.progressDialog.dismiss();
            } else {
                // showProgress();
                userLogin();
            }
        } else {
            this.progressDialog.dismiss();
            Toast.makeText(getApplicationContext(),
                    "Please Insert valid Username and Password",
                    Toast.LENGTH_SHORT).show();
        }
    }

    // TODO: set the limits on sizes of username length and password length and more login rules
    private boolean validateFields() {
        Log.i(TAG, "validateFields");
        if (username.length() > 0 && password.getText().length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Handles parse user login
     */
    public void userLogin() {
        Log.i(TAG, "userLogin");
        ParseUser.logInInBackground(mUserEmail, mPassword, new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    Toast.makeText(getApplicationContext(),
                            "Welcome, " + user.getString("first_name"),
                            Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(ParseLoginDispatchActivity.this,
                            MainActivity.class);
                    startActivity(i);
                } else {
                    // Signup failed. Look at the ParseException to see what
                    // happened.
                    Log.e(TAG,"Login failed: " +e.getMessage());
                    Toast.makeText(getApplicationContext(),
                            "Login failed please try again", Toast.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }

    /**
     * Handles facebook login request.
     */
    private void onFBLoginButtonClicked() {
        ParseLoginDispatchActivity.this.progressDialog = ProgressDialog.show(
                ParseLoginDispatchActivity.this, "", "Logging in...", true);
        List<String> permissions = Arrays.asList("public_profile", "user_friends", "user_about_me",
                "user_relationships", "user_birthday", "user_location");
        ParseFacebookUtils.logIn(permissions, this, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException err) {
                ParseLoginDispatchActivity.this.progressDialog.dismiss();
                if (user == null) {
                    Log.d(TAG,
                            "Uh oh. The user cancelled the Facebook login.");
                } else if (user.isNew()) {
                    Log.d(TAG,
                            "User signed up and logged in through Facebook!");
                    startMainActivity();
                } else {
                    Log.d(TAG,
                            "User logged in through Facebook!");
                    startMainActivity();
                }
            }
        });
    }




    /**
     * Starts the Main Activity.
     */
    public void startMainActivity() {
        Log.i(TAG, "starting Main Activity");
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    //@Override
    protected Class<?> getTargetClass() {
        return MainActivity.class;
    }
}