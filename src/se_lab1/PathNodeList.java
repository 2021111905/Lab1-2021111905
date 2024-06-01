package se_lab1;

import java.util.ArrayList;

// PathNodeList类继承自ArrayList，用于存储路径节点
public class PathNodeList<E> extends ArrayList<E> {
    private static final long serialVersionUID = -1372237003015374659L;

    // 构造方法
    public PathNodeList() {
    }

    // 添加元素到列表末尾
    public boolean push(E e) {
        return this.add(e);
    }

    // 从列表头部移除并返回第一个元素
    public PathNode pop() {
        PathNode node = null;
        if (this.size() != 0) {
            node = (PathNode)this.get(0);
            this.remove(0);
        }

        return node;
    }

    // 获取路径长度最短的节点
    public PathNode getShortestPath() {
        PathNode retNode = null;
        int shortestLength = Integer.MAX_VALUE;

        // 遍历列表，找出路径长度最短的节点
        for(int i = 0; i < this.size(); ++i) {
            PathNode node = (PathNode)this.get(i);
            if (node.pathLength < shortestLength) {
                retNode = node;
                shortestLength = node.pathLength;
            }
        }

        return retNode;
    }
}
// PathNodeList类继承自ArrayList，用于存储PathNode对象的列表，并提供了一些额外的方法来操作这些对象。其主要功能和特性包括：

// 构造方法：

// PathNodeList()：初始化一个新的PathNodeList对象。
// 成员方法：

// boolean push(E e)：将元素添加到列表中，相当于add方法。
// PathNode pop()：移除并返回列表中的第一个元素。如果列表为空，返回null。
// PathNode getShortestPath()：遍历列表，找到路径长度最短的PathNode对象并返回。
// 主要数据结构
// ArrayList<E>：继承自Java标准库的ArrayList，用于存储任意类型的元素。
// 方法功能
// push方法：将元素添加到列表的末尾，使用ArrayList的add方法实现。
// pop方法：从列表中移除并返回第一个元素。如果列表为空，则返回null。
// getShortestPath方法：遍历整个列表，找到路径长度最短的PathNode对象并返回。
// 这个类扩展了ArrayList的功能，特别是为路径节点提供了一些特定的操作方法，例如添加、移除和查找最短路径节点。这对于路径图算法中的节点管理和操作非常有用。