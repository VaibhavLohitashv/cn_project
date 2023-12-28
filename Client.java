import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Client {
    private Socket socket;
    private ObjectInputStream inputStream;

    public Client(String serverAddress, int port) {
        try {
            socket = new Socket(serverAddress, port);
            System.out.println("Connected to server");
            inputStream = new ObjectInputStream(socket.getInputStream());

            // Receive and display the message from the server
            receiveAndDisplayMessage();

            // Close the client after receiving the message
            closeClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void receiveAndDisplayMessage() {
        try {
            // Receive message from the server
            String message = (String) inputStream.readObject();
            System.out.println("Message from server: " + message);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void closeClient() throws IOException {
        // Close the client resources
        inputStream.close();
        socket.close();
        System.out.println("Client closed.");
    }

    public static void main(String[] args) {
        Client client = new Client("localhost", 5000);
    }
}
