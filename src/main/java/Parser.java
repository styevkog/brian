import java.io.IOException;
import java.util.List;

public interface Parser {

    List<String> readLineOfStrings() throws IOException;

    List<Integer> readLineOfIntegers() throws IOException;

    List<List<Integer>> readLinesOfIntegers(int numberOfLines) throws IOException;

    List<List<List<Integer>>> readMatrixOfIntegerTuples(int rows, int columns, int size) throws IOException;
}
