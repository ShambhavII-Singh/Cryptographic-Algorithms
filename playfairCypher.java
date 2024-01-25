import java.util.*;
import java.text.CharacterIterator; 
import java.text.StringCharacterIterator; 

public class Main {
    
    public static ArrayList<ArrayList<Character>> playfairMatrixGenerator(String keyword) {
        
        ArrayList<ArrayList<Character>> matrix = new ArrayList<ArrayList<Character>>();
        ArrayList<Character> sequence = new ArrayList<Character>();
        CharacterIterator itr = new StringCharacterIterator(keyword);
        int pointer = 0;
        
        while (itr.current() != CharacterIterator.DONE) {
            sequence.add(itr.current());
            itr.next();
        }
        
        for (int i = 65; i < 91; i++) {
            if (sequence.contains((char) i)==false && i!=74) {
                sequence.add((char) i);
            }
        }
        
        System.out.println("Matrix created: ");
        Iterator seq = sequence.iterator();  
        for (int row = 0; row < 5; row++) {
            ArrayList<Character> temp = new ArrayList<Character>();
            for (int column = 0; column < 5; column++) {
                temp.add((char) seq.next());
            }
            matrix.add(temp);
            System.out.println("|" + temp + "|");
        }
        
        System.out.println();
        return matrix;
        
    }

    public static ArrayList<ArrayList<Character>> playfairDigraphGenerator(String plaintext) {
        
        plaintext = plaintext.replaceAll("\\s","");
        int remaining = plaintext.length();
        ArrayList<ArrayList<Character>> diagraphs = new ArrayList<ArrayList<Character>>();
        CharacterIterator itr = new StringCharacterIterator(plaintext); 
        
        while (itr.current() != CharacterIterator.DONE) {
            ArrayList<Character> temp = new ArrayList<Character>();
            char first = itr.current();
            char second = itr.next();
            
            if (first == 'J') {
                first = 'I';
            } 
            if (second == 'J') {
                second = 'I';
            }
            
            if ((int) second == 65535) {
                temp.add(first);
                temp.add('Z');
                diagraphs.add(temp);
            }
            else {
                if (first != second) {
                    temp.add(first);
                    temp.add(second);
                    diagraphs.add(temp);
                }
                else if (first == second) {
                    temp.add(first);
                    temp.add('Z');
                    diagraphs.add(temp);
                    
                    temp.clear();
                    
                    temp.add(second);
                    temp.add('Z');
                    diagraphs.add(temp);
                }
            }
            
            
            itr.next();
        }
        
        System.out.println("Digraphs created: ");
        System.out.println(diagraphs);
        System.out.println();
        return diagraphs;
    }

        public static ArrayList<ArrayList<Character>> playfairCypherEncryption(String plaintext, String keyword) {
        
        ArrayList<ArrayList<Character>> matrix = playfairMatrixGenerator(keyword);
        ArrayList<ArrayList<Character>> digraphs = playfairDigraphGenerator(plaintext);
        ArrayList<ArrayList<Character>> encryptedDigraphs = new ArrayList<ArrayList<Character>>();
        
        for (ArrayList<Character> digraph : digraphs) {
            int firstRow = 0;
            int secondRow = 0;
            int firstCol = 0;
            int secondCol = 0;
            ArrayList<Character> temp = new ArrayList<Character>();
            
            for (int i = 0; i < 5; i++) {
                if (matrix.get(i).contains(digraph.get(0))) {
                    firstRow = i;
                    firstCol = matrix.get(i).indexOf(digraph.get(0));
                }
                if (matrix.get(i).contains(digraph.get(1))) {
                    secondRow = i;
                    secondCol = matrix.get(i).indexOf(digraph.get(1));
                }
            }
            
            if (firstRow == secondRow) {
                temp.add(matrix.get(firstRow).get((firstCol+1)%5));
                temp.add(matrix.get(firstRow).get((secondCol+1)%5));
            }
            else if (firstCol == secondCol) {
                temp.add(matrix.get(firstRow).get((firstCol+1)%5));
                temp.add(matrix.get(secondRow).get((firstCol+1)%5));
            }
            else {
                if (firstCol > secondCol) {
                    int dimention = firstCol - secondCol;
                    temp.add(matrix.get(firstRow).get((firstCol-dimention)%5));
                    temp.add(matrix.get(secondRow).get((secondCol+dimention)%5));
                }
                else {
                    int dimention = secondCol - firstCol;
                    temp.add(matrix.get(firstRow).get((firstCol+dimention)%5));
                    temp.add(matrix.get(secondRow).get((secondCol-dimention)%5));
                }
            }
            encryptedDigraphs.add(temp);
        }
        
        System.out.println("Encrypted Digraphs: ");
        System.out.println(encryptedDigraphs);
        System.out.println();
        return encryptedDigraphs;
        
    }
    
}
