package net.semicircle.clubengine;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Xml;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class TabSchedule extends ListActivity implements Runnable{
	
	private List<SchedItem> li = new ArrayList<SchedItem>();
	// ProgressDialog
	private ProgressDialog dProg;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        dProg = new ProgressDialog(TabSchedule.this);
        dProg.setMessage("加载中..");
        dProg.show();
        
        Thread thread = new Thread(TabSchedule.this);
        thread.start();
        
        getListView().setCacheColorHint(0);
        
        // Use an existing ListAdapter that will map an array
        // of strings to TextViews
        //setListAdapter(new ArrayAdapter<String>(this,
        //		R.layout.simple_list_item_1, mStrings));
        //getListView().setTextFilterEnabled(true);
    }
	
	private Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			dProg.dismiss();
			setListAdapter(new SchedItemListAdapter(TabSchedule.this, li));
			Toast.makeText(TabSchedule.this, "点击可以添加比赛提醒到手机日程表", Toast.LENGTH_LONG).show();
		}
	};
	
	@Override
	public void run()
	{
		li = getRss(getResources().getText(R.string.tab_sched_xml_src_url).toString());       
        handler.sendEmptyMessage(0);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id)
	{
		final int pos = position;
		
		new AlertDialog.Builder(TabSchedule.this)
		.setTitle("确认?")
		.setIcon(R.drawable.icon)
		.setMessage("是否添加日程?")
		.setPositiveButton("确定", 
				new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						SchedItem si = (SchedItem)li.get(pos);
						
						Intent intent = new Intent(Intent.ACTION_EDIT);
						intent.setType("vnd.android.cursor.item/event");
						intent.putExtra("beginTime", si.getStartTime());
						intent.putExtra("endTime", si.getEndTime());
						intent.putExtra("title", si.getTitle());
						intent.putExtra("eventLocation", "i don't know");
						intent.putExtra("description", si.getHostTeam());
						startActivity(intent);
					}
				})
		.setNegativeButton("取消", 
				new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						
					}
				})
		.show();	
		
	}

	private List<SchedItem> getRss(String path)
    {
    	List<SchedItem> data = new ArrayList<SchedItem>();
    	URL url = null;
    	try
    	{
    		url = new URL(path);
    		SchedItemXmlHandler myHandler = new SchedItemXmlHandler();
    		android.util.Xml.parse(url.openConnection().getInputStream(), Xml.Encoding.UTF_8, myHandler);
    		data = myHandler.getParsedData();
    	}
    	catch (Exception e)
    	{
    		Toast.makeText(this, e.toString(), Toast.LENGTH_LONG);
    		//Toast.makeText(this, e.toString(), Toast.LENGTH_LONG);    		
    	}
    	return data;
    }

}
