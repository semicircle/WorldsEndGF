package net.semicircle.clubengine;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SchedItemXmlHandler extends DefaultHandler {
	private SchedItem currentSchedItem;
	private List<SchedItem> li;
	private StringBuffer buf = new StringBuffer();
	
	public List<SchedItem> getParsedData() {
		return li;
	}
	
	@Override
	public void startDocument() throws SAXException
	{
		li = new ArrayList<SchedItem>();

	}
	
	@Override
	public void endDocument() throws SAXException
	{
		
	}
	
	public void startElement(String namespaceURI, String localName, 
			String qName, Attributes atts) throws SAXException
	{
		if ("scheditem" == localName)
		{
			currentSchedItem = new SchedItem();
		}
	}
	
	public void endElement(String namespaceURI, String localName, 
			String qName) throws SAXException
	{
		if ("title" == localName)
		{
			currentSchedItem.setTitle(buf.toString().trim());			
			buf.setLength(0);
		}
		else if ("link" == localName)
		{
			currentSchedItem.setLink(buf.toString().trim());
			buf.setLength(0);
		}
		else if ("hostteam" == localName)
		{
			currentSchedItem.setHostTeam(buf.toString().trim());
			buf.setLength(0);
		}
		else if ("visitteam" == localName)
		{
			currentSchedItem.setVisitTeam(buf.toString().trim());
			buf.setLength(0);
		}
		else if ("starttime" == localName)
		{
			long parseRet = 0;
			try
			{
				parseRet = Long.parseLong(buf.toString().trim());					
			}
			catch (Exception ex)
			{
				parseRet = 0;
			}
			finally
			{
				currentSchedItem.setStartTime(parseRet);
				buf.setLength(0);
			}
		}
		else if ("endtime" == localName)
		{
			long parseRet = 0;
			try
			{
				parseRet = Long.parseLong(buf.toString().trim());					
			}
			catch (Exception ex)
			{
				parseRet = 0;
			}
			finally
			{
				currentSchedItem.setEndTime(parseRet);
				buf.setLength(0);
			}
		}
		else if ("scheditem" == localName)
		{
			li.add(currentSchedItem);
		}
	}
	
	@Override
	public void characters(char ch[], int start, int length)
	{
		buf.append(ch, start, length);
	}

}
