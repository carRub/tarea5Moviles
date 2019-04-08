package com.iteso.sesion9.tools;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class WebAppInterface {

    Context context;
    public WebAppInterface(Context c){
        context = c;
    }

    @JavascriptInterface
    public void showToast(String toast){
        Toast.makeText(context, toast, Toast.LENGTH_LONG).show();
    }

}
