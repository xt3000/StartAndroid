package net.finch.parsinghtml;
import android.os.*;
import org.jsoup.nodes.*;
import org.jsoup.*;
import java.io.*;
import org.jsoup.select.*;

public class parsAticle extends AsyncTask<String,Void,String>
{

	@Override
	protected String doInBackground(String... args)
	{
		Document doc=null;
		String articleHTML="";
		
		try
		{
			doc = Jsoup.connect(args[0]).get();
			Element e_head=doc.head();
			Element e_article_header= doc.select("header.article-header").first();
			e_article_header.getElementsByAttribute("href").removeAttr("href");
			
			Element e_article_aside= doc.select("aside.article-aside").first();
			
			Element e_article_content= doc.select("section.article-content").first();
			e_article_content.select("h3").remove();
			e_article_content.select("ul.relateditems").remove();
			e_article_content.select("ul.latestnews").remove();
			e_article_content.select("div.custom").remove();
			//String e_a_c_p ="";
			/*for (Element p:e_article_content_p)
			{
				e_a_c_p+=p.toString();
			}
			*/
			
			
			
			articleHTML="<!Doctype html><html>"+e_head+"<body>" + e_article_header+e_article_aside+e_article_content +"</body></html>";
		}
		catch (IOException e)
		{e.printStackTrace();}
		
		return articleHTML;
	}
	
}
