package net.semicircle.clubengine;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Vector;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;


public class TabOthers extends Activity {
	
	Button mButtonWall;
	Button mButtonNext;
	Gallery mGallery;
	ImageAdapter mImageAdapter;
	/**
     * Called when the activity is first created. Responsible for initializing the UI.
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other);
        
        mButtonWall = (Button)findViewById(R.id.button_wall);
        mButtonNext = (Button)findViewById(R.id.button_next);
        // Reference the Gallery view
        mGallery = (Gallery) findViewById(R.id.gallery);    
        mImageAdapter = new ImageAdapter(this);
        mGallery.setAdapter(mImageAdapter);
        
        mGallery.setSelection(0);
        
        mButtonNext.setOnClickListener(new Button.OnClickListener()
        {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int ppos = mGallery.getSelectedItemPosition();
				if (ppos < mGallery.getCount()-1){
					mGallery.setSelection(ppos+1);
				}
			}
        });
        
        mButtonWall.setOnClickListener(new Button.OnClickListener()
        {

			@Override
			public void onClick(View vc) {
				// TODO Auto-generated method stub
				int ppos = mGallery.getSelectedItemPosition();
				try{					
					URL aryURI = new URL(mImageAdapter.myImageURL[ppos]);	            	
	            	URLConnection conn = aryURI.openConnection();
	            	conn.connect();	            	
	            	InputStream is = conn.getInputStream();	            	
	            	Bitmap bm = BitmapFactory.decodeStream(is);
	            	is.close();					
				    TabOthers.this.setWallpaper(bm);
				    Toast.makeText(TabOthers.this, "设置成功", Toast.LENGTH_SHORT).show();
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
        
        });
        
    }
    
    
    
    public class ImageAdapter extends BaseAdapter implements Runnable  {
        int mGalleryItemBackground;
        public Vector<ImageView> mViews;
        
        public ImageAdapter(Context c) {
        	
        	mViews = new Vector<ImageView>(20);
        	mViews.setSize(20);
        	
            mContext = c;
            // See res/values/attrs.xml for the <declare-styleable> that defines
            // Gallery1.
            TypedArray a = obtainStyledAttributes(R.styleable.Gallery1);
            mGalleryItemBackground = a.getResourceId(
                    R.styleable.Gallery1_android_galleryItemBackground, 0);
            a.recycle();
            
        }

        public int getCount() {
            return myImageURL.length;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
        	
        	if (mViews.get(position) != null)
        	{
        		return mViews.get(position);
        	}
        		
        	
            ImageView i = new ImageView(mContext);
            
            try{
            	URL aryURI = new URL(myImageURL[position]);
            	
            	URLConnection conn = aryURI.openConnection();
            	conn.connect();
            	
            	InputStream is = conn.getInputStream();
            	
            	Bitmap bm = BitmapFactory.decodeStream(is);
            	
            	is.close();
            	i.setImageBitmap(bm);
            	
            }
            catch (IOException ex)
            {
            	ex.printStackTrace();
            }

            i.setScaleType(ImageView.ScaleType.CENTER_CROP);
            i.setLayoutParams(new Gallery.LayoutParams(200, 280));
            
            // The preferred Gallery item background
            i.setBackgroundResource(mGalleryItemBackground);
            
            mViews.set(position, i);
            
            return i;
        }

        private Context mContext;

        /* image url list */
        public String[] myImageURL = {
        	"http://semicircle-test.appspot.com/files/milan1.jpg",
        	"http://semicircle-test.appspot.com/files/milan2.jpg",
        	"http://semicircle-test.appspot.com/files/milan3.jpg",
        	"http://semicircle-test.appspot.com/files/milan4.jpg",
        	"http://semicircle-test.appspot.com/files/milan5.jpg",
        	"http://semicircle-test.appspot.com/files/nesta.jpg",
        	"http://semicircle-test.appspot.com/files/pirlo.jpg",
        	"http://semicircle-test.appspot.com/files/pirlo2.jpg",
        	"http://semicircle-test.appspot.com/files/pirlo3.jpg",
        	"http://semicircle-test.appspot.com/files/9.jpg",
        	"http://semicircle-test.appspot.com/files/7.jpg"        	        	      	
        };
        
        private Handler handler = new Handler()
    	{
    		@Override
    		public void handleMessage(Message msg)
    		{
    			
    		}
    	};

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}
    }
}
