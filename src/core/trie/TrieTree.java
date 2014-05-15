package core.trie;

import java.util.ArrayList;
import java.util.Iterator;


public class TrieTree<T> {
	public TrieTree() {
		root = new TrieNode<T>();
		_size = 0;
	}
	
	private int _size;
	
	public int size() {
		return _size;
	}
	
	public void insert(T item) {
			
		TrieNode<T> p = root;
		String keyword = item.toString();//.word;
		char key;
		for (int i=0, length=keyword.length(); i<length; i++) {
			key = keyword.charAt(i);
			
			if (!p.children.containsKey(key)) {
				p.children.put(key, new TrieNode<T>());
			}
			p = p.children.get(key);
		}
		
		p.item = item;
		_size++;
	}
	
	public T search(String word) {
		String keyword = word;
		TrieNode<T> p = root;
		
		for (int i=0, length=word.length(); i<length; i++) {
			char key = keyword.charAt(i);
			if (!p.children.containsKey(key)) {
				return null;
			}
			p = p.children.get(key);
		}
		
		return p.item;
	}
	
	public ArrayList<String> findTips(String keyword, int maxtips) {
		ArrayList<String> tips = new ArrayList<String>();
		
		TrieNode<T> p = root;
		for (int i=0, length=keyword.length(); i<length; i++) {
			char key = keyword.charAt(i);
			if (!p.children.containsKey(key)) {
				return tips;
			}
			p = p.children.get(key);
		}
		
		BFS(p, tips, maxtips);
		
		return tips;
	}
	
	private void BFS(TrieNode<T> root, ArrayList<String> v, int maxsize) {
		
		if (v.size() >= maxsize) {
			return;
		}
		
		if (root.item != null) {
			v.add(root.item.toString());
		}
		
		Iterator<Character> iterator = root.children.keySet().iterator(); 
		while (iterator.hasNext()) { 
			BFS(root.children.get(iterator.next()), v, maxsize);
        } 
	}
	
	
	private TrieNode<T> root;
}
