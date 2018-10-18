import java.io.*;
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
        addWord(newWord.getWord_target(), newWord.getWord_explain());
    }

    public void addWord(String target, String explain) {
        int len = target.length();
        Dictionary.Node cur = dict.root;
        for(int i=0;i<len;i++) {
            if(cur.nodes[target.charAt(i)-dict.START]==null) {
                cur.nodes[target.charAt(i)-dict.START] = new Dictionary.Node();
            }
            cur = cur.nodes[target.charAt(i)-dict.START];

            if(i==len-1) {
                cur.isEndOfWord = true;
                cur.explain = explain;
            }
        }
    }
    /* Delete by pos
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
        File f = new File("database/DictEV-Modified.dict");
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
    }*/
    public void importWordsFromFile(){
        try {
            Scanner br = new Scanner(new FileInputStream("database/dictEV-Modified-final.dict"));
            while (br.hasNextLine()) {
                String line = br.nextLine();
                try {
                    String[] es = line.split("=");
                    addWord(es[0], es[1]);
                } catch (Exception e) {
                    System.out.println(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}