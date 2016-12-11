package net.finch.parsinghtml;


import org.jsoup.nodes.Document;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.widget.ListView;
import android.widget.TextView;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.support.v7.widget.Toolbar;
import java.util.concurrent.ExecutionException;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.view.View;
import android.os.Bundle;
import java.util.Map;
import android.widget.SimpleAdapter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
	Intent intent;
	
	ListView ListView;
	TextView tvItem;
	WebView webView;
	parsAticle pAticle;
	
	Document doc;
	Parser parser;
	
	ItemObj itemObj;
	
	//test;
	
	//
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		//test
		
		TabLayout tabLayout = (TabLayout)findViewById(R.id.TabLayout);
			tabLayout.addTab(tabLayout.newTab().setText("Все уроки"), true);
			tabLayout.addTab(tabLayout.newTab().setText("История"));
			tabLayout.addTab(tabLayout.newTab().setText("Избранное"));
		//////
		intent=new Intent(this, ArticleActivity.class);
		
		tvItem=(TextView)findViewById(R.id.tvTitle);
		ListView =(ListView)findViewById(R.id.listView);
		webView=(WebView)findViewById(R.id.webView);
		
		
		
		parser =new Parser();
		parser.execute();
		try
		{
			itemObj = parser.get();
		}
		catch (ExecutionException e)
		{}
		catch (InterruptedException e)
		{}
		
		String[] from = {"title", "date"};
		int[] to = {R.id.tvTitle, R.id.tvData};
			
		SimpleAdapter adapter = new SimpleAdapter(this, itemObj.aTitle, R.layout.item, from, to);
		ListView.setAdapter(adapter);
		ListView.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> aView, View view, int pos, long id)
				{
					switch (view.getId())
					{
						case R.id.llItem:
						{
							itemObj.History(pos);
							String url=itemObj.aLink.get(pos);
							intent.putExtra("link", url);
							startActivity(intent);
							break;	
						}
						default:break;
					}
					
					
				}
				
			
		});
		
		

    }
	
	
	//test
	
	//
}