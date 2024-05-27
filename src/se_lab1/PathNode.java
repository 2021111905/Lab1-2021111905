package se_lab1;

public class PathNode implements Cloneable{
    int pathLength = 0;
    TreeNode presentNode;
    TreeNodeList<TreeNode> path;

    public PathNode(TreeNode startNode) {
        this.presentNode = startNode;
        this.path = new TreeNodeList();
        this.path.push(startNode);
    }

    protected Object clone() throws CloneNotSupportedException {
        PathNode newPathNode = (PathNode)super.clone();
        newPathNode.path = (TreeNodeList)this.path.clone();
        return newPathNode;
    }

}
