package net.semicircle.clubengine;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SchedItemListAdapter extends BaseAdapter  {
	private LayoutInflater mInflater;
	private List<SchedItem> items;
	
	public SchedItemListAdapter(Context context, List<SchedItem> it)
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
		SchedItem tmpN = (SchedItem)items.get(position);
		holder.text.setText(tmpN.getTitle());
		
		return convertView;
		
	}
	
	private class ViewHolder
	{
		TextView text;
	}
}
