package Utils;

import java.nio.file.Files;
import java.nio.file.Paths;

public class TestDataReader {

    public static String getJson(String path)
            throws Exception {

        return Files.readString(Paths.get(path));
    }
}