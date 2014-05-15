package core;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Definition {
	
	public static final String LANGUAGE = "lang";
	public static final String DEFINITION = "defs";
	public static final String CONTENT = "cont";
	
	public String language;
	public String content;
	public ArrayList<Example> examples;
	
	public Definition() {
		language = "";
		content = "";
		examples = new ArrayList<Example>();
	}
	
	public Definition(String language, String content) {
		this.language = language;
		this.content = content;
		examples = new ArrayList<Example>();
	}
	
	public Definition(Node node) {
		language = ((Element)node).getAttribute(LANGUAGE);
		examples = new ArrayList<Example>();
		
		NodeList nodelist = node.getChildNodes();
		for (int i=0, length=nodelist.getLength(); i<length; i++) {
			Node childNode = nodelist.item(i);
			
			if (childNode.getNodeName().equals(Example.EXAMPLE)) {
				examples.add(new Example(childNode));
			} else if (childNode.getNodeName().equals(CONTENT)) {
				content = childNode.getTextContent();
			}
		}
	}

	public void addExample(Example example) {
		//examples.add(example);
	}
	
	public void addExample(String sentence, String meaning) {
		//examples.add(new Example(sentence, meaning));
	}
}
