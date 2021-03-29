import java.io.*; 
import java.util.*; 

// Tree node
class Node {
  	int id;
  	Node boss,left,right; 
  	int level;
    Vector<Node>children = new Vector<>();
}


public class OrgHierarchy implements OrgHierarchyInterface{
	Node newNode(int id){
    	Node n = new Node();
    	n.id = id;
    	n.boss = n.left = n.right =  null ;
    	return n;
	}	

	int search_level(Node r, int id){
		if(r == null){
			return -1;
		}
		else if(r.id == id){
			return r.level;
		}
		else if(id > r.id){
			return search_level(r.right,id);
		}
		else{
			return search_level(r.left,id);
		}
	}
	Node get_node(Node r, int id){
		if(r == null){
			return null;
		}
		else if(r.id == id){
			return r.boss;
		}
		else if(id > r.id){
			return get_node(r.right,id);
		}
		else{
			return get_node(r.left,id);
		}
	}
	Node insert(Node r, int id, Node employeenode, int level){
		if(r == null){
			r = newNode(id);
			r.boss = employeenode;
			r.level = level ;
		}
		else if(id > r.id){
			r.right = insert(r.right,id,employeenode,level);
		}
		else{
			r.left = insert(r.left,id,employeenode,level);
		}
		return r;
	}
	int get_min(Node n){
		int r = n.id ;
		while(n.left != null){
			r = n.left.id ;
			n = n.left ;
		}
		return r;
	}
	Node removeit(Node r1, int id){
		if(r1 == null){
			return r1;
		}
		else if(r1.id<id){
			r1.right = removeit(r1.right,id);
		}
		else if(r1.id>id){
			r1.left = removeit(r1.left,id);
		}
		else{
			if(r1.left == null){
				return r1.right ;
			}
			else if(r1.right == null){
				return r1.left;
			}
			else{
				r1.id = get_min(r1.right);
				r1.right = removeit(r1.right,r1.id);
			}
		}
		return r1 ;
	}

	//root node
	Node root;
	Node root1 ;
	private int size = 0;

	public boolean isEmpty(){
		if(root == null){
			return true;
		}
		return false; 	
	} 

	public int size(){
		return size ;
	}

	public int level(int id) throws IllegalIDException, EmptyTreeException{
		if(root1 == null){
			throw new EmptyTreeException("Empty Tree");
		}
		int level = search_level(root1,id);
		if(level == -1){
			throw new IllegalIDException("Illegal Id");
		}
		return level;
	} 

	public void hireOwner(int id) throws NotEmptyException{
		if(root!=null){
			throw new NotEmptyException("Tree Not Empty");
		}
		size++;
		root = newNode(id);
		root.boss = null ;
		root.level = 1 ;
		root1 = newNode(id);
		root1.boss = root;
		root1.level = 1 ;
	}

