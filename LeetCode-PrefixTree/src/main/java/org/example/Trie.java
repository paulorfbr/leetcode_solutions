package org.example;

import java.util.ArrayList;
import java.util.List;

public class Trie {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));   // return True
        System.out.println(trie.search("app"));     // return False
        trie.startsWith("app"); // return True
        trie.insert("app");
        System.out.println(trie.search("app"));     // return True
    }

    class TrieNode {
        TrieNode[] childNode;
        boolean wordEnd;
        String value;

        TrieNode()
        {
            childNode = new TrieNode[26];
            wordEnd = false;
        }
    }


    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = this.root;

        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (node.childNode[index] == null) {
                //open new trie for that char
                node.childNode[index] = new TrieNode();
            }
            node = node.childNode[index];
        }
        node.value = word;
        node.wordEnd=true;
    }

    public boolean search(String word) {
        TrieNode currentNode = root; //start root
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (currentNode.childNode[index] == null) {
                //still not insert full word
                return false;
            }
            currentNode = currentNode.childNode[index];
        }
        return currentNode.wordEnd;
    }

    public boolean startsWith(String prefix) {
        if (prefix.length() == 0){
            return false;
        }

        TrieNode currentNode = root; //start
        List<String> results = new ArrayList<>();

        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';
            if (currentNode.childNode[index] == null) {
                return false;
            }
            currentNode = currentNode.childNode[index];
        }

        if (currentNode!=null){
            for (TrieNode t: currentNode.childNode) {
                if (t!=null && t.wordEnd) {
                    results.add(t.value);
                }
            }
        }
        return true;
    }
}