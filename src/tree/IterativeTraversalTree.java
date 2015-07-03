package tree;

import list.IList;
import list.arraylist.MyArrayList;
import list.linkedlist.MyLinkedList;
import stack.IStack;

public class IterativeTraversalTree<K> extends BinaryTree<K> {
	
	public IList<K> populateIterativePreorderTraversal() {
		IList<K> nodes = new MyArrayList<K>();
		IStack<TreeNode<K>> stack = new MyLinkedList<TreeNode<K>>();
		stack.push(root);
		TreeNode<K> removedEle = stack.pop();
		while(removedEle != null) {
			nodes.add(removedEle.data);
			if(removedEle.right != null)
				stack.push(removedEle.right);
			if(removedEle.left != null)
				stack.push(removedEle.left);
			removedEle = stack.pop();
		}
		return nodes;
	}
	
	public IList<K> populateIterativeInorderTraversal() {
		IList<K> nodes = new MyArrayList<K>();
		IStack<TreeNode<K>> stack = new MyLinkedList<TreeNode<K>>();
		stack.push(root);
		TreeNode<K> itrElement = root;
		while(stack.canPop()) {
			if(itrElement == null) {
				TreeNode<K> pop = stack.pop();
				nodes.add(pop.data);
				itrElement = pop.right;
			} else {
				itrElement = itrElement.left;
			}
			if(itrElement != null) {
				stack.push(itrElement);
			}
		}
		return nodes;
	}
	
	public IList<K> populateIterativePostorderTraversal() {
		IList<K> nodes = new MyArrayList<K>();
		MyLinkedList<TreeNode<K>> stack = new MyLinkedList<TreeNode<K>>();
		stack.push(root);
		TreeNode<K> itrElement = root;
		while(stack.canPop()) {
			if(itrElement == null) {
				itrElement = stack.pop();
				nodes.add(itrElement.data);
				itrElement = itrElement.right;
				if(itrElement != null)
					stack.push(itrElement);
			} else {
				/*if(itrElement.right != null)
					stack.push(itrElement.right);*/
				if(itrElement.left != null) {
					stack.push(itrElement.left);
				}
				itrElement = itrElement.left;
			}
		}
		return nodes;
	}
	/**
	 * http://www.geeksforgeeks.org/boundary-traversal-of-binary-tree/
	 * 
	 * Boundary Traversal of binary tree
		Given a binary tree, print boundary nodes of the binary tree Anti-Clockwise starting from the root.
		For example, boundary traversal of the following tree is “20 8 4 10 14 25 22″
	 * @return
	 */
	public IList<K> populateBoundaryTraversal() {
		IList<K> nodes = new MyArrayList<K>();
		//get non leaf nodes in left edge
		TreeNode<K> ptr = root;
		while(ptr != null) {
			nodes.add(ptr.data);
			ptr = ptr.left;
		}
		//populate leaf nodes
		populateLeafNodesInInorderFashion(root.right, nodes);
		//populate non leaf nodes in right edge
		
		return nodes;
	}
	public void populateNonLeafNodesInRightEdge(TreeNode<K> ptr,IList<K> list) {
		if(ptr == null) {
			return;
		} else {
			populateNonLeafNodesInRightEdge(ptr.right, list);
			list.add(ptr.data);
		}
		
	}
	public void populateLeafNodesInInorderFashion(TreeNode<K> ptr,IList<K> list) {
		if(ptr == null) {
			return;
		} else if(ptr.left == null && ptr.right == null) {
			//leaf
			list.add(ptr.data);
		} else {
			populateLeafNodesInInorderFashion(ptr.left, list);
			populateLeafNodesInInorderFashion(ptr.right, list);
		}
	}
}