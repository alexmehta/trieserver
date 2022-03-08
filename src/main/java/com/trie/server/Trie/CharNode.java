package com.trie.server;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "char_node")
public class CharNode {
    //character stores the current character
    Character character;
    //many to one association here will store the parent. This is because a parent can have multiple children
    @ManyToOne(fetch = FetchType.EAGER)
    CharNode parent;
    //one to many, as there are many children associated with this one parent
    @OneToMany(fetch = FetchType.LAZY)
    List<CharNode> children;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    //inserting an element that has a parent and char value
    public CharNode(CharNode parent, Character character) {
        this.character = character;
        this.children = new ArrayList<>();
        this.parent = parent;

    }

    //if it is at top of a tree then we don't have a parent (null)
    public CharNode(Character character) {
        this.character = character;
        this.children = new ArrayList<>();
        this.parent = null;

    }

    //all element constructor
    public CharNode(Character character, CharNode parent, List<CharNode> children, UUID id) {
        this.character = character;
        this.parent = parent;
        this.children = children;
        this.id = id;
    }

    //required empty constructior
    public CharNode() {
    }

    public CharNode getParent() {
        return parent;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}