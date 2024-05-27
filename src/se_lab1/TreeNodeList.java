package se_lab1;

import java.util.ArrayList;
class TreeNodeList<E> extends ArrayList<E> implements Cloneable  {
    private static final long serialVersionUID = 682330081079347841L;
    int longestWordLength = 0;

    TreeNodeList() {
    }

    public TreeNode nodeCheck(String word) {
        TreeNode existedNode = null;

        for(int i = 0; i < this.size(); ++i) {
            TreeNode getNode = (TreeNode)this.get(i);
            if (word.equals(getNode.getWord())) {
                existedNode = getNode;
                break;
            }
        }

        return existedNode;
    }

    public boolean add(E e) {
        if (e != null) {
            TreeNode addNode = (TreeNode)e;
            String word = addNode.word;
            this.longestWordLength = word.length() > this.longestWordLength ? word.length() : this.longestWordLength;
        }

        return super.add(e);
    }

    public int getLongestWordLength() {
        return this.longestWordLength;
    }

    public boolean push(E pushNode) {
        boolean flag = this.add(pushNode);
        return flag;
    }

    public TreeNode pop() {
        TreeNode popNode;
        if (this.size() != 0) {
            popNode = (TreeNode)this.get(0);
            this.remove(0);
        } else {
            popNode = null;
        }

        return popNode;
    }

    public ArrayList<Integer> multiIndexOf(E e) {
        ArrayList<Integer> multiIndex = new ArrayList();
        TreeNode queryNode = (TreeNode)e;

        for(int i = 0; i < this.size(); ++i) {
            if (this.get(i).equals(queryNode)) {
                multiIndex.add(i);
            }
        }

        return multiIndex;
    }
}
