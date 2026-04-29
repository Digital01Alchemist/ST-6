import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestPlayer {

    private Player player;

    @Before
    public void setUp() {
        player = new Player();
    }

    @Test
    public void testPlayerInitialization() {
        assertNotNull(player);
    }

    @Test
    public void testPlayerSymbol() {
        player.symbol = 'X';
        assertEquals('X', player.symbol);
        
        player.symbol = 'O';
        assertEquals('O', player.symbol);
    }

    @Test
    public void testPlayerMove() {
        player.move = 5;
        assertEquals(5, player.move);
    }

    @Test
    public void testPlayerSelected() {
        player.selected = true;
        assertTrue(player.selected);
        
        player.selected = false;
        assertFalse(player.selected);
    }

    @Test
    public void testPlayerWin() {
        player.win = true;
        assertTrue(player.win);
        
        player.win = false;
        assertFalse(player.win);
    }

    @Test
    public void testMultiplePlayers() {
        Player p1 = new Player();
        Player p2 = new Player();
        
        p1.symbol = 'X';
        p2.symbol = 'O';
        
        assertEquals('X', p1.symbol);
        assertEquals('O', p2.symbol);
    }

    @Test
    public void testPlayerMoveRange() {
        for (int i = 0; i < 9; i++) {
            player.move = i;
            assertEquals(i, player.move);
        }
    }

    @Test
    public void testPlayerDefault() {
        Player p = new Player();
        assertEquals(0, p.move);
        assertEquals(false, p.selected);
        assertEquals(false, p.win);
    }
}
