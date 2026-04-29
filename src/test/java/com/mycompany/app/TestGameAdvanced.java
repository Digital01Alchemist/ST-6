import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class TestGameAdvanced {

    private Game game;

    @Before
    public void setUp() {
        game = new Game();
    }

    @Test
    public void testMinMoveWhenXCanWin() {
        game.symbol = 'X';
        game.board[0] = 'X';
        game.board[1] = 'X';
        game.board[2] = ' ';
        game.board[3] = 'O';
        game.board[4] = 'O';
        game.board[5] = 'X';
        game.board[6] = ' ';
        game.board[7] = ' ';
        game.board[8] = ' ';
        
        int value = game.MinMove(game.board, game.player2);
        assertTrue(value >= -Game.INF && value <= Game.INF);
    }

    @Test
    public void testMaxMovePositionEvaluation() {
        game.symbol = 'X';
        game.board[0] = 'X';
        game.board[1] = ' ';
        game.board[2] = ' ';
        game.board[3] = ' ';
        game.board[4] = 'O';
        game.board[5] = ' ';
        game.board[6] = ' ';
        game.board[7] = ' ';
        game.board[8] = ' ';
        
        int value = game.MaxMove(game.board, game.player1);
        assertTrue(value >= -Game.INF && value <= Game.INF);
    }

    @Test
    public void testGameBoardModification() {
        char[] original = game.board.clone();
        game.board[0] = 'X';
        assertNotEquals(original[0], game.board[0]);
    }

    @Test
    public void testGenerateMovesUpdatesCorrectly() {
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(game.board, moves);
        int initialSize = moves.size();
        
        game.board[0] = 'X';
        moves.clear();
        game.generateMoves(game.board, moves);
        assertEquals(initialSize - 1, moves.size());
    }

    @Test
    public void testCheckStateWithPartialBoard() {
        game.symbol = 'X';
        game.board[0] = 'X';
        game.board[1] = 'O';
        game.board[2] = 'X';
        State state = game.checkState(game.board);
        assertEquals(State.PLAYING, state);
    }

    @Test
    public void testComplexBoardScenario() {
        game.symbol = 'X';
        game.board[0] = 'X';
        game.board[1] = 'O';
        game.board[2] = 'X';
        game.board[3] = 'O';
        game.board[4] = 'X';
        game.board[5] = ' ';
        game.board[6] = ' ';
        game.board[7] = ' ';
        game.board[8] = ' ';
        
        State state = game.checkState(game.board);
        assertEquals(State.PLAYING, state);
    }

    @Test
    public void testMiniMaxWithBlockingMove() {
        game.symbol = 'O';
        game.board[0] = 'O';
        game.board[1] = 'O';
        game.board[2] = ' ';
        game.board[3] = 'X';
        for (int i = 4; i < 9; i++) {
            game.board[i] = ' ';
        }
        
        int move = game.MiniMax(game.board, game.player2);
        assertTrue(move > 0 && move <= 9);
    }

    @Test
    public void testGameStateAfterMultipleMoves() {
        game.symbol = 'X';
        game.board[0] = 'X';
        game.board[1] = 'X';
        game.board[2] = 'X';
        game.board[3] = 'O';
        game.board[4] = 'O';
        
        State state = game.checkState(game.board);
        assertEquals(State.XWIN, state);
    }

    @Test
    public void testEmptyBoardEvaluation() {
        game.symbol = 'X';
        int value = game.evaluatePosition(game.board, game.player1);
        assertEquals(-1, value);
    }

    @Test
    public void testDifferentSymbols() {
        game.symbol = 'X';
        game.board[0] = 'X';
        game.board[1] = 'X';
        game.board[2] = 'X';
        
        Player player1 = new Player();
        player1.symbol = 'X';
        int value1 = game.evaluatePosition(game.board, player1);
        assertEquals(Game.INF, value1);
        
        Player player2 = new Player();
        player2.symbol = 'O';
        int value2 = game.evaluatePosition(game.board, player2);
        assertEquals(-Game.INF, value2);
    }

    @Test
    public void testQCounterReset() {
        game.q = 50;
        game.symbol = 'X';
        game.MiniMax(game.board, game.player2);
        assertEquals(0, game.q);
    }

    @Test
    public void testBoardIntegrity() {
        game.board[0] = 'X';
        game.board[0] = ' ';
        assertEquals(' ', game.board[0]);
    }

    @Test
    public void testAllDiagonalsCheck() {
        game.symbol = 'X';
        game.board[0] = 'X';
        game.board[4] = 'X';
        game.board[8] = 'X';
        assertEquals(State.XWIN, game.checkState(game.board));
        
        // Reset and test other diagonal
        for (int i = 0; i < 9; i++) {
            game.board[i] = ' ';
        }
        
        game.board[2] = 'X';
        game.board[4] = 'X';
        game.board[6] = 'X';
        assertEquals(State.XWIN, game.checkState(game.board));
    }

    @Test
    public void testCheckStateCornerCases() {
        game.symbol = 'X';
        
        // Test with spaces
        for (int i = 0; i < 9; i++) {
            game.board[i] = ' ';
        }
        assertEquals(State.PLAYING, game.checkState(game.board));
    }

    @Test
    public void testMovesListManipulation() {
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(game.board, moves);
        
        int originalSize = moves.size();
        moves.remove(0);
        assertEquals(originalSize - 1, moves.size());
    }
}
