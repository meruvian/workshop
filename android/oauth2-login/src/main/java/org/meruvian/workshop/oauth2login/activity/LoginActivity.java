package org.meruvian.workshop.oauth2login.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.meruvian.workshop.oauth2login.R;
import org.meruvian.workshop.oauth2login.core.commons.User;
import org.meruvian.workshop.oauth2login.repository.rest.model.Authentication;
import org.meruvian.workshop.oauth2login.repository.rest.service.LoginService;
import org.meruvian.workshop.oauth2login.util.AuthenticationUtils;
import org.meruvian.workshop.oauth2login.util.ServiceGenerator;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.meruvian.workshop.oauth2login.GlobalVariables.API_BASE_URL;
import static org.meruvian.workshop.oauth2login.GlobalVariables.APP_AUTHORIZATION;
import static org.meruvian.workshop.oauth2login.GlobalVariables.APP_ID;
import static org.meruvian.workshop.oauth2login.GlobalVariables.APP_SECRET;
import static org.meruvian.workshop.oauth2login.GlobalVariables.BASIC;
import static org.meruvian.workshop.oauth2login.GlobalVariables.BEARER;
import static org.meruvian.workshop.oauth2login.GlobalVariables.GRANT_TYPE_PASSWORD;
import static org.meruvian.workshop.oauth2login.GlobalVariables.HAS_URL;
import static org.meruvian.workshop.oauth2login.GlobalVariables.KEY_URL;
import static org.meruvian.workshop.oauth2login.GlobalVariables.PREFS_SERVER;
import static org.meruvian.workshop.oauth2login.GlobalVariables.URL_CHANGE;

/**
 * Created by meruvian on 23/02/16.
 */
