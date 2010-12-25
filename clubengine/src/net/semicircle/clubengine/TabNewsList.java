package net.semicircle.clubengine;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Xml;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class TabNewsList extends ListActivity implements Runnable {
	
	// NewsItems for the current ListActivity
	private List<NewsItem> li = new ArrayList<NewsItem>();
	// ProgressDialog
	private ProgressDialog dProg;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.setContentView(R.layout.list_view_content);
        
        //old code.
        // Use an existing ListAdapter that will map an array
        // of strings to TextViews
        //setListAdapter(new ArrayAdapter<String>(this,
        //        R.layout.simple_list_item_1, mStrings));
        //getListView().setTextFilterEnabled(true);
        
        getListView().setCacheColorHint(0);
        
        dProg = new ProgressDialog(TabNewsList.this);
        dProg.setMessage("加载中..");
        dProg.show();
        
        Thread thread = new Thread(TabNewsList.this);
        thread.start();
        
    }

	//old code.
    //private String[] mStrings = {
    //        "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
    //        "Acorn", "Adelost"};
	
	private Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			dProg.dismiss();
			setListAdapter(new NewsItemListAdapter(TabNewsList.this, li));
		}
	};
	
	@Override
	public void run()
	{
		li = getRss(getResources().getText(R.string.tab_news_xml_src_url).toString());        
        handler.sendEmptyMessage(0);
	}
	
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id)
	{
		NewsItem ni = (NewsItem)li.get(position);
		Intent intent = new Intent();
		intent.setClass(TabNewsList.this, NewsItemWebView.class);
		Bundle bundle = new Bundle();
		bundle.putString("link", ni.getLink());
		intent.putExtras(bundle);
		startActivity(intent);
	}
    
    private List<NewsItem> getRss(String path) {
    	List<NewsItem> data = new ArrayList<NewsItem>();
    	URL url = null;
    	try{
    		url = new URL(path);
    		NewsItemXmlHandler myHandler = new NewsItemXmlHandler();
    		android.util.Xml.parse(url.openConnection().getInputStream(), Xml.Encoding.UTF_8, myHandler);
    		data = myHandler.getParsedData();
    	} catch (Exception e) {
    		Toast.makeText(this, e.toString(), Toast.LENGTH_LONG);
    		//Toast.makeText(this, e.toString(), Toast.LENGTH_LONG);    		
    	}
    	return data;
    }
}
