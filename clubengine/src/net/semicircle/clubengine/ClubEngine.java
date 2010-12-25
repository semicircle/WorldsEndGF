package net.semicircle.clubengine;


import android.app.TabActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.content.Intent;

public class ClubEngine extends TabActivity {
	
	private void setTabViewStyle(TextView v)
	{
		//final TextView tabIndicatorView = v;
		
		v.setGravity(Gravity.CENTER);
        v.setTextColor(getResources().getColor(R.color.tab_text_color));
        v.setBackgroundColor(getResources().getColor(R.color.tab_background_color));        
        v.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				//tabIndicatorView1.setBackgroundColor(android.graphics.Color.RED);	
				
				int nowAction = event.getAction();
				
				if (MotionEvent.ACTION_DOWN == nowAction)
				{
					v.setBackgroundColor(getResources().getColor(R.color.tab_background_selected_color));	
				}
				else if (MotionEvent.ACTION_UP == nowAction)
				{
					v.setBackgroundColor(getResources().getColor(R.color.tab_background_color));
				}
				return false;
			}
		});
	}
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main_tab_view);
        
        final TabHost tabHost = getTabHost();
        final TabWidget tabWidget = this.getTabWidget();
        
        //Set alpha
        View v_ml = findViewById(R.id.main_layout);
        v_ml.getBackground().setAlpha(200);
        
        ///////////////////////////////////////
        // Tab1.
        ///////////////////////////////////////
        final TextView tabIndicatorView1 = new TextView(this);
        tabIndicatorView1.setText(getResources().getText(R.string.tab_news_displayname));
        setTabViewStyle(tabIndicatorView1);        
        tabHost.addTab(tabHost.newTabSpec("News")
        		.setIndicator(tabIndicatorView1)
        		.setContent(new Intent(this, TabNewsList.class)));
        
        ///////////////////////////////////////
        // Tab2.
        ///////////////////////////////////////
        final TextView tabIndicatorView2 = new TextView(this);        
        tabIndicatorView2.setText(getResources().getText(R.string.tab_sched_displayname));
        setTabViewStyle(tabIndicatorView2);
        tabHost.addTab(tabHost.newTabSpec("Sched")
        		.setIndicator(tabIndicatorView2)
        		.setContent(new Intent(this, TabSchedule.class)));
         
        ///////////////////////////////////////
        // Tab3.
        ///////////////////////////////////////
        final TextView tabIndicatorView3 = new TextView(this);
        tabIndicatorView3.setText(getResources().getText(R.string.tab_others_displayname));
        setTabViewStyle(tabIndicatorView3); 
        tabHost.addTab(tabHost.newTabSpec("Others")
        		.setIndicator(tabIndicatorView3)
        		.setContent(new Intent(this, TabOthers.class)));
        
        
        
        //tabWidget.setMinimumHeight(20);
        //View childView = tabWidget.getChildTabViewAt(0);
        //childView.setBackgroundColor(0xffff0000);
        
        
        
        
        
    }
}