package com.mycompany.app;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

public class TestGameStrategies {

    private Game game;

    @Before
    public void setUp() {
        game = new Game();
    }

    @Test
    public void testMinMaxAlgorithm() {
        game.symbol = 'O';
        int move = game.MiniMax(game.board, game.player2);
        assertTrue(move >= 1 && move <= 9);
    }

    @Test
    public void testMinMaxDefense() {
        game.symbol = 'O';
        game.board[0] = 'X';
        game.board[1] = 'X';
        game.board[2] = ' ';
        for (int i = 3; i < 9; i++) {
            game.board[i] = ' ';
        }
        
        int move = game.MiniMax(game.board, game.player2);
        assertTrue(move > 0 && move <= 9);
    }

    @Test
    public void testAllWinConditions() {
        int[][] winLines = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
        };
        
        for (int[] line : winLines) {
            for (int i = 0; i < 9; i++) {
                game.board[i] = ' ';
            }
            game.symbol = 'X';
            game.board[line[0]] = 'X';
            game.board[line[1]] = 'X';
            game.board[line[2]] = 'X';
            assertEquals(State.XWIN, game.checkState(game.board));
        }
    }

    @Test
    public void testQCounterReset() {
        game.q = 1000;
        game.symbol = 'X';
        game.MiniMax(game.board, game.player2);
        assertEquals(0, game.q);
    }

    @Test
    public void testEvaluateWinningPosition() {
        game.symbol = 'X';
        game.board[0] = 'X';
        game.board[1] = 'X';
        game.board[2] = 'X';
        int value = game.evaluatePosition(game.board, game.player1);
        assertEquals(Game.INF, value);
    }
}
