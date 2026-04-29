import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class TestUtility {

    @Test
    public void testPrintCharArray() {
        char[] board = {'X', 'O', 'X', ' ', ' ', ' ', 'O', 'X', ' '};
        assertNotNull(board);
        Utility.print(board);
    }

    @Test
    public void testPrintIntArray() {
        int[] board = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertNotNull(board);
        Utility.print(board);
    }

    @Test
    public void testPrintArrayList() {
        ArrayList<Integer> moves = new ArrayList<>();
        moves.add(0);
        moves.add(1);
        moves.add(2);
        assertNotNull(moves);
        Utility.print(moves);
    }

    @Test
    public void testPrintEmptyArrayList() {
        ArrayList<Integer> moves = new ArrayList<>();
        assertNotNull(moves);
        assertEquals(0, moves.size());
        Utility.print(moves);
    }

    @Test
    public void testPrintLargeBoard() {
        char[] board = new char[9];
        for (int i = 0; i < 9; i++) {
            board[i] = 'X';
        }
        Utility.print(board);
        assertNotNull(board);
    }
}
