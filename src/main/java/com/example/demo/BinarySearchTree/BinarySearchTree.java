package com.example.demo.BinarySearchTree;
import com.fasterxml.jackson.core.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {
    private TreeNode root;

    public class TreeNode {
        int value;
        TreeNode left, right;

        public TreeNode(int value) {
            this.value = value;
            left = right = null;
        }
    }

    public void insert(int value) {
        root = insertRecord(root, value);
    }

    private TreeNode insertRecord(TreeNode root, int value) {
        if (root == null) {
            root = new TreeNode(value);
            return root;
        }

        if (value < root.value) {
            root.left = insertRecord(root.left, value);

        } else {
            root.right = insertRecord(root.right, value);
        }

        return root;
    }

    public List<Integer> inorderTraversal() {
        List<Integer> result = new ArrayList<>();
        inorderTraversalRecord(root, result);
        return result;
    }

    private void inorderTraversalRecord(TreeNode root, List<Integer> result) {
        if (root != null) {
            inorderTraversalRecord(root.left, result);
            result.add(root.value);
            inorderTraversalRecord(root.right, result);
        }
    }

}
