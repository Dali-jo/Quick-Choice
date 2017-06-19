package com.example.leejaewon.quickchoice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by LeeJaeWon on 2017-06-19.
 */

public class webview extends AppCompatActivity {
    WebView WebView01;
    String add="";
    String add_detail="";
    Intent intent =new Intent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web);
        WebView01 = (WebView) findViewById(R.id.webView);
        WebView01.setWebViewClient(new WebViewClient(){
        });

        WebView01.setWebViewClient(new WebViewClient());
        WebSettings webSettings = WebView01.getSettings();
        WebView01.addJavascriptInterface(new MyJavascriptInterface(), "CommunicateApp");
        webSettings.setJavaScriptEnabled(true);
        WebView01.loadUrl("http://220.122.180.160:8080/test/jusoPopup.jsp");

    }


    public class MyJavascriptInterface {

        @JavascriptInterface
        public void getHtml(String html) { //위 자바스크립트가 호출되면 여기로 html이 반환됨
            add=html;
            System.out.println(html);
        }
        @JavascriptInterface
        public void getdetail(String detail){
            add_detail=" "+detail;
            System.out.println(detail);
            intent.putExtra("juso",add+add_detail);
            setResult(1,intent);
            finish();
        }
    }
}
