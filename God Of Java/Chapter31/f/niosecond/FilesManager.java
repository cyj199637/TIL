package f.niosecond;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FilesManager {
    public static void main(String[] args) {
        FilesManager manager = new FilesManager();
        String fileName = "AboutThisBook.txt";
        Path fromPath = manager.writeAndRead(fileName);
        manager.copyMoveDelete(fromPath, fileName);
    }
    
    public List<String> getContents() {
        List<String> contents = new ArrayList<String>();
        contents.add("이 책은 저자의 6번째 책입니다.");
        contents.add("필자의 수년 간의 자바 경험을 바탕으로 집필되었습니다.");
        contents.add("많은 분들에게 도움이 되면 좋겠습니다.");
        contents.add("책에 대한 질문은 god@godofjava.com으로 문의주시기 바랍니다.");
        contents.add("Current Date = "+new Date());
        return contents;
    }
    
    public Path writeFile(Path path) throws Exception {
        Charset charset = Charset.forName("UTF-8");
        List<String> contents = getContents();
        StandardOpenOption openOption = StandardOpenOption.CREATE;
        return Files.write(path, contents, charset, openOption);
    }

    public void readFile(Path path) throws Exception {
        Charset charset = Charset.forName("UTF-8");
        System.out.println("Path = "+path);
        List<String> fileContents = Files.readAllLines(path, charset);
        for (String tempContents:fileContents) {
            System.out.println(tempContents);
        }
        System.out.println();
    }

    public Path writeAndRead(String fileName) {
        Path returnPath = null;
        try {
            Path path = Paths.get(fileName);

            returnPath = writeFile(path);
            System.out.println("***** Created file contents ****");

            readFile(returnPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnPath;
    }

    public void copyMoveDelete(Path fromPath, String fileName) {
        try {
            Path toPath = fromPath.toAbsolutePath().getParent();

            Path copyPath = toPath.resolve("copied");
            if (!Files.exists(copyPath)) {
                Files.createDirectories(copyPath);
            }

            Path copiedFilePath = copyPath.resolve(fileName);
            StandardCopyOption copyOption = StandardCopyOption.REPLACE_EXISTING;
            Files.copy(fromPath, copiedFilePath, copyOption);

            System.out.println("***** Copied file contents *****");
            readFile(copiedFilePath);

            Path movedFilePath = Files.move(copiedFilePath, copyPath.resolve("moved.txt"), copyOption);

            Files.delete(movedFilePath);
            Files.delete(copyPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}