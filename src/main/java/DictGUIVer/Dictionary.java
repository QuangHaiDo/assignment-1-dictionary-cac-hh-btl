package DictGUIVer;

import java.util.ArrayList;

public class Dictionary {
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
     * DUyệt theo chiều sâu, trả danh sách độ dài limit kết quả

    private void traversal(Node node, int limit,String prefix, ArrayList<Pair<String,String>> result) {
        if(result.size() >= limit || node == null) return;
        if(node.isEndOfWord && result.size()<limit) {
            result.add(new Pair<String, String>(prefix,node.explain));
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

    /**
     * Đi theo prefix,

    public ArrayList<Pair<String,String>> searchWord(String prefix, int limit) {

        ArrayList<Pair<String,String>> result = new ArrayList<Pair<String, String>>();
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
    }*/

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

    /**
     * Đi theo prefix,
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

}
