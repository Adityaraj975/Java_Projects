import java.io.*; 
import java.util.*; 

// Tree node
class Node {
  	int id;
  	Node boss,left,right; 
  	int level, height;
    Vector<Node>children = new Vector<>();
}
class avltree{
	Node root;
	int height(Node n){
		if(n==null){
			return 0;
		}
		return n.height;
	}
	int BF(Node n){
    	if(n == null){
    		return 0;
    	}
    	return height(n.left) - height(n.right);
  	}
  	Node n_min(Node n){
  		while(n.left!=null){
  			n = n.left ''
  		}
  		return n;
  	}
	Node RR(Node n){
		Node nl = n.left; 
		Node nr = n.right;
		nl.right = n ;
		nr.left = nr ;
		if(height(n.left)>=height(n.right)){
			n.height = height(n.left)+1 ;
		}
		else{
			n.height = height(n.right)+1;
		}
		if(height(nl.left)>=height(nl.right)){
			nl.height = height(nl.left)+1;
		}
		else{
			nl.height = height(nl.right)+1;
		}
		return nl;
	}
	Node LR(Node n){
		node nr = n.right;
		node nl = n.left;
		nr.left = n;
		n.right = nl ;
		if(height(n.left)>=height(n.right)){
			n.height = height(n.left)+1 ;
		}
		else{
			n.height = height(n.right)+1;
		}
		if(height(nr.left)>=height(nr.right)){
			nr.height = height(nr.left)+1;
		}
		else{
			nr.height = height(nr.right)+1;
		}
		return nr;
	}
	Node insertn(Node n,int id, Node employeenode,int level){
		if(n==null){
			Node n = newNode(id);
			n.boss = employeenode;
			n.level = level;
		}
		if(id>n.id){
			n.right = insertn(n,id,employeenode,level);
		}
		else if(id<n.id){
			n.left = insertn(n.left,id);
		}
		else{
			return n;
		}
		if(height(n.left)>=height(n.right)){
			n.height = height(n.left)+1;
		}
		else{
			n.height = height(n.right)+1;
		}
		int bf = BF(n);
		if(bf>1){
			if(id>n.left.id){
				n.left = LR(n.left);
				return RR(n);
			}
			else if(id<n.leftlid){
				return RR(n);
			}
		}
		else if(bg<-1){
			if(id>n.righ.id){
				return LR(n);
			}
			else if(id<n.right.id){
				n.right = LR(n.right);
				return LR(n);
			}
		}
		return n;
	}

	Node deleten(Node root, int id){
		if(root==null){
			return root;
		}
		else if(id<root.id){
			root.left = deleten(root.left,id);
		}
		else if(id>root.id){
			root.right = deleten(root.right,id);
		}
		else{
			if(root.left == null){
				if(root.right==null){
					root = null;
				}
				else{
					root = root.right ;
				}
			}
			else if(root.right==null){
				if(root.left==null){
					root = null;
				}
				else{
					root = root.left ;
				}
			}
			else{
				Node nwm = n_min(root.right);
				root.id = nwm.id;
				root.level = nwm.level;
				root.boss = nwm.boss;
				root.right = deleten(root.right,nwm.id);
			}
		}
		if(root==null){
			return root;
		}
		if(height(root.left)>=height(root.right)){
			root.height = height(root.left)+1 ;
		}
		else{
			root.height = height(root.right)+1;
		}

    	int bf = BF(root);
    	if (balanceFactor > 1) {
      		if (BF(root.left) >= 0){
        		return RR(root);
      		} 	
      		else{
        		root.left = LR(root.left);
        		return RR(root);
      			}
    	}
    	if (balanceFactor < -1) {
      		if (bF(root.right) <= 0){
        	return LR(root);
      		}
      		else {
        		root.right = RR(root.right);
        		return LR(root);
      		}
    	}
    	return root
	}

	
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
	avltree tree = new avltree();
	Node root1 = tree.root ;
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
		tree.root = tree.insertn(tree.root,id,null,1);
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
		tree.root = tree.insertn(tree.root,id,newemployee,boss_level+1);
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
	tree.root = tree.deleten(tree.root,id);
	size--;
    employee = null ;
 	//throw new java.lang.UnsupportedOperationException("Not implemented yet.");
}
public void fireEmployee(int id, int manageid) throws IllegalIDException,EmptyTreeException{
	if(root==null){
		throw new EmptyTreeException("Empty Tree");
	}
	Node employee = get_node(root1,id);
	if(employee==null || root.id == id){
		throw new IllegalIDException("Illegal Id");
	}
	Node manager = get_node(root1,manageid);
	for(int j=0;j<employee.children.size();j++){
		employee.children.get(j).boss = manager ;
		manager.children.add(employee.children.get(j));
	}
	employee.boss.children.remove(employee);
	tree.root = tree.deleten(tree.root,id);
	size--;
	employee = null ;
} 

public int boss(int id) throws IllegalIDException,EmptyTreeException{
	//your implementation
	if(root==null){
		throw new EmptyTreeException("Empty Tree");
	}
	Node employee = get_node(tree.root,id);
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
	Node e1 = get_node(tree.root,id1);
	Node e2 = get_node(tree.root,id2);
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
	if(tree.root == null){
		throw new EmptyTreeException("Empty Tree");
	}
	String s = "";
	Vector<Vector<Integer>> v = new Vector<Vector<Integer>>();
	Node id_root = get_node(tree.root,id);
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
