package DictionaryCommandLine;

import java.util.Scanner;

/**
 * Class trách nhiêm kiểm soát hoạt động chính của ứng dụng.
 */
public class DictionaryCommandline {

    DictionaryManagerment dictManagerment = new DictionaryManagerment();

    /**
     * Khởi tạo ứng dụng từ điển chính
     * Gồm menu và số thứ tự
     * Bấm số tương ứng để lựa chọn
     */
    public void dictionaryBasic() {
        System.out.println("Scanning database, please hold...");
        dictManagerment.importWordsFromDatabase();
        dictManagerment.importWordsFromFavourite();
        System.out.println("Scan complete! Starting...");
        System.out.println("===Dictionary Commandline ===");
        int opt = -1;
        Scanner opts = new Scanner(System.in);
        while (opt !=0) {
            System.out.println("Choose option: ");
            System.out.println("1.Search by keyword");
            System.out.println("2. My Favourite");
            System.out.println("3. Show All Words");
            System.out.println("4. Add New Word From Commandline");
            System.out.println("5. Remove Word From My Favorite");
            System.out.println("6. Save changes");
            System.out.println("0.Exit");
            opt = opts.nextInt();
            switch (opt) {
                case 1: {
                    while (opt!=9){
                        System.out.println("Search what:");
                        Scanner typeWord = new Scanner(System.in);
                        String wordSearch = typeWord.nextLine();
                        // Search and show results
                        for(Word p :dictManagerment.dict.searchWord(wordSearch.toLowerCase(),100)) {
                            System.out.println(p.getWord_target()+" : "+p.getWord_explain());
                        }
                        System.out.println("1. Find Next");
                        System.out.println("9. Back to Menu");
                        opt = opts.nextInt();
                    }
                    break;
                }
                case 2:{
                    System.out.println();
                    System.out.println("No || English          || Vietnamese         ");
                    System.out.println("===============================================");
                    for (Word w:dictManagerment.myFavortire.wordsList){
                        System.out.printf("%3d %-18s %-20s \n", dictManagerment.myFavortire.wordsList.indexOf(w),w.getWord_target(), w.getWord_explain());
                    }
                    break;
                }
                case 3: {
                    dictManagerment.showAllWords();
                    break;
                }
                case 4: {
                     dictManagerment.insertFromCommandline();
                    break;
                }
                case 5:{
                    System.out.println("pos??");
                    Scanner s = new Scanner(System.in);
                    int pos = s.nextInt();
                    dictManagerment.deleteWord(pos);
                    break;
                }
                case 6: {
                    dictManagerment.exportWordsToMyFavourite();
                    break;
                }
                default: break;
            }
        }
    }
}
