package com.trie.server.Trie.prediction;

import io.swagger.annotations.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Predictor", description = "API to get predictions based on the Trie from a snippet of a word")
@RestController("/api/v2")
public class PredictionController {
    private final PredictionService predictionService;

    public PredictionController(PredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @Operation(summary = "Predict possible words",
            description = "Predict possible words from the trie, given a certain part of a word",
            tags = "prediction"
    )
    @GetMapping("/predict/{word}")
    public List<String> words(@PathVariable String word) {
        return predictionService.predictions(word);
    }
}
