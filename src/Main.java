import java.io.*;
import java.util.Scanner;

public class Main {

    public static BufferedReader getReader(String name) {
        try {
            return new BufferedReader(new InputStreamReader(new FileInputStream(name)));
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
            throw new RuntimeException(e);
        }
    }
    public static BufferedWriter getWriter(String name) {
        try {
            return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(name)));
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String nameIn, nameOut;
        nameIn = scanner.next();
        nameOut = scanner.next();
        BufferedReader reader = getReader(nameIn);
        int size = 'z' - 'a' + 'Z' - 'A' + 2;
        int[] count = new int[size];
        reader.lines().forEach(line -> {
            char[] arr = line.toCharArray();
            for (char ch2 : arr) {
                if (ch2 >= 'a' && ch2 <= 'z')
                    count[ch2 - 'a']++;
                else if (ch2 >= 'A' && ch2 <= 'Z')
                    count[ch2 - 'A' + size / 2]++;
            }
        });
        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedWriter writer = getWriter(nameOut);
        try {
            for (int i = 0; i < size / 2; ++i) {
                writer.write((char) (i + 'a') + " - " + count[i] + "\n");
            }
            for (int i = size / 2; i < size; ++i) {
                writer.write((char) (i + 'A' - size / 2) + " - " + count[i] + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}