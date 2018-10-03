import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class DictionaryCommandline {
    Dictionary dict = new Dictionary();

    // Show all words in alphabet order
    public void showAllWords () {
        // Sort
        Collections.sort(dict.wordsList, new Comparator<Word>() {
            public int compare(Word w1, Word w2) {
                return (w1.getWord_target().compareTo(w2.getWord_target()));
            }
        });

        // Print out
        System.out.println();
        System.out.println("No   || English          || Vietnamese         ");
        System.out.println("===============================================");
        for (Word w:dict.wordsList){
            System.out.printf("%5d %-18s %-20s \n", dict.wordsList.indexOf(w),w.getWord_target(), w.getWord_explain());
        }
    }

    public void dictionaryBasic() {
        System.out.println("Dictionary Ver 1");

        int opt = -1;
        Scanner opts = new Scanner(System.in);
        while (opt !=0) {
            System.out.println("Choose option: ");
            System.out.println("1. insertFromCommandLine.");
            System.out.println("2. showAllWords");
            System.out.println("0.Exit");
            opt = opts.nextInt();
            switch (opt) {
                case 1: {
                    DictionaryManagerment.insertFromCommandline();
                    break;
                }
                case 2: {
                    showAllWords();
                    break;
                }
                case 3: {
                    opt=0;
                    System.out.println("Exiting!!!");
                    break;
                }
            }
        }
    }



}
