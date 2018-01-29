package de.coding.quiz.OrderedKeyValues;

public class Node {
	
	private Node prevNode;
	private Node nextNode;
	private String key;
	private int value;
	
	public Node(String key, int value) {
		this.key=key;
		this.value=value;
	}
	
	public Node getPrevNode() {
		return prevNode;
	}
	public void setPrevNode(Node prevNode) {
		this.prevNode = prevNode;
	}
	public Node getNextNode() {
		return nextNode;
	}
	public void setNextNode(Node nextNode) {
		this.nextNode = nextNode;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}

}
