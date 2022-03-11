package com.trie.server.Trie;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "TrieNode")
public class CharNode {
    //character stores the current character
    @Column
    Character nodeChar;
    //many to one association here will store the parent. This is because a parent can have multiple children
    @ManyToOne(fetch = FetchType.EAGER)
    CharNode parent;
    //one to many, as there are many children associated with this one parent
    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Map<Character, CharNode> children;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name = "wordend")
    private boolean wordEnd = false;
    @Column(name = "sharedwords")
    private int sharedWords = 1;

    //inserting an element that has a parent and char value
    public CharNode(CharNode parent, Character character) {
        this.nodeChar = character;
        this.children = new HashMap<>();
        this.parent = parent;

    }

    //if it is at top of a tree then we don't have a parent (null)
    public CharNode() {

        this.nodeChar = null;
        this.children = new HashMap<>();
        this.parent = null;

    }

    //remove the parent
    @PreRemove
    public void preRemove() {
        parent = null;
    }

    public int getSharedWords() {
        return sharedWords;
    }

    public void incrementShared() {
        sharedWords++;
    }

    public void decrementShared() {
        sharedWords--;
    }

    public boolean isWordEnd() {
        return wordEnd;
    }

    public void setWordEnd(boolean end) {
        this.wordEnd = end;
    }

    public Map<Character, CharNode> getChildren() {
        return children;
    }

    public void setChildren(Map<Character, CharNode> children) {
        this.children = children;
    }

    public void setChildren(HashMap<Character, CharNode> children) {
        this.children = children;
    }

    public CharNode getParent() {
        return parent;
    }

    public void setParent(CharNode parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "CharNode{" +
                "character=" + nodeChar +
                ", parent=" + parent +
                ", id=" + id +
                ", end=" + wordEnd +
                '}';
    }

    public Character getNodeChar() {
        return nodeChar;
    }

    public void setNodeChar(Character character) {
        this.nodeChar = character;
    }

}