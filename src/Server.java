import java.io.*;
import java.net.*;

class Server {

    public static void main(String[] args) {
        int port = 6868;

        InetAddress serverAddress = null;
        try {
            serverAddress = InetAddress.getByAddress(new byte[]{(byte) 10, (byte) 42, (byte) 0, (byte) 1});
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        try (ServerSocket serverSocket = new ServerSocket(port, 50, serverAddress)) {

            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();

                System.out.println("New client connected");

                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                String time = reader.readLine();

                System.out.println(time);
            }

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
