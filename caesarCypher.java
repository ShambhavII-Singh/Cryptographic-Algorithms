import java.util.*;
import java.text.CharacterIterator; 
import java.text.StringCharacterIterator; 

public class Main {

    public static String caesarCypherEncryption(String message, int key) {

        int len = message.length();
        char[] answer = message.toCharArray();
        int pointer = 0;
        CharacterIterator itr = new StringCharacterIterator(message); 

        while (itr.current() != CharacterIterator.DONE) {
            if (itr.current()==' ') {
                answer[pointer] = ' ';
            }
            else {
                int isolated = (int) itr.current() + key;
                if (isolated<=90) {
                    answer[pointer] = (char) isolated;
                }
                else {
                    isolated = (isolated % 91) + 65;
                    answer[pointer] = (char) isolated;
                }
            }
            pointer++;
            itr.next();
        } 

        System.out.print("ENCRYPTED MESSAGE IS : ");
        System.out.println(answer);

        String encrypted = new String(answer);
        return encrypted;

    }

        public static String caesarCypherDecryption(String message, int key) {

        int len = message.length();
        char[] answer = message.toCharArray();
        int pointer = 0;
        CharacterIterator itr = new StringCharacterIterator(message); 

        while (itr.current() != CharacterIterator.DONE) { 
            if (itr.current()==' ') {
                answer[pointer] = ' ';
            }
            else {
                int isolated = (int) itr.current() - key;
                if (isolated>=65) {
                    answer[pointer] = (char) isolated;
                }
                else {
                    isolated = 90 - (64-isolated);
                    answer[pointer] = (char) isolated;
                }
            }
            pointer++;
            itr.next();
        } 

        System.out.print("DECRYPTED MESSAGE IS : ");
        System.out.println(answer);

        String decrypted = new String(answer);
        return decrypted;

    }

        public static void main(String[] args) {

        Scanner cursor = new Scanner(System.in);
        String message = cursor.nextLine();
        int key = cursor.nextInt();

        String encrypted = caesarCypherEncryption(message,key);
        System.out.println("---------------------------");
        String decrypted = caesarCypherDecryption(encrypted,key);

    }
  
}
