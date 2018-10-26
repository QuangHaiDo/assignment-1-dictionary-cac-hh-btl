package DictionaryCommandLine;

import java.io.*;
import java.util.Scanner;

/**
 * Class quản lý nhập xuất/thêm/sửa/xóa từ trong từ điển
 */
public class DictionaryManagerment {
    /**
     * Khởi tạo danh sách từ trong từ điển và danh sách từ ưa thích
     */
    Dictionary dict = new Dictionary();
    Favourite myFavortire = new Favourite();

    /**
     * Hàm nhập từ mới bằng commandline
     */
    public void insertFromCommandline(){
        Word newWord = new Word();
        Scanner sc = new Scanner(System.in);
        System.out.println("Word_Target: ");
        newWord.setWord_target(sc.nextLine().toLowerCase());
        System.out.println("Word_Explane: ");
        newWord.setWord_explain(sc.nextLine().toLowerCase());
        /**
         * Sau khi nhập, thêm từ vào cả 2 danh sách
         */
        addToMyFavourite(newWord.getWord_target(), newWord.getWord_explain());
        addWordToTree(newWord.getWord_target(),newWord.getWord_explain());
    }

    /**
     * Do danh sách từ trong từ điển lưu theo dạng cây Trie, khi thêm từ mới sẽ theo prefix từ đó đến hết độ dài từ và
     * thêm Node con mới là từ mới
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

    /**
     * Hàm hiển thị toàn bộ từ trong từ điển, sử dụng hàm tìm kiếm từ với prefix để là rỗng thì kết quả trả về sẽ là
     * toàn bộ từ trong từ điển.
     */
    public void showAllWords () {
        System.out.println();
        System.out.println("English          || Vietnamese         ");
        System.out.println("===============================================");
        int index = 0;
        for(Word p:dict.searchWord("",104000)) {
            System.out.println(++index + "||"+p.getWord_target() + " : " + p.getWord_explain());
        }
    }


    /**
     * Các hàm thêm/sửa/xóa từ.
     */
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

    /**
     * Các hàm nhập/ xuất từ file, dù để đuôi là .dict nhưng file thực chất là file text với định dạng:
     * Mỗi Word nằm trên một dòng
     * mỗi dòng định dạng "word_target=word_explain"
     * 1. Khi đọc file, mỗi dòng lưu dạng String[], 2 phần tử ngắt ở dấu "=", với String[0] là word_target và String[1] là word_explain
     * 2. Tương tự, khi xuất file, mỗi word ở 1 dòng, xuất mỗi dòng định dang: "word_target=word_explain"
     */
    public void exportWordsToMyFavourite()
    {
        File f = new File("database/MyFavourite");
        try {
            BufferedWriter fileOut = new BufferedWriter(new FileWriter(f));
            for (DictionaryCommandLine.Word w:this.myFavortire.wordsList) {
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