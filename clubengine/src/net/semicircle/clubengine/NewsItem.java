package net.semicircle.clubengine;

public class NewsItem {
	private String _title="";
	private String _link="";
	public String getTitle()
	{
		return _title;
	}
	public String getLink()
	{
		return _link;
	}
	public void setTitle(String title)
	{
		_title = title; 
	}
	public void setLink(String link)
	{
		_link = link;
	}
}
