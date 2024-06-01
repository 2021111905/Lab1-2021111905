package se_lab1;

// PathGraphAssist类用于辅助路径图的操作
public class PathGraphAssist {
    TreeNodeList<TreeNode> allNodes;  // 所有节点列表
    PathNodeList<PathNode> allPaths;  // 所有路径列表

    // 构造方法
    public PathGraphAssist(TreeNodeList<TreeNode> treeNodes) {
        this.allNodes = (TreeNodeList)treeNodes.clone();  // 克隆传入的节点列表
    }

    // 查询两个节点之间的状态
    public int queryNodeToNode(TreeNode node1, TreeNode node2) {
        int state = -2;  // 初始状态设为-2

        // 检查node1的子节点列表中是否包含node2
        int i;
        for(i = 0; i < node1.childList.size(); ++i) {
            if (((TreeNode)node1.childList.get(i)).equals(node2)) {
                state = -1;  // 如果包含，状态设为-1
                break;
            }
        }

        // 如果state为-1，继续检查路径列表
        if (state == -1) {
            for(i = 0; i < this.allPaths.size(); ++i) {
                for(int j = 0; j < ((PathNode)this.allPaths.get(i)).path.size() - 1; ++j) {
                    if (((TreeNode)((PathNode)this.allPaths.get(i)).path.get(j)).equals(node1) && ((TreeNode)((PathNode)this.allPaths.get(i)).path.get(j + 1)).equals(node2)) {
                        if (state == -1) {
                            state = i;  // 找到路径，设置state为路径索引
                        } else {
                            state = Integer.MAX_VALUE;  // 多条路径，设置state为最大整数
                        }
                    }
                }
            }
        }

        return state;  // 返回状态
    }
}
// PathGraphAssist类用于辅助路径图的操作，主要包括以下功能：

// 构造方法：接收一个节点列表TreeNodeList，并将其克隆后存储在allNodes变量中。
// queryNodeToNode方法：查询两个节点之间的状态。
// 首先检查node1的子节点列表中是否包含node2，如果包含，设置状态为-1。
// 如果状态为-1，则继续检查所有路径列表allPaths，查找路径中是否包含node1到node2的连接。
// 如果找到，设置状态为路径的索引。
// 如果有多条路径，则将状态设置为最大整数值。
// 主要数据结构
// TreeNodeList<TreeNode>：存储所有节点的列表。
// PathNodeList<PathNode>：存储所有路径的列表。
// 方法功能
// queryNodeToNode方法：
// 输入：两个节点node1和node2。
// 输出：节点之间的状态。
// -2：初始状态，表示未找到连接。
// -1：node1的子节点列表中包含node2。
// 正整数：路径的索引，表示找到从node1到node2的路径。
// Integer.MAX_VALUE：表示有多条路径。
// 这个类主要用于路径查询，帮助确定两个节点之间是否存在直接连接或通过路径连接，并返回相应的状态值。