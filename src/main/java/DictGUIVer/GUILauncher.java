package DictGUIVer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class GUILauncher {
    JFrame frame = new JFrame("Dictionary HH-Team GUILauncher");
    private JPanel mainPanel;
    private JTextPane textPane1;
    private JScrollPane scrollPane;
    private JList word_target_JList;
    private JTextField SearchBox;
    private JButton button1;
    private JButton myFavoriteButton;
    private JButton addWordButton;
    private JButton listenButton;

    public GUILauncher(){
        dictionaryAdvancedStart();
        showWordsListOnStart();
        textPane1.setEditable(false);

        /**
         * Hiện danh sách kết quả trong khi gõ phím
         */
        SearchBox.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                        String input = SearchBox.getText().toLowerCase();
                        showWordsListOnSearching(input,104000);
                    }
        });

        /**
         * Y tuong: chọn 1 item trong Jlist, hien explain trong textPane
         *  NOTE: HAM NAY LA NGUYEN NHAN LOI EVENT DISPATCH THREAD
         * */

        word_target_JList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
               //textPane1.setText(word_target_JList.getSelectedValue().toString());
                textPane1.setText(dictManagerment.dict.searchWord(word_target_JList.getSelectedValue().toString(), 1).get(0).getWord_explain());
            }
        });

        frame.add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,400);

    }


    public void dictionaryAdvancedStart() {
        // SHOW TO GUI
        System.out.println("Scanning database, please hold...");

        dictManagerment.importWordsFromDatabase();
        dictManagerment.importWordsFromFavourite();
        // SHOW TO GUI
        System.out.println("Scan complete! Starting...");
        System.out.println("===Dictionary GUI Version ===");
    }


    private void showWordsListOnStart(){
        DefaultListModel word_target_List = new DefaultListModel<>();
        for(Word w:dictManagerment.dict.searchWord("",104000)) {
            word_target_List.addElement(w.getWord_target());
        }
        this.word_target_JList.setModel(word_target_List);
        this.word_target_JList.setSelectedIndex(0);
        this.word_target_JList.setVisibleRowCount(3);
    }

    private void showWordsListOnSearching(String prefix,int limit){
        DefaultListModel word_target_List = new DefaultListModel<>();
        for(Word w:dictManagerment.dict.searchWord(prefix,limit)) {
            word_target_List.addElement(w.getWord_target());
            word_explain_List.add(w.getWord_explain());

        }
        this.word_target_JList.setModel(word_target_List);
        this.word_target_JList.setSelectedIndex(0);
        this.word_target_JList.setVisibleRowCount(3);
    }

    ArrayList<String> word_explain_List = new ArrayList<>();
    DictionaryManagerment dictManagerment = new DictionaryManagerment();


    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            GUILauncher init = new GUILauncher();
            init.frame.setVisible(true);
    }
}
