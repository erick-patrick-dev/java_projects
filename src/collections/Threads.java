import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Threads {

    public static List<Integer> numbers = new ArrayList<>();

    // Solucionando problema de concorrência de acesso a variável
    private synchronized static void incSync(int number){
        numbers.add(number);
    }

    private synchronized static void showSync(){
        System.out.println(numbers);

    }

    public static void concurrenceProblem(){
        Runnable inc = () -> {
          for (int i = 0; i < 100_000; i++){
              incSync(i);
          }
        };
        Runnable dec = () -> {
            for (int i = 100_000; i > 0; i--){
                incSync(i);
            }
        };

        Runnable show = () -> {
            for (int i = 0; i < 250_000; i++) {
                showSync();
            }  
        };

        new Thread(inc).start();
        new Thread(dec).start();
        new Thread(show).start();
    }

    // Outra modalidade de implementação
    public static Queue<Integer> numbers2 = new LinkedBlockingQueue<>(250_000);

    private static void inc(int number){
        numbers2.add(number);
    }

    private static void show(){
        System.out.println(numbers2);
    }

    public static void queueImplement() throws InterruptedException {
        Runnable inc = () -> {
            for (int i = 0; i < 100; i++){
                inc(i);
            }
        };
        Runnable dec = () -> {
            for (int i = 100; i > 0; i--){
                inc(i);
            }
        };

        Runnable show = () -> {
            for (int i = 0; i < 250; i++) {
                show();
            }
        };

        var execInc = new Thread(inc);
        execInc.start();
        execInc.setName("execInc");
        // execInc.join(8000); // Prioriza essa thread em detrimento das outras

        var execDec = new Thread(dec);
        execDec.start();
        execDec.setName("execDec");

        var execShow = new Thread(show);
        execShow.start();
        execShow.setName("execShow");

        System.out.println(execInc.getName());
        System.out.println(execDec.getName());
        System.out.println(execShow.getName());
    }

    /*
    * LinkedBlockingQueue sincroniza as requisições.
    */

    // Outra implementação

    //private static int num = 0;
    private static AtomicInteger num = new AtomicInteger(0); // Garantir previsibilidade

    public static void run2(){
        Runnable inc = () -> {
            for (int i = 0; i < 1000_000; i++){
                num.incrementAndGet();
            }
        };
        Runnable dec = () -> {
            for (int i = 0; i > -1000_000; i--){
                num.decrementAndGet();
            }
        };

        Runnable show = () -> {
            for (int i = 0; i < 1000_000; i++) {
                System.out.println(num);
            }
        };

        var execInc = new Thread(inc);
        execInc.start();
        execInc.setName("execInc");
        // execInc.join(8000); // Prioriza essa thread em detrimento das outras

        var execDec = new Thread(dec);
        execDec.start();
        execDec.setName("execDec");

        var execShow = new Thread(show);
        execShow.start();
        execShow.setName("execShow");

//        System.out.println(execInc.getName());
//        System.out.println(execDec.getName());
//        System.out.println(execShow.getName());
    }


}
