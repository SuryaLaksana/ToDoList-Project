public class TreeCategory {
     private NodeCategory root;

    public TreeCategory(String rootName) {
        this.root = new NodeCategory(rootName);
    }

    public NodeCategory getRoot() {
        return root;
    }

    public void addCategory(String parentName, String newCategoryName) {
        NodeCategory parent = search(root, parentName);

        if (parent == null) {
            System.out.println("Parent '" + parentName + "' tidak ditemukan.");
            return;
        }
        NodeCategory newNode = new NodeCategory(newCategoryName);

        if (parent.firstChild == null) {
            parent.firstChild = newNode;
        } else {
            NodeCategory current = parent.firstChild;
            while (current.nextSibling != null) {
                current = current.nextSibling;
            }
            current.nextSibling = newNode;
        }
    }

    private NodeCategory search(NodeCategory node, String name) {

        if (node == null) return null;

        if (node.name.equalsIgnoreCase(name))
            return node;

        NodeCategory found = search(node.firstChild, name);
        if (found != null) return found;

        return search(node.nextSibling, name);
    }

    public void display(NodeCategory node, String indent) {
        if (node == null) return;

        System.out.println(indent + "- " + node.name);

        display(node.firstChild, indent + "  ");
        display(node.nextSibling, indent);
    }
}
