package se_lab1;

import java.util.ArrayList; // 导入ArrayList类，用于存储节点列表
import java.util.Random; // 导入Random类，用于生成随机数

// 定义Tree类，用于构建树结构和进行各种操作
class Tree {
    TreeNode head; // 树的根节点
    TreeNodeList<TreeNode> treeNodes; // 树节点列表

    // 构造方法，初始化树结构
    public Tree(String[] words) {
        this.head = new TreeNode(words[0], null); // 创建根节点
        this.treeNodes = new TreeNodeList<>(); // 初始化节点列表
        TreeNode nodepr = this.head; // 前一个节点，初始化为根节点
        this.treeNodes.add(nodepr); // 将根节点添加到节点列表

        // 遍历输入的单词数组，从第二个单词开始
        for (int i = 1; i < words.length; ++i) {
            TreeNode nodeafter = this.treeNodes.nodeCheck(words[i]); // 检查当前单词是否已存在于节点列表中
            if (nodeafter != null) {
                nodepr.addChild(nodeafter); // 如果已存在，将其添加为前一个节点的子节点
            } else {
                nodeafter = new TreeNode(words[i], nodepr); // 如果不存在，创建新节点
                nodepr.addChild(nodeafter); // 将新节点添加为前一个节点的子节点
                this.treeNodes.add(nodeafter); // 将新节点添加到节点列表中
            }
            nodeafter.addParent(nodepr); // 将前一个节点添加为当前节点的父节点
            nodepr = nodeafter; // 更新前一个节点为当前节点
        }

        this.calculateNodeLevel(); // 计算所有节点的层级
    }

    // 计算所有节点的层级
    public void calculateNodeLevel() {
        TreeNodeList<TreeNode> queue = new TreeNodeList<>(); // 创建队列，用于层级遍历
        TreeNodeList<TreeNode> copyList = new TreeNodeList<>(); // 创建副本列表，存储所有节点
        copyList.addAll(this.treeNodes); // 将所有节点添加到副本列表中
        this.head.setNodeLevel(1); // 设置根节点的层级为1
        queue.push(this.head); // 将根节点添加到队列中

        // 当副本列表不为空时，继续遍历
        while (copyList.size() != 0) {
            TreeNode presentNode = queue.pop(); // 弹出队列中的第一个节点
            int presentLevel = presentNode.getNodeLevel() + 1; // 当前节点的子节点层级为当前节点层级+1

            // 遍历当前节点的所有子节点
            for (int i = 0; i < presentNode.childList.size(); ++i) {
                TreeNode childNode = presentNode.childList.get(i); // 获取子节点
                if (copyList.indexOf(childNode) != -1) { // 如果子节点在副本列表中
                    childNode.setNodeLevel(presentLevel); // 设置子节点的层级
                    if (queue.indexOf(childNode) == -1) { // 如果子节点不在队列中
                        queue.push(childNode); // 将子节点添加到队列中
                    }
                }
            }

            copyList.remove(presentNode); // 从副本列表中移除当前节点
        }
    }

    // 查询两个单词之间的桥接词
    public String queryBridgeWords(String word1, String word2) {
        String res = ""; // 初始化结果字符串
        TreeNodeList<TreeNode> retNodes = this.calculateBridge(word1, word2); // 计算桥接词列表
        if (retNodes == null) {
            res = res + "No " + word1 + " or " + word2 + " in the graph!"; // 如果桥接词列表为空，返回提示信息
        } else if (retNodes.size() == 0) {
            res = res + "No bridge words from " + word1 + " to " + word2 + "!"; // 如果没有桥接词，返回提示信息
        } else if (retNodes.size() == 1) {
            res = res + "The bridge word from " + word1 + " to " + word2 + " is: " + retNodes.get(0).getWord() + "."; // 如果有一个桥接词，返回桥接词
        } else {
            res = res + "The bridge words from " + word1 + " to " + word2 + " are:"; // 如果有多个桥接词，返回桥接词列表
            for (int i = 0; i < retNodes.size() - 1; ++i) {
                res = res + " " + retNodes.get(i).getWord() + ","; // 遍历桥接词列表，添加到结果字符串中
            }
            res = res + "and " + retNodes.get(retNodes.size() - 1).getWord() + "."; // 添加最后一个桥接词
        }
        return res; // 返回结果字符串
    }

