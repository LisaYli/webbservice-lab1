import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    static Scanner sc = new Scanner(System.in);
    private static HttpURLConnection con;

    public static void main(String[] args) throws IOException {
        while (true) {
            int user = Menu.mainMenu();
            switch (user) {
                case 1:
                    System.out.println("GET ALL USERS");
                    getAllUsers();
                    break;
                case 2:
                    System.out.println("POST USER");
                    addUserPost();
                    break;

            }
        }

    }

    public static void getAllUsers() throws IOException {
        StringBuilder content = null;
        HttpURLConnection con = null;
        var url = "http://localhost:1237/";
        try {
            var myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "Content-Type: text/html");
            try (var br = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {

                String line;
                content = new StringBuilder();

                while ((line = br.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }

        } finally {
            System.out.println(content);

           
        }
    }


    public static void addUserPost() throws IOException {
        System.out.print("Enter username: ");
        String name = sc.nextLine();
        var url = "http://localhost:1415/create";
        byte[] postData = name.getBytes(StandardCharsets.UTF_8);

        try {

            var myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();

            con.setDoOutput(true);
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", "Java client");
            con.setRequestProperty("Content-Type", "Content-Type: text/plain");

            try (var wr = new DataOutputStream(con.getOutputStream())) {

                wr.write(postData);
            }

            StringBuilder content;

            try (var br = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {

                String line;
                content = new StringBuilder();

                while ((line = br.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }

            System.out.println(content.toString());

        } finally {

            con.disconnect();
        }
    }

}




