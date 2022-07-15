import java.util.*;

public class AVLT{

    //================================================================================
    // NODE
    //================================================================================

    /**
     *  Classe interna per la rappresentazione di un nodo
     *  Ogni nodo contiene:
     *  - key: valore numerico (int)
     *  - value: valore alfanumerico associato alla chiave (String)
     *  - height: altezza relativa a quel nodo (int)
     *  - left, right: puntatori ai nodi figli (Node)
     */
	class Node{

        int key;
        String value;
        int height;

        Node right;
        Node left;

        /**
         * Costruttore per la creazione di un nodo con i campi:
         * @param key: chiave del nodo (int)
         * @param value: valore alfanumerico (String)
         * e l'altezza = 1
         */
        Node(int key, String value){
            this.key = key;
            this.value = value;
            height = 1;

            right = null;
            left = null;
        }
    }


    //================================================================================
    // AVLT - Balanced Binary Search Tree
    //================================================================================

	Node node;

     /**
     * Costruttore per la creazione di un AVLT vuoto
     */
	public AVLT(){
		node = null;
	}

    /**
     * metodo privato che ritorna l'altezza del nodo
     * @param node: nodo di cui si vuole sapere l'altezza (Node)
     * @return altezza del nodo node (int)
     */
	private int getHeight(Node node){
		if(node == null){
			return 0;
		} else{
			return node.height;
        }
	}

    /**
     * Metodo privato che ritorna la differenza tra le altezze dei figli di un nodo.
     * Necessario per controllare se l'albero è bilanciato o meno
     * @param node: nodo di cui si vuole sapere la differenza di altezze dei suoi nodi figli (Node)
     * @return differenza di altezze dei figli del nodo node (int)
     */
	private int getBalance(Node node){
		if(node == null){
			return 0;
		} else{
			return getHeight(node.left) - getHeight(node.right);
		}
	}

	/**
	 * Metodo di supporto per calcolare massimo tra x e y
	 * @param x valore (int)
	 * @param y valore (int)
	 * @return massimo tra x e y (int)
	 */
	private int getMax(int x, int y){
		if(x > y){
			return x;
		}else{
			return y;
		}
	}

    /**
     * Metodo pubblico che aggiunge un nodo all'AVLT chiamando addRecursive
     * @param key: chiave del nodo (int)
     * @param value: valore alfanumerico (String)
     */
	public void add(int key, String value){
		node = addRecursive(node, key, value);
	}

    /**
     * Metodo pubblico che rimuove un nodo all'AVLT chiamando deleteRecursive
     * @param key: chiave del nodo (int)
     */
    public void delete(int key){
		node = deleteRecursive(node, key);
	}

    /**
     * metodo pubblico che ricerca il valore associato alla chiave passata
     * @param key: chiave del nodo (int)
     * @return valore alfanumerico associato a key (String)
     */
	public String find(int key) {
		return findRecursive(node, key);
	}

	/**
     * Metodo privato che svolge effettivamente l'inserimento del nodo nell'AVLT:
     * effettuo un inserimento normale come nei BST e successivamente gestisco i casi
     * in cui l'albero non sia bilanciato svolgendo rotazioni, a dx o a sx
     * @param node: nodo radice dell'AVLT da "agganciare" al nuovo nodo (Node)
     * @param key: chiave del nodo (int)
     * @param value: valore alfanumerico (String)
     * @return nodo radice con un figlio con chiave key e valore value (Node)
     */
	private Node addRecursive(Node node, int key, String value){

        //normale inserimento come nei BST
		if(node == null){
            return new Node(key, value);
        }
        if(key < node.key){
            node.left = addRecursive(node.left, key, value);
        }else if(key > node.key) {
            node.right = addRecursive(node.right, key, value);
        }else{
            return node;
        }

        //aggiorno l'altezza del nodo
		node.height = 1 + getMax(getHeight(node.left), getHeight(node.right));

        //controllo se l'albero è bilanciato e gestisco i vari casi
		int balance = getBalance(node);
		if(balance > 1 && key < node.left.key){
			return rightRotate(node);
		}
		if(balance < -1 && key > node.right.key){
			return leftRotate(node);
		}
		if(balance > 1 && key > node.left.key){
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}
		if(balance < -1 && key < node.right.key){
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}
		return node;
	}

