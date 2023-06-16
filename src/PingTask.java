import java.util.concurrent.Callable;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PingTask implements Callable<PingResult> {

    static boolean isWindows = System.getProperty("os.name").toLowerCase().contains("win");
    private String ipAddress ;

    public PingTask(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    
    @Override
    public PingResult call() {
        String s = null ;
        int pingNum = 2 ; // Number of pings sent each call

        try {
            ProcessBuilder processBuilder = new ProcessBuilder("ping", isWindows? "-n" : "-c",Integer.toString(pingNum), ipAddress);
            Process proc = processBuilder.start();

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

            while((s = stdInput.readLine()) !=null) {
                if(s.indexOf(Integer.toString(pingNum)+" received")!=-1) {
                    return new PingResult(ipAddress,1);
                }
            } 

            while((s = stdError.readLine()) !=null) {
                System.out.println(s); // uncomment for error reporting
                return new PingResult(ipAddress,0);
            }

        } catch(Exception e) {
            e.printStackTrace();
            return new PingResult(ipAddress, 0);
        }
        return new PingResult(ipAddress, 0);
    }
}
