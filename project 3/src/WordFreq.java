
public class WordFreq {
	private String word;
	private int count;
	
	WordFreq(String word){
		this.word = word;
	}
	
	public void addCount() {
		count++;
	}
	
	public int getFreq() {
		return count;
	}
	
	public void setFreq(int count) {
		this.count = count;
	}
	
	public void setKey(String w){
		word = w;
	}
	
	public String key() {
		return word;
	}
	
	public String toString() {
		return " "; //to be fixed
	}

	
	
}
