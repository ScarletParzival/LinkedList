

class IndexWrapper{
    public int index = 0;
}

class LinkedListCustom{
	
	public LinkedListNode head;
	
	public void addNodeToEndOfLinkedList(LinkedListNode newNode){
		if(head == null){
			head = newNode;
			head.next = null;
		}
		else{
			LinkedListNode current = head;
			while(current.next != null){
				current = current.next;
			}
			current.next = newNode; 
		}
	}
	
	public void addNodeToStartOfLinkedList(LinkedListNode newNode){
		newNode.next = head; 
		head = newNode;
	}
	
	public void printLinkedList(){
		LinkedListNode current = head;
		while(current!=null){
			System.out.print(current.number + "-->");
			current = current.next;
		}
		System.out.print("null");
	}
	
	public void reverseLinkedList(){
		LinkedListNode previous, current, next;
		current = head;
		previous = null;
		while(current != null){
			next = current.next;
			current.next = previous;
			previous = current;
			current = next;
		}
		head = previous;
	}

    public void findKNodeFromTheEnd(int k){
      findKNodeFromTheEnd(head,k);
    }

    private int findKNodeFromTheEnd(LinkedListNode head, int k){
      if(head == null){
          return 0;
      }
      int i = findKNodeFromTheEnd(head.next, k)+1;
      if(i == k){
          System.out.println("\n\nFound the kth element from the end: "+head.number);
      }
      return i;
    }

    public LinkedListNode findKNodeFromTheEnd(int k, IndexWrapper i){
        return findKNodeFromEnd(head,k,i);
    }

    private LinkedListNode findKNodeFromEnd(LinkedListNode head, int k, IndexWrapper i){

        if(head == null){
            return null;
        }

        LinkedListNode node = findKNodeFromEnd(head.next, k, i);
        i.index++;
        if(i.index == k){
            return head;
        }
        return node;
    }

    public LinkedListNode findKNodeFromEndIteratively(int k){
        return findKNodeFromEndIteratively(head,k);
    }

    private LinkedListNode findKNodeFromEndIteratively(LinkedListNode head,int k){
        if(head == null){
            return null;
        }
        LinkedListNode headStart = head;
        for(int i=0; i<k; ++i){
            headStart = headStart.next;
            if(headStart == null){
                return null;
            }
        }
        LinkedListNode kthNode = head;
        while(headStart!= null){
            headStart = headStart.next;
            kthNode = kthNode.next;
        }
        return kthNode;
    }

    public boolean deleteNodeFromMiddle(LinkedListNode nodeToBeDeleted){

        if(nodeToBeDeleted == null || nodeToBeDeleted.next == null){
            return false;
        }

        nodeToBeDeleted.number = nodeToBeDeleted.next.number;
        nodeToBeDeleted.next = nodeToBeDeleted.next.next;
        return true;

        /*
        if(nodeToBeDeleted == null){
            return false;
        }
        else if(nodeToBeDeleted.next ==null){
            nodeToBeDeleted = null;
            // Making it null here doesn't mean that we are deleting the node,
            // it just means that the node that was sent by reference (as is the case with Java always)
            // has just been made to reference to the null value and nothing else.
            // The linkedList still has access to this node through the next pointer of the previous node while traversing the list.
            // Uncomment this section, run it and see for yourself if you don't recollect much.
        }
        else {
            nodeToBeDeleted.number = nodeToBeDeleted.next.number;
            nodeToBeDeleted.next = nodeToBeDeleted.next.next;
        }
        return true;
        */
    }

    public int linkedListLength(){
        LinkedListNode current = head;
        int length = 0;
        while (current!=null){
            current = current.next;
            ++length;
        }
        return length;
    }
    public LinkedListNode returnARandomNodeFromMiddle(){
        int length = linkedListLength();
        int randomNumber = (int)(Math.random()*length);
        LinkedListNode randomNode = head;
        for(int i=0; i<randomNumber; ++i){
            randomNode = randomNode.next;
        }
        return randomNode;
    }

    public LinkedListNode reverseLinkedListRecursive(LinkedListNode current){
        if(current.next==null){
            return current;
        }
        LinkedListNode head = reverseLinkedListRecursive(current.next);
        System.out.println("Head: "+head.number);
        System.out.println("Current: "+current.number);
        System.out.println("Current.next: "+current.next.number);
        current.next.next = current;
        current.next = null;
        return head;
    }

    public LinkedListNode reverseLinkedListInGroupsOfK(int k){
        return reverseLinkedListInGroupsOfK(head,k);
    }
    public LinkedListNode reverseLinkedListInGroupsOfK(LinkedListNode head, int k){

        LinkedListNode current, previous, next;
        current = head;
        previous = null;
        next = null;
        int count = 0;
        while(current!=null && count <k){
            next = current.next;
            current.next = previous;
            previous = current;
            current =next;
            ++count;
//            System.out.print(k);
        }

        if(next!=null){
            head.next = reverseLinkedListInGroupsOfK(next,k);
//            System.out.println("Calling next from: "+head.number);
        }
//        head = previous;
        return previous;
    }
}

public class kthNodeInALinkedList {
	
	public static void main(String[] args) {
		LinkedListCustom linkedList = new LinkedListCustom();
		linkedList.head = new LinkedListNode(2);
		for(int i=3; i<= 20; ++i){
			linkedList.addNodeToEndOfLinkedList(new LinkedListNode(i));
		}
		linkedList.addNodeToStartOfLinkedList(new LinkedListNode(1));
		linkedList.printLinkedList();

        int k = 3;
        linkedList.findKNodeFromTheEnd(k);

        k=1;
        LinkedListNode kNode = linkedList.findKNodeFromTheEnd(k,new IndexWrapper());
        System.out.println("\n'Number-value' of the "+ k + "-th node from the end: "+kNode.number);

        k=5;
        kNode = linkedList.findKNodeFromEndIteratively(k);
        System.out.println("\n'Number-value' of the "+ k + "-th node from the end: "+kNode.number);

		linkedList.reverseLinkedList();
		System.out.println();
		linkedList.printLinkedList();

        linkedList.reverseLinkedList();
        linkedList.printLinkedList();

        System.out.println("\nReverse linked list recursively");
        linkedList.head = linkedList.reverseLinkedListRecursive(linkedList.head);
        System.out.println("Head is: "+linkedList.head.number);
        linkedList.printLinkedList();

        LinkedListNode randomNodeFromMiddle = linkedList.returnARandomNodeFromMiddle();
        int nodeValue = randomNodeFromMiddle.number;
        boolean randomNodeDeleted = linkedList.deleteNodeFromMiddle(randomNodeFromMiddle);
        if(randomNodeDeleted){
            System.out.println("\n\nRandom node with number: " +nodeValue+" has been successfully deleted!");
            linkedList.printLinkedList();
        }
//        for(int i=6; i>=1; --i){
//            linkedList.addNodeToEndOfLinkedList(new LinkedListNode(i));
//        }
//        linkedList.printLinkedList();
        int K=3;
        System.out.println("\n\nReversing the linkedList in groups of K: "+K);
        linkedList.head = linkedList.reverseLinkedListInGroupsOfK(K);
        linkedList.printLinkedList();
	}
	
}
