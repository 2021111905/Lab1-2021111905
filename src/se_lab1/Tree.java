package se_lab1;

import java.util.ArrayList;
import java.util.Random;
class Tree {
    TreeNode head;
    TreeNodeList<TreeNode> treeNodes;

    public Tree(String[] words) {
        this.head = new TreeNode(words[0], (TreeNode)null);
        this.treeNodes = new TreeNodeList();
        TreeNode nodepr = this.head;
        this.treeNodes.add(nodepr);

        for(int i = 1; i < words.length; ++i) {
            TreeNode nodeafter = this.treeNodes.nodeCheck(words[i]);
            if (nodeafter != null) {
                nodepr.addChild(nodeafter);
            } else {
                nodeafter = new TreeNode(words[i], nodepr);
                nodepr.addChild(nodeafter);
                this.treeNodes.add(nodeafter);
            }

            nodeafter.addParent(nodepr);
            nodepr = nodeafter;
        }

        this.calculateNodeLevel();
    }

    public void calculateNodeLevel() {
        TreeNodeList<TreeNode> queue = new TreeNodeList();
        TreeNodeList<TreeNode> copyList = new TreeNodeList();
        copyList.addAll(this.treeNodes);
        this.head.setNodeLevel(1);
        queue.push(this.head);

        while(copyList.size() != 0) {
            TreeNode presentNode = queue.pop();
            int presentLevel = presentNode.getNodeLevel() + 1;

            for(int i = 0; i < presentNode.childList.size(); ++i) {
                TreeNode childNode = (TreeNode)presentNode.childList.get(i);
                if (copyList.indexOf(childNode) != -1) {
                    childNode.setNodeLevel(presentLevel);
                    if (queue.indexOf(childNode) == -1) {
                        queue.push(childNode);
                    }
                }
            }

            copyList.remove(presentNode);
        }

    }

    public String queryBridgeWords(String word1, String word2) {
        String res = "";
        TreeNodeList<TreeNode> retNodes = this.calculateBridge(word1, word2);
        if (retNodes == null) {
            res = res + "No " + word1 + " or " + word2 + " in the graph!";
        } else if (retNodes.size() == 0) {
            res = res + "No bridge words from " + word1 + " to " + word2 + "!";
        } else if (retNodes.size() == 1) {
            res = res + "The bridge word from " + word1 + " to " + word2 + " is: " + ((TreeNode)retNodes.get(0)).getWord() + ".";
        } else {
            res = res + "The bridge words from " + word1 + " to " + word2 + " are:";

            for(int i = 0; i < retNodes.size() - 1; ++i) {
                res = res + " " + ((TreeNode)retNodes.get(i)).getWord() + ",";
            }

            res = res + "and " + ((TreeNode)retNodes.get(retNodes.size() - 1)).getWord() + ".";
        }

        return res;
    }

    public String generateNewText(String inputText) {
        String res = "";
        String wordsStr = Lab1.replaceStr(inputText);
        String[] words = Lab1.wordSplit(wordsStr);

        for(int i = 0; i < words.length - 1; ++i) {
            String word1 = words[i];
            String word2 = words[i + 1];
            res = res + word1 + " ";
            TreeNodeList<TreeNode> retNodes = this.calculateBridge(word1, word2);
            if (retNodes != null && retNodes.size() != 0) {
                if (retNodes.size() == 1) {
                    res = res + "[" + ((TreeNode)retNodes.get(0)).getWord() + "] ";
                } else {
                    Random random = new Random();
                    int s = random.nextInt(retNodes.size() - 1);
                    res = res + "[" + ((TreeNode)retNodes.get(s)).getWord() + "] ";
                }
            }
        }

        res = res + words[words.length - 1];
        return res;
    }

