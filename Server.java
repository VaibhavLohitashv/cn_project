import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ObjectOutputStream outputStream;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server is listening on port " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startServer() {
        try {
            clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress());
            outputStream = new ObjectOutputStream(clientSocket.getOutputStream());

            // Send a simple message to the client
            sendMessageToClient("");

            // Close the server after sending the message
            closeServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessageToClient(String message) throws IOException {
        // Send the message to the client
        outputStream.writeObject(message);
    }

    private void closeServer() throws IOException {
        // Close the server resources
        outputStream.close();
        clientSocket.close();
        serverSocket.close();
        System.out.println("Server closed.");
    }

    public static void main(String[] args) {
        Server server = new Server(5000);
        server.startServer();
    }
}
