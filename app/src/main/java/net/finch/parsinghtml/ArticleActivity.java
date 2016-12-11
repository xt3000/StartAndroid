package net.finch.parsinghtml;
import android.app.*;
import android.content.*;
import android.os.*;
import android.webkit.*;
import java.util.concurrent.*;
import android.widget.*;
import android.support.v7.app.*;

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