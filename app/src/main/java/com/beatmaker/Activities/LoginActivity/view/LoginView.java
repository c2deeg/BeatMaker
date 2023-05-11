package com.beatmaker.Activities.LoginActivity.view;

import android.app.Activity;

public interface LoginView {
    public void showDialog(Activity activity);

    public void hideDialog();

    public void showMessage(Activity activity, String message);
}
