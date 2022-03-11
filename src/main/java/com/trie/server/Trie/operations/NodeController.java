package com.trie.server.Trie.operations;

import org.springframework.web.bind.annotation.*;


@RestController()
public class NodeController {

    final TrieService trieService;

    public NodeController(TrieService trieService) {
        this.trieService = trieService;
    }

    @GetMapping("/find/{word}")
    public boolean contains(@PathVariable String word) {
        return trieService.containsWord(word);
    }


    @DeleteMapping("/delete/{word}")
    public String words(@PathVariable String word) {
        return trieService.deleteWord(word);
    }

    @PostMapping("/insert/{word}")
    public void newWord(@PathVariable String word) {
        trieService.insertWord(word);
    }


    @Override
    public String toString() {
        return "NodeController{" +
                "trieService=" + trieService +
                '}';
    }


}
