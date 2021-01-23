import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        var dictString = Files.readString(Path.of("dict.txt"), StandardCharsets.UTF_8);

        var dict = Arrays.stream(dictString.split("\n"))
                .collect(Collectors.toUnmodifiableMap(s -> s.split(" ")[0].strip(),
                        s -> s.split(" ")[1].strip()));

        System.out.println(System.getProperty("file.encoding"));
        System.out.println(Charset.defaultCharset());
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        System.out.println("Loaded " + dict.size() + " words");

        Scanner scanner = new Scanner(System.in);

        String text;
        while ((text = scanner.nextLine()).length() > 0) {
            var in = Arrays.stream(text.split(" "))
                    .collect(Collectors.toList());

            for (int i = 0; i < in.size(); i++) {
                in.replaceAll(s -> dict.getOrDefault(s, s));
            }

            for (String s : in) {
                System.out.print(s+" ");
            }
            System.out.println();
        }
    }

}
