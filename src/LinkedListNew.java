import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class LinkedListNew<T> implements ListNew<T> {
    private int size = 0;
    private Node<T> first;
    private Node<T> last;

    LinkedListNew(T value) {
        this.first = this.last = new Node<T>(value);
        size = 1;

    }

    LinkedListNew() {
        last = first = null;
    }

    public int find(T value){
        Node<T> current = first;
        for(int i = 0;current!=null;current = current.next, i++){
            if(current.value.equals(value)){
                return i;
            }
        }
        return -1;
    }

    public boolean add(T value, int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> current = first;
        Node<T> newElement = new Node<T>(value);
        if (index == 0) {
            newElement.setNext(current);
            first = newElement;
            size++;
            return true;
        } else if (index == size) {
            add(value);
            newElement = null;
            return true;
        }
        for (int i = 0; i < index - 1; i++) {
            current = current.getNext();
        }
        newElement.setNext(current.getNext());
        current.setNext(newElement);
        size++;
        return true;

    }

    public boolean add(T value) {
        Node<T> newElement = new Node<T>(value);
        if (first == null) {
            first = newElement;
            last = newElement;
            size = 1;
        } else {
            last.setNext(newElement);
            last = newElement;
            size++;
        }
        return true;
    }

    public T remove() {
        if (first == null) {
            throw new NoSuchElementException();
        } else {
            Node<T> current = first;
            if (current.getNext() != null) {
                if (current.getNext().getNext() != null) {
                    for (; current.getNext().getNext() != null; ) {
                        current = current.getNext();
                    }
                    last = current;
                    current = current.getNext();
                    last.setNext(null);

                } else {
                    first.setNext(null);
                    last = first;
                }
            } else {
                first = null;
                last = null;
            }
            size--;
            return current.getValue();
        }
    }

    public T remove(int index) {
        Node<T> current = first;
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            first = current.getNext();
            size--;
            return current.getValue();
        } else if (index == size - 1) {
            return remove();
        } else {
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            Node<T> returnElement = current.getNext();
            current.setNext(returnElement.getNext());
            size--;
            return returnElement.getValue();
        }
    }

    public T get(int index) {
        Node<T> current = first;
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        } else if (index == size - 1) {
            return getLast();
        } else {
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            return current.getValue();
        }
    }

    public T getLast() {
        if (last != null) {
            return last.getValue();
        } else {
            throw new NoSuchElementException();
        }
    }

    public T getFirst() {
        if (first != null) {
            return first.getValue();
        } else {
            throw new NoSuchElementException();
        }
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        String out = new String("[");
        Node<T> current = first;
        for (int i = 0; i < size - 1; i++) {
            out += current.getValue().toString() + ",";
            current = current.getNext();
        }
        out += current.getValue().toString();
        return out + "]";
    }

    @Override
    public void set(int index, T value) {
        Node<T> current = first;
        for(int i = 0; current.next!=null&&i<index; current = current.next, i++){

        }
        current.value = value;
    }

    @Override
    public boolean isEmpty() {
        if (first == null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void clear() {
        first = last = null;
        size = 0;
    }

    @Override
    public T removeFirst() {
        return remove(0);
    }



    class Node<T> {
        private T value;
        private Node next;

        Node(T value) {
            this.value = value;
            this.next = null;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }
    }

}

