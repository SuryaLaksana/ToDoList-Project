public class NodeCategory {
    String name;
    NodeCategory firstChild;
    NodeCategory nextSibling;

    public NodeCategory(String name) {
        this.name = name;
        this.firstChild = null;
        this.nextSibling = null;
    }
}