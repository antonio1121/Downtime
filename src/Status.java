import java.io.IOException;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.lang.StringBuilder;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Status {
    static int setUp = 0 ;
    static int sizeOfBars = 20 ; //How many bars are displayed
    static int queueCount ;
    static ArrayList<Queue<Integer>> displayArray ;
    static int[] resultArray = new int[App.AddressCount];

    public static void display() throws IOException {

        if(setUp==0) {
            displayArray = new ArrayList<Queue<Integer>>();

            for(int i = 0; i != App.AddressCount; i++) {
                Queue<Integer> queue = new ArrayBlockingQueue<>(sizeOfBars);
                displayArray.add(queue);
            }
            setUp++;
        }
    
        for(int i = 0; i!= App.AddressCount;i++) {
            if(!displayArray.get(i).offer(resultArray[i])) {
                displayArray.get(i).poll();
                displayArray.get(i).add(resultArray[i]);
            }
        }

        // For console output

        /**System.out.println("\n\n\n\n\n\n");
        System.out.println("The interval between each number is " + App.pingTime + " minute(s).");
        for(int i = 0; i!=App.AddressCount;i++) {
            System.out.print(App.AddressNames.get(i)+ " \n");
            System.out.println(displayArray.get(i));
            System.out.print("\n");
        } */

        // HTML output

        StringBuilder output = new StringBuilder();

        output.append("<html>");
        output.append("<head>");
        output.append("<title> Downtime Report </title>");
        output.append("</head>");
        output.append("<meta http=equiv=\"refresh\" content=\"" + App.pingTime * 30000 + "\">");
        output.append("<body>");
        output.append("<b> The interval between each number is " + App.pingTime + " minutes(s). </b>");
        
        for(int i = 0; i!=App.AddressCount; i++) {
            output.append("<p> <b>" + App.AddressNames.get(i) + "</b> </p>");
            output.append("<p>" + displayArray.get(i) + "</p>");
            }

        FileWriter file = new FileWriter("page.html");
        BufferedWriter out = new BufferedWriter(file);
        out.write(output.toString());
        out.close();

    }
}
