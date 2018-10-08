import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class DictionaryCommandline {
    DictionaryManagerment dictManagerment = new DictionaryManagerment();

    // Show all words
    public void showAllWords () {
        System.out.println();
        System.out.println("No   || English          || Vietnamese         ");
        System.out.println("===============================================");
        for (Word w:dictManagerment.dict.wordsList){
            System.out.printf("%6d %-18s %-20s \n", dictManagerment.dict.wordsList.indexOf(w),w.getWord_target(), w.getWord_explain());
        }
    }

    public void dictionaryBasic() {
        System.out.println("Dictionary Ver 1");
        dictManagerment.importWordsFromFile();
        int opt = -1;
        Scanner opts = new Scanner(System.in);
        while (opt !=0) {
            System.out.println("Choose option: ");
            System.out.println("1. Add New Word From Commandline");
            System.out.println("2. Show All Words");
            System.out.println("3. Save changes");
            System.out.println("0.Exit");
            opt = opts.nextInt();
            switch (opt) {
                case 1: {
                    dictManagerment.insertFromCommandline();
                    break;
                }
                case 2: {
                    showAllWords();
                    break;
                }
                case 3: {
                    dictManagerment.exportWordsToFile();
                }
                default: break;
            }
        }
    }



}
