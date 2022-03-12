import com.trie.server.Trie.operations.TrieService;
import com.trie.server.TrieServerApplication;
import com.trie.server.exceptions.WordNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;


@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = TrieServerApplication.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InsertRemoveTest {
    String[] words;
    @Autowired
    private TrieService trieService;

    public InsertRemoveTest() {
        words = new String[20];
        for (int z = 0; z < words.length; z++) {
            StringBuilder name = new StringBuilder();
            for (int i = 0; i < 1 + Math.random() * 10; i++) {
                char rand = (char) (new Random().nextInt(26) + 'A');
                name.append(rand);
            }
            words[z] = name.toString();
        }
    }


    @Test
    @Order(2)
    public void removeFromTrie() {
        for (String word : words) {
            try {
                trieService.deleteWord(word);
            } catch (WordNotFoundException wordNotFoundException) {
                wordNotFoundException.getStackTrace();
            }
            assert !trieService.containsWord(word);
        }
    }

    @Test
    @Order(1)
    public void insertIntoTrie() {
        for (String word : words) {
            trieService.insertWord(word);
            assert trieService.containsWord(word);
        }
    }
}
