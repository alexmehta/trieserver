package com.trie.server.Trie.operations;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NodeRepository extends JpaRepository<CharNode, Long> {
}
