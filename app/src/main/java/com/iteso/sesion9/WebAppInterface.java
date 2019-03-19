package com.iteso.sesion9;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class WebAppInterface {

    Context context;
    WebAppInterface(Context c){
        context = c;
    }

    @JavascriptInterface
    public void showToast(String toast){
        Toast.makeText(context, toast, Toast.LENGTH_LONG).show();
    }

}
