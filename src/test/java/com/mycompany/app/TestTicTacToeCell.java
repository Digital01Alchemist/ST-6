import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestTicTacToeCell {

    private TicTacToeCell cell;

    @Before
    public void setUp() {
        cell = new TicTacToeCell(0, 0, 0);
    }

    @Test
    public void testCellInitialization() {
        assertNotNull(cell);
        assertEquals(' ', cell.getMarker());
        assertEquals(0, cell.getRow());
        assertEquals(0, cell.getCol());
        assertEquals(0, cell.getNum());
    }

    @Test
    public void testSetMarkerX() {
        cell.setMarker("X");
        assertEquals('X', cell.getMarker());
    }

    @Test
    public void testSetMarkerO() {
        cell.setMarker("O");
        assertEquals('O', cell.getMarker());
    }

    @Test
    public void testGetRow() {
        TicTacToeCell cell1 = new TicTacToeCell(0, 0, 1);
        assertEquals(1, cell1.getRow());
    }

    @Test
    public void testGetCol() {
        TicTacToeCell cell1 = new TicTacToeCell(0, 2, 0);
        assertEquals(2, cell1.getCol());
    }

    @Test
    public void testGetNum() {
        TicTacToeCell cell1 = new TicTacToeCell(5, 0, 0);
        assertEquals(5, cell1.getNum());
    }

    @Test
    public void testMultipleCells() {
        for (int i = 0; i < 9; i++) {
            TicTacToeCell c = new TicTacToeCell(i, i % 3, i / 3);
            assertEquals(i, c.getNum());
            assertEquals(i % 3, c.getCol());
            assertEquals(i / 3, c.getRow());
        }
    }

    @Test
    public void testCellMarkerAfterSet() {
        cell.setMarker("X");
        assertEquals('X', cell.getMarker());
        
        TicTacToeCell cell2 = new TicTacToeCell(1, 1, 1);
        cell2.setMarker("O");
        assertEquals('O', cell2.getMarker());
    }

    @Test
    public void testDifferentCellPositions() {
        TicTacToeCell[] cells = new TicTacToeCell[9];
        for (int i = 0; i < 9; i++) {
            cells[i] = new TicTacToeCell(i, i % 3, i / 3);
        }
        
        assertEquals(0, cells[0].getNum());
        assertEquals(8, cells[8].getNum());
        assertEquals(2, cells[8].getCol());
        assertEquals(2, cells[8].getRow());
    }

    @Test
    public void testCellInitialMarker() {
        assertEquals(' ', cell.getMarker());
    }
}
