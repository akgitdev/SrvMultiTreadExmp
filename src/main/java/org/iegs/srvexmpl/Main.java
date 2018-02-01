package org.iegs.srvexmpl;

import java.util.Random;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {

        ExecutorService service = Executors.newCachedThreadPool();

        Future<Integer> future = service.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Random random = new Random();
                int duration = random.nextInt(4000); // 1 second

                System.out.println("Starting ...");

                try {
                    Thread.sleep(duration);
//                    TimeUnit.SECONDS.sleep(duration);
                }
                catch (InterruptedException e) {
                    e.printStackTrace(); // auto matically generated
//                    throw new IllegalStateException("task interrupted", e);
                }

                System.out.println("Finished");

                return duration;
            }
        });  // Future

        service.shutdown();

        try {
            System.out.println("Result = " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    };

}

