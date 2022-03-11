package com.trie.server.Trie;

import com.trie.server.Trie.CharNode;
import com.trie.server.Trie.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class NodeController {

    final TrieService trieService;

    public NodeController(TrieService trieService) {
        this.trieService = trieService;
    }

    @GetMapping("/find/{word}")
    public boolean contains(@PathVariable String word) {
        return trieService.containsWord(word);
    }

    /**
     * @return a list of all nodes for debug
     * if it fails, return a new parent
     */
    @GetMapping("/debug/nodes")
    public CharNode findParent() {
        return trieService.getParent();
    }

    @DeleteMapping("/delete/{word}")
    public String words(@PathVariable String word) {
        return trieService.deleteWord(word);
    }

    @PostMapping("/insert/word")
    public void newWord(@RequestBody String word) {
       trieService.insertWord(word);
    }


    @Override
    public String toString() {
        return "NodeController{" +
                "trieService=" + trieService +
                '}';
    }



}
