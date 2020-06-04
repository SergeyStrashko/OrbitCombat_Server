import java.io.*;
import java.net.*;

class Server {

    public static void main(String[] args) {
        int port = 6868;

        InetAddress serverAddress = null;
        try {
            serverAddress = InetAddress.getByName("192.168.1.100");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(port, 50, serverAddress)) {

                System.out.println("Server is listening on port " + port);
                Socket firstPlayer = serverSocket.accept();
                System.out.println("First device connected");
                Socket secondPlayer = serverSocket.accept();
                System.out.println("Second device connected");

                while (true) {
                    InputStream input = firstPlayer.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                    String recFirst = reader.readLine();

                    if (recFirst == null) break;

                    input = secondPlayer.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(input));

                    String recSecond = reader.readLine();

                    if (recSecond == null) break;

                    OutputStream output = secondPlayer.getOutputStream();
                    PrintWriter writer = new PrintWriter(output, true);

                    writer.println(recFirst);

                    output = firstPlayer.getOutputStream();
                    writer = new PrintWriter(output, true);

                    writer.println(recSecond);
                }

            } catch (IOException ex) {
                System.out.println("Server exception: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}
