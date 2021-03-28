public interface MyPriorityQueueInterface<T>
{
    void add(T value);
    T poll() throws EmptyQueueException;
    T peek() throws EmptyQueueException;
    void clear();
}
