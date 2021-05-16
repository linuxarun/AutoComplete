import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String... args) throws FileNotFoundException {
        Trie trie = new Trie(new TrieNode());
        FileInputStream fis=new FileInputStream("words.txt");
        Scanner sc=new Scanner(fis);
        while(sc.hasNextLine()) {
            trie.insert(sc.nextLine());
        }
        System.out.println(trie.autoComplete("them"));
    }
}
