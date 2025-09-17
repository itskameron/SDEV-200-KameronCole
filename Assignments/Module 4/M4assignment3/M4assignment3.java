import java.util.*;

interface MyList<E> extends Iterable<E> {
    void add(E e);
    void add(int index, E e);
    E get(int index);
    E remove(int index);
    int size();
    java.util.ListIterator<E> listIterator();
    java.util.ListIterator<E> listIterator(int index);
}

// Node class for doubly linked list
class Node<E> {
    E element;
    Node<E> next;
    Node<E> previous;

    public Node(E e) {
        element = e;
    }
}

// Doubly linked list implementation
class TwoWayLinkedList<E> implements MyList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size = 0;

    public TwoWayLinkedList() {}

    @Override
    public void add(E e) {
        Node<E> newNode = new Node<>(e);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }
        size++;
    }

    @Override
    public void add(int index, E e) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        Node<E> newNode = new Node<>(e);
        if (index == 0) {
            if (head == null) {
                head = tail = newNode;
            } else {
                newNode.next = head;
                head.previous = newNode;
                head = newNode;
            }
        } else if (index == size) {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        } else {
            Node<E> current = head;
            for (int i = 0; i < index; i++) current = current.next;
            newNode.next = current;
            newNode.previous = current.previous;
            current.previous.next = newNode;
            current.previous = newNode;
        }
        size++;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node<E> current = head;
        for (int i = 0; i < index; i++) current = current.next;
        return current.element;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node<E> removed;
        if (index == 0) {
            removed = head;
            head = head.next;
            if (head != null) head.previous = null;
        } else if (index == size - 1) {
            removed = tail;
            tail = tail.previous;
            tail.next = null;
        } else {
            Node<E> current = head;
            for (int i = 0; i < index; i++) current = current.next;
            removed = current;
            current.previous.next = current.next;
            current.next.previous = current.previous;
        }
        size--;
        if (size == 0) head = tail = null;
        return removed.element;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public java.util.ListIterator<E> listIterator() {
        return new TwoWayListIterator(0);
    }

    @Override
    public java.util.ListIterator<E> listIterator(int index) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        return new TwoWayListIterator(index);
    }

    private class TwoWayListIterator implements java.util.ListIterator<E> {
        private Node<E> nextNode;
        private Node<E> lastReturned = null;
        private int nextIndex;

        public TwoWayListIterator(int index) {
            nextNode = head;
            for (int i = 0; i < index; i++) nextNode = nextNode.next;
            nextIndex = index;
        }

        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            lastReturned = nextNode;
            nextNode = nextNode.next;
            nextIndex++;
            return lastReturned.element;
        }

        @Override
        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        @Override
        public E previous() {
            if (!hasPrevious()) throw new NoSuchElementException();
            if (nextNode == null) nextNode = tail;
            else nextNode = nextNode.previous;
            lastReturned = nextNode;
            nextIndex--;
            return lastReturned.element;
        }

        @Override
        public int nextIndex() {
            return nextIndex;
        }

        @Override
        public int previousIndex() {
            return nextIndex - 1;
        }

        @Override
        public void remove() {
            if (lastReturned == null) throw new IllegalStateException();
            Node<E> prev = lastReturned.previous;
            Node<E> next = lastReturned.next;
            if (prev != null) prev.next = next;
            else head = next;
            if (next != null) next.previous = prev;
            else tail = prev;
            if (nextNode == lastReturned) nextNode = next;
            lastReturned = null;
            size--;
        }

        @Override
        public void set(E e) {
            if (lastReturned == null) throw new IllegalStateException();
            lastReturned.element = e;
        }

        @Override
        public void add(E e) {
            Node<E> newNode = new Node<>(e);
            Node<E> prev = (nextNode != null) ? nextNode.previous : tail;
            newNode.previous = prev;
            newNode.next = nextNode;
            if (prev != null) prev.next = newNode;
            else head = newNode;
            if (nextNode != null) nextNode.previous = newNode;
            else tail = newNode;
            lastReturned = null;
            nextIndex++;
            size++;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return listIterator();
    }
}

// Test program
public class M4assignment3 {
    public static void main(String[] args) {
        TwoWayLinkedList<String> list = new TwoWayLinkedList<>();
        list.add("Alice");
        list.add("Bob");
        list.add("Charlie");

        System.out.println("List size: " + list.size());
        System.out.println("Element at index 1: " + list.get(1));

        list.add(1, "Diana");
        System.out.println("After adding Diana at index 1:");
        for (String name : list) {
            System.out.print(name + " ");
        }
        System.out.println();

        list.remove(2);
        System.out.println("After removing element at index 2:");
        for (String name : list) {
            System.out.print(name + " ");
        }
        System.out.println();

        System.out.println("Traversing list using ListIterator:");
        ListIterator<String> it = list.listIterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();
        while (it.hasPrevious()) {
            System.out.print(it.previous() + " ");
        }
    }
}
