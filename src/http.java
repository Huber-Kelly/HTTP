//Alex taught HTTP in our group meeting, he gave us permission to play with his code, I manipulated and changed a few things
//This was a learning experience playing with this sandbox code
import java.net.*;
import java.io.*;
import java.security.Permission;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class http {

    public static void main(String[] args) throws IOException {

        //Get user input for website URL
        Scanner userInput = new Scanner(System.in);
        System.out.println("Which website do you wish to connect to? Enter it now (For example: 'byui.edu'): ");
        String userHTTP = userInput.nextLine();

        //added "http://" to make this user friendly it will fail without it, user will not have to enter it in
        try {
            URL url = new URL("http://" + userHTTP);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            //gives 3 seconds to get a response from website
            connection.setConnectTimeout(3000);

            //Method used to get response from URL
            String response = connection.getResponseMessage();


            // If statements, informing user if connection was successful or not, being user friendly
            //getResponseMessage method will return OK or Not Found and null if HTTP is not valid
           if (response.equals("Not Found")) {
                System.out.println("Website Connection Failed.\n");
            }
            if (response.equals("OK")) {
                System.out.println("Website Connection Successful.\n");
            }
            //I tried multiple ways to have this print "Invalid Website"
            // when an invalid url was entered but could not get it to work
            if (response.equals(null)) {
                System.out.println("Invalid Website.\n");
            }


            //Get Permission method query
            Permission request = connection.getPermission();
            System.out.println("Permission used: " + request + "\n");

            // 3 second pause, so user can read "Permission Used:"
            TimeUnit.SECONDS.sleep(3);


            //Output html from user selected website URL
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String LineInput;

            while ((LineInput = in.readLine()) != null)
                System.out.println(LineInput);
            in.close();

            // Disconnect from URL
            connection.disconnect();

            //"happy path" not a "nasty path"
        }   //catch will catch errors, and run a stack trace
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
