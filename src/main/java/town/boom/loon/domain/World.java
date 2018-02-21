package town.boom.loon.domain;

import lombok.Builder;
import lombok.Data;
import town.boom.io.Reader;

import java.io.IOException;
import java.util.List;

@Data
@Builder
public class World {

    private int rows;
    private int cols;
    private int altitudesNo;

    private int targetCellsNo;
    private int coverageRadius;
    private int balloonsNo;
    private int turnsNo;

    private int startingCellRow;
    private int startingCellCol;

    private int[] targetCellsRows;
    private int[] targetCellsCols;

    private int[][][] vectorsRows;
    private int[][][] vectorsCols;


    public static World fromFile(String fileName) throws IOException {
        WorldBuilder builder = World.builder();
        Reader reader = new Reader(fileName);

        //● one line containing the following natural numbers separated by single spaces:
        //    ○ R​(1 ≤ R ≤ 1000) ​denotes the number of rows in the grid,
        //    ○ C​(1 ≤ C ≤ 1000) denotes the number of columns in the grid,
        //    ○ A​(1 ≤ A ≤ 1000) denotes the number of different altitudes considered in the model
        List<Integer> line = reader.readLineOfIntegers();
        builder
            .rows(line.get(0))
            .cols(line.get(1))
            .altitudesNo(line.get(2));

        //  ● one line containing the following natural numbers separated by single spaces:
        //    ○ L​(1 ≤ L ≤ 1000) denotes the number of target cells,
        //    ○ V ​(0 ≤ V ≤ 100) denotes the coverage radius,
        //    ○ B​(1 ≤ B ≤ 1000) denotes the number of available balloons,
        //    ○ T​(1 ≤ T ≤ 1000) denotes the number of turns in the simulation;
        line = reader.readLineOfIntegers();
        builder
            .targetCellsNo(line.get(0))
            .coverageRadius(line.get(1))
            .balloonsNo(line.get(2))
            .turnsNo(line.get(3));

        //        one line containing a pair of natural numbers separated by a single space: r​s and c​s
        //            (0 ≤ rs < R, 0 ≤ cs < C)describing the starting cell of the balloons as [rs, cs]
        line = reader.readLineOfIntegers();
        builder
            .startingCellRow(line.get(0))
            .startingCellCol(line.get(1));

        //        L ​lines describing the target cells, each of which contains:
        //○ a pair of natural numbers separated by a single space: r​i and c​i
        //            (0 ≤ r , )
        //        i < R 0 ≤ ci < C
        //        describing i­th target cell as [ri
        //            , ci
        //] . Each target cell will be unique, but the starting cell can
        //        be a target cell.
        List<List<Integer>> targetCellsLines = reader.readLinesOfIntegers(builder.targetCellsNo);
        int[] rows = new int[builder.targetCellsNo];
        int[] cols = new int[builder.targetCellsNo];
        for (int i = 0; i < builder.targetCellsNo; i++) {
            rows[i] = targetCellsLines.get(i).get(0);
            cols[i] = targetCellsLines.get(i).get(1);
        }
        builder.targetCellsRows(rows);
        builder.targetCellsCols(cols);

        //        A sections describing the movement grids at subsequent altitudes from 1 to A​. Each section
        //        consists of:
        //○ R​lines describing subsequent rows of wind vectors. Each of the rows contains:
        //■ C pairs of natural numbers: Δr​rc and Δc​rc
        //            (− 100 ≤ Δrrc ≤ 100,− 100 ≤ Δcrc ≤ 100)
        //        separated by single spaces (both within a pair as well as between the pairs). Each
        //        subsequent pair describes the wind vector at [r, c] as (Δrrc,Δcrc)

        builder.vectorsRows(new int[builder.altitudesNo][builder.rows][builder.cols]);
        builder.vectorsCols(new int[builder.altitudesNo][builder.rows][builder.cols]);

        for (int altitude = 0; altitude < builder.altitudesNo; altitude++) {
            List<List<List<Integer>>> grid = reader.readMatrixOfIntegerTuples(builder.rows, builder.cols, 2);
            for (int row = 0; row < builder.rows; row++) {
                for (int col = 0; col < builder.cols; col++) {
                    builder.vectorsRows[altitude][row][col] = grid.get(row).get(col).get(0);
                    builder.vectorsCols[altitude][row][col] = grid.get(row).get(col).get(1);
                }
            }
        }

        return builder.build();
    }

}
