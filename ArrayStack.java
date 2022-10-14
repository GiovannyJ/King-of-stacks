
/**
 * ArrayStack.java - an array-based implementation of Stack ADT
 */
import java.util.EmptyStackException;
import java.util.Arrays;

public class ArrayStack<T> implements StackInterface<T>
{
    //declare our data properties
    private T[] stack;          //the array holding our entries
    private int topIndex;       //index of the top of the array
    private final static int DEFAULT_CAPACITY = 25;
    private final static int MAX_CAPACITY = 1000;       //maximum allowable size of a Bag
    private boolean integrityOK;        //flag value to signal data is not corrupted

    //define constructors

    //default constructor - creates empty stack with DEFAULT_CAPACITY
    public ArrayStack()
    {
        this(DEFAULT_CAPACITY);
    }

    //parameterized constructor - creates empty stack with given capacity (if allowed)
    public ArrayStack(int capacity)
    {
        integrityOK = false;        //set to false at start of constructor

        //check if capacity is too small
        if (capacity <= 0)
        {
            stack = (T[]) new Object[DEFAULT_CAPACITY];    //use default capacity instead
        } else {
            checkCapacity(capacity);            //check to make sure not too big
            stack = (T[]) new Object[capacity];   //capacity is okay
        } 

        topIndex = -1;        // initially stack is empty, so use -1 as signal
        integrityOK = true; // made it through -- set integrity flag to true
    }


    //checkIntegrity() - helper method to ensure bag is okay to work with
    private void checkIntegrity()
    {
        if (!integrityOK)
            throw new SecurityException("Data is corrupt.");
    }

    //checkCapacity() - private helper method to ensure we are not attempting to create bag too big
    private void checkCapacity(int capacity)
    {
        if (capacity > MAX_CAPACITY)
            throw new IllegalStateException("Attempted to create a bag whose capacity exceeds max allowed value.");
    }
    
    //ensureCapacity() - private helper method to double capcity of array when full
    private void ensureCapacity()
    {
        //check if array is full
        if (topIndex >= stack.length - 1)
        {
            int newCapacity = stack.length * 2;   
            checkCapacity(newCapacity);     //helper method to make sure new capacity is ok

            stack = Arrays.copyOf(stack, newCapacity);
        }
        
        
    }
    
    //push() - adds a new entry to the top of this stack
    //   @param newEntry - the object to be added to the stack
    public void push(T newEntry)
    {
        checkIntegrity();
        ensureCapacity();       //if array is full, it will resize for us
        stack[topIndex + 1] = newEntry;
        topIndex++;
    }
    
    //pop() - removes and returns the top entry from this stack
    //   @return - the object at the top of the stack
    //   @throws - EmptyStackException if the stack is empty before the operation
    public T pop()
    {
        checkIntegrity();
        if (isEmpty())
            throw new EmptyStackException();
        else
        {
            T topData = stack[topIndex];
            stack[topIndex] = null;
            topIndex--;
            return topData;
        }
    }
    
    //peek() - retrieves the top entry from the stack (without removing it)
    //   @return - the object at the top of the stack
    //   @throws - EmptyStackException if the stack is empty before the operation
    public T peek()
    {
        checkIntegrity();
        if (isEmpty())
            throw new EmptyStackException();
        else
            return stack[topIndex];
    }
    
    //isEmpty() - detects where the stack is empty
    //   @return - TRUE if stack is empty; FALSE otherwise
    public boolean isEmpty()
    {
        return (topIndex < 0);
    }
    
    //clear() - removes all entries from this stack
    public void clear()
    {
        //while (!isEmpty())
        //    pop();
        for (int i = 0; i <= topIndex; i++)
            stack[i] = null;
            
        topIndex = -1;
    }
}
