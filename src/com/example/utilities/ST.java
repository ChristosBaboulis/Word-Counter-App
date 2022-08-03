package com.example.utilities;

import com.example.objects.TreeNode;
import com.example.structures.List;
import com.example.objects.WordFreq;
import java.io.*;

public class ST{

    private TreeNode head;
    private List stopWords = new List();
    int max = 0;
    WordFreq word;
    double maxFreq = 0;
    double meanFreq = 0;
	int fc = 0;

    //search()
    private WordFreq searchR(TreeNode h, String w) {
        if (h == null) return null;
        if (w.compareTo(h.getItem().key()) == 0){
            if(h.getItem().getFreq() > getMeanFrequency()) {
                remove(h.getItem().key());
                insertAtRoot(h.getItem());
            }
            return h.getItem();
        }
        if (w.compareTo(h.getItem().key()) < 0) return searchR(h.getL(), w);
        else return searchR(h.getR(), w);
    }

    public WordFreq search(String w) { return searchR(head, w); }

    private TreeNode insertT(TreeNode h, WordFreq x) {
        if (h == null) return new TreeNode(x);
        if (x.key().compareTo(h.getItem().key()) < 0) {
            h.setL(insertT(h.getL(), x));
            h = rotR(h);
        }
        else {
            h.setR(insertT(h.getR(), x));
            h = rotL(h);
        }
        return h;
    }

    public void insertAtRoot(WordFreq x) {
        head = insertT(head, x);
    }
    //search()

    //insert()
    void insert(WordFreq item){
        if (item == null){
            throw new IllegalArgumentException();
        }

        if (getDistinctWords() == 0){
            head = new TreeNode(item);
        }
        else {
            TreeNode n = head;
            TreeNode p = null;
            int result = 0;

            while (n != null) {
                result = item.key().toLowerCase().compareTo(n.getItem().key().toLowerCase());

                if (result == 0) {
                    p = n;
                    break;
                }

                p = n;
                n = result < 0 ? n.getL() : n.getR();
            }

            TreeNode node = new TreeNode(item);
            node.setParent(p);

            if (result < 0) {
                p.setL(node);

                while (p != head) {
                    p.setNumber();
                    p = p.getParent();
                }

                head.setNumber();
            }
            else if (result > 0) {
                p.setR(node);

                while (p != head) {
                    p.setNumber();
                    p = p.getParent();
                }

                head.setNumber();
            }
            else {
                node = null;
                node.setParent(null);
            }
        }
    }
    //insert()

    //update
    void update(String w){

        if (getDistinctWords() == 0){
            WordFreq newWord = new WordFreq(w);
            insert(newWord);
        }

        TreeNode n = head;
        int result = 0;
        while(n != null){
            result = w.toLowerCase().compareTo(n.getItem().key().toLowerCase());

            if(result == 0){
                n.getItem().incFreq();
                break;
            }
            n = result < 0 ? n.getL() : n.getR();
        }
        if(result != 0){
            WordFreq newWord = new WordFreq(w);
            newWord.incFreq();
            insert(newWord);
        }
    }
    //update()

    //remove()
    TreeNode partR(TreeNode h, int k) {
        int t = (h.getL() == null) ? 0 : h.getL().getNumber();
        if (t > k) {
            h.setL(partR(h.getL(), k));
            h = rotR(h);
        }
        if (t < k) {
            h.setR(partR(h.getR(), k-t-1));
            h = rotL(h); }
        return h;
    }

    private TreeNode rotR(TreeNode h) {
        TreeNode x = h.getL();
        h.setL(x.getR());
        x.setR(h);
        return x;
    }

    private TreeNode rotL(TreeNode h) {
        TreeNode x = h.getR();
        h.setR(x.getL());
        x.setL(h);
        return x;
    }

    public void remove(String w) {
        removeR(head, w);
    }

    private TreeNode removeR(TreeNode h, String w) {
        if (h == null) return null;
        String v = h.getItem().key();
        if (w.compareTo(v) < 0) h.setL(removeR(h.getL(), w));
        if (w.compareTo(v) > 0) h.setR(removeR(h.getR(), w));
        if (w.compareTo(v) == 0) h = joinLR(h.getL(), h.getR());
        return h;
    }

