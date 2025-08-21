
public class Main {
    public static void main(String[] args) {

        int size = 10;

        System.out.println("Hello world!");
        BinaryTree tree = new BinaryTree();
        for (int i = 1; i < size; i++) {
            tree.insert((int) (Math.random() * 10000));
        }
        System.out.println(tree.in_order_traversal());
        System.out.println("Number of nodes: " + tree.count_nodes());
        System.out.println("Depth: " + tree.depth());

        tree = new BinaryTree();
        for (int i = 1; i < size; i++) {
            tree.insert((int) (100));
        }
        System.out.println(tree.in_order_traversal());
        System.out.println("Number of nodes: " + tree.count_nodes());
        System.out.println("Depth: " + tree.depth());

        tree = new BinaryTree();
        for (int i = 1; i < size; i++) {
            tree.insert(i);
        }
        System.out.println(tree.in_order_traversal());
        System.out.println("Number of nodes: " + tree.count_nodes());
        System.out.println("Depth: " + tree.depth());
    }
}