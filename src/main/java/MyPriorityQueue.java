import java.util.*;

public class MyPriorityQueue<T extends Comparable<T>> implements MyPriorityQueueInterface<T>, Iterable<T>
{
    private class QueueElement<T>
    {
        public T value;
        public QueueElement<T> next;

        public QueueElement(T value)
        {
            this.value = value;
        }

        public QueueElement(T value, QueueElement<T> next)
        {
            this.value = value;
            this.next = next;
        }
    }

    private QueueElement<T> head = null;
    private QueueElement<T> tail = null;
    private int size = 0;
    private Comparator<T> comparator;

    public MyPriorityQueue(Comparator<T> comparator)
    {
        this.comparator = comparator;
    }

    public int size()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }

    public void add(T value)
    {
        QueueElement<T> newElement = new QueueElement<>(value);

        if (isEmpty())
        {
            head = newElement;
            tail = newElement;
        }
        else
        {
            if (compare(newElement.value, tail.value) > 0)
            {
                tail.next = newElement;
                tail = newElement;
                size++;
                return;
            }

            if (compare(newElement.value, head.value) < 0)
            {
                head = new QueueElement<>(value, head);
                size++;
                return;
            }

            QueueElement<T> currentElement = head;

            for (int i = 0; i < size(); i++)
            {
                if (compare(newElement.value, currentElement.next.value) > 0)
                {
                    currentElement = currentElement.next;
                }
                else
                {
                    newElement.next = currentElement.next;
                    currentElement.next = newElement;
                    break;
                }
            }
        }
        size++;
    }

    @Override
    public T poll() throws EmptyQueueException
    {
        throwEmptyQueueException();
        T value = head.value;
        head = head.next;
        size--;
        if (isEmpty())
        {
            tail = null;
        }
        return value;
    }

    @Override
    public T peek() throws EmptyQueueException
    {
        throwEmptyQueueException();
        return head.value;
    }

    @Override
    public void clear()
    {
        head = tail = null;
        size = 0;
    }

    public Iterator<T> iterator()
    {
        class PriorityQueueIterator implements Iterator<T>
        {
            QueueElement<T> curr;

            public PriorityQueueIterator(QueueElement<T> head)
            {
                curr = head;
            }

            @Override
            public boolean hasNext()
            {
                return curr != null;
            }

            @Override
            public T next()
            {
                T result = curr.value;
                curr = curr.next;
                return result;
            }
        }
        return new PriorityQueueIterator(head);
    }

    private void throwEmptyQueueException() throws EmptyQueueException
    {
        if (isEmpty())
        {
            throw new EmptyQueueException();
        }
    }

    private int compare(T a, T b)
    {
        return comparator.compare(a, b);
    }
}

