public class Node{
    // node data and pointer:
    private final String[] data;
    private Node next;

    /**
     * constructor
     * @param person is an array of strings the holds the person's data:
     *               person [0] - id
     *               person[1]- first name
     *               person[2] - last name
     *               person[3] - ticket category
     */
    public Node(String[] person){
        data = person;
        next = null; // Next node is set to null as default

    }

    /**
     *
     * @return the next node that is after this one
     */
    public Node getNext(){ return next; }

    /**
     * sets the input node to be the next node, after this one
     * @param node - input node, replaces the default (null)
     */
    public void setNext(Node node){ next = node; }

    /**
     *
     * @return node data as string array
     */
    public String[] getData(){return data;}

    }

