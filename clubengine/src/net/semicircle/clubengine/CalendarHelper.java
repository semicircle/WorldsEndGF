package net.semicircle.clubengine;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.text.format.DateUtils;


public class CalendarHelper {
	
	private Context context;
	private Uri remindersUri;
	private Uri eventsUri;
	private Uri calendarsUri;
	
	public CalendarHelper(Context context){
		this.context = context;
		String strContentProvider;
		if (Build.VERSION.RELEASE.contains("2.2")){
			strContentProvider = "com.android.calendar";
		}else{
			strContentProvider = "calendar";
		}
		
		this.remindersUri = Uri.parse(String.format("content://%s/reminders",strContentProvider));
		this.eventsUri = Uri.parse(String.format("content://%s/events",strContentProvider));
		this.calendarsUri = Uri.parse(String.format("content://%s/calendars",strContentProvider));
		
	}
	
	public LinkedHashMap<String, String> getAndroidCalendarList(){
		LinkedHashMap<String, String> ret =  new LinkedHashMap<String, String>();
		ContentResolver contentResolver = this.context.getContentResolver();
		Cursor cursor = contentResolver.query(this.calendarsUri, 
				(new String[] { "_id", "displayName"}),	 "selected = 1", null, null);
		while (cursor.moveToNext()) {
			final String _id = cursor.getString(0);
            final String displayName = cursor.getString(1);
            ret.put(_id, displayName);            
		}		
		return ret;
	}
	
	private void _AddNewEvent(
			int calendarId,
			String title,
			String description,
			String eventLocation,
			long startTime,
			long endTime,
			int allDay,
			int eventStatus,
			int visibility,
			int transparency,
			int hasAlarm){		
		ContentValues myEvent = new ContentValues();
		myEvent.put("calendar_id", calendarId);
        myEvent.put("title", title);
        myEvent.put("description", description);
        myEvent.put("eventLocation", eventLocation);
        myEvent.put("dtstart", startTime);
        myEvent.put("dtend", endTime);
        myEvent.put("allDay", allDay);
        myEvent.put("eventStatus", eventStatus);
        myEvent.put("visibility", visibility);
        myEvent.put("transparency", transparency);
        myEvent.put("hasAlarm", hasAlarm);
        context.getContentResolver().insert(eventsUri, myEvent);
	}
	
	public void AddNewEvent(int calendarId, String title, String description, long startTime, long endTime){
		_AddNewEvent(calendarId, title, description, "", startTime, endTime, 0, 0, 0, 1, 1);
		return;
	}

}
