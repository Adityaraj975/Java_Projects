import java.io.*;
import java.util.*;
public class FabricBreakup {	
	public static void main(String args[]){
		// Implement FabricBreakup puzzle using Stack interface
		StackInterface sorted_elements = new Stack();
		StackInterface ans = new Stack();
		try{
			FileReader f = new FileReader(args[0]);
			Scanner sc = new Scanner(f);  
			int n_op= sc.nextInt(); 
			for(int i=0;i<n_op;i++){
				int n= sc.nextInt();
				int id= sc.nextInt();
				if(id==1){
					int like = sc.nextInt(); 
					if(sorted_elements.isEmpty()){
						sorted_elements.push(like);
						ans.push(0);
					}
					else{
						int top=0;
						try{
							top = (Integer)sorted_elements.top();
						}
						catch(EmptyStackException e){
        						System.out.println("Empty Stack");
      					}
      					if(top<=like){
      						sorted_elements.push(like);
      						ans.push(0);
      					}
      					else{
      						try{
								top = (Integer)ans.pop();
							}	
							catch(EmptyStackException e){
        							System.out.println("Empty Stack");
      						}
      						ans.push(top+1);
      					}
					}
				}
				else{
					if(ans.isEmpty()){
						System.out.println(n+" "+"-1");
					}
					else{
						int top = 0;
						try{
							top = (Integer)ans.pop();
						}	
						catch(EmptyStackException e){
        						System.out.println("Empty Stack");
      					}
						System.out.println(n+" "+top);
						try{
							top = (Integer)sorted_elements.pop();
						}	
						catch(EmptyStackException e){
        						System.out.println("Empty Stack");
      					}
					}
				}
			}
		}
		catch(FileNotFoundException e){
			System.out.println("File Not Found");
		}
	}
}
