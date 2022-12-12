import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        Person person;
        try	(ServerSocket server = new ServerSocket(1111)){
            Socket client = server.accept();
            System.out.print("Connection accepted.");
            ObjectInputStream in = new ObjectInputStream(client.getInputStream());
            while(!client.isClosed()) {
                System.out.println("Server reading from channel");
                person = (Person) in.readObject();
                System.out.println("READ from client message - " + person);
            }
            System.out.println("Client disconnected");
            in.close();
            client.close();
        } catch (ClassNotFoundException | IOException e) {
            System.exit(0);
        }
    }
}