package core;

import java.util.Vector;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Word {

	public static final String ITEM = "item";
	
	public static final String WORD = "word";
	public static final String IMAGEURL = "image";
	public static final String SOUNDURL = "sound";
	public static final String PHONETIC = "phonetic";

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

		Definition d1 = new Definition("English",
				"n. an instance to illustrate \nv. make a example");
		d1.addExample("For example", "For instance");
		d1.addExample("Example it", "Take this as an example");

		Definition d2 = new Definition("中文", "例子， 举例子");
		d2.addExample("For example", "例如");
		d2.addExample("Example it", "以此为例");

		definitions.add(d1);
		definitions.add(d2);
	}

	public Word(Node node) {
		definitions = new Vector<Definition>();
		NodeList nodelist = node.getChildNodes();

		for (int i = 0, length = nodelist.getLength(); i < length; i++) {
			Node childnode = nodelist.item(i);

			if (childnode.getNodeName().equals(WORD)) {
				word = childnode.getTextContent();
			} else if (childnode.getNodeName().equals(IMAGEURL)) {
				image_url = childnode.getTextContent();
			}else if (childnode.getNodeName().equals(SOUNDURL)) {
				sound_url = childnode.getTextContent();
			}else if (childnode.getNodeName().equals(PHONETIC)) {
				phonetic = childnode.getTextContent();
			}else if (childnode.getNodeName().equals(Definition.DEFINITION)) {
				definitions.add(new Definition(childnode));
			}
		}
	}

	public Boolean IsEmptyorNull() {
		if (word == null) {
			return true;
		}

		return word.isEmpty();
	}
	
	@Override
	public String toString() {
		return word.toString();
	}

}
