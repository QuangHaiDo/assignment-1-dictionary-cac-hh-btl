import java.io.*;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) throws FileNotFoundException {
        /**
        Khi chạy hàm tìm kiếm cần đọc/ cập nhật lại từ trong file.
         */
        DictionaryManagerment dictManagerment = new DictionaryManagerment();
        System.out.println("Scanning databases...");
        dictManagerment.importWordsFromFile();
        System.out.println("SCAN COMPLETE !!! SEARCH BY WORD:");
        Scanner sc = new Scanner(System.in);
        String wordSearch = sc.nextLine();
            // Search and show results
        for(Pair<String,String> p:dictManagerment.dict.searchWord(wordSearch,100)) {
            System.out.println(p.getKey()+" : "+p.getValue());
        }
    }
}