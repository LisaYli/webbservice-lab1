import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MessageUtils {

    public static String getMimeTypeOfFile(String filePathInString) {
        Path path = new File(filePathInString).toPath();
        String mimeType = null;
        try {
            mimeType = Files.probeContentType(path);
        } catch (IOException e) {
            System.out.println("something is wrong in getMimeTypeOfFile()");
        }
        return mimeType;
    }


    public static void main(String[] args) {
        System.out.println(getMimeTypeOfFile("doggee.jpg"));
    }

}
