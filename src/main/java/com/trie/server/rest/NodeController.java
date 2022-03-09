package com.trie.server.rest;

import com.trie.server.Trie.dataModel.CharNode;
import com.trie.server.Trie.dataModel.NodeRepository;
import org.springframework.web.bind.annotation.*;


@RestController
public class NodeController {
    private final NodeRepository repository;

    NodeController(NodeRepository repository) {
        this.repository = repository;

    }

    @GetMapping("/find/{word}")
    public boolean contains(@PathVariable String word) {
        CharNode curr = findParent();
        for (int i = 0; i < word.length(); i++) {
            var node = curr.getChildren().get(word.charAt(i));
            if (node == null) {
                return false;
            }
            curr = node;
        }
        return curr.isEnd();
    }

    /**
     * @return a list of all nodes for debug
     * if it fails, return a new parent
     */
    @GetMapping("/debug/nodes")
    CharNode findParent() {
        return repository.findById(1L).isPresent() ? repository.findById(1L).get() : null;

    }

    @DeleteMapping("/delete/{word}")
    public String words(@PathVariable String word) {
        if (!contains(word)) return "Word not found";
        CharNode curr = findParent();
        for (int i = 0; i < word.length(); i++) {
            var node = curr.getChildren().get(word.charAt(i));
            if (node.getShared() == 1 && node.getParent() != null) {
                repository.delete(node);
                System.out.println("deleted node ");
            } else
                //used by others but is the end of the word
                if (node.isEnd() && word.length() - 1 == i) {
                    node.setEnd(false);
                    repository.save(node);
                }
            curr = node;
        }
        System.out.println("deleted");
        return "Deleted";
    }

    @PostMapping("/insert/word")
    String newWord(@RequestBody String word) {
        char[] letters = word.toCharArray();
        CharNode c = getParent();
        insertLetter(c, letters, 0);
        return findParent().toString();
    }

    private void insertLetter(CharNode c, char[] letters, int i) {
        if (letters.length == i) return;
        System.out.println(c);
        CharNode parent;
        if (c.getChildren().containsKey(letters[i])) {
            parent = c.getChildren().get(letters[i]);
            parent.incrementShared();
        } else {
            parent = new CharNode(c, letters[i]);
            c.getChildren().put(letters[i], parent);
            if (i == letters.length - 1) parent.setEnd(true);
        }
        repository.save(parent);
        insertLetter(parent, letters, i + 1);
    }

    @Override
    public String toString() {
        return "NodeController{" +
                "repository=" + repository +
                '}';
    }

    @GetMapping("/getParentNode")
    CharNode getParent() {
        return repository.getById(1L);
    }

}
