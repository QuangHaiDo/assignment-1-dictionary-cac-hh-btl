import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class DictionaryManagerment {

    Dictionary dict = new Dictionary();

    public void insertFromCommandline(){
        Word newWord = new Word();
        Scanner sc = new Scanner(System.in);
        System.out.println("Word_Target: ");
        newWord.setWord_target(sc.nextLine());
        System.out.println("Word_Explane: ");
        newWord.setWord_explain(sc.nextLine());
        addWord(newWord);
    }

    public void addWord(Word w){
        this.dict.wordsList.add(w);
    }

    public void addWord(String target,String explain){
        Word addNewWord = new Word(target,explain);
        this.dict.wordsList.add(addNewWord);
    }

    // Delete by pos
    public void deleteWord(int pos){
        try{
            this.dict.wordsList.remove(pos);
        } catch (Exception e) {
            System.out.println("Word isn't exist!");
        }
    }
    // Delete by word_target
    public void deleteWord(Word w){
        try{
        int pos = this.dict.wordsList.indexOf(w);
        this.dict.wordsList.remove(pos);
        } catch (Exception e) {
            System.out.println("Word isn't exist!");
        }
    }
    public void exportWordsToFile()
    {
        File f = new File("database/DictEV-Modified.dic");
        try {
            BufferedWriter fileOut = new BufferedWriter(new FileWriter(f));
            for (Word w:this.dict.wordsList) {
                fileOut.write(w.getWord_target() +"="+w.getWord_explain());
                fileOut.newLine();
                fileOut.flush();
            }
            fileOut.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void importWordsFromFile(){
        File f = new File("database/dictEV-Modified.dict");
        try{
            Scanner sc = new Scanner(f);
                try {
                    while (sc.hasNextLine()) {
                        String[] str = sc.nextLine().split("=");
                        addWord(str[0],str[1]);
                    }
                } catch (Exception e){
                    System.out.println("No database found!!");
                }
                finally
                {
                    Collections.sort(dict.wordsList, new Comparator<Word>() {
                        public int compare(Word w1, Word w2) {
                            return (w1.getWord_target().compareTo(w2.getWord_target()));
                        }
                    });
                    if(sc!= null) sc.close();
                }
            }
            catch (Exception e) {System.out.println(e.getMessage());}
    }
}
