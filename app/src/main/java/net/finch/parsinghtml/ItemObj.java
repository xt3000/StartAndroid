package net.finch.parsinghtml;
import java.util.*;

public class ItemObj
{
	ArrayList<Map<String,Object>> aTitle;
	ArrayList<String> aLink;
	
	ArrayList<Map<String,Object>> aHistoryTitle=null;
	ArrayList<String> aHistoryLink;
	
	ItemObj(ArrayList<Map<String,Object>> title, ArrayList<String> link)
	{
		this.aTitle=title;
		this.aLink=link;
		aHistoryTitle=new ArrayList<>();
		aHistoryLink=new ArrayList<>();
	}
	
	public void History(int pos)
	{
		aHistoryTitle.add(aTitle.get(pos));
		aHistoryLink.add(aLink.get(pos));
	}
}
