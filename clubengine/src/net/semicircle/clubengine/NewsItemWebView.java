package net.semicircle.clubengine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class NewsItemWebView extends Activity{
    private WebView mWebView1;
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.webview1);
    	
    	mWebView1 = (WebView)findViewById(R.id.webview1);
    	Intent intent = this.getIntent();
    	Bundle bundle = intent.getExtras();
    	
    	String link = bundle.getString("link");
    	
    	link = java.net.URLDecoder.decode(link);
    	
    	mWebView1.loadUrl(link);
    	
    }
	
}
