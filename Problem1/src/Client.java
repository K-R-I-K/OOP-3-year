import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        //Person person = new Person("Italy", 18, "Volodymyr", 190);
        try(Socket socket = new Socket("localhost", 1111);
            Scanner scanner = new Scanner(System.in);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {
            System.out.println("Client connected to socket.");
            while(!socket.isOutputShutdown()){
                System.out.println("Enter some information about person");
                System.out.println("Enter country: ");
                String country = scanner.next();
                System.out.println("Enter age: ");
                int age = scanner.nextInt();
                System.out.println("Enter name: ");
                String name = scanner.next();
                System.out.println("Enter height: ");
                int height = scanner.nextInt();
                Person person = new Person(country, age, name, height);
                System.out.println("Write to Server message - " + person);
                out.writeObject(person);
                out.flush();
                System.out.println("Want to continue? (Y/N)");
                String ans = scanner.next();
                if(ans.equals("N")) {
                    System.out.println("Client kill connections");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
