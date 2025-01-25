// Tom Gordon 12/26/22

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runners.MethodSorters;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestAllMoves {

    /**
     * These functions test every possible input for each of the verification
     * functions. This includes some out-of-bounds inputs.
     *
     * i -> start row
     * j -> start col
     * k -> end row
     * l -> end col
     */

    @Test
    @WorthPoints(points = 6)
    @Category(category = "Source and Destination")
    public void sourceAndDestination() {
        bools.nextLine();
        for(int i = -1; i < 9; i++){
            for(int j = -1; j < 9; j++){
                for(int k = -1; k < 9; k++){
                    for(int l = -1; l < 9; l++){
                        boolean expected = bools.nextBoolean();
                        try {
                            assertEquals(expected, board.verifySourceAndDestination(i, j, k, l, true));
                            expected = bools.nextBoolean();
                            assertEquals(expected, board.verifySourceAndDestination(i, j, k, l, false));
                        }
                        catch(AssertionError a){
                            updateScanner();
                            throw errorMessage(i,j,k,l,"verifySourceAndDestination()", expected);
                        }
                    }
                    bools.nextLine();
                }
            }
        }
    }


    @Test
    @WorthPoints(points = 1)
    @Category(category = "Source and Destination")
    public void sourceAndDestinationFreebie() {};

    @Test
    @WorthPoints(points = 6)
    @Category(category = "Horizontal")
    public void horizontal(){
        bools.nextLine();
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    for (int l = 0; l < 8; l++) {
                        boolean expected = bools.nextBoolean();
                        try {
                            assertEquals(expected, board.verifyHorizontal(i, j, k, l));
                        }
                        catch(AssertionError a){
                            updateScanner();
                            throw errorMessage(i,j,k,l,"verifyHorizontal()", expected);
                        }
                    }
                    bools.nextLine();
                }
            }
        }
    }

    @Test
    @WorthPoints(points = 1)
    @Category(category = "Horizontal")
    public void horizontalFreebie() {};

    @Test
    @WorthPoints(points = 6)
    @Category(category = "Vertical")
    public void veritcal(){
        bools.nextLine();
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    for (int l = 0; l < 8; l++) {
                        boolean expected = bools.nextBoolean();
                        try {
                            assertEquals(expected, board.verifyVertical(i, j, k, l));
                        }
                        catch(AssertionError a){
                            updateScanner();
                            throw errorMessage(i,j,k,l,"verifyVertical()", expected);
                        }
                    }
                    bools.nextLine();
                }
            }
        }
    }

    @Test
    @WorthPoints(points = 1)
    @Category(category = "Vertical")
    public void verticalFreebie() {};

    @Test
    @WorthPoints(points = 6)
    @Category(category = "Diagonal")
    public void diagonal(){
        bools.nextLine();
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    for (int l = 0; l < 8; l++) {
                        boolean expected = bools.nextBoolean();
                        try{
                            assertEquals(expected, board.verifyDiagonal(i, j, k, l));
                        }
                        catch(AssertionError a){
                            updateScanner();
                            throw errorMessage(i,j,k,l,"verifyDiagonal()", expected);
                        }
                    }
                    bools.nextLine();
                }
            }
        }
    }

    @Test
    @WorthPoints(points = 1)
    @Category(category = "Diagonal")
    public void diagonalFreebie() {};

    @Test
    @WorthPoints(points = 6)
    @Category(category = "Adjacent")
    public void adjacent(){
        bools.nextLine();
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++) {
                for (int k = 0; k < 8; k++) {
                    for (int l = 0; l < 8; l++) {
                        boolean expected = bools.nextBoolean();
                        try{
                            assertEquals(expected, board.verifyAdjacent(i,j,k,l));
                        }
                        catch(AssertionError a){
                            updateScanner();
                            throw errorMessage(i,j,k,l,"verifyAdjacent()", expected);
                        }
                    }
                    bools.nextLine();
                }
            }
        }
    }

    @Test
    @WorthPoints(points = 1)
    @Category(category = "Adjacent")
    public void adjacentFreebie() {}

    ///BOILERPLATE...YOU MAY IGNORE///

    private static void updateScanner(){
        while(bools.hasNextBoolean()){
            bools.nextLine();
        }
    }

    private static AssertionError errorMessage(int i, int j, int k, int l, String func, boolean expected){
        StringBuilder out = new StringBuilder();
        out.append(String.format("\nat %s\n(%d, %d) -> (%d, %d)\n", func, i, j, k, l));
        out.append(String.format(ANSI_CONTEXT + "Expected: " + ANSI_RESET + "%b\n" + ANSI_CONTEXT + "Returned: " + ANSI_RESET + "%b", expected, !expected));
        return new AssertionError(out);
    }

    private static final ScoringRule SCORING_RULE = new ScoringRule(TestAllMoves.class);

    @Rule
    public ScoringRule scoringRule = SCORING_RULE;

    // option 1 gives bold, 3 gives italics
    public static final String ANSI_RESET = "\u001B[1;3;0m";
    public static final String ANSI_WHITE = "97m";

    public static final String ANSI_CONTEXT = "\u001B[" + ANSI_WHITE;

    public static Board board;

    // The following will contain files with expected results for invalid verifications
    // This is my solution to not wanting a copy of each valid verification func here
    private static Scanner bools;

    @BeforeClass
    public static void setUp() {
        board = new Board();
        board.clear();
        Fen.load("8/1p2P3/8/3p2P1/1P6/5p2/3P4/8", board);

        try {
            bools = new Scanner(new File("Code Correctness Tests/bools.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterClass
    public static void printScore() {
        System.out.print("\n" + SCORING_RULE + "\n");
    }
}