    // 生成新的文本
    public String generateNewText(String inputText) {
        String res = ""; // 初始化结果字符串
        String wordsStr = Lab1.replaceStr(inputText); // 处理输入文本
        String[] words = Lab1.wordSplit(wordsStr); // 分割处理后的文本

        // 遍历输入的单词数组
        for (int i = 0; i < words.length - 1; ++i) {
            String word1 = words[i]; // 当前单词
            String word2 = words[i + 1]; // 下一个单词
            res = res + word1 + " "; // 将当前单词添加到结果字符串中
            TreeNodeList<TreeNode> retNodes = this.calculateBridge(word1, word2); // 计算桥接词列表
            if (retNodes != null && retNodes.size() != 0) {
                if (retNodes.size() == 1) {
                    res = res + "[" + retNodes.get(0).getWord() + "] "; // 如果有一个桥接词，将其添加到结果字符串中
                } else {
                    Random random = new Random(); // 创建随机数生成器
                    int s = random.nextInt(retNodes.size() - 1); // 生成随机索引
                    res = res + "[" + retNodes.get(s).getWord() + "] "; // 将随机选择的桥接词添加到结果字符串中
                }
            }
        }

        res = res + words[words.length - 1]; // 添加最后一个单词
        return res; // 返回结果字符串
    }

    // 计算桥接词
    public TreeNodeList<TreeNode> calculateBridge(String word1, String word2) {
        TreeNode preNode = this.treeNodes.nodeCheck(word1); // 查找第一个单词对应的节点
        TreeNode afterNode = this.treeNodes.nodeCheck(word2); // 查找第二个单词对应的节点
        TreeNodeList<TreeNode> retNodes = null; // 初始化返回的节点列表
        if (preNode != null && afterNode != null) {
            retNodes = new TreeNodeList<>(); // 创建新的节点列表

            // 遍历第一个单词的子节点列表
            for (int i = 0; i < preNode.childList.size(); ++i) {
                // 遍历第二个单词的父节点列表
                for (int j = 0; j < afterNode.parentList.size(); ++j) {
                    // 如果子节点和父节点相同
                    if (preNode.childList.get(i).equals(afterNode.parentList.get(j))) {
                        TreeNode retNode = preNode.childList.get(i); // 获取桥接词节点
                        if (!retNode.equals(preNode) && !retNode.equals(afterNode)) {
                            retNodes.add(preNode.childList.get(i)); // 添加桥接词节点到返回列表中
                        }
                    }
                }
            }
        }
        return retNodes; // 返回桥接词节点列表
    }

    // 计算最短路径
    public String calcShortestPath(String word1, String word2, PathGraphAssist pga) throws CloneNotSupportedException {
        String res = ""; // 初始化结果字符串
        TreeNode startNode = this.treeNodes.nodeCheck(word1); // 查找起始单词对应的节点
        TreeNode endNode = this.treeNodes.nodeCheck(word2); // 查找结束单词对应的节点
        PathNodeList<PathNode> findPaths = new PathNodeList<>(); // 创建路径节点列表
        PathNodeList<PathNode> certainPaths = new PathNodeList<>(); // 创建确定的路径节点列表
        findPaths.add(new PathNode(startNode)); // 将起始节点添加到路径节点列表中

        int j;
        // 当路径节点列表不为空时，继续遍历
        while (findPaths.size() != 0) {
            PathNode popPathNode = findPaths.pop(); // 弹出路径节点列表中的第一个节点
            TreeNode presentNode = popPathNode.presentNode; // 获取当前节点

            // 遍历当前节点的子节点列表
            for (j = 0; j < presentNode.childList.size(); ++j) {
                TreeNode childNode = presentNode.childList.get(j); // 获取子节点
                if (popPathNode.path.nodeCheck(childNode.getWord()) == null) { // 如果子节点不在当前路径中
                    PathNode branchNode = (PathNode) popPathNode.clone(); // 克隆当前路径节点
                    int bridgeWeightValue = presentNode.getWeightOfNode(childNode); // 获取桥接词权重
                    branchNode.pathLength += bridgeWeightValue; // 更新路径长度
                    branchNode.path.add(childNode); // 将子节点添加到路径中
                    branchNode.presentNode = childNode; // 更新当前节点为子节点
                    if (childNode.equals(endNode)) {
                        certainPaths.push(branchNode); // 如果子节点是结束节点，将路径添加到确定的路径列表中
                    } else if (certainPaths.size() != 0) {
                        PathNode path = certainPaths.getShortestPath(); // 获取最短路径
                        if (path.pathLength > branchNode.pathLength) {
                            findPaths.push(branchNode); // 如果当前路径长度小于最短路径长度，将路径添加到路径节点列表中
                        }
                    } else {
                        findPaths.push(branchNode); // 将路径添加到路径节点列表中
                    }
                }
            }
        }

        // 如果确定的路径列表不为空，生成路径字符串
        if (certainPaths.size() != 0) {
            for (int i = 0; i < certainPaths.size(); ++i) {
                res = res + "Path " + i + " :"; // 添加路径编号
                TreeNodeList<TreeNode> path = certainPaths.get(i).path; // 获取路径节点列表

                // 遍历路径节点列表，生成路径字符串
                for (j = 0; j < path.size() - 1; ++j) {
                    res = res + path.get(j).getWord() + "->"; // 添加节点到路径字符串
                }
                res = res + endNode.getWord() + ".\n"; // 添加结束节点到路径字符串
            }
        } else {
            res = res + "There's no path from " + word1 + " to " + word2 + "."; // 如果没有路径，返回提示信息
        }

        pga.allPaths = certainPaths; // 设置路径图辅助对象的所有路径
        return res; // 返回路径字符串
    }

