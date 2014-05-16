package core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import core.trie.TrieTree;


public class WordBook {
	

	
	public Vector<Word> words;
	private TrieTree<Word> trieTree;
	
	public Word search(String word) {
		return trieTree.search(word);
	}
	
	public void insert(Word word) {
		trieTree.insert(word);
	}
	
	public ArrayList<String> findTips(String keyword, int maxtips) {
		return trieTree.findTips(keyword, maxtips);
	}
	
	public ArrayList<String> toList() {
		return trieTree.findTips("", trieTree.size());
	}
	public WordBook(String filename) {
		InputStream is;
		try {
			is = new FileInputStream(filename);
			Document document = parse(is);
			parseWordBook(document);
			is.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public WordBook() {
		
	}
	
	private Document parse(InputStream is) {
		Document document = null;
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory
				.newInstance();
		try {
			// DOM parser instance
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			// parse an XML file into a DOM tree
			document = builder.parse(is);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return document;
	}

	private void parseWordBook(Document document)
	{
		words = new Vector<Word>();
		trieTree = new TrieTree<Word>();
		if (document == null) return;
		// get root element
		Element rootElement = document.getDocumentElement();
		// traverse child elements
		NodeList nodes = rootElement.getChildNodes();
		
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			String nodename = node.getNodeName();
			
			if (nodename.equals(Word.ITEM)) {
				//words.add(new Word(node));
				trieTree.insert(new Word(node));
			}
		}		
	}
}
