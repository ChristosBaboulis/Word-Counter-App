package com.example.objects;

public class ListNode {
    private final String data;
    private ListNode nextNode;

    public ListNode( String word )
    {
        this( word, null );
    }

    public ListNode( String word, ListNode node )
    {
        data = word;
        nextNode = node;
    }

    public String getWord(){
        return data;
    }

    public ListNode getNext(){ return nextNode; }

    public void setNextNode(ListNode node) { nextNode = node; }
}
