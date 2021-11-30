import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

public class Response {
    protected static void sendTextResponse(OutputStream outputToClient, String input) throws IOException {
        String sendMessage = input + " is saved in mySQL database";
        byte[] data = sendMessage.getBytes();
        String header = "HTTP/1.1 200 OK\r\nContent-Type: text/plain\r\nContent-length: " + data.length + "\r\n\r\n";

        outputToClient.write(header.getBytes(StandardCharsets.UTF_8));
        outputToClient.write(data);
        outputToClient.flush();

    }

  static void sendJsonResponse(OutputStream outputToClient, String body) throws IOException {
        Gson gson = new Gson();
        String jsonData = gson.toJson(body);
        byte[] data = jsonData.getBytes(StandardCharsets.UTF_8);

        String header = "HTTP/1.1 200 OK\r\nContent-Type: application/json\r\nContent-length: " + data.length + "\r\n\r\n";

        outputToClient.write(header.getBytes(StandardCharsets.UTF_8));
        outputToClient.write(data);
        outputToClient.flush();
    }

    static void sendHtmlResponse(OutputStream outputToClient) throws IOException {
        String header = "";
        byte[] data = new byte[0];
        File f = Path.of("hello.html").toFile();
        if (!(f.exists() && !f.isDirectory())) {
            header = "HTTP/1.1 404 Not Found\r\nContent-length: 0\r\n\r\n";
        }
        else
        {
            try(FileInputStream fileInputStream = new FileInputStream(f)){
                data = new byte[(int) f.length()];
                fileInputStream.read(data);

                header = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\nContent-length: " + data.length +"\r\n\r\n";
            }  catch (IOException e) {
                e.printStackTrace();
            }
        }
        outputToClient.write(header.getBytes());
        outputToClient.write(data);

        outputToClient.flush();
    }

    static void sendImageResponse(OutputStream outputToClient) throws IOException {
        String header = "";
        byte[] data = new byte[0];

        File f = Path.of("doggee.jpg").toFile();
        if (!(f.exists() && !f.isDirectory())) {
            header = "HTTP/1.1 404 Not Found\r\nContent-length: 0\r\n\r\n";
        }
        else
        {
            try(FileInputStream fileInputStream = new FileInputStream(f)){
                data = new byte[(int) f.length()];
                fileInputStream.read(data);

                header = "HTTP/1.1 200 OK\r\nContent-Type: image/jpeg\r\nContent-length: " + data.length +"\r\n\r\n";
            }  catch (IOException e) {
                e.printStackTrace();
            }
        }
        outputToClient.write(header.getBytes());
        outputToClient.write(data);
        outputToClient.flush();
    }


}