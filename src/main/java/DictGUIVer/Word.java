package DictGUIVer;

public class Word {

    // Các thuộc tính
    private String word_target;
    private String word_explain;

    // Constructor
    public Word(String word_target,String word_explain) {
        this.word_target= word_target;
        this.word_explain=word_explain;
    }

    public Word() {
    }

    // Getter/Setter tương ứng
    public String getWord_target() {
        return word_target;
    }

    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }

    public String getWord_explain() {
        return word_explain;
    }

    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }
    public boolean isSame(Word w){
        if (this.word_explain.equals(w.word_target)&&
                this.word_explain.equals(w.word_explain))
            return true;
        else return false;
    }
}
