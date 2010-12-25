package net.semicircle.clubengine;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
//import android.util.Xml;

public class NewsItemXmlHandler extends DefaultHandler {

	private NewsItem currentNewsItem;
	private List<NewsItem> li;
	private StringBuffer buf = new StringBuffer();
	
	public List<NewsItem> getParsedData() {
		return li;
	}
	
	@Override
	public void startDocument() throws SAXException
	{
		li = new ArrayList<NewsItem>();

	}
	
	@Override
	public void endDocument() throws SAXException
	{
		
	}
	
	public void startElement(String namespaceURI, String localName, 
			String qName, Attributes atts) throws SAXException
	{
		if ("newsitem" == localName)
		{
			currentNewsItem = new NewsItem();
		}
	}
	
	public void endElement(String namespaceURI, String localName, 
			String qName) throws SAXException
	{
		if ("title" == localName)
		{
			currentNewsItem.setTitle(buf.toString().trim());
			buf.setLength(0);
		}
		else if ("link" == localName)
		{
			currentNewsItem.setLink(buf.toString().trim());
			buf.setLength(0);
		}
		else if ("newsitem" == localName)
		{
			li.add(currentNewsItem);
		}
	}
	
	@Override
	public void characters(char ch[], int start, int length)
	{
		buf.append(ch, start, length);
	}

}
