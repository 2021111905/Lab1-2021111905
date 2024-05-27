package se_lab1;

import java.util.ArrayList;
class TreeNode {
    String word;
    int level;
    TreeNodeList<TreeNode> parentList;
    TreeNodeList<TreeNode> childList;
    ArrayList<Integer> childPathWeightList;

    public TreeNode(String word, TreeNode parent) {
        this.word = word;
        this.parentList = new TreeNodeList();
        if (parent != null) {
            this.parentList.add(parent);
        }

        this.childList = new TreeNodeList();
        this.childPathWeightList = new ArrayList();
    }

    public String getWord() {
        return this.word;
    }

    public void addParent(TreeNode anotherParent) {
        if (this.parentList.nodeCheck(anotherParent.getWord()) == null) {
            this.parentList.add(anotherParent);
        }

    }

    public void addChild(TreeNode anotherChild) {
        TreeNode checkNode = this.childList.nodeCheck(anotherChild.getWord());
        if (checkNode != null) {
            int nodeIndex = this.childList.indexOf(checkNode);
            int childPathWeight = (Integer)this.childPathWeightList.get(nodeIndex) + 1;
            this.childPathWeightList.set(nodeIndex, childPathWeight);
        } else {
            this.childList.add(anotherChild);
            this.childPathWeightList.add(1);
        }

    }

    public int getWeightOfNode(TreeNode childNode) {
        int childIndex = this.childList.indexOf(childNode);
        int weight;
        if (childIndex == -1) {
            weight = 0;
        } else {
            weight = (Integer)this.childPathWeightList.get(childIndex);
        }

        return weight;
    }

    public void setNodeLevel(int level) {
        this.level = level;
    }

    public int getNodeLevel() {
        return this.level;
    }

}
