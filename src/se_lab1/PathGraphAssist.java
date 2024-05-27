package se_lab1;

public class PathGraphAssist {
    TreeNodeList<TreeNode> allNodes;
    PathNodeList<PathNode> allPaths;

    public PathGraphAssist(TreeNodeList<TreeNode> treeNodes) {
        this.allNodes = (TreeNodeList)treeNodes.clone();
    }

    public int queryNodeToNode(TreeNode node1, TreeNode node2) {
        int state = -2;

        int i;
        for(i = 0; i < node1.childList.size(); ++i) {
            if (((TreeNode)node1.childList.get(i)).equals(node2)) {
                state = -1;
                break;
            }
        }

        if (state == -1) {
            for(i = 0; i < this.allPaths.size(); ++i) {
                for(int j = 0; j < ((PathNode)this.allPaths.get(i)).path.size() - 1; ++j) {
                    if (((TreeNode)((PathNode)this.allPaths.get(i)).path.get(j)).equals(node1) && ((TreeNode)((PathNode)this.allPaths.get(i)).path.get(j + 1)).equals(node2)) {
                        if (state == -1) {
                            state = i;
                        } else {
                            state = Integer.MAX_VALUE;
                        }
                    }
                }
            }
        }

        return state;
    }
}
