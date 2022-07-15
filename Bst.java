import java.util.Scanner;

public class Bst {
    public static void main(String[] args) {
        //var input = args[0].split("\s");
        //System.out.println(args[0]); 
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] ss = input.split(" ");
        Bst b = new Bst();
        System.out.println(b.IsBst(ss));
    }
   public int IsBst(String[] A) {
        if (A[0].equals("NULL")) {
            return 1;
        }
        int n = A.length;
        int key = Integer.parseInt(A[0]);
        Node root = new Node(key);
        Tree tree = new Tree(root);
        String[] parents = new String[n];
        for (int l = 0; l < parents.length; l++) {
            parents[l] = "";
        }
        parents[0] = A[0];
        int j = 0;
        int count = 0;
        Boolean go = true;
        for (int i = 1; i < n; i++) {
            if (!A[i].equals("NULL")) {
                if (count == 1) {
                    tree.add(A[i], parents[j], false);
                    parents[j] = A[i];
                    count = 0;
                    go = true;
                } else {
                    tree.add(A[i], parents[j], true);
                    j++;
                    parents[j] = A[i];
                }
        } else {
            if (go) {
                int k = i;
                while (A[k].equals("NULL")) {
                    count++;
                    if (k != n - 1) {
                        k++;
                    } else {
                        break;
                    }
                }
                while (count != 1) {
                    parents[j] = "";
                    j--;
                    count--;
                    go = false;
                }
            }
        }

        }
        Boolean result = tree.check(tree.root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return result ? 1 : 0;
    }

    class Node {
            public int key;
            public String value;
            public Node right;
            public Node left;

            public Node(int key){
                this.key = key;
                right = null;
                left = null;
            } 
        }

     class Tree {
        Node root;

        public Tree(Node n) {
            root = n;
        }
        
        public void add(String n, String p, Boolean isLeftSon) {
            Node node = new Node(Integer.parseInt(n));
            Node parent = find(Integer.parseInt(p));
            if (parent!=null){
            if (isLeftSon) {
                parent.left = node;
            } else {
                parent.right = node;
            }}
        }

        public Node find(int key) {
            return findRecursive(this.root, key);
        }

        private Node findRecursive(Node node, int key){
            if (node == null) {
                    return null;
                }
                if (node.key == key) {
                    return node;
                }
                Node res1 = findRecursive(node.left, key);
                if(res1 != null) {
                    return res1;
                }
                Node res2 = findRecursive(node.right, key);
                return res2;
        }
    

        public Boolean check(Node node, int min, int max) {
            if (node == null) {
                return true;
            }
            if (node.key < min || node.key > max) {
                return false;
            }
            return check(node.left, min, node.key - 1) && check(node.right, node.key + 1, max);
        }

    }
}