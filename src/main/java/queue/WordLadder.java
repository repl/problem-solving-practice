package queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import edu.princeton.cs.algs4.Queue;

public class WordLadder {
	public static void main(String[] args) {
		List<String> dict = new LinkedList<>();
		dict.add("hit");
		dict.add("dot");
		dict.add("dog");
		dict.add("lot");
		dict.add("cog");	
		String start = "hot", end = "log";
		
		dict.clear();
		dict.add("mail");
		dict.add("main");
		dict.add("tail");
		dict.add("suit");
		dict.add("bail");
		dict.add("wail");
		dict.add("rain");
		dict.add("rail");
		dict.add("pain");
		dict.add("sain");
                
		start = "sail";
		end = "ruin";
		WordLadder wordLadder = new WordLadder();
		
		System.out.println(wordLadder.findPathLength(start, end, dict));
	}

	private int findPathLength(String start, String end, List<String> dict) {
		int resultLength = 1000;
		int pathLength = 0;
		Queue<QueueItem> queue = new Queue<>();
		queue.enqueue(new QueueItem(pathLength, start));
		while (!queue.isEmpty()) {
			QueueItem currQItem = queue.dequeue();			
			String currWord = currQItem.getWord();
			System.out.println("DeQ: " + currWord + ", pathLength: " + currQItem.getPathLength());
			if (isAdjecent(currWord, end)) {
				if (currQItem.getPathLength() + 1 < resultLength) {
					resultLength = currQItem.getPathLength() + 1;
				}				
				continue;
			}
			List<String> entriesTobeRemoved = new ArrayList<>();
			for (int k = 0;k < dict.size(); k++) {
				String dictWord = dict.get(k);
				if (isAdjecent(currWord, dictWord)) {
					entriesTobeRemoved.add(dictWord);
					if (currQItem.getPathLength() + 1 < resultLength) {
						System.out.println("EnQ: " + dictWord + ", pathLength: " + (currQItem.getPathLength() + 1));
						queue.enqueue(new QueueItem(currQItem.getPathLength() + 1, dictWord));
						
					}
				}				
			}
			entriesTobeRemoved.stream().forEach(i -> {
				dict.remove(i);
			});	
			System.out.println("dict is now: " + dict);
		}
		return resultLength;
	}
	
	private boolean isAdjecent(String word1, String word2) {
		System.out.println("comparing " + word1 + " and  " + word2);
		char[] word1Array = word1.toCharArray();
		char[] word2Array = word2.toCharArray();
		int i = 0, diffCount = 0;
		while (i < word1Array.length && i < word2Array.length) {
			if (word1Array[i] != word2Array[i]) {
				if (diffCount == 0) {
					diffCount++;
				} else {
					return false;
				}
			}
			i++;
		}
		if (i == word1Array.length && i == word2Array.length) {
			return true;
		}
		return false;
	}
	
	class QueueItem {
		int pathLength;
		String word;
		
		public QueueItem(int pathLength, String word) {
			super();
			this.pathLength = pathLength;
			this.word = word;
		}
		public int getPathLength() {
			return pathLength;
		}
		public void setPathLength(int pathLength) {
			this.pathLength = pathLength;
		}
		public String getWord() {
			return word;
		}
		public void setWord(String word) {
			this.word = word;
		}
		
	}
}
