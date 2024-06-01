package se_lab1;

import java.util.ArrayList; // 导入ArrayList类，用于存储树节点列表

// 定义TreeNodeList类，继承自ArrayList，实现Cloneable接口，用于存储TreeNode节点列表
class TreeNodeList<E> extends ArrayList<E> implements Cloneable {
    private static final long serialVersionUID = 682330081079347841L; // 序列化版本号
    int longestWordLength = 0; // 存储最长单词的长度

    // 构造方法，初始化TreeNodeList对象
    TreeNodeList() {
    }

    // 检查节点列表中是否存在指定单词的节点
    public TreeNode nodeCheck(String word) {
        TreeNode existedNode = null; // 初始化存在节点为null

        // 遍历节点列表
        for (int i = 0; i < this.size(); ++i) {
            TreeNode getNode = (TreeNode) this.get(i); // 获取当前节点
            if (word.equals(getNode.getWord())) { // 如果节点的单词与指定单词相同
                existedNode = getNode; // 设置存在节点为当前节点
                break; // 退出循环
            }
        }

        return existedNode; // 返回存在节点
    }

    // 重写add方法，添加节点到列表中
    public boolean add(E e) {
        if (e != null) {
            TreeNode addNode = (TreeNode) e; // 将元素转换为TreeNode类型
            String word = addNode.word; // 获取节点的单词
            // 更新最长单词长度
            this.longestWordLength = word.length() > this.longestWordLength ? word.length() : this.longestWordLength;
        }

        return super.add(e); // 调用父类的add方法添加元素
    }

    // 获取列表中最长单词的长度
    public int getLongestWordLength() {
        return this.longestWordLength; // 返回最长单词长度
    }

    // 向列表中添加节点
    public boolean push(E pushNode) {
        boolean flag = this.add(pushNode); // 调用add方法添加节点
        return flag; // 返回添加结果
    }

    // 从列表中移除并返回第一个节点
    public TreeNode pop() {
        TreeNode popNode;
        if (this.size() != 0) {
            popNode = (TreeNode) this.get(0); // 获取第一个节点
            this.remove(0); // 移除第一个节点
        } else {
            popNode = null; // 如果列表为空，返回null
        }

        return popNode; // 返回移除的节点
    }

    // 获取列表中指定元素的所有索引
    public ArrayList<Integer> multiIndexOf(E e) {
        ArrayList<Integer> multiIndex = new ArrayList<>(); // 初始化索引列表
        TreeNode queryNode = (TreeNode) e; // 将元素转换为TreeNode类型

        // 遍历节点列表
        for (int i = 0; i < this.size(); ++i) {
            if (this.get(i).equals(queryNode)) { // 如果元素与查询节点相同
                multiIndex.add(i); // 添加索引到索引列表
            }
        }

        return multiIndex; // 返回索引列表
    }
}

// TreeNodeList类继承自ArrayList，实现了Cloneable接口，主要用于存储TreeNode节点列表，并提供了一些特定的操作方法，如检查节点、获取最长单词长度、添加节点、移除节点和获取元素的多重索引。
// 以下是对各方法功能的总结：

// TreeNodeList()：构造方法，初始化TreeNodeList对象。
// nodeCheck(String word)：检查节点列表中是否存在指定单词的节点。
// add(E e)：重写add方法，添加节点到列表中，并更新最长单词长度。
// getLongestWordLength()：获取列表中最长单词的长度。
// push(E pushNode)：向列表中添加节点。
// pop()：从列表中移除并返回第一个节点。
// multiIndexOf(E e)：获取列表中指定元素的所有索引。
// 主要数据结构
// TreeNode：表示树节点。
// ArrayList<E>：用于存储树节点的列表。
// 方法功能详解
// 构造方法：初始化TreeNodeList对象。
// nodeCheck：遍历节点列表，检查是否存在指定单词的节点，返回节点或null。
// add：重写ArrayList的add方法，在添加节点的同时更新最长单词长度。
// getLongestWordLength：返回列表中最长单词的长度。
// push：调用add方法将节点添加到列表中。
// pop：从列表中移除并返回第一个节点，如果列表为空，返回null。
// multiIndexOf：遍历节点列表，获取指定元素的所有索引，返回索引列表。
// 这个类为存储和操作树节点提供了基本的数据结构和方法。