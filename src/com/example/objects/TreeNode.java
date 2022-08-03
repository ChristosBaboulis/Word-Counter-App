package com.example.objects;

public class TreeNode {

    private final WordFreq item;
    private TreeNode l;
    private TreeNode r;
    private TreeNode parent;
    private int number;


    public TreeNode(WordFreq item) {
        if (item == null) throw new IllegalArgumentException();
        this.item = item;
        this.l = null;
        this.r = null;
    }

    public WordFreq getItem() {
        return item;
    }

    public TreeNode getL() {
        return l;
    }

    public void setL(TreeNode l) {
        this.l = l;
    }

    public TreeNode getR() {
        return r;
    }

    public void setR(TreeNode r) {
        this.r = r;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber() {
        this.number++;
    }
}
