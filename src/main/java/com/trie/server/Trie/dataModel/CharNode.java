package com.trie.server.Trie.dataModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "char_node")
public class CharNode {
    //character stores the current character
    Character character;
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
    @Column(name = "end")
    private boolean end = false;
    @Column(name = "shared_words")
    private int shared = 1;

    //inserting an element that has a parent and char value
    public CharNode(CharNode parent, Character character) {
        this.character = character;
        this.children = new HashMap<>();
        this.parent = parent;

    }

    //if it is at top of a tree then we don't have a parent (null)
    public CharNode() {
        this.character = null;
        this.children = new HashMap<>();
        this.parent = null;

    }
    //remove the parent
    @PreRemove
    public void preRemove() {
        parent = null;
    }

    public int getShared() {
        return shared;
    }

    public void incrementShared() {
        shared++;
    }

    public void decrementShared() {
        shared--;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
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
                "character=" + character +
                ", parent=" + parent +
                ", id=" + id +
                ", end=" + end +
                '}';
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

}