package com.trie.server.Trie.operations;

import com.trie.server.exceptions.MalformedWordException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class TrieService {
    private final NodeRepository repository;

    public TrieService(NodeRepository repository) {
        this.repository = repository;
    }

    public String insertWord(String word) {
        if (word.isEmpty()) throw new MalformedWordException("You can't insert a blank word into a trie");
        if (!word.matches("([A-Za-z0-9\\-_]+)"))
            throw new MalformedWordException("Words must only contain uppercase and lowercase letters, and _ or -");
        char[] letters = word.toCharArray();
        CharNode c = getParent();
        insertLetter(c, letters, 0);
        return "Inserted " + word;
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
        Optional<CharNode> parent = repository.findById(1L);
        if (!parent.isPresent()) {
            repository.save(new CharNode());
            parent = repository.findById(1L);
        }
        return parent.get();
    }

    public String deleteWord(String word) {
        if (!containsWord(word)) return "Word not found";
        CharNode curr = getParent();
        for (int i = 0; i < word.length(); i++) {
           CharNode node = curr.getChildren().get(word.charAt(i));
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

    public CharNode getLatestHead(String snippit) {
        CharNode curr = getParent();
        for (int i = 0; i < snippit.length(); i++) {
            CharNode node = curr.getChildren().get(snippit.charAt(i));
            if (node == null) {
                return curr;
            }
            curr = node;
        }
        return curr;
    }

    public boolean containsWord(String word) {
        CharNode curr = getParent();
        for (int i = 0; i < word.length(); i++) {
            CharNode node = curr.getChildren().get(word.charAt(i));
            if (node == null) {
                return false;
            }
            curr = node;
        }
        return curr.isWordEnd();
    }
}
