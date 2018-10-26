package DictionaryCommandLine;

import java.util.ArrayList;

public class Favourite {
    /**
     * Khai báo danh sách ưa thích
     */
    ArrayList<Word> wordsList = new ArrayList<Word>();

    /**
     *  Kiểm tra từ đã tồn tại
     * @param _word : từ cần kiểm tra
     * @return true/false
     */
    public boolean isWordExisted(Word _word){
        for (Word w: wordsList){
            if (_word.isSame(w)) return true;
        }
        return false;
    }
}
