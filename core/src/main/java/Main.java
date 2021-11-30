import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        try (ServerSocket serverSocket = new ServerSocket(1239)) {
            while (true) {
                Socket client = serverSocket.accept();
                System.out.println("Connection from : " + client.getInetAddress());
                executorService.submit(() -> handleConnection(client));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleConnection(Socket clientSocket) {
        try {
            InputStream inputStream = clientSocket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            var outputToClient = clientSocket.getOutputStream();
            var url = readRequest(bufferedReader);

            urlRouting(bufferedReader, outputToClient, url);

            inputStream.close();
            inputStreamReader.close();
            clientSocket.close();
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }

    }
    private static void urlRouting(BufferedReader br, OutputStream outputToClient, String url) throws IOException {
        switch (url) {
            case "/" -> Response.sendHtmlResponse(outputToClient);
            case "/create" -> readUsername(br, outputToClient);
            case "/getusers" -> sendListOfUsers(br, outputToClient);
            case "/dogge" -> Response.sendImageResponse(outputToClient);
            case "/html" -> Response.sendHtmlResponse(outputToClient);
        }
    }

    private static String readRequest(BufferedReader inputFromClient) throws IOException {
        var url = "";
        String headerLine = "";
        while ((headerLine = inputFromClient.readLine()).length() != 0) {
            if (headerLine.startsWith("POST") || headerLine.startsWith("GET") || headerLine.startsWith("HEAD")) {
                url = headerLine.split(" ")[1];
            }
            System.out.println(headerLine);
        }
        return url;
    }

    private static void sendListOfUsers(BufferedReader inputFromClient,OutputStream outputToClient) throws IOException {
        StringBuilder payload = new StringBuilder(AccessUserFunctions.getAllUsers());
        while(inputFromClient.ready()){
            payload.append((char) inputFromClient.read());
        }
        String users = payload.toString();
        Response.sendJsonResponse(outputToClient,users);
    }


    private static void readUsername(BufferedReader inputFromClient, OutputStream outputToClient) throws IOException {
        StringBuilder payload = new StringBuilder();
        while (inputFromClient.ready()) {
            payload.append((char) inputFromClient.read());
        }
        String userName = payload.toString();
        AccessUserFunctions.addUser(userName);
        Response.sendTextResponse(outputToClient,userName);

    }


}
