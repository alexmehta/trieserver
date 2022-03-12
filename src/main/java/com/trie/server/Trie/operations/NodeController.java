package com.trie.server.Trie.operations;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController()
@Tag(name = "Trie Node", description = "Trie Node API. Allows for create, read, and delete operations on the Trie")
public class NodeController {

    final TrieService trieService;

    public NodeController(TrieService trieService) {
        this.trieService = trieService;
    }

    @Operation(summary = "Find if a word is in the trie",
            description = "Returns a boolean that describes if a word is represented in the trie", tags = "read trie"
    )
    @GetMapping("/find/{word}")
    public boolean contains(@PathVariable String word) {
        return trieService.containsWord(word);
    }

    @Operation(summary = "print out global trie words list (display trie)", description = "display the trie in list format", tags = "read trie", responses = {@ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json"))})
    @GetMapping("/state")
    public List<String> printTrie() {
        return trieService.getGlobalState();
    }

    @Operation(summary = "Delete a word", description = "Deletes a word from the trie if it exists",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json"))
                    , @ApiResponse(description = "Word not found", responseCode = "404")
            }
    )
    @DeleteMapping("/delete/{word}")
    public String words(@PathVariable String word) {
        return trieService.deleteWord(word);
    }

    @PostMapping("/insert/{word}")
    public String newWord(@PathVariable String word) {
        return trieService.insertWord(word);
    }

    @DeleteMapping("/clear")
    @Operation(summary = "clear the trie", description = "clear the head of the tries children")
    public void clearTrie() {
        trieService.clearParent();
    }

    @Override
    public String toString() {
        return "NodeController{" +
                "trieService=" + trieService +
                '}';
    }


}
