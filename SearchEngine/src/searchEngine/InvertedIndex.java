package searchEngine;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
public class InvertedIndex {
	

	static HashMap<String,HashMap<String,Integer>> wordIndex;
	
	public HashMap<String,HashMap<String,Integer>> createInvertedIndex(File[] files) throws IOException{
		int occurences;
		if(wordIndex==null) {
			wordIndex=new HashMap<String,HashMap<String,Integer>>();
			for(File f: files) {
				BufferedReader br = null;
				String sCurrentLine;
				br = new BufferedReader(new FileReader(f));
				while((sCurrentLine=br.readLine())!=null) {
					String tokens[]=sCurrentLine.split("\\W+");
					for(String word:tokens) {
						word=word.toLowerCase();
						if(!wordIndex.containsKey(word)) {
							wordIndex.put(word,new HashMap<String,Integer>());
							HashMap<String,Integer> docs_count=wordIndex.get(word);
							occurences=1;
							docs_count.put(f.getName(),occurences);
						}
						else{
							HashMap<String,Integer> docs_count=wordIndex.get(word);
							if(!docs_count.containsKey(f.getName())) {
								occurences=1;
								docs_count.put(f.getName(),occurences);
							}
							else {
								occurences=docs_count.get(f.getName())+1;
								docs_count.replace(f.getName(),occurences);
							}
						}
							
					}
				}
				br.close();
			}
			
		}
		return wordIndex;
	}
	
	public static void main (String args[]) throws IOException {
		File[] files=new File("C:\\Users\\TEKWISSEN Group-13\\eclipse-workspace\\SearchEngine\\src\\webpages\\Text").listFiles();
		InvertedIndex in=new InvertedIndex();
		in.createInvertedIndex(files);
		System.out.println(wordIndex.entrySet());
	}
	
}



