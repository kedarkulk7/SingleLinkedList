package utd.ds.assign1;

import java.util.Random;

public class SingleLinkedListMain {

	public static void main(String[] args) {

		Random random = new Random();
		SingleLinkedList list = new SingleLinkedList();

		for(int k = 0; k < 15; k++) {
			list.insertValue(list, random.nextInt(100));
		}

		System.out.println("Initial List");
		if(list != null) {
			list.traverse(list);
		}
		System.out.println("After Sorting");
		list = list.sortListAsc(list);
		list.traverse(list);
	}

}

class Node {

	public int value;
	public Node nextNode;


	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public Node getNextNode() {
		return nextNode;
	}
	public void setNextNode(Node nextNode) {
		this.nextNode = nextNode;
	}
}

class SingleLinkedList {

	public Node node;

	public Node getNode() {
		return node;
	}
	public void setNode(Node node) {
		this.node = node;
	}

	/**
	 * Traversing the single linked list
	 * @param list
	 */
	public void traverse(SingleLinkedList list) {
		
		Node node = list.getNode();
		if(node == null) {
			System.out.println("empty list");
		}else {
			while(node != null) {
				System.out.print(node.getValue()+" ");
				node = node.getNextNode();
			}
		}
		System.out.println();
	}


	/**
	 * Adding values into single linked list
	 * @param list
	 * @param a
	 * @return
	 */
	public SingleLinkedList insertValue(SingleLinkedList list, int a) {

		Node node = list.getNode();

		if(node == null) {
			node = new Node();
			node.setValue(a);
			//node.setNextNode(null);
			list.setNode(node);
		}else {
			while(node.getNextNode() != null) {
				node = node.getNextNode();
			}
			node.setNextNode(new Node());
			node = node.getNextNode();
			node.setValue(a);
			node.setNextNode(null);
		}


		return list;
	}


	/**
	 * Insertion Sort
	 * Sorting the list by getting the least valued number at the start 
	 * @param list
	 * @return
	 */
	public SingleLinkedList sortListAsc(SingleLinkedList list) {

		if(list != null) {
			Node prevCurrentnode = new Node();
			prevCurrentnode.setValue(-1);
			prevCurrentnode.setNextNode(list.getNode());
			Node currentNode = prevCurrentnode.getNextNode();
			Node nextCurrentNode = currentNode.getNextNode();

			boolean flag = false;
			boolean swap = false;

			int min = -1;

			while(currentNode != null) {
				min = currentNode.getValue();
				Node prevTranversenode = prevCurrentnode.getNextNode();
				Node traverseNode = prevTranversenode.getNextNode();

				while(traverseNode != null) {					
					swap = false;
					if(traverseNode.getValue() < min) {
						swap = true;
						min = traverseNode.getValue();

						boolean consecutive = false;

						if(currentNode.getNextNode() == traverseNode) {
							consecutive = true;
						}

						prevCurrentnode.setNextNode(traverseNode);
						if(!consecutive) {
							prevTranversenode.setNextNode(currentNode);
						}
						currentNode.setNextNode(traverseNode.getNextNode());
						if(consecutive) {
							traverseNode.setNextNode(currentNode);
						}else {
							traverseNode.setNextNode(nextCurrentNode);
						}

						currentNode = prevCurrentnode.getNextNode();
						traverseNode = prevTranversenode.getNextNode();

						if(currentNode != null && currentNode.getNextNode() != null) {
							nextCurrentNode = currentNode.getNextNode();
						}else {
							nextCurrentNode = null;
						}
					}
					if(swap) {
						prevTranversenode = currentNode;
					}else {
						prevTranversenode = prevTranversenode.getNextNode();
					}
					if(prevTranversenode != null) {
						traverseNode = prevTranversenode.getNextNode();
					}else {
						traverseNode = null;
					}

				}

				if(!flag) {
					list.setNode(prevCurrentnode.getNextNode());
					flag = true;
				}

				prevCurrentnode = prevCurrentnode.getNextNode();
				if(prevCurrentnode != null) {
					currentNode = prevCurrentnode.getNextNode();
				}else {
					currentNode = null;
				}
				if(currentNode != null && currentNode.getNextNode() != null) {
					nextCurrentNode = currentNode.getNextNode();
				}else {
					nextCurrentNode = null;
				}
			}

		}else {
			System.out.println("empty list");
		}

		return list;
	}

	/**
	 * Bubble sort algorithm
	 * @param list
	 * @return
	 */
	public SingleLinkedList sortAsc(SingleLinkedList list) {

		if(list != null) {

			Node prevCurrentNode = new Node();
			prevCurrentNode.setValue(-1);
			prevCurrentNode.setNextNode(list.getNode());

			boolean first = true;
			boolean swap = true;

			Node currentNode = list.getNode();
			Node traverseNode = currentNode.getNextNode();

			while(swap) {
				swap = false;
				first = true;

				while(traverseNode != null) {

					if(currentNode.getValue() > traverseNode.getValue()) {

						prevCurrentNode.setNextNode(traverseNode);
						currentNode.setNextNode(traverseNode.getNextNode());
						traverseNode.setNextNode(currentNode);
						swap = swap || true;
					}else {

						swap = swap || false;
					}

					if(first) {
						list.setNode(prevCurrentNode.getNextNode());						
					}

					prevCurrentNode = prevCurrentNode.getNextNode();
					currentNode = prevCurrentNode.getNextNode();
					traverseNode = currentNode.getNextNode();
					first = false;

				}
				prevCurrentNode = new Node();
				prevCurrentNode.setValue(-1);
				prevCurrentNode.setNextNode(list.getNode());
				currentNode = prevCurrentNode.getNextNode();
				traverseNode = currentNode.getNextNode();
			}
		}

		return list;
	}

}

