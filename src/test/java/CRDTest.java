import com.trie.server.TrieServerApplication;
import com.trie.server.rest.NodeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

@SpringBootTest(classes = TrieServerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CRDTest {
    @Autowired
    NodeController nodeController;
    HashSet<String> words = new HashSet<>();


    @Test
    public void insertWords() {
        try {
            loadWords();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("testing with " + words.size() + " words.");
        for (String word : words) {
            nodeController.newWord(word);
        }
        testWords();
    }

    @Test
    public void testWords() {
        for (String word : words) {
            assert nodeController.contains(word);
        }
    }

    public void loadWords() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("src/test/resources/words.txt")));
        String s = br.readLine();
        while (s != null) {
            words.add(s);
            s = br.readLine();
        }
    }


}
