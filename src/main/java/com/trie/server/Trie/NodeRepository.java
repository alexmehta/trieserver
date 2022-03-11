package com.trie.server.Trie;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NodeRepository extends JpaRepository<CharNode, Long> {
}
