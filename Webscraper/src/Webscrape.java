import java.util.regex.Pattern;
import com.jaunt.*;

public class Webscrape {
	String article = new String();
	int window_count = 0;
	public static void main(String[] args)  throws JauntException {
		Testonly obj = new Testonly();
		DisplayArticle displayWindow = new DisplayArticle();
		//Jaunt demo: searches for 'butterflies' at Google and prints urls of search results from first page.
		UserAgent userAgent = new UserAgent();      //create new userAgent (headless browser)
		userAgent.settings.autoSaveAsHTML = true;
		userAgent.visit("https://google.com");       //visit google
		//String news  = new String("Hampi Utsav: State blast, World Heritage site suffered");
		//String news  = new String("DROP Ramesh from cabinet : SPS");
		String news  = new String("Rituals being at Mysuru");
		//String news  = new String("Board CBSE class must exams");
		userAgent.doc.apply(news);         //apply form input (starting at first editable field)
		userAgent.doc.submit("Google Search");      //click submit button labelled "Google Search"
		Elements links = userAgent.doc.findEvery("<h3 class=r>").findEvery("<a>");   //find search result links
		for(Element full_link : links){ //System.out.println(link.getAt("href")); //print results
	    String link = full_link.getAt("href");
	    int start = link.indexOf("=") + 1;
	    int end = link.indexOf("&");
	    String url = link.substring(start, end);
	    System.out.println(url);
	    int paper_name_start = url.indexOf(".")+1;
	    if(paper_name_start==0)
	    	continue;
	    int paper_name_end = url.indexOf(".", paper_name_start);
	    String paper_name;
	    if(paper_name_end!=-1)
	    	paper_name = url.substring(paper_name_start, paper_name_end);
	    else
	    	paper_name = url.substring(paper_name_start);
	    Element div = userAgent.doc.findFirst("<html>");
	    if(paper_name.compareTo("thehindu")==0) {
	    	userAgent.settings.autoRedirect = true;
		    userAgent.visit(url);
		    div = userAgent.doc.findEach("<p class=body>");
		    obj.article = div.innerHTML().toString();
		    if(obj.article.isEmpty()){
		    	String Pattern = "<div id=\"content-body-[0-9]+-[0-9]+\">";
		    	div = userAgent.doc.findEach(Pattern);
			    obj.article = div.innerHTML().toString();
		    }
		    String pattern = "<[^>]*>";
		    String refined_article = obj.article.replaceAll(pattern, " ");
		    obj.window_count++;
		    displayWindow.displayOnWindow(refined_article,paper_name,url,obj.window_count);
	    }
	    if(paper_name.compareTo("indiatimes")==0) {
		    userAgent.visit(url);
		    div = userAgent.doc.findFirst("<div class=normal>");
		    obj.article = div.innerHTML().toString();
		    String pattern = "<[^>]*>";
		    String refined_article = obj.article.replaceAll(pattern, " ");
		    obj.window_count++;
		    displayWindow.displayOnWindow(refined_article,paper_name,url,	obj.window_count);
	    }
	    if(paper_name.compareTo("hindustantimes")==0) {
		    userAgent.visit(url);
		    div = userAgent.doc.findFirst("<div id=div_storyContent>");
		    obj.article = div.innerHTML().toString();
		    String pattern = "<[^>]*>";
		    String refined_article = obj.article.replaceAll(pattern, " ");
		    obj.window_count++;
		    displayWindow.displayOnWindow(refined_article,paper_name,url,	obj.window_count);
	    }
		}
		System.out.println("done");
	}
}
