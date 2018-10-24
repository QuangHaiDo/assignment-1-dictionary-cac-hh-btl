package DictGUIVer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

        addWordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dictManagerment.insertFromGUI();
            }
        });
        myFavoriteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dictManagerment.showMyFavourite();
            }
        });
        frame.add(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,400);

    }


    public void dictionaryAdvancedStart() {
        JDialog scanDatabaseDialog = new JDialog();
       // scanDatabaseDialog.setLayout(new FlowLayout());
        scanDatabaseDialog.setTitle("English-Vietnamese Dictionary By team H-H");
        scanDatabaseDialog.setSize(200,150);
        scanDatabaseDialog.add(new JLabel("Scanning database, please hold..."));
        scanDatabaseDialog.setVisible(true);
        dictManagerment.importWordsFromDatabase();
        dictManagerment.importWordsFromFavourite();
        scanDatabaseDialog.setVisible(false);
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
        //DictOnStart onStart = new DictOnStart();
        GUILauncher init = new GUILauncher();
        init.frame.setVisible(true);
    }
}
