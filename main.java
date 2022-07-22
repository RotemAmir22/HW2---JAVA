import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Arrays;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        //Part a

        String[] person = new String[]{"123456683", "JERRY", "SEINFELD", "VIP"};
        Node node = new Node(person);
        System.out.println(Arrays.deepToString(node.getData()));

        LinkedList linkedList = new LinkedList();
        System.out.println(linkedList.getHead());
        linkedList.add(person);
        System.out.println(Arrays.toString(linkedList.getHead().getData()));
        linkedList.add(new String[]{"777777779", "KURT", "COBAIN", "INNER_RING"});
        System.out.println(Arrays.toString(linkedList.getHead().getNext().getData()));
        linkedList.add(new String[]{"777777729","KURT","COBANA","OUTER_RING"});
        System.out.println(Arrays.toString(linkedList.getHead().getNext().getNext().getData()));
        System.out.println(linkedList.getSize());

        HashClosed table = new HashClosed(3);
        table.insert(person);
        table.insert(new String[]{"777777779", "KURT", "COBAIN", "INNER_RING"});
        table.insert(new String[]{"777777729","KURT","COBANA","OUTER_RING"});
        System.out.println(Arrays.toString(table.getNodesSize()));
        System.out.println(Arrays.toString(table.search(new String[]{"777777779", "KURT", "COBAIN", "INNER_RING"})));
        System.out.println(Arrays.toString(table.search(new String[]{"777777729","KURT","COBANA","OUTER_RING"})));
        System.out.println(Arrays.toString(table.search(person)));
        System.out.println(Arrays.toString(table.search(new String[]{"777777729","MEOW","COBAIN","OUTER_RING"})));
        System.out.println(Arrays.toString(table.search(new String[]{"777777779","MEOW","COBANA","OUTER_RING"})));
        System.out.println(Arrays.toString(table.search(new String[]{"123456691","MEOW","PEARSOM","OUTER_RING"})));
        System.out.println(table.getSize());

        //Part b

        HashClosed hash = Concert.registerCrowd("input1.txt");
        int[] arrived = Concert.reception("input2.txt", hash);
        System.out.println(arrived);
        int avg = Concert.reception_AverageSteps("input2.txt", hash);
        System.out.println(avg);
        int[] steps1 = Concert.seatingArrangement(arrived, hash, 1);
        int[] steps2 = Concert.seatingArrangement(arrived, hash, 2);
        System.out.println("hello");


    }

}
