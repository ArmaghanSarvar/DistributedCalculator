import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private Socket clientSocket;
    private InetAddress host;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private String input;

    public Client(int port , String input) {
        this.input = input;
        try {
            host = InetAddress.getLocalHost();
            clientSocket = new Socket(host.getHostName(), port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        accept();
    }

    private void accept(){
            try {
                outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
                outputStream.writeObject(input);
                inputStream = new ObjectInputStream(clientSocket.getInputStream());
                System.out.println((String)inputStream.readObject());

                outputStream.close();
                clientSocket.close();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
    }
}
