package e.io;

import java.io.File;
import java.io.FilenameFilter;

public class JPGFileNameFilter implements FilenameFilter {
    public boolean accept(File file, String fileName) {
        if (fileName.endsWith(".JPG")) return true;
        return false;
    }
}