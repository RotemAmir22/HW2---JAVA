public class LinkedList {
    // list pointers and data
    private Node head;
    private Node last;
    private int size;

    /**
     * constructor
     * the default is to create an empty list, so the head is a pointer to null
     * empty list so, last node is also null
     * size of an empty list is 0
     */
    public LinkedList(){
        head = null;
        last = null;
        size = 0;
    }

    /**
     * add node to linked list to the end of list
     * update size of linked list by adding 1
      * @param person array of strings that contain the data of the new node:
     *                   person [0] - id
     *                   person[1]- first name
     *                   person[2] - last name
     *                   person[3] - ticket category
     */
    public void add(String[] person){
        size ++;

        if (head == null){ // empty list
            head =new  Node(person);
            last = head;
        }
        else{
            last.setNext(new Node(person)); //add to end of list
            last = last.getNext();
        }
    }

    /**
     *
     * @return number of nodes in linked list
     */
    public int getSize(){return size;}

    /**
     *
     * @return head of linked list
     */
    public Node getHead(){return head;}

    /**
     * removes the first node from list
     * and update size
     */
    public void removeHead(){
        Node temp = getHead();
        head = temp.getNext();
        size--;
    }
}
