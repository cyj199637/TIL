package f;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TryWithResource {
    public void scanFile(String fileName, String encoding) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(fileName), encoding);
            System.out.println(scanner.nextLine());
        } catch (IllegalArgumentException | FileNotFoundException | NullPointerException exception) {
            exception.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    public void scanFileTryWithResource(String fileName, String encoding) {
        try (Scanner scanner = new Scanner(new File(fileName), encoding)) {
            System.out.println(scanner.nextLine());
        } catch (IllegalArgumentException | FileNotFoundException | NullPointerException exception) {
            exception.printStackTrace();
        }
    }
}