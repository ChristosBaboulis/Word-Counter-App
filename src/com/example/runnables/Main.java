package com.example.runnables;

import com.example.utilities.ST;
import com.example.objects.WordFreq;

public class Main {
    public static void main(String[] args) {
        ST tree = new ST();
		ST tree2 = new ST();

        //TEST load()
        tree.load("test.txt");

        //TEST remove
        tree.remove("you");

        //TEST printAlphabetically()
        tree.printAlphabetically(System.out);

        //TEST search()
        WordFreq searched;
        searched = tree.search("have");
        System.out.println("Searched Word: "+searched.toString());

        //TEST getDistinctWords()
        int n = tree.getDistinctWords();
        System.out.println("size: "+n);

        //TEST getTotalWords()
        int n2 = tree.getTotalWords();
        System.out.println("total words: "+n2);

        //TEST printByFrequency()
        //tree.printByFrequency(System.out);


        //TEST getMaximumFrequency()
        WordFreq mf = tree.getMaximumFrequency();
        System.out.println("Max Frequency Of Tree Items: "+mf);

        //TEST getMeanFrequency()
        double n6 = tree.getMeanFrequency();
        System.out.println("Mean Frequency Of Tree Items: "+n6);

        //TEST getFrequency()
        int n3 = tree.getFrequency("you");
        int n4 = tree.getFrequency("hello");
        int n5 = tree.getFrequency("nonono");
        System.out.println("frequency of you: "+n3+"\nfrequency of hello: "+n4+"\nfrequency of nonono: "+n5+"\n");



		//SECOND TREE TEST



		//TEST load()
        tree2.load("test2.txt");

        //TEST remove
        tree2.remove("if");

        //TEST printAlphabetically()
        tree2.printAlphabetically(System.out);

        //TEST search()
        WordFreq searched2;
        searched2 = tree2.search("so");
        System.out.println("Searched Word: "+searched2.toString());

        //TEST getDistinctWords()
        int m = tree2.getDistinctWords();
        System.out.println("size: "+m);

        //TEST getTotalWords()
        int m2 = tree2.getTotalWords();
        System.out.println("total words: "+m2);

        //TEST getMaximumFrequency()
        WordFreq mf2 = tree2.getMaximumFrequency();
        System.out.println("Max Frequency Of Tree Items: "+mf2);

        //TEST getMeanFrequency()
        double m6 = tree2.getMeanFrequency();
        System.out.println("Mean Frequency Of Tree Items: "+m6);

        //TEST getFrequency()
        int m3 = tree2.getFrequency("this");
        int m4 = tree2.getFrequency("so");
        int m5 = tree2.getFrequency("nonono");
        System.out.println("frequency of this: "+m3+"\nfrequency of so: "+m4+"\nfrequency of nonono: "+m5);
    }
}
