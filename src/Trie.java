import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class TrieNode {
    boolean isEnd = false;
    TrieNode[] child;

    public TrieNode() {
        child = new TrieNode[256];
    }
}

public class Trie {

    public TrieNode root;

    public Trie(TrieNode root) {
        this.root = root;
    }

    public TrieNode insert(String str) {
        TrieNode root = this.root;
        if (root == null) {
            root = new TrieNode();
        }

        int strLen = str.length();
        int idx;
        for (int i = 0; i < strLen; i++) {
            idx = str.charAt(i);
            if (root.child[idx] == null) {
                root.child[idx] = new TrieNode();
            }
            root = root.child[idx];
        }
        root.isEnd = true;
        return root;
    }

    public boolean search(String str) {
        TrieNode root = this.root;
        if (root == null || str == null) {
            return false;
        }

        int strLen = str.length();
        int idx;
        for (int i = 0; i < strLen; i++) {
            idx = str.charAt(i);
            if (root.child[idx] == null) {
                return false;
            }
            root = root.child[idx];
        }
        return root.isEnd;
    }

    public TrieNode delete(String str, TrieNode root, int i) {
        if (root == null) {
            return null;
        }

        if (i == str.length()) {
            root.isEnd = false;
            return root;
        }

        int idx = str.charAt(i);
        root.child[idx] = delete(str, root.child[idx], i + 1);

        if (root.isEnd == false) {
            if (isEmpty(root.child[idx])) {
                root.child[idx] = null;
            }
        }

        return root;
    }

    private boolean isEmpty(TrieNode root) {
        if (root == null || root.child == null) {
            return true;
        }

        for (int i = 0; i < 256; i++) {
            if (root.child[i] != null) {
                return false;
            }
        }

        return true;
    }

    public List<String> autoComplete(String str) {
        TrieNode root = this.root;

        if (root == null || str == null) {
            return Collections.emptyList();
        }

        int strLen = str.length();
        int idx;
        for (int i = 0; i < strLen; i++) {
            idx = str.charAt(i);
            if (root.child[idx] == null) {
                return Collections.emptyList();
            }
            root = root.child[idx];
        }
        List<String> strings = new LinkedList<>();
        dfsAutoComplete(str, strings, root);
        return strings;
    }

    private void dfsAutoComplete(String prefix, List<String> strings, TrieNode root) {
        if (root == null) {
            return;
        }
        if (root.isEnd) {
            strings.add(prefix);
        }

        for (int i = 0; i < 256; i++) {
            if (root.child[i] != null) {
                dfsAutoComplete(prefix + (char)i, strings, root.child[i]);
            }
        }
    }
}
