import java.util.Comparator;

public class ArrayNew<E> implements ListNew<E> {

    // Определяем массив
    private E[] data;
    // Количество данных массива
    private int size;

    // Конструктор, инициализируем массив
    public ArrayNew(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }
    public ArrayNew(int size, E element) {
        data = (E[]) new Object[size];
        this.size = size;
        for(int i = 0; i < size; i++){
            data[i] = element;
        }
    }

    public ArrayNew(ArrayNew<? extends E> c) {
        data = (E[]) new Object[10];
        size = 0;
        for (int i = 0; i < c.getSize(); i++) {
            try {
                addLast(c.get(i));
            } catch (Exception e) {

            }
        }
    }

    public void set(int index, E element) {
        if (index >= 0 ) {
            if(data[index] == null){
                size++;
            }
            data[index] = element;
        }
    }


    public ArrayNew() {
        this(10);
    }

    // Получаем количество элементов в массиве
    public int getSize() {
        return size;
    }

    // Получаем длину массива
    public int getCapacity() {
        return data.length;
    }


    // Вставляем новый элемент e в позицию индекса
    public void add(int index, E e) throws IllegalAccessException {
        if (index < 0 || index > size) {
            throw new IllegalAccessException();
        }
        // Расширяем, когда длина данных равна длине массива
        if (size == data.length) {
            resize(2 * data.length);
        }
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    // Получить элемент в указанной позиции индекса
    public E get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return data[index];
    }

    // Находим позицию элемента e в массиве по индексу, возвращаем -1, если не найден
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    // Удаляем элемент в позиции индекса из массива и возвращаем удаленный элемент
    public E remove(int index) {
        if (index < 0 || index >= size) {
            return data[0];
        }
        E ret = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;

        // Длина массива уменьшена вдвое до 1/4, чтобы предотвратить колебание сложности, и длина не может быть 0 после расширения
        if (size == data.length / 4 && data.length != 0) {
            resize(data.length / 2);
        }
        return ret;
    }

    // Удаляем элемент e из массива
    public void removeElement(E e) throws IllegalAccessException {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    //Выход
    @Override
    public String toString() {
        return "Array{" +
                "data=" + data.toString() +
                ", size=" + size +
                '}';
    } // я хз че это


    public void look() {
        for (int i = 0; i < size; i++) {
            System.out.println(get(i));
        }
    }

    // Расширение массива
    private void resize(int capacity) {
        E[] newData = (E[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    public E getLast() {
        return get(size - 1);
    }

    public E getFirst() {
        try {
            return get(0);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean add(E value) {
        try {
            addLast(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    public E remove() {
        try {
            return remove(size - 1);

        } catch (Exception e) {
            return null;
        }
    }

    public void addLast(E e) throws IllegalAccessException {
        add(size, e);
    }

    public void clear() {
        data = (E[]) new Object[getCapacity()];
        size = 0;
    }

    @Override
    public E removeFirst() {
        try {
            return remove(0);
        } catch (Exception e) {
            return null;
        }
    }


}

