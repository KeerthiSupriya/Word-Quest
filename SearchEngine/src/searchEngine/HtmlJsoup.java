package searchEngine;
import java.io.IOException;



import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class HtmlJsoup {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		 Document doc= Jsoup.connect("https://www.w3.org/").get();
		 String title=doc.title();
		 System.out.println(title);
		 
		 Elements links=doc.select("*");
		 for(Element link: links) {
			 System.out.println("\nlink: "+link.attr("href"));
			 System.out.println("\ntext "+link.text());
		 }

	}

}
