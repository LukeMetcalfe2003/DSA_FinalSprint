package com.example.demo;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import com.fasterxml.jackson.databind.ObjectMapper;


public class DemoAplicationTests {

    public static class BinarySearchTree {
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

    @Test
    public void testInsert() {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(5);
        bst.insert(3);
        bst.insert(9);

        BinarySearchTree.TreeNode root = bst.getRoot();

        assertNotNull(root);
        assertEquals(5, root.value);
        assertEquals(3, root.left.value);
        assertEquals(9, root.right.value);
    }

    @Test
    public void testInOrderTraversal() {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(4);
        bst.insert(3);
        bst.insert(10);
        bst.insert(9);
        bst.insert(5);

        List<Integer> inOrder = bst.inorderTraversal();

        assertEquals(5, inOrder.size());
        assertEquals(3, inOrder.get(0));
        assertEquals(4, inOrder.get(1));
        assertEquals(5, inOrder.get(2));
        assertEquals(9, inOrder.get(3));
        assertEquals(10, inOrder.get(4));
    }

    @Test
    public void testEmptyTree() {
        BinarySearchTree bst = new BinarySearchTree();

        BinarySearchTree.TreeNode root = bst.getRoot();

        assertNull(root);
    }

    @org.junit.jupiter.api.Test
    public void testToJson() throws Exception {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(5);
        bst.insert(3);
        bst.insert(9);
        bst.insert(10);
        bst.insert(24);

        String json = bst.toJson();

        assertNotNull(json);

        ObjectMapper objectMapper = new ObjectMapper();
        Map treeMap = objectMapper.readValue(json, Map.class);

        assertEquals(5, treeMap.get("value"));

        Map<String, Object> left = (Map<String, Object>) treeMap.get("left");
        assertNotNull(left);
        assertEquals(3, left.get("value"));

        Map<String, Object> right = (Map<String, Object>) treeMap.get("right");
        assertNotNull(right);
        assertEquals(9, right.get("value"));

        Map<String, Object> rightRight = (Map<String, Object>) right.get("right");
        assertNotNull(rightRight);
        assertEquals(10, rightRight.get("value"));

        Map<String, Object> rightRightRight = (Map<String, Object>) rightRight.get("right");
        assertNotNull(rightRightRight);
        assertEquals(24, rightRightRight.get("value"));
    }
    @Test
    public void testBalancedTree() {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(10);
        bst.insert(6);
        bst.insert(15);
        bst.insert(4);
        bst.insert(8);
        bst.insert(12);
        bst.insert(18);

        BinarySearchTree.TreeNode root = bst.getRoot();

        assertNotNull(root);
        assertEquals(10, root.value);
        assertEquals(6, root.left.value);
        assertEquals(15, root.right.value);
        assertEquals(4, root.left.left.value);
        assertEquals(8, root.left.right.value);
        assertEquals(12, root.right.left.value);
        assertEquals(18, root.right.right.value);
    }

}
