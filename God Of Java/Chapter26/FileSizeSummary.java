import java.io.File;
import java.text.DecimalFormat;

public class FileSizeSummary {
    DecimalFormat formatter = null;

    public static void main(String[] args) {
        FileSizeSummary sample = new FileSizeSummary();
        String path = "C:\\Users\\cyj19\\WebProgramming\\godofjava\\Chapter26";
        long sum = sample.printFileSize(path);
        System.out.println(path+"'s total size = "+sum);
    }

    public long printFileSize(String dirName) {
        File dir = new File(dirName);
        long sum = 0;
        if (dir.isDirectory()) {
            File[] fileList = dir.listFiles();
            for (File file:fileList) {
                if (file.isFile()) {
                    String tempFileName = file.getAbsolutePath();
                    long fileLength = file.length();
                    System.out.println(tempFileName+" = "+convertFileLength(fileLength));
                    sum += fileLength;
                } else {
                    String tempDirName = file.getAbsolutePath();
                    long fileLength = printFileSize(tempDirName);
                    System.out.println("["+tempDirName+"] = "+convertFileLength(fileLength));
                    sum += fileLength;
                }
            }
        }
        return sum;
    }

    public void FileSizeSummary() {
        String pattern = "#,##0.0#";
        formatter = new DecimalFormat(pattern);
    }
    
    private final long KB = 1024;
    private final long MB = 1024 * 1024;
    private final long GB = 1024 * 1024 * 1024;
    
    private String convertFileLength(long fileLength) {
        FileSizeSummary();
        double quot;
        if (fileLength < 1024) {
            return fileLength+" b";
        } else if (fileLength < MB) {
            quot = (1.0 * fileLength) / KB;
            return formatter.format(quot) + " kb";
        } else if (fileLength < GB) {
            quot = (1.0 * fileLength) / MB;
            return formatter.format(quot) + " mb";
        } else {
            quot = (1.0 * fileLength) / GB;
            return formatter.format(quot) + " gb";
        }
    }
}