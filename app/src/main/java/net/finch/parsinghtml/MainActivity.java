package net.finch.parsinghtml;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.concurrent.ExecutionException;

import android.support.v7.widget.Toolbar;
import android.support.v4.view.ViewPager;

public class MainActivity extends AppCompatActivity
{
	
	ListView ListView;
	WebView webView;
	parsAticle pAticle;
	
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
			Toast.makeText(this, "ошибка", Toast.LENGTH_LONG).show();
		else if (itemObj.aTitle.size()==0) Toast.makeText(this, "Проверьте родключение к интернету", Toast.LENGTH_LONG).show();
		else Toast.makeText(this, "parsing завершен. ", Toast.LENGTH_SHORT).show();
		
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
		adapter.addFragment(new FullFrag(itemObj, 1), "Все  уроки");
		adapter.addFragment(new FullFrag(itemObj, 2), "История");
		adapter.addFragment(new FavoriteFrag(), "Избранное");
		viewPager.setAdapter(adapter);
	}
	
	
}
