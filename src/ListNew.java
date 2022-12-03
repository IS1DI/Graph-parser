

public interface ListNew<T>  {
    T getFirst();
    int find(T value);

    boolean add(T value);

    boolean isEmpty();

    T remove(int index);

    int getSize();

    T get(int index);

    void clear();

    T getLast();

    T removeFirst();
    String toString();


    void set(int index, T value);
}

