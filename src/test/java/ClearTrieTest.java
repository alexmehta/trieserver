import com.trie.server.Trie.operations.TrieService;
import com.trie.server.Trie.prediction.PredictionService;
import com.trie.server.TrieServerApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = TrieServerApplication.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClearTrieTest {

    private final static int TESTSIZE = 20;
    ArrayList<String> words;
    @Autowired
    private TrieService trieService;
    @Autowired
    private PredictionService predictionService;

    //this method will create an array with words incrementing by one character. For example, words[0] = 'a' and words[1]="ab" and words[2]= "abc"
    @Before
    public void setUp() throws Exception {
        words = new ArrayList<>();
        for (int z = 0; z < TESTSIZE; z++) {
            StringBuilder name = new StringBuilder();
            for (int i = 0; i < 1 + Math.random() * 10; i++) {
                char rand = (char) (new Random().nextInt(26) + 'A');
                name.append(rand);
            }
            words.add(name.toString());
        }
        for (String word : words) {
            trieService.insertWord(word);
        }
    }

    @Test
    public void testClear() {
        trieService.clearParent();
        for (String word : words) {
            assert !trieService.containsWord(word);
        }
    }
}
