

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BST implements WordCounter{
	
	private TreeNode head; //root of the tree
	private int countOfWords = 0; 
	private int countOfNodes = 0; 
	private WordFreq maxFreq = null;  //for getMaximumFrequency method
	private QueueInterface<String> stopWordsList = new QueueImpl<String>(); 
	private QueueInterface<WordFreq> inOrderWords = new QueueImpl<WordFreq>();
	private String bufferForInOrder = "";
	

	
	public int compare(String word1, String word2) {
		
		int minLength = Math.min(word1.length(), word2.length());
		
		for(int i=0; i<minLength; i++) {
			int str1_ch = (int)word1.charAt(i);
			int str2_ch = (int)word2.charAt(i);
		
			if(str1_ch != str2_ch) {
				return str1_ch - str2_ch;
			}
		}
		
		if(word1.length() != word2.length()) {
			return word1.length() - word2.length();
		}
			return 0;	
	}
	
	

	public void insert(String w) { 
		 
		 TreeNode cur = head;
		 TreeNode prev = null;
		 
		 int c = -1;
		 while(cur!=null) {
			 c = compare(w,cur.getItem().key());
			 if(c==0) {
				 cur.item.setKey(w);
				 cur.getItem().addCount();
				 countOfWords++; //for getTotalWords method
				 return;
				 
			 }
			 
			 prev = cur;
			 
			 if(c<0) {
				 cur = cur.getLeft();
			 }else {
				 cur = cur.getRight();
			 }
			 
		 }
		 
		 
		 TreeNode node = new TreeNode(new WordFreq(w));
		 
		 if(prev==null) {
			 head = node;
			 head.getItem().addCount();  
			 countOfWords++; //for getTotalWords method
		 }else {
			 if(c<0) {
				 prev.setLeft(node);
				 prev.getLeft().getItem().addCount(); //establish frequency
				 countOfWords++; //for getTotalWords method
			 }else {
				 prev.setRight(node);
				 prev.getRight().getItem().addCount(); //establish frequency
				 countOfWords++; //for getTotalWords method
			 }
		 }
		 
		 countOfNodes++; //for getDistinctWords method
		 
		
	}

	
	public WordFreq search(String w) {  
		
		TreeNode current = head;
        while (true) {
            if (current == null)
            	return null;
            	
            if (compare(current.getItem().key(),w)==0) {
            	return current.getItem();
            }	
            	
            if (compare(current.getItem().key(),w) < 0)
                current = current.getRight();
            else
                current = current.getLeft();
        }
		
	}

	
	public void remove(String w) {
		
		TreeNode p;  //current
		TreeNode pp; //parent of current;
		int c;
		
		p = head;
		pp = null;
		
		while(p!=null && (c=compare(w,p.getItem().key()))!=0) {
			
			pp = p;
			p = c < 0 ? p.getLeft() : p.getRight();
			
		}
		
		if(p==null) {
			return;
		}
		
		if(p.getLeft()!=null && p.getRight()!=null) {
			//reduce to zero or one child case
			TreeNode node = p.getLeft();
			TreeNode pnode = p;
			
			while(node.getRight()!=null) {
				pnode = node;
				node = node.getRight();
			}
			
			p.getItem().setKey(node.getItem().key());
			p = node;
			pp = pnode;
			countOfWords -= node.getItem().getFreq();
		}
		
		
		TreeNode pchild = (p.getLeft()!=null) ? p.getLeft() : p.getRight();
		if(p==head) {
			head = pchild;
		}else {
			if(p==pp.getLeft()) {
				countOfWords -= p.getItem().getFreq();  
				pp.setLeft(pchild);
			}else{
				pp.setRight(pchild);
				countOfWords -= p.getItem().getFreq(); 
			}
		}
		
		countOfNodes--;
		
	}

	
	public void load(String filename) throws IOException {
		
		QueueInterface<String> parenthesesTextList = new QueueImpl<String>();
		QueueInterface<String> preParenthesesTextList = new QueueImpl<String>();
		QueueInterface<String> allWordsList = new QueueImpl<String>();
		
		
		//read txt file and transform it into a string 
		String txt = new Scanner(new File(filename)).useDelimiter("\\Z").next();
		
		//make all string's characters lowercase
		String tempTxt = txt;
		char curLetter;
		for(int i=0; i<txt.length(); i++) {
			if(txt.charAt(i)>=65 && txt.charAt(i)<=90) {
				curLetter = (char) (txt.charAt(i)+32);
				tempTxt = tempTxt.substring(0,i)+curLetter+txt.substring(i+1);
				
			}
		}
		
		//find how many change line characters exist in the file
		int changeLineCounter = 0;
		for(int i=0; i<tempTxt.length(); i++) {
			if(tempTxt.charAt(i)=='\n') {
				changeLineCounter++;
			}
		}
		
		//replace '\r' and '\n' characters with a ' ' character (in order to filter words later with split method)
		String finalTempTxt = tempTxt;
		int flag = 0;
		for(int i=0; i<tempTxt.length(); i++) {
			if(flag>=changeLineCounter) {
				break;
			}
			if(finalTempTxt.charAt(i)=='\n') {
				curLetter = ' ';
				finalTempTxt = finalTempTxt.substring(0,i-1)+curLetter+finalTempTxt.substring(i+1);
				flag++;
			}
		}
		
		txt = finalTempTxt;
			
		
		//check if the txt file is empty of words
		if(txt.length()==0) {
			return;
		}
		
		
		//loop the txt string to find parentheses and store the including text
		String parenthesisTextBuffer = "";
		for(int i=0; i<txt.length(); i++) {
			if(txt.charAt(i)=='(') {
				int j = i;
				while(true) {
					if(txt.charAt(j+1)==')') {
						parenthesesTextList.put(parenthesisTextBuffer);
						break;
					}
					parenthesisTextBuffer += txt.charAt(j+1);
					j++;
				}
				parenthesisTextBuffer = "";
			}
			
		}
		
		for(int i=0; i<=parenthesesTextList.size(); i++) {
			parenthesisTextBuffer += parenthesesTextList.get()+" ";
		}
		
		//loop the txt string again to store all the non-parenthesis text
		String preParenthesisTextBuffer = "";
		for(int i=0; i<txt.length(); i++) {
			if(txt.charAt(i)=='(') {
				while(true) {
					i++;
					if(txt.charAt(i)==')') {
						break ;
					}
				}
			}
			if(txt.charAt(i)!=')') {
				preParenthesisTextBuffer += txt.charAt(i);
			}
		}
		
		//System.out.println(preParenthesisTextBuffer);
		//System.out.println("---------------------------------------");
		//System.out.println(parenthesisTextBuffer+"\n\n\n\n");
		//System.out.println("---------------------------------------");
		
		//count words OUTSIDE of parentheses text buffer
		int wordsCount = 0;
		String trim = preParenthesisTextBuffer.trim();
		if(trim.isEmpty()) {
			wordsCount = 0;
		}else {
			wordsCount = trim.split("\\s+").length;
		}
		
		//System.out.println("\n\n\n\n"+ wordsCount);
		
		
		//count words INSIDE the parentheses text buffer
		int wordsCountPar = 0;
		String trimPar = parenthesisTextBuffer.trim();
		if(trim.isEmpty()) {
			wordsCountPar = 0;
		}else {
			wordsCountPar = trimPar.split("\\s+").length;   
		}
		
		//System.out.println("\n\n\n\n"+ wordsCountPar);
		
		
		
		//add all detected word (both from parentheses and the rest text) to a list of string in order to filter them and remove non-acceptable words
		
		for(int i=0; i<wordsCount; i++) { 
			//code for checking a word before inserting
			allWordsList.put(preParenthesisTextBuffer.split("\\s+")[i]);
		}
		
		for(int i=0; i<wordsCountPar; i++) {
			//code for checking a word before inserting
			allWordsList.put(parenthesisTextBuffer.split("\\s+")[i]);
		
		}
		
		
		
		//filtering for non-acceptable words 
		//(none of the words contain "parenthesis" character neither at the start nor at the end of a word due to the filtering we've done to detected text inside parentheses)
		QueueInterface<String> fixedWordsList = new QueueImpl<String>();
		String fixedWord = "";
		String curWord = "";
		int allWordsListSize = allWordsList.size();
		for(int i=0; i<allWordsListSize; i++) {
			curWord = allWordsList.get();
			
			//in case of a single special character
			if (curWord.length()==1 &&(curWord.charAt(0)<97 || curWord.charAt(0)>122)){
				continue;
			}else if(curWord.length()==1 && curWord.charAt(0)>=97 && curWord.charAt(0)<=122) {  //in case of a single letter
				fixedWordsList.put(curWord);
				continue;
			}
			
			//in case of a special character at the start of a (probably acceptable) word
			if (curWord.length()>1 && (curWord.charAt(0)<97 || curWord.charAt(0)>122)) {
				curWord = curWord.substring(1,curWord.length());
			}
			
			
			//in case of a special character/s at the end of a word
			int where = 0;
			if((curWord.charAt(curWord.length()-1))<97 || (curWord.charAt(curWord.length()-1))>122) {
				for(int j=0; j<curWord.length(); j++) {
					
					//in case of multiple special characters at the end of the word (for example: "many..." )
					if(curWord.charAt(j)==curWord.charAt(curWord.length()-1)) {
						where = j;
						break;
					}
				}
				
				fixedWord = curWord.substring(0,where); // was curWord.length()-1
				fixedWordsList.put(fixedWord);
				continue;
			}
			
			
			//in case of special character inside a word
			int continueFlag = 0;
			for(int j=0; j<curWord.length(); j++) {
				
				//in case of a double special character inside a word
				if((curWord.charAt(j)<97 || curWord.charAt(j)>122) && (curWord.charAt(j)!=39 && j>=1)) {
					if((curWord.charAt(j-1)<97 || curWord.charAt(j-1)>122) || (curWord.charAt(j+1)<97 || curWord.charAt(j+1)>122)) {
						continueFlag = 1;
						break;
					}else {
						continueFlag = 1;
						break;
					}
					
				}
				
				//in case of an apostrophe character inside a word
				if(curWord.charAt(j)==39 && j>=1) {
					if(curWord.charAt(j-1)>=97 && curWord.charAt(j-1)<=122 && curWord.charAt(j+1)>=97 && curWord.charAt(j+1)<=122) {
						fixedWordsList.put(curWord);
						continueFlag = 1;
						break;
					}
				}
			}
			//trick to double-continue;
			if(continueFlag==1) {
				continue;
			}
			
			//in case of a simple word with no special characters
			fixedWordsList.put(curWord);
		
		}
		//fixedWordsList.printQueue(System.out);-------------------------

		//user input for "stop words"
		Scanner stopWordsInput = new Scanner(System.in);
		
		//message for "Stop words" and user instructions for the user input
		System.out.println("Before counting this files' words please insert some \n'stop words' you'd like to exclude from counting");
		System.out.println("(Note: Type each word in a single line with gaps separating each one and then press enter)");
		
		String stopWordsLine = stopWordsInput.nextLine();
		
		//count the stop words on the input
		int stopWordsCount = 1;
		for(int i=0; i<stopWordsLine.length(); i++) {
			if(stopWordsLine.length()==0) {
				stopWordsCount = 0;
				break;
			}
			
			if(stopWordsLine.charAt(i)==' ') {
				stopWordsCount++;
			}
		}
		
		//add all stop words from the input to the stopWordsList
		for(int i=0; i<stopWordsCount; i++) {
			addStopWord(stopWordsLine.split(" ")[i]);
		}
		
		
		//fixedWordsList.printQueue(System.out);--------------------------
		//System.out.println(fixedWordsList.size());
		
		
		int fixedSize = fixedWordsList.size(); 
		for(int i=0; i<fixedSize; i++) {
			//insert acceptable word in BST
			insert(fixedWordsList.get());
		}
		
		
		//remove all stop words from the BST
		int stopWordsListSize = stopWordsList.size();
		for(int i=0; i<stopWordsListSize; i++) {
			String cur = stopWordsList.get();
				remove(cur);
		
		}
		
		
		//printTreeAlphabetically(System.out);
		//System.out.println("----------------------");
		//printTreeByFrequency(System.out);
		
		//System.out.println(+ search("you").getFreq());
		
		
		
	}	
	

	
	public int getTotalWords() {
		return countOfWords;
		
	}

	
	public int getDistinctWords() {
		return countOfNodes;
	}


	public int getFrequency(String w) {
		
		WordFreq answer = search(w);
		
		if (answer!=null) {
			return answer.getFreq();
		}
		
		return 0;
	}


	public WordFreq getMaximumFrequency() {
		QueueInterface<WordFreq> queue = inFreqOrder(head);
		if(queue.size()==0) {
			return null;
		}
		int maxFreq = -1;
		String maxFreqWord = "";
		int size = queue.size();
		for(int i=0; i<size; i++) {
			WordFreq curItem = queue.get();
			if(curItem.getFreq()>maxFreq) {
				maxFreq = curItem.getFreq();
				maxFreqWord = curItem.key();
			}
		}
		
		//setup the maxWord to return
		WordFreq maxWord = new WordFreq(maxFreqWord);
		maxWord.setFreq(maxFreq);
		
		
		//re-initialization
		queue = new QueueImpl<WordFreq>();
		inOrderWords = new QueueImpl<WordFreq>();
		
		return maxWord;
		
	}

	
	public double getMeanFrequency() {
		
		QueueInterface<WordFreq> queue = inFreqOrder(head);
		
		double counter = 0;
		int size = queue.size();
		for(int i=0; i<size; i++) {
			counter += queue.get().getFreq();
		}
		
		//re-initialization
		queue = new QueueImpl<WordFreq>();
		inOrderWords = new QueueImpl<WordFreq>();
		
		
		return counter/size;
		
		
	}

	
	public void addStopWord(String w) {      
		stopWordsList.put(w);
	}

	
	public void removeStopWord(String w) {     
		stopWordsList.remove(w);
	}
	
	
	public void printTreeAlphabetically(PrintStream stream) {
		
		//inOrder(head);
		stream.println(inOrder(head)+'\n');
	
		//re-initialization
		bufferForInOrder = "";
		
	}
	
	
	
	public String inOrder(TreeNode node ) {
		if(node==null) {
			return bufferForInOrder;
			//return ;
		}
		
		inOrder(node.getLeft());
		bufferForInOrder += node.getItem().key() + " ";
		return inOrder(node.getRight());
	
	}
	
	
	public QueueInterface<WordFreq> inFreqOrder(TreeNode node) {
		
		if(node==null) {
			return inOrderWords;
		}
		
		inFreqOrder(node.getLeft());
		inOrderWords.put(node.getItem());
		return inFreqOrder(node.getRight());
		
	}


	public void printTreeByFrequency(PrintStream stream) {
		
		QueueInterface<WordFreq> queue = inFreqOrder(head);
		
		Node<WordFreq> current = queue.getFirst();
		Node<WordFreq> index = null;
		WordFreq temp;
		
		if(current==null) {
			return ;
		}else {
			while(current!=null){
				index = current.getNext();
				
				while(index!=null) {
					
					if(current.data.getFreq()>index.data.getFreq()){
						temp = current.data;
						current.data = index.data;
						index.data = temp;
						
					}
					index = index.getNext();
					
				}
				
				current = current.getNext();
			}
			
		}
		
		WordFreq cur;
		int size = queue.size();
		for(int i=0; i<size; i++) {
			cur = queue.get();	
			stream.println(cur.key()+" "+cur.getFreq());
			
		}
		
	}
	
	
	private class TreeNode{
		WordFreq item;
		TreeNode left;
		TreeNode right;
		int subTreeSize;
		
		
		
		TreeNode(WordFreq item){
			this.item = item;
			left = null;
			right = null;
		}
		
		public int nodesInSubTree(TreeNode curNode) {
	        if(null == curNode)
	            return 0;
	         
	        int nLeftSubtree = nodesInSubTree(curNode.getLeft());
	        int nRightSubtree = nodesInSubTree(curNode.getRight());
	        return nLeftSubtree + nRightSubtree + 1;
	    }
		
		public int getSubTreeSize() {
			subTreeSize = nodesInSubTree(this);
			return subTreeSize;
		}
		
		public void setLeft(TreeNode left) {
			this.left = left;
		}
		
		public void setRight(TreeNode right) {
			this.right = right;
		}
		
		public WordFreq getItem() {  //was "String"
			return item;
			//return item.key();
		}

		public TreeNode getLeft() {
			return left;
		}
	
		public TreeNode getRight() {
			return right;
		}
		
		
	}
	
	
}
