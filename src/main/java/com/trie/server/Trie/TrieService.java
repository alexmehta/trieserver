package com.trie.server.Trie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TrieService {
    private final NodeRepository repository;

    public TrieService(NodeRepository repository) {
        this.repository = repository;
    }

    public void insertWord(String word) {
        char[] letters = word.toCharArray();
        CharNode c = getParent();
        insertLetter(c, letters, 0);

    }

    private void insertLetter(CharNode c, char[] letters, int i) {
        if (letters.length == i) return;
        CharNode parent;
        if (c.getChildren().containsKey(letters[i])) {
            parent = c.getChildren().get(letters[i]);
            parent.incrementShared();
        } else {
            parent = new CharNode(c, letters[i]);
            c.getChildren().put(letters[i], parent);
            if (i == letters.length - 1) parent.setWordEnd(true);
        }
        repository.save(parent);
        insertLetter(parent, letters, i + 1);
    }

    public CharNode getParent() {
        return repository.findById(1L).isPresent() ? repository.findById(1L).get() : null;

    }

    public String deleteWord(String word) {
        if (!containsWord(word)) return "Word not found";
        CharNode curr = getParent();
        for (int i = 0; i < word.length(); i++) {
            var node = curr.getChildren().get(word.charAt(i));
            if (node.getSharedWords() == 1 && node.getParent() != null) {
                repository.delete(node);
            } else
                //used by others but is the end of the word
                if (node.isWordEnd() && word.length() - 1 == i) {
                    node.setWordEnd(false);
                    repository.save(node);
                }
            curr = node;
        }
        return "Success";
    }


    public boolean containsWord(String word) {
        CharNode curr = getParent();
        for (int i = 0; i < word.length(); i++) {
            var node = curr.getChildren().get(word.charAt(i));
            if (node == null) {
                return false;
            }
            curr = node;
        }
        return curr.isWordEnd();
    }
}
