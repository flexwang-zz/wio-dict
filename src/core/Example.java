package core;

public class Example {
	public String language;
	public String sentence;
	public String meaning;
	
	public Example() {
		sentence = "";
		meaning = "";
	}
	
	public Example(String sentence, String meaning) {
		this.sentence = sentence;
		this.meaning = meaning;
	}
}
