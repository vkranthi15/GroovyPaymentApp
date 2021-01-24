package com.imobile3.groovypayments.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.imobile3.groovypayments.R;
import com.imobile3.groovypayments.ui.BaseActivity;
import com.imobile3.groovypayments.utils.Constants;

public class UserProfileActivity extends BaseActivity {

    private String welcomeMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile_activity);

        Intent intent = getIntent();
        welcomeMessage = "Welcome " + intent.getStringExtra(Constants.KEY_DISPLAY_NAME);

        setUpMainNavBar();
        setUpViews(intent.getStringExtra(Constants.KEY_USERNAME), intent.getStringExtra(Constants.KEY_EMAIL));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void setUpMainNavBar() {
        super.setUpMainNavBar();
        mMainNavBar.showBackButton();
        mMainNavBar.showLogo();
        mMainNavBar.showSubtitle(welcomeMessage);
    }

    @Override
    protected void initViewModel() {
        // No view model needed.
    }

    private void setUpViews(String userName, String email) {
        TextView lblUsername = findViewById(R.id.label_username);
        TextView lblEmail = findViewById(R.id.label_email);

        lblUsername.setText(String.format("Username: %s", userName));
        lblEmail.setText(String.format("Email: %s", email));
    }
}
