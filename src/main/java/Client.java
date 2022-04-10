import javafx.util.Pair;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try {

            socket = new Socket("localhost", 1234);

            inputStreamReader = new InputStreamReader(socket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Provide the name of the file");
                String filename = scanner.nextLine();
                Pair<String, String> freqAndMsg = Program.encode(IOController.read(filename));
                String freq = freqAndMsg.getKey();
                String message = freqAndMsg.getValue();

                // send frequency table
                bufferedWriter.write(freq);
                bufferedWriter.newLine();
                bufferedWriter.flush();

                // send message
                bufferedWriter.write(message);
                bufferedWriter.newLine();
                bufferedWriter.flush();

                System.out.println("Server says: " + bufferedReader.readLine());

                if (message.equalsIgnoreCase("stop")) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) socket.close();
                if (inputStreamReader != null) inputStreamReader.close();
                if (outputStreamWriter != null) outputStreamWriter.close();
                if (bufferedReader != null) bufferedReader.close();
                if (bufferedWriter != null) bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
