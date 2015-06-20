package net.hetic.hetweetic.Utils;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;


public class KeyboardBehavior {

    public static InputMethodManager getInputManager(Activity activity){
        return (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
    }


    public static void hide(Activity activity){

        if (activity.getWindow().getCurrentFocus() != null){
            getInputManager(activity).hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public static void show(Activity activity){

        getInputManager(activity).toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
    }
}