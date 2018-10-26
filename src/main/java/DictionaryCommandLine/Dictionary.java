package DictionaryCommandLine;

import java.util.ArrayList;

public class Dictionary {
    /**
     * Toàn bộ từ sau khi quét từ file sẽ lưu theo dạng cây
     */
    static class Node {
            protected String explain ="";
            protected boolean isEndOfWord = false;
            protected Node[] nodes;
            static final int CHAR_LEN = 256;
            public Node() {
                nodes = new Node[CHAR_LEN];
            }

        }
    final int START = 0;
    public Node root = new Node();

    /**
     * Đi theo prefix đến được Node vị trí là ký tự cuối cùng của từ.
     */
    public ArrayList<Word> searchWord(String prefix, int limit) {

        ArrayList<Word> result = new ArrayList<Word>();
        Node cur = root;
        for(int i=0;i<prefix.length();i++) {
            if(cur.nodes[prefix.charAt(i)-START]!=null) {
                cur = cur.nodes[prefix.charAt(i)-START];
            }else {
                cur = null;
                break;
            }
        }
        if(cur!=null) {
            traversal(cur,limit,prefix,result);
        }
        return result;
    }

    /**
     *  Hàm duyệt cây con của 1 node, chính là toàn bộ từ mà có cùng prefix, chỉ trả về với @para limit kết quả
     */
    private void traversal(Node node, int limit,String prefix, ArrayList<Word> result) {
        if(result.size() >= limit || node == null) return;
        if(node.isEndOfWord && result.size()<limit) {
            result.add(new Word(prefix,node.explain));
        }
        for(int i=0;i<Node.CHAR_LEN;i++) {
            if(result.size()>=limit) return;
            if(node.nodes[i]!=null) {
                String pref = prefix+(char)(i+START);
                if(result.size()<limit) {
                    traversal(node.nodes[i],limit, pref,result);
                }
            }
        }
    }
}
