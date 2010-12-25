package net.semicircle.clubengine;

public class SchedItem {
	private String _title="";
	private String _hostTeam="";
	private String _visitTeam="";
	private long _startTime=0;
	private long _endTime=0;
	private String _link="";
	public String getTitle()
	{
		return _title;
	}
	public String getLink()
	{
		return _link;
	}
	public String getHostTeam()
	{
		return _hostTeam;
	}
	public String getVisitTeam()
	{
		return _visitTeam;
	}
	public long getStartTime()
	{
		return _startTime;
	}
	public long getEndTime()
	{
		return _endTime;
	}
	public void setTitle(String title)
	{
		_title = title; 
	}
	public void setLink(String link)
	{
		_link = link;
	}
	public void setHostTeam(String hostTeam)
	{
		_hostTeam = hostTeam;
	}
	public void setVisitTeam(String visitTeam)
	{
		_visitTeam = visitTeam;
	}
	public void setStartTime(long startTime)
	{
		_startTime = startTime;
	}
	public void setEndTime(long endTime)
	{
		_endTime = endTime;
	}
}
