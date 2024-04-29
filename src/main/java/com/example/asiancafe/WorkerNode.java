import java.io.File;

class WorkerNode implements

abstract class Worker {
    Worker name;
    Worker worker;
    WorkerNode left, right;

    public WorkerNode(Worker worker) {
        this.worker = worker;
        this.left = this.right = null;
    }
}
class BST implements abstract class Worker{
    private WorkerNode root;

    public BST() {
        this.root = null;
    }

    public void insert(Worker worker) {
        root = insertRec(root, worker);
    }

    private WorkerNode insertRec(WorkerNode root, Worker worker) {
        if (root == null) {
            root = new WorkerNode(worker);
            return root;
        }

        if (worker.name.compareTo(root.worker.name) < 0) {
            root.left = insertRec(root.left, worker);
        } else if (worker.name.compareTo(root.worker.name) > 0) {
            root.right = insertRec(root.right, worker);
        }

        return root;
    }

    public WorkerNode search(String name) {
        return searchRec(root, name);
    }

    private WorkerNode searchRec(WorkerNode root, String name) {
        if (root == null || root.worker.name.equals(name)) {
            return root;
        }

        if (name.compareTo(root.worker.name) < 0) {
            return searchRec(root.left, name);
        }

        return searchRec(root.right, name);
    }

    public void inorder() {
        inorderRec(root);
    }

    private void inorderRec(WorkerNode root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.println(root.worker.name);
            inorderRec(root.right);
        }
    }
}