    private TreeNode joinLR(TreeNode a, TreeNode b) {
        if (b == null) return a;
        b = partR(b, 0);
        b.setL(a);
        return b;
    }
    //remove()

    //load()
    public void load(String fileName) {
        String line;
        String newLine;
        File f;
        FileReader fr;
        BufferedReader br = null;
        String word;


        try {
            f = new File(fileName);
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            line = br.readLine();

            int indexFirstSpace;
            int indexSecondSpace;

            while (line != null) {
                newLine = line.replaceAll("\\p{Punct}", "").toLowerCase();
                newLine = newLine.replaceAll("\\w*\\d\\w* *","").trim();
                indexFirstSpace = newLine.indexOf(" ");
                indexSecondSpace = 0;
                while(indexFirstSpace > 0){
                    word = newLine.substring(indexSecondSpace, indexFirstSpace).trim();
                    if(stopWords.compare(word) < 0) {
						update(word);
                    }
                    indexSecondSpace = indexFirstSpace + 1;
                    indexFirstSpace = newLine.indexOf(" ", indexFirstSpace + 1);
                }
                word = newLine.substring(indexSecondSpace).trim();
				if(stopWords.compare(word) < 0) {
                    update(word);
                }
                line = br.readLine();
            }
        }
        catch(IOException e){
                System.err.println("Error opening file!");
        }

        try {
            assert br != null;
            br.close();
        } catch (IOException e) {
                System.err.println("Error closing file.");
        }
    }
    //load()

    //getTotalWords()
    public int getTotalWords(){
            fc = 0;
			return getTotalWordsR(head);
    }
	
	int getTotalWordsR(TreeNode h){
        if(h == null) return 0;
        int c = h.getItem().getFreq();
        fc += c;
        c = getTotalWordsR(h.getL());
        c = getTotalWordsR(h.getR());
        return fc;
    }
    //getTotalWords()

    //getDistinctWords()
    int countR(TreeNode h){
        if(h == null) return 0;
        return 1 + countR(h.getL()) + countR(h.getR());
    }

    public int getDistinctWords(){
        return countR(head);
    }
    //getDistinctWords()

    //getFrequency()
    public int getFrequency(String w){
        TreeNode n = head;
        int result = 0;
        int frequency = 0;

        while(n != null){
            result = w.toLowerCase().compareTo(n.getItem().key().toLowerCase());

            if(result == 0){
                frequency = n.getItem().getFreq();
                break;
            }

            n = result < 0 ? n.getL() : n.getR();
        }

        if(result != 0){
            return 0;
        }
        else{
            return frequency;
        }
    }
    //getFrequency()

    //getMaximumFrequency
    public WordFreq getMaximumFrequency(){
		max = 0;
        return getMaximumFrequencyR(head);
    }

    WordFreq getMaximumFrequencyR(TreeNode h){
        if(h == null) return null;
        WordFreq f = h.getItem();
        int iFreq = f.getFreq();
        if (iFreq > max) {
            max = iFreq;
            word = h.getItem();
        }
        f = getMaximumFrequencyR(h.getL());
        f = getMaximumFrequencyR(h.getR());
        return word;
    }
    //getMaximumFrequency

    //getMeanFrequency()
    public double getMeanFrequency(){
        maxFreq = 0;
        meanFreq = getMeanFrequencyR(head);

        meanFreq = meanFreq / getDistinctWords();
        return meanFreq;
    }

    double getMeanFrequencyR(TreeNode h){
        if(h == null) return 0;
        double f = h.getItem().getFreq();
        maxFreq += f;
        f = getMeanFrequencyR(h.getL());
        f = getMeanFrequencyR(h.getR());
        return maxFreq;
    }
    //getMeanFrequency()

    //addStopWord()
    void addStopWord(String w){
        stopWords.insertAtFront(w);
    }
    //addStopWord()

    //removeStopWord()
    void removeStopWord(String w){
        stopWords.removeByName(w);
    }
    //removeStopWord()

    //printAlphabetically()
    private String toStringR(TreeNode h){
        if(h == null) return "";
        String s = toStringR(h.getL());
        s += h.getItem().toString()+"\n";
        s += toStringR(h.getR());
        return s;
    }

    public void printAlphabetically(PrintStream stream){
        stream.println(toStringR(head));
        stream.flush();
    }
    //printAlphabetically()
}

