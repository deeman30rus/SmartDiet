package com.delizarov.smartdiet.ui.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.delizarov.smartdiet.R;
import com.delizarov.smartdiet.domain.models.User;
import com.delizarov.smartdiet.presentation.login.LoginPresenter;
import com.delizarov.smartdiet.presentation.login.LoginView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity implements LoginView {

    private static final int RC_SIGN_IN = 1;

    @BindView(R.id.sign_in_button)
    SignInButton signInButton;

    @Inject
    LoginPresenter presenter;

    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getApplicationComponent().inject(LoginActivity.this);

        setContentView(R.layout.activity_login);

        ButterKnife.bind(LoginActivity.this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(LoginActivity.this, gso);

        signInButton.setSize(SignInButton.SIZE_WIDE);
        signInButton.setOnClickListener(view -> {

            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
        });

        presenter.attachView(LoginActivity.this);
        presenter.onViewCreated();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    @Override
    public void showDailyMeals() {

        Intent intent = new Intent(getApplicationContext(), RecipesActivity.class);

        startActivity(intent);
        finish();
    }


    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            presenter.onSuccessLogIn(new User(account.getId(), account.getDisplayName()));

        } catch (ApiException e) {

            presenter.onFailedLogIn();
        }
    }
}
