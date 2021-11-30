import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

        @SneakyThrows
        @Test
        public void checkMimeType() {
            Path path = new File("doggee.jpg").toPath();
            String mimeType = Files.probeContentType(path);

            assertEquals(mimeType, "image/jpeg");
        }




    }

