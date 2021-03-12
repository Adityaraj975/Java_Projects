// You should utilise your implementation of ArrayDeque methods to implement this
public class Stack implements StackInterface {	
	DequeInterface myDeque = new ArrayDeque();
	public void push(Object o){
    	//you need to implement this
    	myDeque.insertLast(o);
    	//throw new java.lang.UnsupportedOperationException("Not implemented yet.");
  	}

	public Object pop() throws EmptyStackException{
    	//you need to implement this
    	try{
      		Object top = myDeque.removeLast();
      		return top ;
    	}
    	catch (EmptyDequeException e){
      		throw new EmptyStackException("Empty Stack Exception");
    	}
    	//throw new java.lang.UnsupportedOperationException("Not implemented yet.");
	}

	public Object top() throws EmptyStackException{
    	//you need to implement this
    	try{
      		Object top = myDeque.last();
      		return top ;
    	}
    	catch (EmptyDequeException e){
      		throw new EmptyStackException("Empty Stack Exception");
    	}
		//throw new java.lang.UnsupportedOperationException("Not implemented yet.");
	}

	public boolean isEmpty(){
    	//you need to implement this
    	if(myDeque.size()==0){
    		return true;
    	}
    	return false;
    	//throw new java.lang.UnsupportedOperationException("Not implemented yet.");
	}

    public int size(){
    	return myDeque.size();
    	//you need to implement this
    	//throw new java.lang.UnsupportedOperationException("Not implemented yet.");
    }
}