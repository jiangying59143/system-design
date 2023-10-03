package org.example.algorithm.baseKnowledge;

import org.example.algorithm.dataStructure.BinaryTreeNode;

import java.util.LinkedList;

public class BinaryTreeTraversal {
    enum Order{
        pre,
        in,
        post
    }
    public static void order(BinaryTreeNode node,Order order){
        if(node ==  null){
            return;
        }
        if(order == order.pre){
            System.out.print(node.val + "  ");
        }
        order(node.left, order);
        if(order == order.in){
            System.out.print(node.val + "  ");
        }
        order(node.right, order);
        if(order == order.post){
            System.out.print(node.val + "  ");
        }
    }

    public static void preOrder(BinaryTreeNode node){
        if(node == null){
            return;
        }
        LinkedList<BinaryTreeNode> stack  =  new LinkedList<>();
        stack.push(node);
        while(!stack.isEmpty()){
            node = stack.pop();
            System.out.print(node.val + "  ");
            if(node.right != null) stack.push(node.right);
            if(node.left  != null) stack.push(node.left);
        }
    }

    public static void inorder(BinaryTreeNode node){
        if(node == null){
            return;
        }
        LinkedList<BinaryTreeNode> stack  =  new LinkedList<>();
        while(node != null || !stack.isEmpty()){
            while(node != null){
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            System.out.print(node.val + "  ");
            node = node.right;
        }
    }

    public static void postorder(BinaryTreeNode node){
        if(node == null){
            return;
        }
        LinkedList<BinaryTreeNode> stack  =  new LinkedList<>();
        LinkedList<BinaryTreeNode> stack2  =  new LinkedList<>();
        stack.push(node);
        while(!stack.isEmpty()){
            node = stack.pop();
            stack2.push(node);
            if(node.left  != null) stack.push(node.left);
            if(node.right != null) stack.push(node.right);
        }
        while(!stack2.isEmpty()){
            System.out.print(stack2.pop().val + "  ");
        }
    }

    public static void morris(BinaryTreeNode node, Order order){
        if(node == null){
            return;
        }
        BinaryTreeNode head  = node;
        while(node != null){
            BinaryTreeNode mostRight = node.left;
            if(mostRight != null){
                while(mostRight.right != null && mostRight.right != node){
                    mostRight =  mostRight.right;
                }
                if(mostRight.right == null){
                    if(order == Order.pre) System.out.print(node.val + "  ");
                    mostRight.right = node;
                    node = node.left;
                    continue;
                }else{
                    mostRight.right = null;
                    if(order== Order.post) morrisPosPrint(node.left);
                }
            }else{
                if(order == Order.pre) System.out.print(node.val + "  ");
            }
            if(order == Order.in) System.out.print(node.val + "  ");
            node = node.right;
        }
        if(order== Order.post) morrisPosPrint(head);
        System.out.println();
    }

    private static void morrisPosPrint(BinaryTreeNode node){
        node = reverseList(node);
        BinaryTreeNode head = node;
        while(node != null){
            System.out.print(node.val + "  ");
            node = node.right;
        }
        reverseList(head);

    }

    private static BinaryTreeNode reverseList(BinaryTreeNode node){
        BinaryTreeNode dummyHead = new BinaryTreeNode(-1),next;
        while(node!= null){
            next = dummyHead.right;
            dummyHead.right = node;
            node=node.right;
            dummyHead.right.right= next;
        }
        return dummyHead.right;
    }

    public static void main(String[] args) {
        BinaryTreeNode root = new BinaryTreeNode(1);
        BinaryTreeNode left = root.left(2);
        BinaryTreeNode right = root.right(3);
        left.left(4);left.right(5);
        right.left(6); right.right(7);
        BinaryTreeNode.printTree(root);
        preOrder(root);
        System.out.println();
        inorder(root);
        System.out.println();
        postorder(root);
        System.out.println();
        System.out.println("morris");
        morris(root, Order.pre);
        morris(root, Order.in);
        morris(root, Order.post);
        BinaryTreeNode.printTree(root);
    }
}
