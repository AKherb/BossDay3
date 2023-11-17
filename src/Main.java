import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        long before = System.currentTimeMillis();

        ArrayList<Thread> list = new ArrayList<>();

        AtomicInteger sum = new AtomicInteger();
        for(int i = 0; i < 100; i++)
        {
            AtomicInteger finalSum = sum;
            list.add(Thread.ofVirtual().start(() -> {
                int x = 0;
                for(int j = 0; j < 1000000; j++)
                {
                    x++;
                }
                finalSum.addAndGet(x);
            }));
        }

        for(int i = 0; i < 100; i++)
        {
            list.get(i).join();
        }

        long after = System.currentTimeMillis();

        System.out.println(sum);
        System.out.println("Counting finished in " + (after - before) / 1000.0 + " seconds.");

    }
}