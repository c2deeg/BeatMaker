package com.beatmaker.Activities.SignUpActivity.View;

import android.app.Activity;

public interface SignupView {
    public void showDialog(Activity activity);

    public void hideDialog();

    public void showMessage(Activity activity, String message);
}
