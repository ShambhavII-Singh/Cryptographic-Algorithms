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
}
