import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class TestGame {

    private Game game;

    @Before
    public void setUp() {
        game = new Game();
    }

    @Test
    public void testGameInitialization() {
        assertNotNull(game);
        assertNotNull(game.board);
        assertEquals(State.PLAYING, game.state);
        assertNotNull(game.player1);
        assertNotNull(game.player2);
        assertEquals('X', game.player1.symbol);
        assertEquals('O', game.player2.symbol);
        for (int i = 0; i < 9; i++) {
            assertEquals(' ', game.board[i]);
        }
    }

    @Test
    public void testGenerateMoves() {
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(game.board, moves);
        assertEquals(9, moves.size());
        
        // Test with partially filled board
        game.board[0] = 'X';
        game.board[1] = 'O';
        moves.clear();
        game.generateMoves(game.board, moves);
        assertEquals(7, moves.size());
    }

    @Test
    public void testGenerateMovesEmpty() {
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(game.board, moves);
        assertTrue(moves.size() > 0);
    }

    @Test
    public void testGenerateMovesFullBoard() {
        for (int i = 0; i < 9; i++) {
            game.board[i] = 'X';
        }
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(game.board, moves);
        assertEquals(0, moves.size());
    }

    @Test
    public void testCheckStateXWinHorizontal1() {
        game.symbol = 'X';
        game.board[0] = 'X';
        game.board[1] = 'X';
        game.board[2] = 'X';
        assertEquals(State.XWIN, game.checkState(game.board));
    }

    @Test
    public void testCheckStateXWinHorizontal2() {
        game.symbol = 'X';
        game.board[3] = 'X';
        game.board[4] = 'X';
        game.board[5] = 'X';
        assertEquals(State.XWIN, game.checkState(game.board));
    }

    @Test
    public void testCheckStateXWinHorizontal3() {
        game.symbol = 'X';
        game.board[6] = 'X';
        game.board[7] = 'X';
        game.board[8] = 'X';
        assertEquals(State.XWIN, game.checkState(game.board));
    }

    @Test
    public void testCheckStateXWinVertical1() {
        game.symbol = 'X';
        game.board[0] = 'X';
        game.board[3] = 'X';
        game.board[6] = 'X';
        assertEquals(State.XWIN, game.checkState(game.board));
    }

    @Test
    public void testCheckStateXWinVertical2() {
        game.symbol = 'X';
        game.board[1] = 'X';
        game.board[4] = 'X';
        game.board[7] = 'X';
        assertEquals(State.XWIN, game.checkState(game.board));
    }

    @Test
    public void testCheckStateXWinVertical3() {
        game.symbol = 'X';
        game.board[2] = 'X';
        game.board[5] = 'X';
        game.board[8] = 'X';
        assertEquals(State.XWIN, game.checkState(game.board));
    }

    @Test
    public void testCheckStateXWinDiagonal1() {
        game.symbol = 'X';
        game.board[0] = 'X';
        game.board[4] = 'X';
        game.board[8] = 'X';
        assertEquals(State.XWIN, game.checkState(game.board));
    }

    @Test
    public void testCheckStateXWinDiagonal2() {
        game.symbol = 'X';
        game.board[2] = 'X';
        game.board[4] = 'X';
        game.board[6] = 'X';
        assertEquals(State.XWIN, game.checkState(game.board));
    }

    @Test
    public void testCheckStateOWin() {
        game.symbol = 'O';
        game.board[0] = 'O';
        game.board[1] = 'O';
        game.board[2] = 'O';
        assertEquals(State.OWIN, game.checkState(game.board));
    }

    @Test
    public void testCheckStateDraw() {
        game.symbol = 'X';
        char[] board = {'X', 'O', 'X', 'O', 'X', 'O', 'O', 'X', 'O'};
        assertEquals(State.DRAW, game.checkState(board));
    }

    @Test
    public void testCheckStatePlaying() {
        game.symbol = 'X';
        game.board[0] = 'X';
        game.board[1] = 'O';
        assertEquals(State.PLAYING, game.checkState(game.board));
    }

    @Test
    public void testEvaluatePositionXWin() {
        game.symbol = 'X';
        game.board[0] = 'X';
        game.board[1] = 'X';
        game.board[2] = 'X';
        int value = game.evaluatePosition(game.board, game.player1);
        assertEquals(Game.INF, value);
    }

    @Test
    public void testEvaluatePositionOWin() {
        game.symbol = 'O';
        game.board[0] = 'O';
        game.board[1] = 'O';
        game.board[2] = 'O';
        int value = game.evaluatePosition(game.board, game.player2);
        assertEquals(Game.INF, value);
    }

    @Test
    public void testEvaluatePositionDraw() {
        game.symbol = 'X';
        char[] board = {'X', 'O', 'X', 'O', 'X', 'O', 'O', 'X', 'O'};
        int value = game.evaluatePosition(board, game.player1);
        assertEquals(0, value);
    }

    @Test
    public void testEvaluatePositionLoss() {
        game.symbol = 'X';
        game.board[0] = 'X';
        game.board[1] = 'X';
        game.board[2] = 'X';
        int value = game.evaluatePosition(game.board, game.player2);
        assertEquals(-Game.INF, value);
    }

    @Test
    public void testEvaluatePositionPlaying() {
        game.symbol = 'X';
        game.board[0] = 'X';
        int value = game.evaluatePosition(game.board, game.player1);
        assertEquals(-1, value);
    }

    @Test
    public void testMinMaxEmptyBoard() {
        game.symbol = 'X';
        int move = game.MiniMax(game.board, game.player2);
        assertTrue(move > 0 && move <= 9);
    }

    @Test
    public void testMinMaxTwoMovesPlayed() {
        game.symbol = 'X';
        game.board[0] = 'X';
        game.board[4] = 'O';
        int move = game.MiniMax(game.board, game.player2);
        assertTrue(move > 0 && move <= 9);
        assertNotEquals(1, move); // Should not place at already taken position
        assertNotEquals(5, move);
    }

    @Test
    public void testMiniMaxReturnsValidMove() {
        game.symbol = 'X';
        game.board[0] = 'X';
        game.board[1] = 'X';
        int move = game.MiniMax(game.board, game.player2);
        assertTrue(move > 0 && move <= 9);
    }

    @Test
    public void testPlayerInitialization() {
        Player player = new Player();
        assertNotNull(player);
    }

    @Test
    public void testGameStateTransitions() {
        assertEquals(State.PLAYING, game.state);
    }

    @Test
    public void testBoardSize() {
        assertEquals(9, game.board.length);
    }

    @Test
    public void testMultipleBoardConfigurations() {
        for (int i = 0; i < 5; i++) {
            game.board[i] = 'X';
        }
        ArrayList<Integer> moves = new ArrayList<>();
        game.generateMoves(game.board, moves);
        assertEquals(4, moves.size());
    }

    @Test
    public void testCheckStateAllDraw() {
        game.symbol = 'X';
        char[] testBoard = {'X', 'X', 'O', 'O', 'O', 'X', 'X', 'O', 'X'};
        assertEquals(State.DRAW, game.checkState(testBoard));
    }

    @Test
    public void testEvaluateMultiplePositions() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int pos = i * 3 + j;
                game.board[pos] = 'X';
                game.symbol = 'X';
                int value = game.evaluatePosition(game.board, game.player1);
                assertTrue(value >= -Game.INF && value <= Game.INF);
                game.board[pos] = ' ';
            }
        }
    }
}
