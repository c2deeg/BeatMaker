package com.beatmaker.Fragments.CareerFragment.view;

import android.app.Activity;

public interface CarrerView {
    public void showDialog(Activity activity);
    public void hideDialog();
    public void showMessage(Activity activity, String message);
}
