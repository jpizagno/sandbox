package de.coding.quiz.OrderedKeyValues;

/**
 * Hello world!
 *
 */
public class App {
	
	private Node startNode;
	
    public static void main( String[] args ) {
        System.out.println( "Only Tests" ); 
    }

    /**
     * Inserts Key/Value in the correct place
     * 
     * @param string Key
     * @param value to be used to sort
     */
	public void setValue(String key, int value) {
		Node inNode = new Node(key,value);
		
		if (startNode == null) {
			startNode = inNode;
		} else if(value==0){
			deleteNode(startNode,key);
		} else {
			insertNode(startNode,inNode);
		}
	}
	
	private void deleteNode(Node curNode, String key) {
		if (curNode.getKey().equals(key)) {
			curNode.getPrevNode().setNextNode(curNode.getNextNode());  // Node removed  prev -> next  (cur skipped)
			curNode = null; // GC
		} else {
			if(curNode.getNextNode() != null) {
				// keep searching
				deleteNode(curNode.getNextNode() , key);
			}  // else key not found
		}
	}
	
	private void insertNode(Node curNode, Node inNode) {		
		if ( inNode.getKey().equals(curNode.getKey()) 
				&& curNode.getNextNode().getValue() > inNode.getValue() 
				&& curNode.getPrevNode().getValue() < inNode.getValue()) {
			// same Key, but inNode fits here
			curNode = inNode;
			curNode.setValue(inNode.getValue());
			return;
		}
		
		if ( inNode.getKey().equals(curNode.getKey()) 
				&& curNode.getNextNode().getValue() < inNode.getValue()) {
			// same Key, but inNode needs to go farther into collection
			// remove curNode: 
			curNode.getPrevNode().setNextNode(curNode.getNextNode());  // just point Prev -> Next .. so curNode is delete
			curNode = null; // GC
			// Node list is reset, so start from begining, and insert Node
			insertNode(startNode,inNode);
			return;
		}
		
		if (startNode.getValue() > inNode.getValue()) {
			// inNode is smaller than startNode, so set to beginning
			inNode.setNextNode(startNode);
			startNode = inNode;
			return;
		} else if(curNode.getNextNode() == null && curNode.getPrevNode()!= null && curNode.getValue() < inNode.getValue()) {
			// at END, and largest value
			inNode.setPrevNode(curNode);
			curNode.setNextNode(inNode);
			return;
		} else if (curNode.getNextNode() == null && curNode.getPrevNode()!= null && curNode.getValue() > inNode.getValue()) {
			// at END, but 2nd to largest value
			// prev=3 cur=5, inNode=4, next=null
			curNode.getPrevNode().setNextNode(inNode); // prev=3.next -> inNode=4
			inNode.setNextNode(curNode); // inNode=4.next -> cur=5
			inNode.setPrevNode(curNode.getPrevNode()); // inNode=4.prev -> prev=3
			return;
		} else if(curNode.getNextNode() == null && curNode.getPrevNode()== null && curNode.getValue() < inNode.getValue()) {
			// at START  getPrev = null
			// cur < inNode
			curNode.setNextNode(inNode);
			inNode.setPrevNode(curNode);
			return;
		} else if (curNode.getNextNode() == null && curNode.getPrevNode()== null && curNode.getValue() > inNode.getValue()) {
			// at START getPrev==null
			// cur > inNode , switch nodes  1st=inNode, 2nd=curNode, reset Start
			startNode = inNode;
			inNode.setNextNode(curNode);
			curNode.setPrevNode(inNode);
			return;
		} else if (curNode.getNextNode().getValue() < inNode.getValue()) {
			// GOTO Next because next.value < inNode value.
			// prev = 2, cur = 3, next=4, inNode=5
			insertNode(curNode.getNextNode(),inNode); // recursive call insertNode until null or nextNode is greater than inNode
		} else if (curNode.getValue() < inNode.getValue() && inNode.getValue() < curNode.getNextNode().getValue()) {
			// middle
			// prev=2, cur=4, next=6, inNode=5
			inNode.setNextNode(curNode.getNextNode()); // inNode=5.next -> next=6
			inNode.setPrevNode(curNode); // inNode=5.prev -> cur=4
			curNode.setNextNode(inNode); // cur=4.next -> inNode=5
			return;
		} else if (curNode.getValue() < inNode.getValue() && inNode.getValue() > curNode.getNextNode().getValue()) {
			// middle
			// prev=2, cur=4, next=5, inNode=3
			inNode.setPrevNode(curNode.getPrevNode()); // inNode=3.prev -> 2
			inNode.setNextNode(curNode);  // inNode=3.next -> cur=4
			curNode.setPrevNode(inNode);  // cur=4.prev -> inNode=3
			return;
		} else {
			String msg = "ERROR:  curNode="+curNode+" \n inNode="+inNode;
			throw new RuntimeException(msg);
		}
	}

	/**
	 * Returns the keys, ordered by values
	 * 
	 * @return String
	 */
	public String values() {
		// They are already ordered, so just call one after the next
		if (startNode.getNextNode() == null) {
			return startNode.getKey();
		} else {
			// recursion
			String answer = startNode.getKey() + getNext(startNode);
			return answer;
		}
	}
	
	private String getNext(Node inNode) {
		if (inNode.getNextNode() == null) {
			return "";
		} else {
			return inNode.getNextNode().getKey() + getNext(inNode.getNextNode());
		}
	}
}
