import java.util.Scanner;

public class ClientMain {

    public static void main(String[] args) {
        new Client(2000 , getInput());
    }

    private static String getInput(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
