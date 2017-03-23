import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;


public class LogNList<Object> implements List<Object> {
	private TreeNode root=null;

	class TreeNode {
		int height, size;
		TreeNode left, right;
		Object obj;

		TreeNode(Object obj) {
			height = 1;
			size = 1;
			this.obj = obj;
		}
	}

	private int height(TreeNode node) {
		if (node != null)
			return node.height;
		return 0;
	}

	public int height() {
		return height(root);
	}

	private int max(int a, int b) {
		return a > b ? a : b;
	}

	
	/**
     * left left rotation
     * @return the root after rotation
     */
	private TreeNode llRotation(TreeNode n2) {
		TreeNode n1 = n2.left;
		n2.left = n1.right;
		n1.right = n2;
		
		updateNode(n2);
		
		n1.height = max(height(n1.left), n2.height) + 1;
		n1.size = size(n1.left) + size(n2) + 1;

		return n1;
	}
	
	/**
     * right right rotation
     * @return the root after rotation
     */
	private TreeNode rrRotation(TreeNode n1) {
		TreeNode n2 = n1.right;
	    n1.right = n2.left;
	    n2.left = n1;

	    updateNode(n1);
		
	    n2.height = max(height(n2.right), n1.height) + 1;
		n2.size = size(n1) + size(n2.right) + 1;

	    return n2;
	}
		
	/**
     * left right rotation. Need to rotate twice
     * @return the root after rotation
     */
	private TreeNode lrRotation(TreeNode n) {
	    n.left = rrRotation(n.left);
	    return llRotation(n);
	}
	
	/**
     * right left rotation. Need to rotate twice
     * @return the root after rotation
     */
	private TreeNode rlRotation(TreeNode n) {
	    n.right = llRotation(n.right);
	    return rrRotation(n);
	}
	
	//reset height and size property of node
	private void updateNode(TreeNode n) {
		n.height = max(height(n.left), height(n.right)) + 1;
		n.size = size(n.left) + size(n.right) + 1;
	}
	
	//size of tree
	public int size() {
		if (root != null)
			return size(root);
		return 0;
	}
	
	//helper method to get size of current node
	private int size(TreeNode n) {
		if (n != null)
			return n.size;
		return 0;
	}
	public boolean isEmpty() {
		if (root != null)
			return true;
		return false;
	}
	
	public Object root() {
		return root.obj;
	}

	public boolean add(Object obj) {
		this.root = insert(this.root, size(this.root), obj);
		return true;
	}
	
	public void add(int index, Object obj) {
		this.root = insert(this.root, index, obj);
	}
	
	private TreeNode insert(TreeNode node, int index, Object obj) {
		if (node == null) {
			node = new TreeNode(obj);
			return node;
		}
		if (index <= size(node.left)) {
			node.left = insert(node.left, index, obj);
		} else {
			node.right = insert(node.right, index-1-size(node.left), obj);
		}
		node = rebalance(node);

		return node;
	}

	//inorder traversal. Can be used to test add(Object obj) method
	public ArrayList<Object> inorder() {
		ArrayList<Object> out = new ArrayList<>();
		inorderHelper(out, this.root);
		return out;
	}
	
	private void inorderHelper(ArrayList<Object> curList, TreeNode node) {
		if (node==null) return;
		inorderHelper(curList, node.left);
		curList.add(node.obj);
		inorderHelper(curList, node.right);
	}
	
	//Customized method used to test add(int index, Object obj) method
	//Get the right child at first, then parent, then left child
	public ArrayList<Object> reverseInorder() {
		ArrayList<Object> out = new ArrayList<>();
		reverseInorderHelper(out, this.root);
		return out;
	}
	
	private void reverseInorderHelper(ArrayList<Object> curList, TreeNode node) {
		if (node==null) return;
		reverseInorderHelper(curList, node.right);
		curList.add(node.obj);
		reverseInorderHelper(curList, node.left);
	}
	public Object get(int index) {
		TreeNode node = getHelper(index);
		return node.obj;
	}
	
	private TreeNode getHelper(int index) {
		TreeNode cur = this.root;
		while (index!=size(cur.left)) {
			if (index < size(cur.left)) {
				cur = cur.left;
			} else {
				index-=size(cur.left)+1;
				cur = cur.right;
			}
		}
		return cur;
	}

	
	public Object set(int index, Object obj) {
		TreeNode oldNode  = getHelper(index);
		Object old = oldNode.obj;
		oldNode.obj = obj;
		return old;
	}
	
