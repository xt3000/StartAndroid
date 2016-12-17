package net.finch.parsinghtml;
import java.util.ArrayList;
import java.util.Map;

public class ItemObj
{
	ArrayList<Map<String,Object>> aTitle;
	ArrayList<Map<String,Object>> aHistoryTitle=null;
	
	ItemObj(ArrayList<Map<String,Object>> title)
	{
		this.aTitle=title;
		aHistoryTitle=new ArrayList<>();
	}
	
	public void History(int id)
	{
		aHistoryTitle.add(aTitle.get(id));
	}
}
