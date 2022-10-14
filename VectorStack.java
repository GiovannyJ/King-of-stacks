
/**
 * VectorStack.java - a Vector-based implementation of Stack ADT
 */
import java.util.EmptyStackException;
import java.util.Vector;

public class VectorStack<T> implements StackInterface<T>
{
    //declare our data properties
    private Vector<T> stack;          //the Vector holding our entries
    private final static int DEFAULT_CAPACITY = 25;
    private final static int MAX_CAPACITY = 1000;       //maximum allowable size of a Bag
    private boolean integrityOK;        //flag value to signal data is not corrupted

    //define constructors

    //default constructor - creates empty stack with DEFAULT_CAPACITY
    public VectorStack()
    {
        this(DEFAULT_CAPACITY);
    }

    //parameterized constructor - creates empty stack with given capacity (if allowed)
    public VectorStack(int capacity)
    {
        integrityOK = false;        //set to false at start of constructor

        //check if capacity is too small
        if (capacity <= 0)
        {
            stack = new Vector<T>(DEFAULT_CAPACITY);    //use default capacity instead
        } else {
            checkCapacity(capacity);            //check to make sure not too big
            stack = new Vector<T>(capacity);    //capacity is okay
        } 

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
      
    //push() - adds a new entry to the top of this stack
    //   @param newEntry - the object to be added to the stack
    public void push(T newEntry)
    {
        checkIntegrity();
        stack.add(newEntry);        //adds newEntry to the back of the Vector
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
            return stack.remove(stack.size() - 1);  //remove element in last spot of Vector and return it
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
            return stack.lastElement();
    }
    
    //isEmpty() - detects where the stack is empty
    //   @return - TRUE if stack is empty; FALSE otherwise
    public boolean isEmpty()
    {
        return stack.isEmpty();
    }
    
    //clear() - removes all entries from this stack
    public void clear()
    {
        stack.clear();
    }
}
