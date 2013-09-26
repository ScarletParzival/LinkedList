/**
 * Created with IntelliJ IDEA.
 * User: sowmyahariharan
 * Date: 9/23/13
 * Time: 1:38 AM
 * To change this template use File | Settings | File Templates.
 */
class LinkedListNode {
    int number;
    LinkedListNode next;

    LinkedListNode(int number){
        this.number = number;
        this.next = null;
    }

    LinkedListNode(int number, LinkedListNode next){
        this.number = number;
        this.next = next;
    }
}