public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.button_login)
    Button buttonLogin;
    @BindView(R.id.edit_username)
    EditText username;
    @BindView(R.id.edit_password)
    EditText password;
    @BindView(R.id.login_progress)
    View loginProgress;

    private String TAG = getClass().getSimpleName();
    private SharedPreferences preferences;
    private LoginService loginService;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        if (AuthenticationUtils.getCurrentAuthentication() != null) {
            goToMainActivity();
        }

        preferences = getSharedPreferences(PREFS_SERVER, 0);
        SharedPreferences.Editor editor = preferences.edit();

        if (!(preferences.getBoolean(HAS_URL, false))) {
            editor.putString(KEY_URL, API_BASE_URL);
            editor.putBoolean(HAS_URL, true);
            editor.commit();
        }

        if (preferences.getBoolean(URL_CHANGE, false)) {
            ServiceGenerator.changeApiBaseUrl(preferences.getString(KEY_URL, ""));
        }

        loginService = ServiceGenerator.createService(LoginService.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_url, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_setup_parameter) {
            openDialogSetupParameter();
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.button_login)
    public void submitLogin(Button button) {
        username.setEnabled(false);
        password.setEnabled(false);
        buttonLogin.setVisibility(View.GONE);
        loginProgress.setVisibility(View.VISIBLE);

        loginTask(username.getText().toString(), password.getText().toString());
    }

    public void goToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void openDialogSetupParameter() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.setup_parameter);

        LayoutInflater inflater = this.getLayoutInflater();
        View convertView = inflater.inflate(R.layout.setup_parameter, null);

        builder.setView(convertView);

        final EditText server = (EditText) convertView.findViewById(R.id.edit_server);

        server.setText(preferences.getString(KEY_URL, ""));

        builder.setCancelable(false);
        builder.setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(KEY_URL, server.getText().toString());
                editor.putBoolean(URL_CHANGE, true);
                editor.commit();

                ServiceGenerator.changeApiBaseUrl(preferences.getString(KEY_URL, ""));
                loginService = ServiceGenerator.createService(LoginService.class);

                dialog.dismiss();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    private void loginTask(String username, String password) {
        Map<String, String> param = new HashMap<>();
        param.put("grant_type", GRANT_TYPE_PASSWORD);
        param.put("username", username);
        param.put("password", password);
        param.put("client_id", APP_ID);
        param.put("client_secret", APP_SECRET);

        // Authorization Header = Basic base64(clientId:clientSecret)
        Call<Authentication> call = loginService.login(BASIC + APP_AUTHORIZATION.trim(), param);
        call.enqueue(new Callback<Authentication>() {
            @Override
            public void onResponse(Call<Authentication> call, Response<Authentication> response) {

                if (response.code() == HttpURLConnection.HTTP_OK) {
                    //Log to check response
                    /*Log.w(TAG, "CODE: " + response.code());
                    Log.w(TAG, "MESSAGE: " + response.message());
                    Log.w(TAG, "RAW: " + response.raw().toString());
                    Log.w(TAG, "isSuccess: " + response.isSuccessful());

                    Log.w(TAG, "Access Token: " + response.body().getAccessToken());
                    Log.w(TAG, "Token Type: " + response.body().getTokenType());
                    Log.w(TAG, "Refresh Token: " + response.body().getRefreshToken());
                    Log.w(TAG, "Expires In: " + response.body().getExpiresIn());
                    Log.w(TAG, "Scope: " + response.body().getScope());
                    Log.w(TAG, "Jti: " + response.body().getJti());*/

                    AuthenticationUtils.registerAuthentication(response.body());
                    try {
                        requestMe(AuthenticationUtils.getCurrentAuthentication());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    enableInput();
                    Toast.makeText(LoginActivity.this, R.string.login_invalid, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Authentication> call, Throwable t) {
                Toast.makeText(LoginActivity.this, R.string.login_failure, Toast.LENGTH_SHORT).show();
                Log.e(TAG, t.getLocalizedMessage() + " |\n| " + t.getMessage());
                enableInput();
            }
        });
    }

    private void requestMe(Authentication auth) throws IOException {
        Call<User> call = loginService.getUser(BEARER + auth.getAccessToken());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    //Log to check response
                    /*Log.w(TAG, "CODE: " + response.code());
                    Log.w(TAG, "MESSAGE: " + response.message());
                    Log.w(TAG, "RAW: " + response.raw().toString());
                    Log.w(TAG, "isSuccess: " + response.isSuccessful());

                    Log.w(TAG, "USER ID: " + response.body().getId());
                    if (response.body().getLogInformation().getCreateDate() != null) {
                        Log.w(TAG, "CREATE DATE: " + response.body().getLogInformation().getCreateDate().getTime());
                    }
                    Log.w(TAG, "USERNAME: " + response.body().getUsername());
                    Log.w(TAG, "PASSWORD: " + response.body().getPassword());
                    Log.w(TAG, "EMAIL: " + response.body().getEmail());
                    Log.w(TAG, "NAME PREFIX: " + response.body().getName().getPrefix());
                    Log.w(TAG, "NAME FIRST: " + response.body().getName().getFirst());
                    Log.w(TAG, "NAME MIDDLE: " + response.body().getName().getMiddle());
                    Log.w(TAG, "NAME LAST: " + response.body().getName().getLast());
                    if (response.body().getAddress() != null) {
                        Log.w(TAG, "CITY: " + response.body().getAddress().getCity());
                        Log.w(TAG, "STATE: " + response.body().getAddress().getState());
                        Log.w(TAG, "STREET.1: " + response.body().getAddress().getStreet1());
                        Log.w(TAG, "STREET.2: " + response.body().getAddress().getStreet2());
                        Log.w(TAG, "ZIP: " + response.body().getAddress().getZip());
                    }
                    if (response.body().getFileInfo() != null) {
                        Log.w(TAG, "ORI NAME: " + response.body().getFileInfo().getOriginalName());
                        Log.w(TAG, "CONTENT TYPE: " + response.body().getFileInfo().getContentType());
                        Log.w(TAG, "PATH: " + response.body().getFileInfo().getPath());
                        Log.w(TAG, "SIZE: " + response.body().getFileInfo().getSize());
                    }*/

                    AuthenticationUtils.registerUser(response.body());
                    goToMainActivity();
                } else {
                    enableInput();
                    Toast.makeText(LoginActivity.this, R.string.login_failure, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                enableInput();
                Toast.makeText(LoginActivity.this, R.string.login_failure, Toast.LENGTH_SHORT).show();
                Log.e(TAG, t.getLocalizedMessage() + " |\n| " + t.getMessage());
            }
        });
    }

    private void enableInput() {
        buttonLogin.setVisibility(View.VISIBLE);
        loginProgress.setVisibility(View.GONE);

        username.setEnabled(true);
        password.setEnabled(true);
    }

}
