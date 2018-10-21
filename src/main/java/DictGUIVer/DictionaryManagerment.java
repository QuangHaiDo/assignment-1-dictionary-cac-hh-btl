package DictGUIVer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Scanner;

public class DictionaryManagerment {
    Dictionary dict = new Dictionary();
    Favourite myFavortire = new Favourite();

    /**
     * modified this function to add word with GUI
     **
    public void insertFromGUI(){
        Word newWord = new Word();
        Scanner sc = new Scanner(System.in);
        System.out.println("Word_Target: ");
        newWord.setWord_target(sc.nextLine().toLowerCase());
        System.out.println("Word_Explane: ");
        newWord.setWord_explain(sc.nextLine().toLowerCase());
        addToMyFavourite(newWord.getWord_target(), newWord.getWord_explain());
        addWordToTree(newWord.getWord_target(),newWord.getWord_explain());
    }
*/

    public void addWordToTree(String target, String explain) {
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

    // Show all words
    public void showAllWords () {
        System.out.println();
        System.out.println("English          || Vietnamese         ");
        System.out.println("===============================================");
        int index = 0;
        for(Word w :dict.searchWord("",104000)) {
            System.out.println(++index + "||"+w.getWord_target() + " : " + w.getWord_explain());
        }
    }


    public void addToMyFavourite(Word w){
        if (myFavortire.isWordExisted(w)) System.out.println("Word existed!!!");
        else this.myFavortire.wordsList.add(w);
    }

    public void addToMyFavourite(String target,String explain){
        Word addNewWord = new Word(target,explain);
        addToMyFavourite(addNewWord);
    }

    // Delete by pos
    public void deleteWord(int pos){
        try{
            this.myFavortire.wordsList.remove(pos);
        } catch (Exception e) {
            System.out.println("Word isn't exist!");
        }
    }
    // Delete by word_target
    public void deleteWord(Word w){
        try{
            int pos = this.myFavortire.wordsList.indexOf(w);
            this.myFavortire.wordsList.remove(pos);
        } catch (Exception e) {
            System.out.println("Word isn't exist!");
        }
    }
    public void exportWordsToMyFavourite()
    {
        File f = new File("database/MyFavourite");
        try {
            BufferedWriter fileOut = new BufferedWriter(new FileWriter(f));
            for (Word w:this.myFavortire.wordsList) {
                fileOut.write(w.getWord_target() +"="+w.getWord_explain());
                fileOut.newLine();
                fileOut.flush();
            }
            fileOut.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void importWordsFromDatabase(){
        try {
            Scanner br = new Scanner(new FileInputStream("database/dictEV-Modified-final.dict"));
            while (br.hasNextLine()) {
                String line = br.nextLine();
                try {
                    String[] es = line.split("=");
                    addWordToTree(es[0], es[1]);
                } catch (Exception e) {
                    System.out.println(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void importWordsFromFavourite(){
        File f = new File("database/MyFavourite");
        try{
            Scanner sc = new Scanner(f);
            try {
                while (sc.hasNextLine()) {
                    String[] str = sc.nextLine().split("=");
                    addWordToTree(str[0],str[1]);
                    addToMyFavourite(str[0],str[1]);

                }
            } catch (Exception e){
                System.out.println("No database found!!");
            }
            if(sc!= null) sc.close();

        }
        catch (Exception e) {System.out.println(e.getMessage());}
    }
}
