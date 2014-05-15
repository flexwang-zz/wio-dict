package core.trie;

import java.util.Map;
import java.util.TreeMap;

public class TrieNode<T> {
	public T item;
	public Map<Character, TrieNode<T>> children;
	
	public TrieNode() {
		item = null;
		children = new TreeMap<Character, TrieNode<T>>();
	}
}
