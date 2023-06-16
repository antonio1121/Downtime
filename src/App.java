import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.List;

public class App {

    static int AddressCount = 0 ;
    static int resultIndex = 0 ;
    static ArrayList<String> AddressNames = new ArrayList<String>();
    static int pingTime = 30 ; //Sets how often to ping addresses in minutes.

    public static void main(String[] args) throws Exception {

        var Addresses = new ArrayList<String>();
        BufferedReader buffer = new BufferedReader(new FileReader("address"));

        System.out.println("Make sure that the addresses file has correct addresses,\n and that they are spaced by line.");

        for(String line = buffer.readLine(); line!= null; line = buffer.readLine()) {
            AddressNames.add(line);
            Addresses.add(buffer.readLine());
            AddressCount++;
        }

        buffer.close();
        while(true) {
        resultIndex = 0 ;

        ExecutorService executor = Executors.newFixedThreadPool(AddressCount);
        List<Future<PingResult>> resultStack = new ArrayList<Future<PingResult>>() ;
        Callable<PingResult> callStack = null ;

        for(String ip : Addresses) {
            callStack = new PingTask(ip);
            Future<PingResult> future = executor.submit(callStack);
            resultStack.add(future);
        }
        
        for(Future<PingResult> result: resultStack) {
            // System.out.println(result.get());
            Status.resultArray[resultIndex] = result.get().getResultCode();
            resultIndex++;
        }
        Status.display();
         Thread.sleep(pingTime * 60000);
    }
}
}
