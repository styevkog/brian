package town.boom.io;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Reader implements Parser {

    private final String fileName;
    private final BufferedReader bufferedReader;

    public Reader(String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        bufferedReader = new BufferedReader(new FileReader(fileName));
    }

    public List<String> readLineOfStrings() throws IOException {
        return Arrays.stream(bufferedReader.readLine().split(" "))
                .collect(Collectors.<String>toList());
    }

    public List<Integer> readLineOfIntegers() throws IOException {
        return readLineOfStrings().stream()
                .map(Integer::parseInt)
                .collect(Collectors.<Integer>toList());
    }

    @Override
    public List<List<Integer>> readLinesOfIntegers(int numberOfLines) throws IOException {
        return IntStream.range(0, numberOfLines)
                .boxed()
                .map(value -> readLineOfIntegersCatchingTheException())
                .collect(toList());
    }

    private List<List<Integer>> readLineOfIntegerGroups(int columns, int groupSize) throws IOException {
        Scanner scanner = new Scanner(bufferedReader.readLine());
        return IntStream.range(0, columns).boxed()
                .map(columnNumber ->
                    IntStream.range(0, groupSize).boxed()
                        .map(sth -> scanner.nextInt())
                        .collect(toList())
                )
                .collect(toList());
    }

    @Override
    public List<List<List<Integer>>> readMatrixOfIntegerTuples(int rows, int columns, int size) throws IOException {
        return IntStream.range(0, rows).boxed()
                .map(value -> {
                    try {
                        return readLineOfIntegerGroups(columns, size);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .collect(toList());

    }

    private List<Integer> readLineOfIntegersCatchingTheException() {
        try {
            return readLineOfIntegers();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
