import java.util.*;

public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    public static void pre(TreeNode root) {
        if (root != null) {
            System.out.println(root.val);
            pre(root.left);
            pre(root.right);
        }
    }

    public static void in(TreeNode root) {
        if (root != null) {
            in(root.left);
            System.out.println(root.val);
            in(root.right);
        }
    }

    public static void preorderTraversal(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode p = root;
        while (!stack.isEmpty() || p != null) {
            if (p != null) {
                stack.push(p);
                // 访问根节点
                System.out.print(p.val + " ");
                p = p.left;
            } else {
                p = stack.pop();
                p = p.right;
            }
        }
    }

    // This approach is based on Morris's article
    // 空间复杂度是O(1)
    public static void morrisTraversal(TreeNode root) {
        TreeNode node = root;
        while (node != null) {
            if (node.left == null) {
                System.out.print(node.val + " ");
                node = node.right;
            } else {
                // 中序排列前任节点
                TreeNode predecessor = node.left;
                while (predecessor.right != null && (predecessor.right != node)) {
                    predecessor = predecessor.right;
                }
                if (predecessor.right == null) {
                    System.out.print(node.val + " ");
                    predecessor.right = node;
                    node = node.left;
                } else {
                    predecessor.right = null;
                    node = node.right;
                }
            }
        }
    }

    public static void back(TreeNode root) {
        if (root != null) {
            back(root.left);
            back(root.right);
            System.out.println(root.val);
        }
    }

    public static void df(TreeNode root) {
        pre(root);
    }

    public static void bf(TreeNode root) {
        if (root == null)
            return;
        LinkedList<TreeNode> trs = new LinkedList<TreeNode>();

        trs.add(root);

        int height = getHeight(root, 0);
        int dist = 0;
        System.out.print(root.val);
        System.out.println();
        while (!trs.isEmpty() && dist < height) {
            LinkedList<TreeNode> tmp = new LinkedList<>();
            while (!trs.isEmpty()) {
                TreeNode t = trs.pollFirst();
                if (t.left != null) {
                    tmp.add(t.left);
                    System.out.print(t.left.val + " ");
                } else {
                    System.out.print("# ");
                }
                if (t.right != null) {
                    System.out.print(t.right.val + " ");
                    tmp.add(t.right);
                } else {
                    System.out.print("# ");
                }
            }
            dist++;
            System.out.println();
            trs = tmp;
        }
    }

    /**
     * `8,#,9,#,10`,bp
     * 
     * @param s
     * @return
     */
    public static TreeNode getRoot(String s) {
        // check ..
        String[] in = s.split(",");
        if (in.length == 0) {
            return null;
        }
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        int index = 0;
        TreeNode root = new TreeNode(Integer.valueOf(in[index]));
        stack.addFirst(root);
        while (true) {
            TreeNode tmp = stack.pollLast();
            index++;
            if (index >= in.length)
                break;
            if (!in[index].equals("#")) {
                tmp.left = new TreeNode(Integer.valueOf(in[index]));
                stack.addFirst(tmp.left);
            }
            index++;
            if (index >= in.length)
                break;
            if (!in[index].equals("#")) {
                tmp.right = new TreeNode(Integer.valueOf(in[index]));
                stack.addFirst(tmp.right);
            }
        }
        return root;
    }

    public static int getHeight(TreeNode root, int height) {
        if (root == null)
            return height;
        height++;
        return Math.max(getHeight(root.left, height), getHeight(root.right, height));
    }

    /**
     * 8,8,7,9,2,#,#,#,#,4,7, bf # present leaf
     * 
     * @param s
     */
    public static TreeNode getRoot1(String s) {
        // check ..
        String[] in = s.split(",");
        TreeNode root = new TreeNode(Integer.valueOf(in[0]));
        getRoot1(root, in, 0, in.length - 1);
        return root;
    }

    private static TreeNode getRoot1(TreeNode root, String[] s, int start, int end) {

        if (root == null || start > end) {
            return null;
        }

        int leftIndex = 2 * start + 1;
        int rightIndex = 2 * start + 2;

        if (leftIndex <= end && !s[leftIndex].equals("#")) {
            root.left = new TreeNode(Integer.valueOf(s[leftIndex]));
        }

        if (rightIndex <= end && !s[rightIndex].equals("#")) {
            root.right = new TreeNode(Integer.valueOf(s[rightIndex]));
        }

        if (root != null) {
            getRoot1(root.left, s, leftIndex, s.length - 1);
            getRoot1(root.right, s, rightIndex, s.length - 1);
        }

        return null;
    }

}