    // 随机游走
    public String randomWalk() {
        String ret = ""; // 初始化返回字符串
        Random random = new Random(); // 创建随机数生成器
        int randomNodeIndex;
        if (this.treeNodes.size() == 1) {
            randomNodeIndex = 0; // 如果只有一个节点，设置随机索引为0
        } else {
            randomNodeIndex = random.nextInt(this.treeNodes.size() - 1); // 否则生成随机索引
        }

        TreeNode startNode = this.treeNodes.get(randomNodeIndex); // 获取起始节点
        TreeNode walkNode = startNode; // 初始化游走节点为起始节点
        TreeNodeList<TreeNode> walkNodes = new TreeNodeList<>(); // 创建游走节点列表
        walkNodes.add(startNode); // 将起始节点添加到游走节点列表中

        // 当游走节点有子节点时，继续游走
        while (walkNode.childList.size() != 0) {
            boolean endState = false; // 初始化结束状态为false
            ret = ret + walkNode.getWord() + " "; // 将游走节点的单词添加到返回字符串中
            if (walkNode.childList.size() == 1) {
                randomNodeIndex = 0; // 如果只有一个子节点，设置随机索引为0
            } else {
                randomNodeIndex = random.nextInt(walkNode.childList.size() - 1); // 否则生成随机索引
            }

            TreeNode nextNode = walkNode.childList.get(randomNodeIndex); // 获取下一个节点
            ArrayList<Integer> multiIndex = walkNodes.multiIndexOf(walkNode); // 获取游走节点在游走节点列表中的多重索引

            // 遍历多重索引，检查是否重复游走
            for (int i = 0; i < multiIndex.size() - 1; ++i) {
                int index = multiIndex.get(i); // 获取索引
                if (walkNodes.get(index + 1).equals(nextNode)) { // 如果下一个节点是游走节点的子节点
                    endState = true; // 设置结束状态为true
                    break;
                }
            }

            if (endState) {
                ret = ret + nextNode.getWord(); // 如果结束，添加下一个节点的单词到返回字符串中
                break;
            }

            walkNode = nextNode; // 更新游走节点为下一个节点
            walkNodes.add(nextNode); // 将下一个节点添加到游走节点列表中
        }

        return ret; // 返回游走结果字符串
    }
}
// Tree类用于构建树结构并进行各种操作，如计算节点层级、查询桥接词、生成新文本、计算最短路径和随机游走。以下是对各方法功能的总结：

// Tree(String[] words)：构造方法，根据给定的单词数组构建树结构。
// calculateNodeLevel()：计算并设置所有节点的层级。
// queryBridgeWords(String word1, String word2)：查询两个单词之间的桥接词。
// generateNewText(String inputText)：生成包含桥接词的新文本。
// calculateBridge(String word1, String word2)：计算两个单词之间的桥接词节点列表。
// calcShortestPath(String word1, String word2, PathGraphAssist pga)：计算两个单词之间的最短路径。
// randomWalk()：进行随机游走并返回游走结果。
// 主要数据结构
// TreeNode：表示树节点。
// TreeNodeList<TreeNode>：用于存储树节点的列表。
// PathNode：表示路径节点。
// PathNodeList<PathNode>：用于存储路径节点的列表。
// 方法功能详解
// 构造方法：构建树结构，初始化根节点和其他节点，并计算节点层级。
// calculateNodeLevel：使用广度优先搜索算法计算并设置每个节点的层级。
// queryBridgeWords：计算两个单词之间的桥接词，并根据计算结果生成描述字符串。
// generateNewText：根据输入文本生成包含桥接词的新文本。
// calculateBridge：计算两个单词之间的桥接词节点列表。
// calcShortestPath：使用广度优先搜索算法计算两个单词之间的最短路径，并生成描述字符串。
// randomWalk：从随机起始节点进行随机游走，生成游走结果字符串。
// 这个类通过实现多种功能，为处理树结构和路径问题提供了丰富的工具和方法。
