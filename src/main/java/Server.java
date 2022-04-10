import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {


    public static void main(String[] args) throws IOException {

        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        ServerSocket serverSocket = new ServerSocket(1234);


        while (true) {
            try {
                socket = serverSocket.accept();
                inputStreamReader = new InputStreamReader(socket.getInputStream());
                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
                bufferedReader = new BufferedReader(inputStreamReader);
                bufferedWriter = new BufferedWriter(outputStreamWriter);

                while (true) {
                    String m1 = bufferedReader.readLine();
                    String status = "received frequency table: " + m1;

                    String m2 = bufferedReader.readLine();
                    String status2 = "received message: " + m2;

                    System.out.println(status);
                    System.out.println(status2);

                    String decodedText = Program.decode(m1, m2);
                    System.out.println("Decoded: " + decodedText);
                    IOController.write("run-app-decoded",decodedText);

                    bufferedWriter.write("received");
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    if (m1.equalsIgnoreCase("stop")) {
                        break;
                    }
                }

                socket.close();
                inputStreamReader.close();
                outputStreamWriter.close();
                bufferedReader.close();
                bufferedWriter.close();
                serverSocket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


}
