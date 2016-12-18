package net.finch.parsinghtml;

import android.os.Bundle;
import android.webkit.WebView;
import java.util.concurrent.ExecutionException;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;

public class ArticleActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		WebView webView;
		String url;
		parsAticle pAticle;
		
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.arcticle);
		
		CollapsingToolbarLayout collapsingToolbarLayout =
			(CollapsingToolbarLayout) findViewById(R.id.collapsing);
		collapsingToolbarLayout.setTitle(getString(R.string.article_name));
		
		Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar2);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		
		
		webView=(WebView)findViewById(R.id.webView);
		url=getIntent().getStringExtra("link");
		
		pAticle = new parsAticle();
		pAticle.execute(url);
		String articleHTML = "";
							
			try
			{
				articleHTML = pAticle.get();
			}
			catch (ExecutionException e)
			{}
			catch (InterruptedException e)
			{}
				
		webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
		webView.getSettings().setUseWideViewPort(true);
		webView.getSettings().setLoadWithOverviewMode(true);
		webView.getSettings().setDefaultTextEncodingName("utf-8");
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadDataWithBaseURL(null, 
		"<style>img{display: inline;height: auto;max-width: 100%;}</style>" +articleHTML,
		"text/html", "utf-8", null);
	}
}