	public void hireEmployee(int id, int bossid) throws IllegalIDException, EmptyTreeException{
		if(root == null){
			throw new EmptyTreeException("Empty Tree");
		}
		Node bossnode = get_node(root1,bossid);
		int boss_level = bossnode.level;
		if(bossnode == null){
			throw new IllegalIDException("Illegal Boss Id");
		}
		size++ ;
		Node newemployee = newNode(id);
		newemployee.boss = bossnode ;
		newemployee.level = boss_level+1 ;
		bossnode.children.add(newemployee);
		/*for(int i=bossnode.children.size()-1;i>=0;i--){
			if(bossnode.children.get(i).id < id){
				bossnode.children.insertElementAt(newemployee,i+1);
				break;
			}
			if(i==0){
				bossnode.children.insertElementAt(newemployee,0);
			}
		}*/
		root1 = insert(root1,id,newemployee,boss_level+1);
	} 

public void fireEmployee(int id) throws IllegalIDException,EmptyTreeException{
	//your implementation
	if(root==null){
		throw new EmptyTreeException("Empty Tree");
	}
	Node employee = get_node(root1,id);
	if(employee==null || root.id == id){
		throw new IllegalIDException("Illegal Id");
	}
	employee.boss.children.remove(employee);
	root1 = removeit(root1,id);
	size--;
    employee = null ;
 	//throw new java.lang.UnsupportedOperationException("Not implemented yet.");
}
public void fireEmployee(int id, int manageid) throws IllegalIDException,EmptyTreeException{
	//your implementation
	if(root==null){
		throw new EmptyTreeException("Empty Tree");
	}
	Node employee = get_node(root1,id);
	if(employee==null || root.id == id){
		throw new IllegalIDException("Illegal Id");
	}
	Node manager = get_node(root1,manageid);
	for(int j=0;j<employee.children.size();j++){
		/*for(int i=manager.children.size()-1;i>=0;i--){
			if(manager.children.get(i).id < id){
				manager.children.insertElementAt(employee.children.get(j),i+1);
				break;
			}
			if(i==0){
				manager.children.insertElementAt(employee.children.get(j),0);
			}
		}*/
		employee.children.get(j).boss = manager ;
		manager.children.add(employee.children.get(j));
	}
	employee.boss.children.remove(employee);
	root1 = removeit(root1,id);
	size--;
	employee = null ;
	//throw new java.lang.UnsupportedOperationException("Not implemented yet.");
} 

public int boss(int id) throws IllegalIDException,EmptyTreeException{
	//your implementation
	if(root==null){
		throw new EmptyTreeException("Empty Tree");
	}
	Node employee = get_node(root1,id);
	if(employee==null || root.id == id){
		throw new IllegalIDException("Illegal Id");
	}
	return employee.boss.id ;
	//throw new java.lang.UnsupportedOperationException("Not implemented yet.");
}

public int lowestCommonBoss(int id1, int id2) throws IllegalIDException,EmptyTreeException{
	//your implementation
	if(root==null){
		throw new EmptyTreeException("Empty tree");
	}
	Node e1 = get_node(root1,id1);
	Node e2 = get_node(root1,id2);
	if(e1 == null || e2==null){
		throw new IllegalIDException("Illegal Id");
	}
	if(e1.id==root.id || e2.id == root.id){
		return -1;
	}
	Vector<Node> c1 = new Vector<>();
	Vector<Node> c2 = new Vector<>();
	while(e1!=null){
		c1.add(e1);
		e1 = e1.boss ;
	}
	while(e2!=null){
		c2.add(e2);
		e2 = e2.boss ;
	}
	int i=1;
	while(c2.get(c2.size()-i)!=c1.get(c1.size()-i)){
		i++;
	}
	return c1.get(c1.size()-i).id;
	//throw new java.lang.UnsupportedOperationException("Not implemented yet.");
}
Vector<Vector<Integer>> Get_sorted(Vector<Vector<Integer>> v, Node r1, int l ){
	if(r1==null){
		return v;
	}
	Vector<Integer> v1 = new Vector<Integer>();
	if(r1.level-l < v.size()){
		v1 = v.get(r1.level-l);
		v1.add(r1.id);
		v.set(r1.level-l,v1);
		for(int i=0;i<r1.children.size();i++){
			v = Get_sorted(v,r1.children.get(i),l);
		}
	}
	else{
		v1.add(r1.id);
		v.add(v1);
		for(int i=0;i<r1.children.size();i++){
			v = Get_sorted(v,r1.children.get(i),l);
		}
	}
	return v ;
}
public String toString(int id) throws IllegalIDException, EmptyTreeException{
	if(root1 == null){
		throw new EmptyTreeException("Empty Tree");
	}
	String s = "";
	Vector<Vector<Integer>> v = new Vector<Vector<Integer>>();
	Node id_root = get_node(root1,id);
	if(id_root == null){
		throw new IllegalIDException("Illegal Id");
	}
	int l = id_root.level; 
	v = Get_sorted(v,id_root,l);
	for(int i=0;i<v.size();i++){
		Vector<Integer> vsorted   = v.get(i);
		Collections.sort(vsorted); 
		for(int j=0;j<vsorted.size();j++){
			s = s + vsorted.get(j).toString() ;
			s = s + " ";
		}
		s = s.substring(0, s.length() - 1);
		s = s + ",";
	}
	s = s.substring(0, s.length() - 1);
	return s;
}

}
