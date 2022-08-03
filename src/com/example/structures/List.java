package com.example.structures;

import com.example.objects.ListNode;
import java.util.NoSuchElementException;

public class List {
    private ListNode firstNode;
    private final String name;

    public List()
    {
        this("list");
    }

    public List( String listName)
    {
        name = listName;
        firstNode = null;
    }

    public void insertAtFront( String insertItem )
    {
        ListNode node = new ListNode( insertItem.toLowerCase() );
        if ( !isEmpty() ) {
            node.setNextNode(firstNode);
        }
        firstNode = node;
    }

    public int compare(String w){
        ListNode curr = firstNode;
        boolean flag = false;

        //while there is a record in the list check if it matches the string input, if it does make flag true and stop, else go to the next record
        while(curr != null){
            if (curr.getWord().compareTo(w) == 0){  //input word exists in list
                flag = true;
                break;
            }
            curr = curr.getNext();
        }

        //if flag is true return 1
        if(flag){
            return 1;
        }
        else{
            return -1;
        }
    }

    public void removeByName(String w) throws NoSuchElementException{
        if ( isEmpty() )
            throw new NoSuchElementException(name);

        ListNode curr = firstNode;
        ListNode prev = null;

        // if name equals input name remove record from list and connect its previous to its next one
        while (curr != null) {
            if (curr.getWord().compareTo(w.toLowerCase()) == 0) {
                if (prev != null) {
                    prev.setNextNode(curr.getNext());
                } else {
                    firstNode = curr.getNext();
                }
                break;
            }
            prev = curr;
            curr = curr.getNext();
        }

    }

    public int size() {
        if (isEmpty()) { return 0; }

        ListNode current = firstNode;
        int i = 0;
        while ( current != null )
        {
            i++;
            current = current.getNext();
        }
        return i;
    }


    public boolean isEmpty()
    {
        return firstNode == null;
    }

    public void print()
    {
        if ( isEmpty() )
        {
            System.out.printf( "Empty %s\n", name );
            return;
        }

        System.out.printf( "The %s is: ", name );
        ListNode current = firstNode;

        while ( current != null )
        {
            System.out.printf( "%s ", current.getWord() );
            current = current.getNext();
        }

        System.out.println( "\n" );
    }
}
