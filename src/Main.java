import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {


        final int THREAD_NUM =10;
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_NUM);


        for (int i = 0; i < THREAD_NUM; i++) {
            int iteration = i;
            Runnable task = () ->{

                System.out.println(iteration+": Hello world");


            };

            executor.submit(task);

        }
        executor.shutdown();
    }
}