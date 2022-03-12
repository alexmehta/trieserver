package com.trie.server.Trie.prediction;

import com.trie.server.Trie.operations.CharNode;
import com.trie.server.Trie.operations.TrieService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class PredictionService {
    TrieService trieService;

    public PredictionService(TrieService trieService) {
        this.trieService = trieService;
    }

    public List<String> predictions(String snippet) {
        CharNode getFarthestNode = trieService.getLatestHead(snippet);
        List<String> possiblities = getPossibilities(snippet, getFarthestNode);
        if (trieService.containsWord(snippet)) possiblities.add(snippet);
        return possiblities;
    }

    private List<String> getPossibilities(String snippet, CharNode getFarthestNode) {
        ArrayList<String> words = new ArrayList<>();
        generateWords(getFarthestNode, words, String.valueOf(getFarthestNode.getNodeChar()));
        cleanWords(snippet, words);
        return words;
    }

    private void generateWords(CharNode getFarthestNode, ArrayList<String> words, String word) {
        if (getFarthestNode == null) return;
        for (CharNode character : getFarthestNode.getChildren().values()) {
            word += character.getNodeChar();
            if (character.isWordEnd()) words.add(word);
            generateWords(character, words, word);
            word = word.substring(0, word.length() - 1);
        }
    }

    private void cleanWords(String hint, ArrayList<String> words) {
        IntStream.range(0, words.size())
                .filter(i ->
                        words.get(i).length() != 0)
                .forEach(
                        i ->
                                words.set(i, hint + words.get(i).substring(1))
                );
    }
}
