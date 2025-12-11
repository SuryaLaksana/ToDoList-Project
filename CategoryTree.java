class CategoryNode {
    String name;
    DoublyLinkedList tasks; 
    CategoryNode firstChild; 
    CategoryNode nextSibling; 

    public CategoryNode(String name) {
        this.name = name;
        this.tasks = new DoublyLinkedList();
        this.firstChild = null;
        this.nextSibling = null;
    }
}

public class CategoryTree {
    public CategoryNode root;

    public CategoryTree() {
        root = new CategoryNode("All Tasks");
    }

    public CategoryNode findCategory(CategoryNode node, String name) {
        if (node == null) return null;
        if (node.name.equalsIgnoreCase(name)) return node;
        
        CategoryNode foundInChild = findCategory(node.firstChild, name);
        if (foundInChild != null) return foundInChild;
        
        return findCategory(node.nextSibling, name);
    }

    public void addSubCategory(String parentName, String newCatName) {
        CategoryNode parent = findCategory(root, parentName);
        if (parent != null) {
            CategoryNode newNode = new CategoryNode(newCatName);
            if (parent.firstChild == null) {
                parent.firstChild = newNode;
            } else {
                CategoryNode current = parent.firstChild;
                while (current.nextSibling != null) {
                    current = current.nextSibling;
                }
                current.nextSibling = newNode;
            }
        }
    }
}