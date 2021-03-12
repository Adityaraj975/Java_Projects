public class ArrayDeque implements DequeInterface {
  private int r=0;
  private int f=0;
  private int n = 1;
  private Object[] arr = new Object[n];
  public void insertFirst(Object o){
    //you need to implement this
    if((n+r-f)%n == n-1){
      Object[] new_arr = new Object[2*n];
      int index = 0;
      new_arr[index] = o;
      index++;
      if(r==f){
        r = 1;
      }
      else if(r>f){
        for(int i=f;i<r;i++){
          new_arr[index] = arr[i];
          index++;
        }
        r = index;
        f = 0;
      }
      else{
        for(int i=f;i<n;i++){
          new_arr[index] = arr[i];
          index++;
        }
        for(int i=0;i<r;i++){
          new_arr[index] = arr[i];
          index++;
        }
        r = index;
        f = 0;
      }
      arr = new_arr ;
      new_arr = null ;
      n = 2*n;
    }
    else{
      if(f==0){
        arr[n-1] = o;
        f = n-1;
      }
      else{
        arr[f-1] = o;
        f--;
      }
    }
    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");
  }
  
  public void insertLast(Object o){
    //you need to implement this
    if((n+r-f)%n == n-1){
      Object[] new_arr = new Object[2*n];
      int index = 0;
      if(r==f){
        r++;
      }
      else if(r>f){
        for(int i=f;i<r;i++){
          new_arr[index] = arr[i];
          index++;
        }
        r = index+1;
        f = 0;
      }
      else{
        for(int i=f;i<n;i++){
          new_arr[index] = arr[i];
          index++;
        }
        for(int i=0;i<r;i++){
          new_arr[index] = arr[i];
          index++;
        }
        r = index+1;
        f = 0;
      }
      new_arr[index] = o;
      arr = new_arr ;
      new_arr = null ;
      n = 2*n;
    }
    else{
      if(r==n-1){
        arr[r] = o;
        r = 0;
      }
      else{
        arr[r] = o;
        r++;
      }
    }
    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");
  }
  
  public Object removeFirst() throws EmptyDequeException{
    if((n+r-f)%n==0){
      throw new EmptyDequeException("Empty queue");
    }
    else if(f!=n-1){
      f++;
      return arr[f-1];
    }
    else{
      f = 0;
      return arr[n-1];
    }
    //you need to implement this
    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");
  }
  
  public Object removeLast() throws EmptyDequeException{
    //you need to implement this
    if((n+r-f)%n==0){
      throw new EmptyDequeException("Empty queue");
    }
    else if(r!=0){
      r--;
      return arr[r];
    }
    else{
      r = n-1;
      return arr[r];
    }
    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");
  }

  public Object first() throws EmptyDequeException{
    //you need to implement this
    if(r==f){
      throw new EmptyDequeException("Empty queue");
    }
    return arr[f];
    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");
  }
  
  public Object last() throws EmptyDequeException{
    //you need to implement this
    if(r==f){
      throw new EmptyDequeException("Empty queue");
    }
    if(r!=0){
      return arr[r-1];
    }
    else{
      return arr[n-1];
    }
    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");
  }
  
  public int size(){
    //you need to implement this
    return (n+r-f)%n ;
    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");
  }
  
  public boolean isEmpty(){
    //you need to implement this
    return r==f;
    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");
  }

  public String toString(){
    //you need to implement this
    String s = "[";
    if(f==r){
      s = "[]";
      return s;
    }
    else if(f<r){
      for(int i=f;i<r;i++){
        s = s+ arr[i].toString();
        s = s+",";
      }
      s = s.substring(0, s.length() - 1);
      s = s+"]";
      return s;
    }
    else{
      for(int i=f;i<n;i++){
        s = s+ arr[i].toString();
        s = s+",";
      }
      for(int i=0;i<r;i++){
        s = s+ arr[i].toString();
        s = s+",";
      }
      s = s.substring(0, s.length() - 1);
      s = s+"]";
      return s;
    }
    //throw new java.lang.UnsupportedOperationException("Not implemented yet.");
  }  
}