package com.wwis.zelle;

import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.fis.digitalpayments.sdk.SDKManager;
import com.fis.digitalpayments.sdk.messaging.WebEvent;
import com.fis.digitalpayments.sdk.messaging.WebEventListener;
import com.fis.digitalpayments.sdk.messaging.WebEventType;

import java.io.UnsupportedEncodingException;

public class ZelleWebviewActivity extends AppCompatActivity {

    private SDKManager sdkManager;
    private final String logTag = "ZELLE_LOAD";
    private final  String[] domainWhitelist = {"epayments-crossdomain-fnc.billdomain.com", "epayments-epayui-uat-1.money-movement.com", "epayments-epayui-uat-3.money-movement.com","epayments-epayui-fnc-3.money-movement.com","10.0.2.2"};
    private String ssoResponseHtml = "<html><head><title></title></head><body onLoad='document.ssoform.submit()'><form method='GET' action='https://epayments-crossdomain-fnc.billdomain.com/Mobile/Index.html' name='ssoform'></body></html>";

  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zelle_webview);

        ProgressBar progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        // Allow remote Chrome debugging on post-KitKat devices
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }

        WebView webView = findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAllowContentAccess(true);

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onPermissionRequest(final PermissionRequest request) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    final String[] requestedResources = request.getResources();
                    request.grant(requestedResources);
                }
            }
        });

        sdkManager = SDKManager.create(this, webView, domainWhitelist, new WebEventListener() {
            @Override
            public void onEventReceived(WebEvent webEvent) {
                Log.d(logTag, "Received web event:" + webEvent.toString());
                if (webEvent.getEventType() == WebEventType.STARTED) {
                    ProgressBar progressBar = findViewById(R.id.progress_bar);
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

        // Directly load test site
        //webView.loadUrl("https://snitest.readiness.billsupport.com/Mobile/Index.html");

        // Load SSO response from String
        try {
            webView.loadUrl("https://epayments-crossdomain-fnc.billdomain.com/Mobile/Index.html");
            //String base64Data = Base64.encodeToString(ssoResponseHtml.getBytes("UTF-8"), Base64.DEFAULT);
            //webView.loadData(base64Data, "text/html;charset=utf-8", "base64");
        } catch (Exception e) {
            Log.d(logTag, "Encoding Exception:" + e.getMessage());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        sdkManager.dispose();
    }

}
