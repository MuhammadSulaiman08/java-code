import java.util.*;

class Node {
    Node prev;
    Node next;
    public String name;
    public Node(String Name) {
        this.name = Name;
        this.prev = null;
        this.next = null;
    }
}

class DoubleLinkedList {
    private Node head;
    private Node tail;

    public DoubleLinkedList() {
        head = null;
        tail = null;
    }

    // Add a new node at the beginning of the list
    public void addFirst(Node node) {
        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
    }

    // Add a new node at the end of the list
    public void addLast(Node node) {
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
    }

    // Insert a new node after a given node
    public void insertAfter(Node existingNode, Node newNode) {
        if (existingNode == null || newNode == null) {
            return;
        }

        newNode.prev = existingNode;
        newNode.next = existingNode.next;

        if (existingNode.next != null) {
            existingNode.next.prev = newNode;
        }

        existingNode.next = newNode;

        if (existingNode == tail) {
            tail = newNode;
        }
    }

    // Insert a new node before a given node
    public void insertBefore(Node existingNode, Node newNode) {
        if (existingNode == null || newNode == null) {
            return;
        }

        newNode.prev = existingNode.prev;
        newNode.next = existingNode;

        if (existingNode.prev != null) {
            existingNode.prev.next = newNode;
        }

        existingNode.prev = newNode;

        if (existingNode == head) {
            head = newNode;
        }
    }

    // Remove a node from the list
    public void remove(Node node) {
        if (node == null || head == null) {
            return;
        }

        if (node == head) {
            head = node.next;
        } else {
            node.prev.next = node.next;
        }

        if (node == tail) {
            tail = node.prev;
        } else {
            node.next.prev = node.prev;
        }

        // Handle the case where the list becomes empty
        if (head == null) {
            tail = null;
        }
    }

    // Make the doubly linked list circular
    public void makeCircular() {
        if (head == null) {
            return;
        }

        tail.next = head;
        head.prev = tail;
    }

    // Print all the nodes in the linked list
    public void printAll() {
        Node current = head;
        while (current != null) {
            System.out.print(current.name + " ");
            current = current.next;

            // Break loop if circular to avoid infinite loop
            if (current == head) {
                break;
            }
        }
        System.out.println();
    }

    // Test the class
    public static void main(String[] args) {
        DoubleLinkedList list = new DoubleLinkedList();

        // Create some nodes
        Node node1 = new Node("A");
        Node node2 = new Node("B");
        Node node3 = new Node("C");
        Node node4 = new Node("D");

        // Add nodes to the list
        list.addFirst(node1);
        list.addLast(node2);
        list.insertAfter(node1, node3);
        list.insertBefore(node2, node4);

        // Print the list
        list.printAll();  // Should print: D A C B

        // Make the list circular
        list.makeCircular();

        // Print the circular list
        list.printAll();  // Should print: D A C B (infinite loop prevented)

        // Remove a node
        list.remove(node2);

        // Print the list after removal
        list.printAll();  // Should print: D A C (infinite loop prevented)
    }
}
