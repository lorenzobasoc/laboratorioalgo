import java.util.Scanner;

public class Bst {
    public static void main(String[] args) {
        //var input = args[0].split("\s");
        //System.out.println(args[0]); 
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] ss = input.split(" ");
        System.out.println(IsBst(ss));
    }
    public static int IsBst(String[] A) {
        int n = A.length();
        int key = Integer.parseInt(A[0]);
        Node root = new Node(key);
        Tree tree = new Tree(root);
        String[] parents = new String[A.length()];
        for (int l = 0; l < parents.length(); l++) {
            parents[l] = "";
        }
        parents[0] = A[0];
        int j = 0;
        int count = 0;
        Boolean go = true;
        for (int i = 1; i < A.length(); i++) {
            if (A[i] != "NULL") {
                if (count == 1) {
                    tree.add(A[i], parent[j], false);
                    parent[j] = A[i];
                    count = 0;
                    go = true;
                } else {
                    tree.add(A[i], parent[j], true);
                    j++;
                    parent[j] = A[i];
                }
        } else {
            if (go) {
                int k = i;
                while (A[k] == "NULL") {
                    count++;
                    if (k != n - 1) {
                        k++;
                    } else {
                        break;
                    }
                }
                while (count != 1) {
                    parent[j] = "";
                    j--;
                    count--;
                    go = false;
                }
            }
        }

        }
        Boolean result = tree.check(tree.root, Integer.minValue, Integer.maxValue);
        return result ? 1 : 0;
    }

    public class Tree {
        Node root;

        public Tree(Node n) {
            root = n;
        }
        
        public void add(String n, String parent, Boolean isLeftSon) {
            Node node = new Node(Integer.parseInt(n));
            Node parent = find(Integer.ParseInt(parent));
            if (isLeftSon) {
                parent.left = node;
            } else {
                parent.right = node;
            }
        }

        public Node find(int key) {
            return findRecursive(this.root, key);
        }

        private Node findRecursive(Node node, int key){
            if (key == node.key ) {
                return node;
            } else if (key < node.key) {
                return findRecursive(node.left, key);
            } else {
                return findRecursive(node.right, key);
            }
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
    }
