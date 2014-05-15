package core;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Example {
	public static final String EXAMPLE = "exam";
	public static final String SENTENCE = "sent";
	public static final String MEANING = "mean";

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

	public Example(Node node) {
		sentence = "";
		meaning = "";

		NodeList nodelist = node.getChildNodes();

		for (int i = 0, length = nodelist.getLength(); i < length; i++) {
			if (nodelist.item(i).getNodeName().equals(SENTENCE)) {
				sentence = nodelist.item(i).getTextContent();
			} else if (nodelist.item(i).getNodeName().equals(MEANING)) {
				meaning = nodelist.item(i).getTextContent();
			}
		}

	}
}
