package se_lab1;

import java.util.ArrayList; // 导入ArrayList类，用于存储子节点路径权重

// 定义TreeNode类，用于表示树中的节点
class TreeNode {
    String word; // 节点的单词
    int level; // 节点的层级
    TreeNodeList<TreeNode> parentList; // 父节点列表
    TreeNodeList<TreeNode> childList; // 子节点列表
    ArrayList<Integer> childPathWeightList; // 子节点路径权重列表

    // 构造方法，初始化节点
    public TreeNode(String word, TreeNode parent) {
        this.word = word; // 设置节点的单词
        this.parentList = new TreeNodeList<>(); // 初始化父节点列表
        if (parent != null) {
            this.parentList.add(parent); // 如果父节点不为空，将其添加到父节点列表中
        }
        this.childList = new TreeNodeList<>(); // 初始化子节点列表
        this.childPathWeightList = new ArrayList<>(); // 初始化子节点路径权重列表
    }

    // 获取节点的单词
    public String getWord() {
        return this.word;
    }

    // 添加父节点
    public void addParent(TreeNode anotherParent) {
        if (this.parentList.nodeCheck(anotherParent.getWord()) == null) { // 检查父节点是否已存在
            this.parentList.add(anotherParent); // 如果不存在，将其添加到父节点列表中
        }
    }

    // 添加子节点
    public void addChild(TreeNode anotherChild) {
        TreeNode checkNode = this.childList.nodeCheck(anotherChild.getWord()); // 检查子节点是否已存在
        if (checkNode != null) {
            int nodeIndex = this.childList.indexOf(checkNode); // 获取子节点的索引
            int childPathWeight = this.childPathWeightList.get(nodeIndex) + 1; // 增加路径权重
            this.childPathWeightList.set(nodeIndex, childPathWeight); // 更新路径权重
        } else {
            this.childList.add(anotherChild); // 如果子节点不存在，将其添加到子节点列表中
            this.childPathWeightList.add(1); // 添加初始路径权重
        }
    }

    // 获取指定子节点的路径权重
    public int getWeightOfNode(TreeNode childNode) {
        int childIndex = this.childList.indexOf(childNode); // 获取子节点的索引
        int weight;
        if (childIndex == -1) {
            weight = 0; // 如果子节点不存在，返回权重0
        } else {
            weight = this.childPathWeightList.get(childIndex); // 否则返回对应的路径权重
        }
        return weight; // 返回权重
    }

    // 设置节点的层级
    public void setNodeLevel(int level) {
        this.level = level;
    }

    // 获取节点的层级
    public int getNodeLevel() {
        return this.level;
    }
}

// TreeNode类用于表示树中的节点，并提供了一些基本操作，如添加父节点、添加子节点、获取节点的单词和层级、获取子节点的路径权重等。以下是对各方法功能的总结：

// TreeNode(String word, TreeNode parent)：构造方法，根据给定的单词和父节点初始化树节点。
// getWord()：获取节点的单词。
// addParent(TreeNode anotherParent)：添加父节点。
// addChild(TreeNode anotherChild)：添加子节点，更新路径权重。
// getWeightOfNode(TreeNode childNode)：获取指定子节点的路径权重。
// setNodeLevel(int level)：设置节点的层级。
// getNodeLevel()：获取节点的层级。
// 主要数据结构
// TreeNode：表示树节点。
// TreeNodeList<TreeNode>：用于存储树节点的列表。
// ArrayList<Integer>：用于存储子节点路径权重的列表。
// 方法功能详解
// 构造方法：初始化节点的单词、父节点列表、子节点列表和子节点路径权重列表。
// getWord：返回节点的单词。
// addParent：如果父节点不在父节点列表中，则将其添加到父节点列表中。
// addChild：如果子节点不在子节点列表中，则将其添加到子节点列表中，并初始化路径权重。如果子节点已存在，则更新路径权重。
// getWeightOfNode：返回指定子节点的路径权重，如果子节点不存在则返回0。
// setNodeLevel：设置节点的层级。
// getNodeLevel：返回节点的层级。
// 这个类为表示和操作树中的节点提供了基本的数据结构和方法。