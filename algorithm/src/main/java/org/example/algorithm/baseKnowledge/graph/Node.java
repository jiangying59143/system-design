package org.example.algorithm.baseKnowledge.graph;

import java.util.ArrayList;
import java.util.List;

public class Node {
    public int value;
    public List<Edge> edges;
    // 入度
    public int in;
    // 出度
    public int out;

    public Node(int value) {
        this.value = value;
        edges = new ArrayList<>();
    }
}
