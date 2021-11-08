public class ArrayDequeMyTest {
    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        ArrayDeque<Integer> ad1 = new ArrayDeque<Integer>();
        System.out.println(ad1.isEmpty());
        ad1.addLast(2);
        ad1.addFirst(1);
        ad1.addFirst(0);
        int k = 2;
        while (k < 10000) {
            k += 1;
            ad1.addFirst(k);
        }
        int i = 2;
        while (i < 100) {
            i += 1;
            ad1.removeFirst();
        }
        ad1.printDeque();
        System.out.println(" ");
        System.out.println("size is " + ad1.size());
        System.out.println((float)ad1.size()/ad1.length());
    }
}
