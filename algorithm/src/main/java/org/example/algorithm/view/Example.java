package org.example.algorithm.view;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;
import java.util.Collections;

public class Example{
    public static void main(String[] args){
        DefaultMutableTreeNode tree=new DefaultMutableTreeNode("0");
        DefaultMutableTreeNode c1=new DefaultMutableTreeNode("1");
        DefaultMutableTreeNode c2=new DefaultMutableTreeNode("2");
        DefaultMutableTreeNode c1_1=new DefaultMutableTreeNode("3");
        DefaultMutableTreeNode c1_2=new DefaultMutableTreeNode("4");
        tree.add(c1);
        tree.add(c2);
        c1.add(c1_1);
        c1.add(c1_2);
        c1_1.add(new DefaultMutableTreeNode("5"));
        c1_1.add(new DefaultMutableTreeNode("6"));
        c1_1.add(new DefaultMutableTreeNode("7"));
        c1_2.add(new DefaultMutableTreeNode("8"));
        c1_2.add(new DefaultMutableTreeNode("9"));
        c2.add(new DefaultMutableTreeNode("10"));

        var pt = new PrettyPrintTree<DefaultMutableTreeNode>(
                (x) -> new ArrayList(Collections.list(x.children())),
                (x) -> x.getUserObject().toString()
        );
        pt.display(tree);
        System.out.println();
    }
}
