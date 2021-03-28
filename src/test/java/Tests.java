import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class Tests
{
    @Test
    public void testIsEmpty()
    {
        MyPriorityQueue<Integer> queue = new MyPriorityQueue<>(Integer::compareTo);
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testIsNotEmpty()
    {
        MyPriorityQueue<Integer> queue = new MyPriorityQueue<>(Integer::compareTo);
        queue.add(1);
        assertFalse(queue.isEmpty());
    }

    @Test
    public void testSize()
    {
        MyPriorityQueue<Integer> queue = new MyPriorityQueue<>(Integer::compareTo);
        assertEquals(0, queue.size());
        queue.add(15);
        assertEquals(1, queue.size());
        queue.add(2);
        assertEquals(2, queue.size());
        queue.add(0);
        assertEquals(3, queue.size());
    }

    @Test
    public void testSingleElement() throws EmptyQueueException
    {
        MyPriorityQueue<String> queue = new MyPriorityQueue<>(String::compareTo);
        queue.add("element");
        assertEquals("element", queue.peek());
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
    }

    @Test
    public void testThreeElements() throws EmptyQueueException
    {
        MyPriorityQueue<String> queue = new MyPriorityQueue<>(String::compareTo);
        queue.add("Sally");
        queue.add("Alice");
        queue.add("John");

        assertEquals("Alice", queue.peek());
        assertEquals(3, queue.size());
        assertFalse(queue.isEmpty());
    }

    @Test
    public void testIterator()
    {
        MyPriorityQueue<String> queue = new MyPriorityQueue<>(String::compareTo);
        queue.add("Sally");
        queue.add("Alice");
        queue.add("John");

        String[] expected = {"Alice", "John", "Sally"};
        int i = 0;
        for (String name : queue)
        {
            assertEquals(expected[i++], name);
        }
    }

    @Test
    public void testPoll() throws EmptyQueueException
    {
        MyPriorityQueue<String> queue = new MyPriorityQueue<>(String::compareTo);
        queue.add("Sally");
        queue.add("Alice");
        queue.add("John");

        assertEquals("Alice", queue.poll());
        assertEquals(2, queue.size());

        assertEquals("John", queue.poll());
        assertEquals(1, queue.size());

        assertEquals("Sally", queue.poll());
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    public void testClear()
    {
        MyPriorityQueue<String> queue = new MyPriorityQueue<>(String::compareTo);
        queue.add("Sally");
        queue.add("Alice");
        queue.add("John");
        queue.clear();

        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
    }

    @Test()
    public void testPollEmptyQueueException() throws EmptyQueueException
    {
        try
        {
            MyPriorityQueue<String> queue = new MyPriorityQueue<>(String::compareTo);
            queue.poll();
            Assert.fail("Expected EmptyQueueException");
        }
        catch (EmptyQueueException e)
        {
            Assert.assertNotEquals("", e.getMessage());
        }
    }

    @Test()
    public void testPeekEmptyQueueException() throws EmptyQueueException
    {
        try
        {
            MyPriorityQueue<String> queue = new MyPriorityQueue<>(String::compareTo);
            queue.peek();
            Assert.fail("Expected EmptyQueueException");
        }
        catch (EmptyQueueException e)
        {
            Assert.assertNotEquals("", e.getMessage());
        }
    }
}
