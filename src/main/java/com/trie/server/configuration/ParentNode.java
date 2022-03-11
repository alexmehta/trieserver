package com.trie.server.configuration;

import com.trie.server.Trie.operations.CharNode;
import com.trie.server.Trie.operations.NodeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParentNode {

    private static final Logger log = LoggerFactory.getLogger(ParentNode.class);

    @Bean
    CommandLineRunner initDatabase(NodeRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new CharNode()));
        };
    }
}
