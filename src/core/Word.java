package core;

import java.util.Vector;

public class Word {
	public String word;
	public String phonetic;
	public String image_url;
	public String sound_url;
	
	public Vector<Definition> definitions;
	
	public Word() {
		word = "example";
		phonetic = "[ɪg'zɑːmp(ə)l; eg-]";
		image_url = "C:\\Users\\flex\\Desktop\\t.png";
		
		definitions = new Vector<Definition>();
		
		Definition d1 = new Definition("English", "n. an instance to illustrate \nv. make a example");
		d1.addExample("For example", "For instance");
		d1.addExample("Example it", "Take this as an example");
		
		Definition d2 = new Definition("中文", "例子， 举例子");
		d2.addExample("For example", "例如");
		d2.addExample("Example it", "以此为例");
		
		definitions.add(d1);
		definitions.add(d2);
	}
	
	public Boolean IsEmptyorNull() {
		if (word == null) {
			return true;
		}
		
		return word.isEmpty();
	}
	
}
