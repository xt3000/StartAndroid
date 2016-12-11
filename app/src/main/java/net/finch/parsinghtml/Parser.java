package net.finch.parsinghtml;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import org.jsoup.*;
import java.io.*;


public class Parser extends AsyncTask<Void,Void,ItemObj>
{

	@Override
	protected ItemObj doInBackground(Void... args)
	{
		ArrayList<Map<String,Object>> arrayTitle=new ArrayList<Map<String, Object>>();
		Map<String,Object> mapTitle = new HashMap<String, Object>();
		ArrayList<String> arrayLink = new ArrayList<String>();
		Document doc=null;
		
		try
		{
			doc=Jsoup.connect("http://startandroid.ru/ru/uroki/vse-uroki-spiskom.html").get();
			Elements elems = doc.select("td.list-title");
			Elements edates = doc.select("td.list-date");
			////
			int i=0;
			for (Element elem:elems)
			{
				mapTitle=new HashMap<>();
				mapTitle.put("title", elem.text());
				mapTitle.put("date", edates.get(i).text());
				i++;
				arrayTitle.add(mapTitle);
				arrayLink.add(elem.select("a[href]").first().attr("abs:href"));
			}
		
		} catch (IOException e) 
			{
				e.printStackTrace();
			}
			
		
		
		ItemObj item = new ItemObj(arrayTitle, arrayLink);
			
		return item;
	}
}
