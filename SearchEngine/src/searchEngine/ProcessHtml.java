package searchEngine;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ProcessHtml {
	/*HashSet<String> stopwords=new HashSet<String>();
	public ProcessHtml() throws IOException{
	BufferedReader br=null;
	br=new BufferedReader(new FileReader("C:\\Users\\TEKWISSEN Group-13\\eclipse-workspace\\SearchEngine\\src\\searchEngine\\stopwords.txt"));
	String line;
	while((line=br.readLine())!=null) {
		stopwords.add(line);
	}
	br.close();
	
	}*/
	public void processfile(String folderPath) throws IOException {
		File myFile=new File(folderPath);
		File[] files=myFile.listFiles();
		for(File f:files) {
			if(!f.isDirectory()) {
			Document doc=Jsoup.parse(f,"UTF-8");
			String text=doc.text().toLowerCase();
			/*
			StringTokenizer st=new StringTokenizer(text," ");
			while(st.hasMoreTokens()) {
				String s=st.nextToken();
				for(String word:stopwords) {
					if(word.equals(s))
						s=s.replace(s, "")
				}
			}
			*/
			
			
			String regex = "[ ](?=[ ]-$%*!#)|[^_@ A-Za-z]+";
			text=text.replaceAll(regex, "");
			FileWriter fw=new FileWriter("C:\\Users\\TEKWISSEN Group-13\\eclipse-workspace\\SearchEngine\\src\\webpages\\Text\\"+f.getName()+".txt");
			fw.write(text);
			fw.close();
			
		}
		}
		
	}
	
	public static void main(String args[]) throws IOException {
		ProcessHtml html=new ProcessHtml();
		html.processfile("C:\\Users\\TEKWISSEN Group-13\\eclipse-workspace\\SearchEngine\\src\\webpages");
	}
	
	
}
