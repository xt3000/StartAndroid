package net.finch.parsinghtml;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.ListView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.concurrent.ExecutionException;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import java.util.ArrayList;
import java.util.Map;
import android.widget.*;

public class FullFrag extends Fragment
{
	SimpleAdapter adapter;
	View view;
	int[] to = {R.id.tvTitle, R.id.tvData};
	String[] from = {"title", "date"};
	Intent intent;
	ListView listView;
	ItemObj itemObj;
	ArrayList<Map<String,Object>> aTitle;
	
	FullFrag(ArrayList<Map<String,Object>> aTitle, ItemObj itemObj)
	{
		this.aTitle=aTitle;
		this.itemObj=itemObj;
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		
		
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		view = inflater.inflate(R.layout.fullfrag, container, false);
		listView =(ListView)view.findViewById(R.id.listView);
		adapter = new SimpleAdapter(getActivity(), aTitle, R.layout.item, from, to);
		listView.setAdapter(adapter);

		intent=new Intent(getActivity(), ArticleActivity.class);

		
		listView.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> aView, View view, int pos, long id)
				{
					int id_item = aTitle.get(pos).get("id");
					Toast.makeText(getActivity(), "id="+id_item, Toast.LENGTH_SHORT).show();
					switch (view.getId())
					{
						case R.id.llItem:
							{
								itemObj.History(id_item);
								String url=itemObj.aTitle.get(id_item).get("link").toString();
								intent.putExtra("link", url);
								startActivity(intent);
								break;	
							}
						default:break;
					}
				}
			});
			
		return view;
	}

	@Override
	public void onResume()
	{
		adapter.notifyDataSetChanged();
		// TODO: Implement this method
		super.onResume();
	}
	
	
}