    public TreeNodeList<TreeNode> calculateBridge(String word1, String word2) {
        TreeNode preNode = this.treeNodes.nodeCheck(word1);
        TreeNode afterNode = this.treeNodes.nodeCheck(word2);
        TreeNodeList<TreeNode> retNodes = null;
        if (preNode != null && afterNode != null) {
            retNodes = new TreeNodeList();

            for(int i = 0; i < preNode.childList.size(); ++i) {
                for(int j = 0; j < afterNode.parentList.size(); ++j) {
                    if (((TreeNode)preNode.childList.get(i)).equals(afterNode.parentList.get(j))) {
                        TreeNode retNode = (TreeNode)preNode.childList.get(i);
                        if (!retNode.equals(preNode) && !retNode.equals(afterNode)) {
                            retNodes.add((TreeNode)preNode.childList.get(i));
                        }
                    }
                }
            }
        }

        return retNodes;
    }

    public String calcShortestPath(String word1, String word2, PathGraphAssist pga) throws CloneNotSupportedException {
        String res = "";
        TreeNode startNode = this.treeNodes.nodeCheck(word1);
        TreeNode endNode = this.treeNodes.nodeCheck(word2);
        PathNodeList<PathNode> findPaths = new PathNodeList();
        PathNodeList<PathNode> certainPaths = new PathNodeList();
        findPaths.add(new PathNode(startNode));

        int j;
        while(findPaths.size() != 0) {
            PathNode popPathNode = findPaths.pop();
            TreeNode presentNode = popPathNode.presentNode;

            for(j = 0; j < presentNode.childList.size(); ++j) {
                TreeNode childNode = (TreeNode)presentNode.childList.get(j);
                if (popPathNode.path.nodeCheck(childNode.getWord()) == null) {
                    PathNode branchNode = (PathNode)popPathNode.clone();
                    int bridgeWeightValue = presentNode.getWeightOfNode(childNode);
                    branchNode.pathLength += bridgeWeightValue;
                    branchNode.path.add(childNode);
                    branchNode.presentNode = childNode;
                    if (childNode.equals(endNode)) {
                        certainPaths.push(branchNode);
                    } else if (certainPaths.size() != 0) {
                        PathNode path = certainPaths.getShortestPath();
                        if (path.pathLength > branchNode.pathLength) {
                            findPaths.push(branchNode);
                        }
                    } else {
                        findPaths.push(branchNode);
                    }
                }
            }
        }

        if (certainPaths.size() != 0) {
            for(int i = 0; i < certainPaths.size(); ++i) {
                res = res + "Path " + i + " :";
                TreeNodeList<TreeNode> path = ((PathNode)certainPaths.get(i)).path;

                for(j = 0; j < path.size() - 1; ++j) {
                    res = res + ((TreeNode)path.get(j)).getWord() + "->";
                }

                res = res + endNode.getWord() + ".\n";
            }
        } else {
            res = res + "There's no path from " + word1 + " to " + word2 + ".";
        }

        pga.allPaths = certainPaths;
        return res;
    }

    public String randomWalk() {
        String ret = "";
        Random random = new Random();
        int randomNodeIndex;
        if (this.treeNodes.size() == 1) {
            randomNodeIndex = 0;
        } else {
            randomNodeIndex = random.nextInt(this.treeNodes.size() - 1);
        }

        TreeNode startNode = (TreeNode)this.treeNodes.get(randomNodeIndex);
        TreeNode walkNode = startNode;
        TreeNodeList<TreeNode> walkNodes = new TreeNodeList();
        walkNodes.add(startNode);

        while(walkNode.childList.size() != 0) {
            boolean endState = false;
            ret = ret + walkNode.getWord() + " ";
            if (walkNode.childList.size() == 1) {
                randomNodeIndex = 0;
            } else {
                randomNodeIndex = random.nextInt(walkNode.childList.size() - 1);
            }

            TreeNode nextNode = (TreeNode)walkNode.childList.get(randomNodeIndex);
            ArrayList<Integer> multiIndex = walkNodes.multiIndexOf(walkNode);

            for(int i = 0; i < multiIndex.size() - 1; ++i) {
                int index = (Integer)multiIndex.get(i);
                if (((TreeNode)walkNodes.get(index + 1)).equals(nextNode)) {
                    endState = true;
                    break;
                }
            }

            if (endState) {
                ret = ret + nextNode.getWord();
                break;
            }

            walkNode = nextNode;
            walkNodes.add(nextNode);
        }

        return ret;
    }


}
