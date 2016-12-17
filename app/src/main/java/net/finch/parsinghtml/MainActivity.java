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
import android.support.v4.view.*;

public class MainActivity extends AppCompatActivity
{
	Intent intent;
	
	ListView ListView;
	WebView webView;
	parsAticle pAticle;
	
	Document doc;
	Parser parser;
	
	ItemObj itemObj;
	
	//test;
	ViewPager viewPager;
	TabLayout tabLayout;
	//
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		//test
		parser =new Parser();
		parser.execute();
		itemObj=null;
		try
		{
			itemObj = parser.get();
		}
		catch (ExecutionException e)
		{}
		catch (InterruptedException e)
		{}
		if (itemObj==null)
		{
			Toast.makeText(this, "ошибка", Toast.LENGTH_LONG).show();
		}
		else Toast.makeText(this, "parsing завершен", Toast.LENGTH_SHORT).show();
		
		//itemObj.History(0);
		
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		setupViewPager(viewPager);
		
		tabLayout = (TabLayout)findViewById(R.id.TabLayout);
		tabLayout.setBackgroundResource(R.color.primary);
		tabLayout.setupWithViewPager(viewPager);
		///////
		
		
		

    }
	
	
	//test
	public void setupViewPager(ViewPager viewPager)
	{
		ViewPagerAdapter adapter=new ViewPagerAdapter(getSupportFragmentManager());
		adapter.addFragment(new FullFrag(itemObj.aTitle, itemObj.aLink, itemObj), "Все  уроки");
		adapter.addFragment(new FullFrag(itemObj.aHistoryTitle, itemObj.aHistoryLink, itemObj), "История");
		adapter.addFragment(new FavoriteFrag(), "Избранное");
		viewPager.setAdapter(adapter);
	}
	
	
}
