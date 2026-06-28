class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class Linked_List {
    Node head;

    // Insert at head
    public void insertAtHead(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    // Delete head
    public void deleteHead() {
        if (head == null) {
            return;
        }
        head = head.next;
    }

    // Find length
    public int length() {
        int count = 0;
        Node temp = head;

        while (temp != null) {
            count++;
            temp = temp.next;
        }

        return count;
    }

    // Search for a value
    public boolean search(int key) {
        Node temp = head;

        while (temp != null) {
            if (temp.data == key) {
                return true;
            }
            temp = temp.next;
        }

        return false;
    }

    // Middle of LinkedList [Tortoise-Hare Method]
    public Node middleNode() {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    // Reverse a LinkedList [Iterative]
    public void reverseIterative() {
        Node prev = null;
        Node current = head;

        while (current != null) {
            Node front = current.next;
            current.next = prev;
            prev = current;
            current = front;
        }

        head = prev;
    }

    // Reverse a LL [Recursive]
    private Node reverseRecursiveHelper(Node node) {
        if (node == null || node.next == null) {
            return node;
        }

        Node newHead = reverseRecursiveHelper(node.next);
        node.next.next = node;
        node.next = null;

        return newHead;
    }

    public void reverseRecursive() {
        head = reverseRecursiveHelper(head);
    }

    // Detect a loop in LL
    public boolean hasCycle() {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }

        return false;
    }

    // Find the starting point in LL
    public Node detectCycleStart() {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                slow = head;

                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }

                return slow;
            }
        }

        return null;
    }

    // Length of loop in LL
    public int lengthOfLoop() {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                int count = 1;
                fast = fast.next;

                while (slow != fast) {
                    count++;
                    fast = fast.next;
                }

                return count;
            }
        }

        return 0;
    }

    // Check if LL is palindrome or not
    public boolean isPalindrome() {
        if (head == null || head.next == null) {
            return true;
        }

        Node slow = head;
        Node fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node secondHead = reverseList(slow.next);
        Node first = head;
        Node second = secondHead;

        while (second != null) {
            if (first.data != second.data) {
                slow.next = reverseList(secondHead);
                return false;
            }

            first = first.next;
            second = second.next;
        }

        slow.next = reverseList(secondHead);
        return true;
    }

    private Node reverseList(Node node) {
        Node prev = null;
        Node current = node;

        while (current != null) {
            Node front = current.next;
            current.next = prev;
            prev = current;
            current = front;
        }

        return prev;
    }

    // Segregate odd and even nodes in Linked List
    public void segregateOddEvenNodes() {
        if (head == null || head.next == null) {
            return;
        }

        Node odd = head;
        Node even = head.next;
        Node evenHead = even;

        while (even != null && even.next != null) {
            odd.next = odd.next.next;
            even.next = even.next.next;
            odd = odd.next;
            even = even.next;
        }

        odd.next = evenHead;
    }

    // Remove Nth node from the back of the LL
    public void removeNthFromEnd(int n) {
        Node dummy = new Node(0);
        dummy.next = head;

        Node fast = dummy;
        Node slow = dummy;

        for (int i = 0; i < n; i++) {
            if (fast.next == null) {
                return;
            }
            fast = fast.next;
        }

        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        slow.next = slow.next.next;
        head = dummy.next;
    }

    // Delete the middle node in LL
    public void deleteMiddle() {
        if (head == null || head.next == null) {
            head = null;
            return;
        }

        Node slow = head;
        Node fast = head;
        Node prev = null;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = slow.next;
    }

    // Sort LL
    public void sortList() {
        head = mergeSort(head);
    }

    private Node mergeSort(Node node) {
        if (node == null || node.next == null) {
            return node;
        }

        Node middle = getMiddleForSort(node);
        Node rightHead = middle.next;
        middle.next = null;

        Node left = mergeSort(node);
        Node right = mergeSort(rightHead);

        return mergeTwoLists(left, right);
    }

    private Node getMiddleForSort(Node node) {
        Node slow = node;
        Node fast = node.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    private Node mergeTwoLists(Node list1, Node list2) {
        Node dummy = new Node(0);
        Node temp = dummy;

        while (list1 != null && list2 != null) {
            if (list1.data <= list2.data) {
                temp.next = list1;
                list1 = list1.next;
            } else {
                temp.next = list2;
                list2 = list2.next;
            }
            temp = temp.next;
        }

        if (list1 != null) {
            temp.next = list1;
        } else {
            temp.next = list2;
        }

        return dummy.next;
    }

    // Sort a Linked List of 0's 1's and 2's
    public void sortZeroOneTwo() {
        int zero = 0;
        int one = 0;
        int two = 0;

        Node temp = head;

        while (temp != null) {
            if (temp.data == 0) {
                zero++;
            } else if (temp.data == 1) {
                one++;
            } else {
                two++;
            }
            temp = temp.next;
        }

        temp = head;

        while (temp != null) {
            if (zero > 0) {
                temp.data = 0;
                zero--;
            } else if (one > 0) {
                temp.data = 1;
                one--;
            } else {
                temp.data = 2;
                two--;
            }
            temp = temp.next;
        }
    }

    // Find the intersection point of Y LL
    public Node getIntersectionNode(Node headA, Node headB) {
        if (headA == null || headB == null) {
            return null;
        }

        Node a = headA;
        Node b = headB;

        while (a != b) {
            if (a == null) {
                a = headB;
            } else {
                a = a.next;
            }

            if (b == null) {
                b = headA;
            } else {
                b = b.next;
            }
        }

        return a;
    }

    // Add one to a number represented by LL
    public void addOne() {
        head = reverseList(head);

        Node temp = head;
        int carry = 1;

        while (temp != null) {
            int sum = temp.data + carry;
            temp.data = sum % 10;
            carry = sum / 10;

            if (carry == 0) {
                break;
            }

            if (temp.next == null) {
                temp.next = new Node(carry);
                carry = 0;
                break;
            }

            temp = temp.next;
        }

        head = reverseList(head);
    }

    // Add two numbers in Linked List
    public Node addTwoNumbers(Node l1, Node l2) {
        Node dummy = new Node(0);
        Node temp = dummy;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;

            if (l1 != null) {
                sum += l1.data;
                l1 = l1.next;
            }

            if (l2 != null) {
                sum += l2.data;
                l2 = l2.next;
            }

            temp.next = new Node(sum % 10);
            carry = sum / 10;
            temp = temp.next;
        }

        return dummy.next;
    }

    // Print LL
    public void printList() {
        Node temp = head;

        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }

        System.out.println();
    }
}

class DNode {
    int data;
    DNode prev;
    DNode next;

    DNode(int data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

class DoublyLinkedList {
    DNode head;

    // Insert node before head
    public void insertBeforeHead(int data) {
        DNode newNode = new DNode(data);

        if (head == null) {
            head = newNode;
            return;
        }

        newNode.next = head;
        head.prev = newNode;
        head = newNode;
    }

    // Delete head
    public void deleteHead() {
        if (head == null) {
            return;
        }

        if (head.next == null) {
            head = null;
            return;
        }

        head = head.next;
        head.prev = null;
    }

    // Reverse doubly linked list
    public void reverse() {
        DNode current = head;
        DNode temp = null;

        while (current != null) {
            temp = current.prev;
            current.prev = current.next;
            current.next = temp;
            current = current.prev;
        }

        if (temp != null) {
            head = temp.prev;
        }
    }
}

public class linked_list {
    // 
}