	//useful for testing method 3 when the (Object obj) can be printed 
	public ArrayList<Object> levelTraversal() {
		ArrayList<Object> out = new ArrayList<>();
		Queue<TreeNode> queue = new LinkedList<>();
		int total = (int) (Math.pow(2, height(this.root))-1);
		queue.add(this.root);
		while (!queue.isEmpty()) {
			TreeNode cur = queue.poll();
			if (cur==null) {
				out.add(null);
				continue;
			}
			out.add(cur.obj);
			if (out.size()==total) break;
			queue.add(cur.left);
			queue.add(cur.right);
		}
		return out;
	} 
	
	public void prettyPrint() {
		ArrayList<Object> level = levelTraversal();
		int j=1;
		System.out.println("--------------------");
		for (int i=2;i<level.size()*2;i*=2) {
			for (;j<i;j++) {
				if (j>level.size()) break;
				System.out.print(level.get(j-1) + " ");
			}
			System.out.println();
		}
		System.out.println("--------------------");
	}
	
	//postorder traversal. 
	public ArrayList<Object> postorder() {
		ArrayList<Object> out = new ArrayList<>();
		postorderHelper(out, this.root);
		return out;
	}
	
	private void postorderHelper(ArrayList<Object> curList, TreeNode node) {
		if (node==null) return;
		postorderHelper(curList, node.left);
		postorderHelper(curList, node.right);
		curList.add(node.obj);
	} 
		
	
	public Object remove(int index) {
		if (index>=size(root)) return null;
		
		RemoveHolder r = remove(this.root,index);
		root = r.node;
		return r.tmpObj;
	}
		
	private RemoveHolder remove(TreeNode node, int index) {
		
		if (index == size(node.left)) {	 //current node is the one to be removed
			Object tmp = node.obj;
//			System.out.println("tmp: "+tmp);
			if (node.left!=null && node.right!=null) {
				if (height(node.left)>height(node.right)) {
					TreeNode max = node.left;
					while (max.right!=null) {
						max = max.right;
					}
					node.obj = max.obj;
					if (max==node.left) node.left=node.left.left;
					else {
						node.left = removeMax(node.left,max);
					}
				} else {
					TreeNode min = node.right;
					while (min.left!=null) {
						min = min.left;
					}
					node.obj = min.obj;
					if (min==node.right) node.right=node.right.right;
					else {
						node.right = removeMin(node.right,min);
					}
				}
				updateNode(node);			
			}  else {
				if (node.left!=null) node = node.left;
				else node=node.right;
			}
			return new RemoveHolder(tmp,node);				
		} 
		 
		RemoveHolder removed = null;
		if (index < size(node.left)) {
			removed = remove(node.left,index);
			node.left = removed.node;
		} else  {
			removed = remove(node.right, index-size(node.left)-1);
			node.right=removed.node;
		}
		node = rebalance(node);
		System.out.println("removing: removed.tmpObj"+removed.tmpObj);
		return new RemoveHolder(removed.tmpObj, node);
		
	}
	
	private TreeNode removeMax(TreeNode node, TreeNode max) { 
		if (node.right == max) {
			node.right = node.right.left;
			node = rebalance(node);
		} else {
			node.right = removeMax(node.right, max);
		}
		updateNode(node);
		return node;
	}
	
	private TreeNode removeMin(TreeNode node, TreeNode min) {
		if (node.left == min) {
			node.left = node.left.right;
			node = rebalance(node);
		} else {
			node.left = removeMin(node.left, min);
			
		}
		updateNode(node);
		return node;
	}
	
	private TreeNode rebalance(TreeNode node) {
		if (height(node.left) - height(node.right) == 2) {
			if (height(node.left.left) < height(node.left.right)) {
				node = lrRotation(node);
			} else {
				node = llRotation(node);
			}
		} else if (height(node.right) - height(node.left) == 2) {
			if (height(node.right.right) < height(node.right.left)) {
				node = rlRotation(node);
			} else {
				node = rrRotation(node);
			}
		}
		updateNode(node);
		return node;
	}
	
	private class RemoveHolder {
		Object tmpObj;
		TreeNode node;
		RemoveHolder(Object tmpObj, TreeNode node) {
			this.tmpObj = tmpObj;
			this.node = node;
		}
	}
	
	
	/**
     * unimplemented methods from list interface
     */
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public Iterator<Object> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean remove(java.lang.Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void clear() {
		// TODO Auto-generated method stub
	}
	
	public boolean contains(java.lang.Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean addAll(Collection<? extends Object> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean addAll(int index, Collection<? extends Object> c) {
		// TODO Auto-generated method stub
		return false;
	}

	public int indexOf(java.lang.Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int lastIndexOf(java.lang.Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public ListIterator<Object> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public ListIterator<Object> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Object> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
