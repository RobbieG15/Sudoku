import static org.junit.Assert.assertEquals;

import org.junit.Test;

import Sudoku.Solver;

/**
 * Class to test the Solver Class that solves valid Sudoku puzzles.
 *
 * @author Robert Greenslade
 * @version 1.00 (12/13/2023)
 */
public class SolverTest {

    // Tests on Safe Spot Check
    @Test
    public void safeSpotCheckTest1() {
        int[][] check1 = new int[][] { { 1, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

        Solver solver = new Solver(check1);
        assertEquals(true, solver.safeSpotCheck(0, 1, 2));
        assertEquals(false, solver.safeSpotCheck(0, 1, 1));
    }

    @Test
    public void safeSpotCheckTest2() {
        int[][] check1 = new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 1, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

        Solver solver = new Solver(check1);
        assertEquals(true, solver.safeSpotCheck(0, 1, 2));
        assertEquals(false, solver.safeSpotCheck(0, 1, 1));
        assertEquals(false, solver.safeSpotCheck(1, 1, 1));
        assertEquals(true, solver.safeSpotCheck(0, 3, 1));
    }

    @Test
    public void isValidCheck1() {
        int[][] check1 = new int[][] { { 0, 0, 0, 1, 0, 0, 0, 0, 0 },
                { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

        Solver solver = new Solver(check1);
        assertEquals(true, solver.isValid());
    }

    @Test
    public void isValidCheck2() {
        int[][] check1 = new int[][] { { 1, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

        Solver solver = new Solver(check1);
        assertEquals(false, solver.isValid());
    }

    @Test
    public void isValidCheck3() {
        int[][] check1 = new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 1, 1, 3, 4, 5, 6, 7, 8, 9 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

        Solver solver = new Solver(check1);
        assertEquals(false, solver.isValid());
    }

    @Test
    public void isValidCheck4() {
        int[][] check1 = new int[][] { { 0, 1, 0, 0, 0, 0, 0, 0, 0 },
                { 1, 1, 3, 4, 5, 6, 7, 8, 9 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 } };

        Solver solver = new Solver(check1);
        assertEquals(false, solver.isValid());
    }
}
