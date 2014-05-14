package core;

import java.util.Vector;

public class Definition {
	public String language;
	public String content;
	public Vector<Example> examples;
	
	public Definition() {
		language = "";
		content = "";
		examples = new Vector<Example>();
	}
	
	public Definition(String language, String content) {
		this.language = language;
		this.content = content;
		examples = new Vector<Example>();
	}
	
	public void addExample(Example example) {
		examples.add(example);
	}
	
	public void addExample(String sentence, String meaning) {
		examples.add(new Example(sentence, meaning));
	}
}
