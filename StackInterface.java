
/**
 * StackInterface.java - implementation of Stack ADT
 */
public interface StackInterface<T>
{
    //push() - adds a new entry to the top of this stack
    //   @param newEntry - the object to be added to the stack
    public void push(T newEntry);
    
    //pop() - removes and returns the top entry from this stack
    //   @return - the object at the top of the stack
    //   @throws - EmptyStackException if the stack is empty before the operation
    public T pop();
    
    //peek() - retrieves the top entry from the stack (without removing it)
    //   @return - the object at the top of the stack
    //   @throws - EmptyStackException if the stack is empty before the operation
    public T peek();
    
    //isEmpty() - detects where the stack is empty
    //   @return - TRUE if stack is empty; FALSE otherwise
    public boolean isEmpty();
    
    //clear() - removes all entries from this stack
    public void clear();
   
}
