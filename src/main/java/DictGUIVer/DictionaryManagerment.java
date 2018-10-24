package DictGUIVer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
     **/
    public void insertFromGUI(){
     JLabel typeWord = new JLabel("Type word: ");
     String newTarget = JOptionPane.showInputDialog(null,"Type target");
     JLabel typeExplain = new JLabel();
     String newExplain = JOptionPane.showInputDialog(null,"Type explain");
     addToMyFavourite(newTarget.toLowerCase(), newExplain.toLowerCase());
     addWordToTree(newTarget,newExplain);
     addToMyFavourite(newTarget,newExplain);
    }

    public void showMyFavourite(){
        JFrame frame = new JFrame("My Favourite");
        DefaultTableModel model = new DefaultTableModel();
        String column[]={"Word Target","Word Explain"};
        String[][] data = new String[myFavortire.wordsList.size()][2];
        for (int i=0;i<myFavortire.wordsList.size();i++) {
            data[i][0] = myFavortire.wordsList.get(i).getWord_target();
            data[i][1] = myFavortire.wordsList.get(i).getWord_explain();
        }
        JTable table=new JTable(data,column);
        //add the table to the frame
        JScrollPane sp = new JScrollPane(table);
        frame.add(sp);
        frame.setSize(600,400);
        frame.setVisible(true);

    }

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
