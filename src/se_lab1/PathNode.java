package se_lab1;

// PathNode类实现了Cloneable接口，用于表示路径节点
public class PathNode implements Cloneable {
    int pathLength = 0;  // 路径长度
    TreeNode presentNode;  // 当前节点
    TreeNodeList<TreeNode> path;  // 路径列表

    // 构造方法，初始化起始节点
    public PathNode(TreeNode startNode) {
        this.presentNode = startNode;  // 设置当前节点为起始节点
        this.path = new TreeNodeList<>();  // 初始化路径列表
        this.path.push(startNode);  // 将起始节点添加到路径列表中
    }

    // 克隆方法，创建PathNode对象的副本
    protected Object clone() throws CloneNotSupportedException {
        PathNode newPathNode = (PathNode)super.clone();  // 调用父类的克隆方法
        newPathNode.path = (TreeNodeList)this.path.clone();  // 克隆路径列表
        return newPathNode;  // 返回新的PathNode对象
    }
}
// PathNode类表示路径中的一个节点，并实现了Cloneable接口，以支持对象克隆。该类的主要功能和特性包括：

// 成员变量：

// pathLength：表示路径的长度，初始值为0。
// presentNode：表示当前节点。
// path：一个TreeNodeList对象，用于存储路径中的节点。
// 构造方法：

// PathNode(TreeNode startNode)：初始化路径节点，设置起始节点为当前节点，并将其添加到路径列表中。
// 克隆方法：

// protected Object clone() throws CloneNotSupportedException：克隆当前PathNode对象，包括克隆路径列表，返回一个新的PathNode对象。
// 主要数据结构
// TreeNode：表示树中的节点。
// TreeNodeList<TreeNode>：一个自定义的列表，用于存储路径中的节点。
// 方法功能
// 构造方法：初始化PathNode对象，设置起始节点，并将其添加到路径列表中。
// 克隆方法：创建PathNode对象的副本，包括克隆路径列表，以支持对象的深拷贝。
// 这个类主要用于在路径图算法中表示和操作路径节点，通过实现克隆方法，可以方便地复制路径节点对象及其路径列表，从而在算法中避免修改原始路径数据。