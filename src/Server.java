import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private Socket socket;
    private ServerSocket serverSocket;
    private long time;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;


    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("server is listening to the port");
        serverListen();
    }

    private void serverListen(){
        while (true){
            try {
                socket = serverSocket.accept();
                inputStream = new ObjectInputStream(socket.getInputStream());
                String message = (String) inputStream.readObject();
                outputStream = new ObjectOutputStream(socket.getOutputStream());
                double cal = calculate(message);
                outputStream.writeObject(time + " " + cal);
                inputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private double calculate(String mess){
        long time1 = System.currentTimeMillis();
        double result = 0.0;
        String op2 = null;
        if (mess.split(" ").length == 3) {
            op2 = mess.split(" ")[2];
        }
        String operation= mess.split(" ")[0];
        String op1= mess.split(" ")[1];
        switch (operation) {
            case "add":
                result= Double.parseDouble(op1) + Double.parseDouble(op2);
                break;
            case "subtract":
                result = Double.parseDouble(op1) + Double.parseDouble(op2);
                break;
            case "divide":
                result = Double.parseDouble(op1) / Double.parseDouble(op2);
                break;
            case "multiply":
                result = Double.parseDouble(op1) * Double.parseDouble(op2);
                break;
            case "sin":
                result = Math.sin(Double.parseDouble(op1));
                break;
            case "cos":
                result = Math.cos(Double.parseDouble(op1));
                break;
            case "tan":
                result = Math.tan(Double.parseDouble(op1));
                break;
            case "cot":
                result = Math.cos(Double.parseDouble(op1)) / Math.sin(Double.parseDouble(op1));
                break;
        }
        long time2 = System.currentTimeMillis();
        time = (time2 - time1) * 1000;
        return result;
    }
}
