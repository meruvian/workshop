package org.meruvian.workshop.oauth2login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.meruvian.workshop.oauth2login.R;
import org.meruvian.workshop.oauth2login.core.commons.User;
import org.meruvian.workshop.oauth2login.util.AuthenticationUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Yusak DK on 15/03/17.
 */

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.text_hello)
    TextView textHello;

    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        User user = AuthenticationUtils.getCurrentAuthentication().getUser();

        textHello.setText("Hello... \n"
                + user.getName().getFirst() + " "
                + user.getName().getLast());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            AuthenticationUtils.logout();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
