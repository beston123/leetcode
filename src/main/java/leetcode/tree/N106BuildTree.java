package leetcode.tree;

/**
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 * <p>
 * 注意:
 * 你可以假设树中没有重复的元素。
 * <p>
 * 例如，给出
 * <p>
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * <p>
 * 返回如下的二叉树：
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * 来源：力扣（LeetCode） 106
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class N106BuildTree {

    public static void main(String[] args) {
        int[] postorder = {9,15,7,20,3};
        int[] inorder = {9, 3, 15, 20, 7};
        TreeNode node = new N106BuildTree().buildTree( inorder,postorder);

    }
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length == 0 || postorder == null || postorder.length == 0 || inorder.length != postorder.length) {
            return null;
        }
        return help(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);

    }

    private TreeNode help(int[] inorder, int inStart, int inEnd, int[] postorder, int posStart, int posEnd) {
        //递归终止条件
        if (inStart > inEnd || posStart > posEnd) {
            return null;
        }
        //后续遍历的最后一个节点就是根节点
        TreeNode head = new TreeNode(postorder[posEnd]);
        int index = 0;
        while (inorder[inStart+index] != postorder[posEnd]) {
            index++;
        }
        head.left = help(inorder, inStart, inStart + index - 1, postorder, posStart, posStart + index - 1);
        head.right = help(inorder, inStart + index + 1, inEnd, postorder, posStart + index, posEnd - 1);
        return head;
    }
}
