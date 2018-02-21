import org.junit.Before;
import org.junit.Test;
import town.boom.io.Parser;
import town.boom.io.Reader;
import town.boom.loon.domain.World;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;


public class BigTest {


    private Parser reader;

    @Before
    public void setup() throws FileNotFoundException {
    }

    @Test
    public void testReadLineOfStrings() throws IOException {
        // given:
        reader = new Reader("src/test/resources/lineOfStrings.txt");

        // when:
        List<String> result = reader.readLineOfStrings();

        // then:
        assertThat(result.get(0), is(equalTo("abc")));
        assertThat(result.get(1), is(equalTo("def")));
        assertThat(result.get(2), is(equalTo("ghi")));
    }

    @Test
    public void testReadLineOfIntegers() throws IOException {
        reader = new Reader("src/test/resources/lineOfIntegers.txt");
        List<Integer> result = reader.readLineOfIntegers();
        assertThat(result.get(0), is(equalTo(1)));
        assertThat(result.get(1), is(equalTo(3)));
        assertThat(result.get(2), is(equalTo(567)));
    }

    @Test
    public void testReadLinesOfIntegers() throws IOException {
        reader = new Reader("src/test/resources/linesOfIntegers.txt");
        List<List<Integer>> result = reader.readLinesOfIntegers(4);
        assertThat(result.get(0).get(0), is(equalTo(1)));
        assertThat(result.get(0).get(1), is(equalTo(2)));
        assertThat(result.get(0).get(2), is(equalTo(3)));
        assertThat(result.get(1).get(0), is(equalTo(4)));
        assertThat(result.get(1).get(1), is(equalTo(5)));
        assertThat(result.get(1).get(2), is(equalTo(6)));
        assertThat(result.get(2).get(0), is(equalTo(7)));
        assertThat(result.get(2).get(1), is(equalTo(8)));
        assertThat(result.get(2).get(2), is(equalTo(9)));
        assertThat(result.get(3).get(0), is(equalTo(0)));
        assertThat(result.get(3).get(1), is(equalTo(1)));
        assertThat(result.get(3).get(2), is(equalTo(2)));
    }


    @Test
    public void testReadSectionsOfIntegers() throws IOException {
        reader = new Reader("src/test/resources/bigMess.txt");
        List<List<List<Integer>>> result = reader.readMatrixOfIntegerTuples(4, 4, 2);
        assertThat(result.get(0).get(0).get(0), is(equalTo(1)));
        assertThat(result.get(0).get(0).get(1), is(equalTo(1)));
        assertThat(result.get(0).get(1).get(0), is(equalTo(2)));
        assertThat(result.get(0).get(1).get(1), is(equalTo(2)));
        assertThat(result.get(0).get(2).get(0), is(equalTo(3)));
        assertThat(result.get(0).get(2).get(1), is(equalTo(3)));
        assertThat(result.get(0).get(3).get(0), is(equalTo(4)));
        assertThat(result.get(0).get(3).get(1), is(equalTo(4)));
        assertThat(result.get(1).get(0).get(0), is(equalTo(2)));
        assertThat(result.get(1).get(0).get(1), is(equalTo(2)));
        assertThat(result.get(1).get(1).get(0), is(equalTo(3)));
        assertThat(result.get(1).get(1).get(1), is(equalTo(3)));
        assertThat(result.get(1).get(2).get(0), is(equalTo(4)));
        assertThat(result.get(1).get(2).get(1), is(equalTo(4)));
        assertThat(result.get(1).get(3).get(0), is(equalTo(5)));
        assertThat(result.get(1).get(3).get(1), is(equalTo(5)));


        result = reader.readMatrixOfIntegerTuples(2, 4, 3);
        assertThat(result.get(0).get(0).get(0), is(equalTo(1)));
        assertThat(result.get(0).get(0).get(1), is(equalTo(1)));
        assertThat(result.get(0).get(0).get(2), is(equalTo(1)));

        assertThat(result.get(0).get(1).get(0), is(equalTo(2)));
        assertThat(result.get(0).get(1).get(1), is(equalTo(2)));
        assertThat(result.get(0).get(1).get(2), is(equalTo(2)));

        assertThat(result.get(0).get(2).get(0), is(equalTo(3)));
        assertThat(result.get(0).get(2).get(1), is(equalTo(3)));
        assertThat(result.get(0).get(2).get(2), is(equalTo(3)));

        assertThat(result.get(0).get(3).get(0), is(equalTo(4)));
        assertThat(result.get(0).get(3).get(1), is(equalTo(4)));
        assertThat(result.get(0).get(3).get(2), is(equalTo(4)));

        assertThat(result.get(1).get(0).get(0), is(equalTo(2)));
        assertThat(result.get(1).get(0).get(1), is(equalTo(2)));
        assertThat(result.get(1).get(0).get(2), is(equalTo(2)));

        assertThat(result.get(1).get(1).get(0), is(equalTo(3)));
        assertThat(result.get(1).get(1).get(1), is(equalTo(3)));
        assertThat(result.get(1).get(1).get(2), is(equalTo(3)));

        assertThat(result.get(1).get(2).get(0), is(equalTo(4)));
        assertThat(result.get(1).get(2).get(1), is(equalTo(4)));
        assertThat(result.get(1).get(2).get(2), is(equalTo(4)));

        assertThat(result.get(1).get(3).get(0), is(equalTo(5)));
        assertThat(result.get(1).get(3).get(1), is(equalTo(5)));
        assertThat(result.get(1).get(3).get(2), is(equalTo(5)));
    }

    @Test
    public void testTheWorld() throws IOException {
        World hello = World.fromFile("src/test/resources/hello.world");
        System.out.println("Brian");
    }

    @Test
    public void testBigInJapan() throws IOException {
        World hello = World.fromFile("src/test/resources/loon_r70_c300_a8_radius7_saturation_250.in");
        System.out.println("Brian!");
    }
}
