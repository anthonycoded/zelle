package com.wwis.zelle;

import android.content.Intent;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import java.util.Map;
import java.util.HashMap;

public class OpenZelleModule extends ReactContextBaseJavaModule {
    OpenZelleModule(ReactApplicationContext context) {
        super(context);
    }

    @Override
    public String getName() {
        return "OpenZelleModule";
    }

    @ReactMethod
    public void open() {
        Intent intent = new Intent(getCurrentActivity(), ZelleWebviewActivity.class);
        getCurrentActivity().startActivity(intent);
    }
}