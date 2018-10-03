import java.util.Scanner;

public class DictionaryManagerment {

    Dictionary dict = new Dictionary();

    public static void insertFromCommandline(){
        Word newWord = new Word();
        Scanner sc = new Scanner(System.in);
        System.out.println("Word_Target: ");
        newWord.setWord_target(sc.nextLine());
        System.out.println("Word_Explane: ");
        newWord.setWord_explain(sc.nextLine());
    }
}
