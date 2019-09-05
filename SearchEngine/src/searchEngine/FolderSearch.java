package searchEngine;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class FolderSearch {
	static HashMap<String,HashMap<String,Integer>> wordIndex ;
	
	public FolderSearch(String folderPath) throws IOException{
		if(wordIndex == null){
			wordIndex = new HashMap<String, HashMap<String,Integer>>();
			File[] files = new File(folderPath+"\\Text").listFiles();
			System.out.println(files.length);
			try {
				loadFilesContent(files);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Entry<String, Integer>> search(String searchContent,int topNRecords) throws IOException {
		searchContent = searchContent.toLowerCase();
		Map<String, Integer> fileContentCount = new HashMap<String, Integer>();
		if(wordIndex.containsKey(searchContent)){
			fileContentCount=wordIndex.get(searchContent);
		}
		System.out.println("fileContentCount: " + fileContentCount);

		List<Entry<String, Integer>> sortedFileContentCount = SortContentByValues(fileContentCount);
		System.out.println("sortedFileContentCount: " + sortedFileContentCount);
		
		
		System.out.println("topNRecords: " + topNRecords);
		System.out.println("sortedFileContentCount.size(): " + sortedFileContentCount.size());
		if(topNRecords > sortedFileContentCount.size()){
			topNRecords = sortedFileContentCount.size();
		}
		
		System.out.println("\n\n");
		
		List<Entry<String, Integer>> topNSortedFileContentCount = sortedFileContentCount.subList(0, topNRecords);
		System.out.println("topNSortedFileContentCount: " + topNSortedFileContentCount);
			
		return topNSortedFileContentCount;
		
	}
	public List<String> suggest(String searchContent){
		System.out.println("method started");
		List<String> suggestedList=new ArrayList<String>();
		for(String word:wordIndex.keySet()) {
			int distance=EditDistance.editDistance(word, searchContent);
			if((distance<=3)&&(word.startsWith(searchContent))){
				suggestedList.add(word);
				System.out.println(word);
			}
		}
		return suggestedList;
	}
	
	
	public static void loadFilesContent(File[] files) throws IOException{
	    InvertedIndex in=new InvertedIndex();
	    wordIndex=in.createInvertedIndex(files);
	    
	}
	
	static <K,V extends Comparable<? super V>> List<Entry<K, V>> SortContentByValues(Map<K,V> map) {

		List<Entry<K,V>> sortedEntries = new ArrayList<Entry<K,V>>(map.entrySet());

		Collections.sort(sortedEntries, 
	            new Comparator<Entry<K,V>>() {
	                @Override
	                public int compare(Entry<K,V> e1, Entry<K,V> e2) {
	                    return e2.getValue().compareTo(e1.getValue());
	                }
	            }
	    );

		return sortedEntries;
	}
}