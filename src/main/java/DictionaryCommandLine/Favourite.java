package DictionaryCommandLine;

import java.util.ArrayList;

public class Favourite {
    ArrayList<Word> wordsList = new ArrayList<Word>();
    public boolean isWordExisted(Word _word){
        for (Word w: wordsList){
            if (_word.isSame(w)) return true;
        }
        return false;
    }
}
