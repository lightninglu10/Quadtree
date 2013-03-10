import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;
import java.io.Reader;
import java.io.File;
import java.io.FileReader;

public class filereader {
    public static void main(String... args) {
        File inputFileName = new File(args[0]);
        File inputFileName2 = new File(args[1]);
        String outputFileName = args[2];
        Reader inputpat; Reader inputstd;
        try {
            inputpat = new FileReader(inputFileName);
        } catch (FileNotFoundException e) {
            System.err.printf("Error: file %s not found", inputFileName);
            System.exit(1);
            return;
        }
        try {
            inputstd = new FileReader(inputFileName2);
        } catch (FileNotFoundException e) {
            System.err.printf("Error: file %s not found", inputFileName);
            System.exit(1);
            return;
        }
        if (outputFileName != null) {
            try {
                System.setOut(new PrintStream(outputFileName));
            } catch (FileNotFoundException e) {
                System.err.printf("Error: could not open %s%n",
                                  outputFileName);
                System.exit(1);
            }
        }
        parse(inputpat, inputstd);
    }

    static void parse(Reader read, Reader reader) {
        Scanner inp = new Scanner(read);
        Scanner inp2 = new Scanner(reader);
        int line = 0;
        while (inp.hasNext() && inp2.hasNext()) {
            line += 1;
            String token = inp.next();
            String token2 = inp2.next();
            if (!token.equals(token2)) {
                System.out.println(token + " ------ " + token2);
                System.out.println(line);
            }
        }
    }
}
