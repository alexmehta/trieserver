package com.trie.server.Trie.prediction;

import com.trie.server.Trie.operations.CharNode;
import com.trie.server.Trie.operations.TrieService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Service
public class PredictionService {
    TrieService trieService;

    public PredictionService(TrieService trieService) {
        this.trieService = trieService;
    }

    public List<String> predictions(String snippet) {
        CharNode getFarthestNode = trieService.getLatestHead(snippet);
        return getPossibilities(getFarthestNode, "");

    }

    private List<String> getPossibilities(CharNode getFarthestNode, String curr) {
        ArrayList<String> words = new ArrayList<>();
        //dfs through all of the possible options
        Map<Character, CharNode> adjacent = getFarthestNode.getChildren();
        if (getFarthestNode.isWordEnd()) {
            words.add(curr + getFarthestNode.getNodeChar());
        }
        for (Character character : adjacent.keySet()) {
            words.addAll(getPossibilities(adjacent.get(character), curr + getFarthestNode.getNodeChar()));
        }
        //remove the first letter
        cleanWords(words);
        return words;
    }

    private void cleanWords(ArrayList<String> words) {
        IntStream.range(0, words.size()).filter(i -> words.get(i).length() != 0).forEachOrdered(i -> words.set(i, words.get(i).substring(1)));
    }
}
