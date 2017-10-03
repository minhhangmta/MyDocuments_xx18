/**
 * Copyright(C) 2017 Luvina Software Company.
 * Log.java, Oct 02, 2017, Pham Van Tuan
 */
package bai2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Ghi log
 *
 * @author phamtuan993@gmail.com
 * @version Revision: 1.0
 */
public class Log {
    /**
     * ghi data to file log.txt
     *
     * @param data
     */
    public synchronized static void log(String data) {
        try {
            File file = new File("log.txt");
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    System.err.println("Không thể ghi log");
                }
                ;
            }
            Files.write(Paths.get("log.txt"), (data + "\n").getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
