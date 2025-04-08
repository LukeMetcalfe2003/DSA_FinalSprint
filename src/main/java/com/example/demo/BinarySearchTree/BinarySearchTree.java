package com.example.demo.BinarySearchTree;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

public class BinarySearchTree {
    private TreeNode root;

    public static class TreeNode {
        int value;
        TreeNode left, right;

        public TreeNode(int value) {
            this.value = value;
            left = right = null;
        }
    }

    public TreeNode getRoot() {
        return root;
    }

    public void insert(int value) {
        root = insertRec(root, value);
    }

    private TreeNode insertRec(TreeNode root, int value) {
        if (root == null) {
            root = new TreeNode(value);
            return root;
        }

        if (value < root.value) {
            root.left = insertRec(root.left, value);
        } else {
            root.right = insertRec(root.right, value);
        }

        return root;
    }

    public List<Integer> inorderTraversal() {
        List<Integer> result = new ArrayList<>();
        inorderTraversalRec(root, result);
        return result;
    }

    private void inorderTraversalRec(TreeNode root, List<Integer> result) {
        if (root != null) {
            inorderTraversalRec(root.left, result);
            result.add(root.value);
            inorderTraversalRec(root.right, result);
        }
    }

    public String toJson() {
        if (root == null) return "{}";

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(this.toJsonRec(root));
        } catch (Exception e) {
            e.printStackTrace();
            return "{}";
        }
    }

    public Map<String, Object> toJsonObject() {
        return toJsonRec(root);
    }

    public Map<String, Object> toJsonRec(TreeNode node) {
        if (node == null) return null;

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("value", node.value);
        map.put("left", toJsonRec(node.left));
        map.put("right", toJsonRec(node.right));
        return map;
    }
}
