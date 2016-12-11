package net.finch.parsinghtml;


import android.content.*;
import android.os.*;
import android.support.design.widget.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.view.*;
import android.webkit.*;
import android.widget.*;
import android.widget.AdapterView.*;
import java.util.concurrent.*;
import org.jsoup.nodes.*;

import android.support.v7.widget.Toolbar;

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
		
		/*TabLayout tabLayout = (TabLayout)findViewById(R.id.TabLayout);
			tabLayout.addTab(tabLayout.newTab().setText("Все уроки"), true);
			tabLayout.addTab(tabLayout.newTab().setText("История"));
			tabLayout.addTab(tabLayout.newTab().setText("Избранное"));
		*///////
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
