public class HashClosed {
    // variables
    private final LinkedList[] table;
    private final int sizeTable;

    /**
     * constructor
     * @param m size of hash table
     *          from input m we build a table.
     *          default - every cell in the table points to an empty linked list,
     *          the size of an empty linked list is 0.
     * update size of table
     */
    public HashClosed(int m){
        sizeTable = m/3;
        table = new LinkedList[sizeTable];

        for(int i = 0;i<sizeTable ;i++ ){ // every cell points to an empty linked list
            table[i] = new LinkedList();
        }
    }

    /**
     * helper function
     * @param id persons id
     * @return h(k) = k mod m
     */
    private int HashFunction(String id){
        return Integer.parseInt(id) % sizeTable;
    }

    /**
     * adds a new person to the table
     * the cell chosen to add the new person is according this hash function: h(k) = k mod m
     *                  k is the id of the person
     *                  m is the size of the table
     * @param person new person to add to the table
     */
    public void insert(String[] person){
        int cell = HashFunction(person[0]); // hash function: h(k) = k mod m
        table[cell].add(person);
    }

    /**
     *
     * @return array of integers, each cell contains the size of the same linked list from the table respectively
     */
    public int[] getNodesSize(){
        int[] NodeSize = new int[sizeTable]; // create empty list to update sizes
        for (int i = 0; i< sizeTable; i++){ // collect all the sizes of each linked list in each cell respectively
            NodeSize[i] = table[i].getSize();
        }
        return NodeSize;
    }

    /**
     * search for specific person in table
     * @param person specific person
     * @return array of integers: search[]
     *          search[0]- 0 if not found, 1 if found
     *          search[1]- number of steps to find person
     */
    public int[] search(String[] person){
        //initialize counters and array
        int[] search = new int[2];
        int found = 0;
        int steps = 0;
        Node temp = table[HashFunction(person[0])].getHead(); //start with head of linked list

        while (temp != null){
            steps++;
            if (temp.getData()[0].equals(person[0])){
                found = 1;
                break;
            }
            temp = temp.getNext();

        }

        if (temp == null){steps++;} //count steps even when not in linked list

        // update array
        search[0] = found;
        search[1]= steps;

        return  search;
    }

    /**
     *
     * @return table size: m from constructor
     */
    public int getSize(){return sizeTable;}
}
