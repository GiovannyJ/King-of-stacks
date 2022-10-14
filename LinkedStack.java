
/**
 * LinkedStack.java - Linked list based implementation of Stack ADT
 */
import java.util.EmptyStackException;

public class LinkedStack<T> implements StackInterface<T>
{
    private Node topNode;       //reference to the first Node in the chain
    
    public LinkedStack()
    {
        topNode = null;
        
        
    }
    
    //push() - adds a new entry to the top of this stack
    //   @param newEntry - the object to be added to the stack
    public void push(T newEntry)
    {
        //create a new Node for the entry
        Node newNode = new Node(newEntry);

        //attach newNode's next pointer to point to firstNode
        newNode.next = topNode;

        //reasssign firstNode to be the new Node
        topNode = newNode;

    }
    
    //pop() - removes and returns the top entry from this stack
    //   @return - the object at the top of the stack
    //   @throws - EmptyStackException if the stack is empty before the operation
    public T pop()
    {
        T top = peek();     //might throw an EmptyStackException
        
        //Assertion: topNode != null
        topNode = topNode.next;     //splice out and remove the top Node
        
        return top;
    }
    
    //peek() - retrieves the top entry from the stack (without removing it)
    //   @return - the object at the top of the stack
    //   @throws - EmptyStackException if the stack is empty before the operation
    public T peek()
    {
        if (isEmpty())
            throw new EmptyStackException();
        else
            return topNode.data;
    }
    
    //isEmpty() - detects where the stack is empty
    //   @return - TRUE if stack is empty; FALSE otherwise
    public boolean isEmpty()
    {
        return (topNode == null);
    }
    
    //clear() - removes all entries from this stack
    public void clear()
    {
        topNode = null;
    }
    
    private class Node
    {
        private T data;
        private Node next;

        private Node (T dataPortion)
        {
            data = dataPortion;
            next = null;
        }
    }
}
