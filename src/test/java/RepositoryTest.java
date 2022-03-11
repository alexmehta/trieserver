import com.trie.server.Trie.NodeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RepositoryTest {
   @Autowired private NodeRepository nodeRepository;
   private String node;

   @BeforeEach
    public void setup(){
   }
}
