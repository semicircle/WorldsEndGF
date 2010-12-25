package net.semicircle.clubengine;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.BaseAdapter;


public class NewsItemListAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	private List<NewsItem> items;
	
	public NewsItemListAdapter(Context context, List<NewsItem> it)
	{
		mInflater = LayoutInflater.from(context);
		items = it;
	}
	
	@Override
	public int getCount()
	{
		return items.size();
	}
	
	@Override
	public Object getItem(int position)
	{
		return items.get(position);
	}
	
	@Override
	public long getItemId(int position)
	{
		return position;
	}
	
	public View getView(int position, View convertView, ViewGroup par)
	{
		ViewHolder holder;
		
		if (convertView == null)
		{
			convertView = mInflater.inflate(R.layout.simple_list_item_1, null);
			holder = new ViewHolder();
			holder.text = (TextView)convertView.findViewById(R.id.TextView01);
			convertView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder)convertView.getTag();
		}
		NewsItem tmpN = (NewsItem)items.get(position);
		holder.text.setText(tmpN.getTitle());
		
		final View theView = convertView;
		
		/*
		convertView.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
                int nowAction = event.getAction();
				
				if (MotionEvent.ACTION_DOWN == nowAction)
			    {
					theView.setBackgroundColor(android.graphics.Color.RED);	
			    }
                else
                {
                    theView.setBackgroundColor(android.graphics.Color.BLACK);
                }
                return false;
                
            }
        });*/
        
        return convertView;
        
    }
    
    private class ViewHolder
    {
        TextView text;
    }
    
}