	/**
     * metodo che esegue una rotazione a destra con perno il nodo passato come parametro,
     * @param node: nodo perno della rotazione a destra (Node)
     * @return nodo radice dopo aver svolto la rotazione a destra (Node)
     */
	public Node rightRotate(Node node) {
        Node x = node.left;
        Node y = x.right;
        x.right = node;
        node.left = y;
        node.height = getMax(getHeight(node.left), getHeight(node.right)) + 1;
        x.height = getMax(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    /**
     * metodo che esegue una rotazione a sinistra con perno il nodo passato come parametro,
     * @param node : nodo perno della rotazione a sinistra (Node)
     * @return nodo radice dopo aver svolto la rotazione a sinistra (Node)
     */
    public Node leftRotate(Node node) {
        Node x = node.right;
        Node y = x.left;
        x.left = node;
        node.right = y;
        node.height = getMax(getHeight(node.left), getHeight(node.right)) + 1;
        x.height = getMax(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    /**
     * metodo privato che svolge effettivamente la ricerca del valore
     * @param node: nodo radice dell'AVLT (Node)
     * @param key: chiave cercata (int)
     * @return valore associato alla chiave key cercata (String)
     */
	private String findRecursive(Node node, int key){
        if(node == null)
            return "null";
        if(key == node.key){
            return node.value;
        }else if(key < node.key){
            return findRecursive(node.left, key);
        }else{
            return findRecursive(node.right, key);
        }
    }

    private Node minValueNode(Node node){
        Node current = node;

        while (current.left != null)
        current = current.left;

        return current;
    }

     /**
     * metodo privato che svolge il remove di un valore e successivamente gestisco i casi
     * in cui l'albero non sia bilanciato svolgendo rotazioni, a dx o a sx
     * @param node: nodo radice dell'AVLT (Node)
     * @param key: chiave cercata (int)
     * @return l'oggetto nodo dopo il remove
     */

    private Node deleteRecursive(Node node, int key){
        // STEP 1
        if (node == null)
            return node;

        if (key < node.key)
            node.left = deleteRecursive(node.left, key);

        else if (key > node.key)
            node.right = deleteRecursive(node.right, key);

        else {
            if ((node.left == null) || (node.right == null)){
                Node temp = null;
                if (temp == node.left)
                    temp = node.right;
                else
                    temp = node.left;

                if (temp == null)
                {
                    temp = node;
                    node = null;
                }
                else
                    node = temp;
                }
            else{
                Node temp = minValueNode(node.right);
                node.key = temp.key;
                node.value = temp.value;
                node.right = deleteRecursive(node.right, temp.key);
            }
        }
        if (node == null)
            return node;

        // STEP 2
        node.height = getMax(getHeight(node.left), getHeight(node.right)) + 1;

        // STEP 3
        int balance = getBalance(node);

        if (balance > 1 && getBalance(node.left) >= 0)
            return rightRotate(node);
        if (balance > 1 && getBalance(node.left) < 0){
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        if (balance < -1 && getBalance(node.right) <= 0)
            return leftRotate(node);
        if (balance < -1 && getBalance(node.right) > 0){
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

	/**
     * metodo per la stampa del AVLT in pre-order partendo dalla radice
     * @param node: nodo radice dell'AVLT (Node)
     */
	private void printTree(Node node){
        if (node == null){
            System.out.print("NULL ");
        }else{
            System.out.print(node.key + ":" + node.value + ":"+node.height+" ");
            printTree(node.left);
            printTree(node.right);
        }
	}

    //================================================================================
    // Main
    //================================================================================

    /**
     * Main
     * @param args
     */
    public static void main(String[] args){
        AVLT tree = new AVLT();
        ArrayList<String[]> list = new ArrayList<String[]>();

        Scanner sc = new Scanner(System.in);
        String line = "";
        while (!line.equals("exit")) {
            line = sc.nextLine();
            String[] parts = line.split(" ");
            list.add(parts);
        }
        sc.close();

        //gestione degli input
        for(int i = 0; i < list.size()-1; i++){
            switch(list.get(i)[0]){
                case "insert":
                    tree.add(Integer.parseInt(list.get(i)[1]), list.get(i)[2]);
                    break;
                case "find":
                    System.out.println(tree.find(Integer.parseInt(list.get(i)[1])));
                    break;
                case "remove":
                    tree.delete(Integer.parseInt(list.get(i)[1]));
                    break;
                case "show":
                    tree.printTree(tree.node);
                    break;
            }
        }
    }
}