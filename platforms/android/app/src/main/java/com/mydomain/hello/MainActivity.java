/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package com.mydomain.hello;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.mydomain.hello.customer_jsbridge.BridgeImpl;
import com.mydomain.hello.customer_jsbridge.JSBridge;
import com.mydomain.hello.customer_jsbridge.JSBridgeWebChromeClient;

import org.apache.cordova.*;
import org.apache.cordova.engine.SystemWebView;
import org.apache.cordova.engine.SystemWebViewEngine;

public class MainActivity extends CordovaActivity
{
    private String testUrl = "file:///android_asset/www/test.html";
    private WebView mWebView;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // enable Cordova apps to be started in the background
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.getBoolean("cdvStartInBackground", false)) {
            moveTaskToBack(true);
        }
        // Set by <content src="index.html" /> in config.xml
        loadUrl(testUrl);

        findViewById(R.id.testCallJs).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loadUrl("javascript:showAlert(\"你好\")");
            }
        });
        initJsBridgeObj();

    }


    @Override
    protected CordovaWebView makeWebView() {
        SystemWebView webView = (SystemWebView) findViewById(R.id.cordov_webView);
        CordovaWebView cordovaWebView = new CordovaWebViewImpl(new SystemWebViewEngine(webView));
        return cordovaWebView;
    }

    /**
     * 此处需要重写父类接口
     */
    @Override
    protected void createViews() {
//        //因为要使用自定义布局，此处setContentView需要注掉
////      appView.getView().setId(100);
////      appView.getView().setLayoutParams(new FrameLayout.LayoutParams(
////              ViewGroup.LayoutParams.MATCH_PARENT,
////              ViewGroup.LayoutParams.MATCH_PARENT));
////      setContentView(appView.getView());
//
//        if (preferences.contains("BackgroundColor")) {
//            int backgroundColor = preferences.getInteger("BackgroundColor", Color.BLACK);
//            // Background of activity:
//            appView.getView().setBackgroundColor(backgroundColor);
//        }
//        appView.getView().requestFocusFromTouch();
    }


    private void initJsBridgeObj(){
        mWebView = (WebView)findViewById(R.id.webView);

        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        mWebView.setWebChromeClient(new JSBridgeWebChromeClient());
        mWebView.loadUrl("file:///android_asset/index.html");
        JSBridge.register("bridge", BridgeImpl.class);
    }
}
