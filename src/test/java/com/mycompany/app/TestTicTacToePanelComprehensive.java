package com.mycompany.app;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import javax.swing.*;
import java.awt.*;

public class TestTicTacToePanelComprehensive {

    private TicTacToePanel panel;

    @Before
    public void setUp() {
        panel = new TicTacToePanel(new GridLayout(3, 3));
    }

    @Test
    public void testPanelCreation() {
        assertNotNull(panel);
        assertTrue(panel instanceof JPanel);
    }

    @Test
    public void testPanelHasNineCells() {
        assertEquals(9, panel.getComponentCount());
    }

    @Test
    public void testAllCellsAreButtons() {
        for (int i = 0; i < 9; i++) {
            Component comp = panel.getComponent(i);
            assertTrue(comp instanceof JButton);
        }
    }

    @Test
    public void testInitialCellStates() {
        for (int i = 0; i < 9; i++) {
            JButton button = (JButton) panel.getComponent(i);
            assertEquals(" ", button.getText());
            assertTrue(button.isEnabled());
        }
    }

    @Test
    public void testPanelLayout() {
        LayoutManager layout = panel.getLayout();
        assertTrue(layout instanceof GridLayout);
        GridLayout gl = (GridLayout) layout;
        assertEquals(3, gl.getRows());
        assertEquals(3, gl.getColumns());
    }

    @Test
    public void testMultiplePanelInstances() {
        TicTacToePanel panel1 = new TicTacToePanel(new GridLayout(3, 3));
        TicTacToePanel panel2 = new TicTacToePanel(new GridLayout(3, 3));
        
        assertNotNull(panel1);
        assertNotNull(panel2);
        assertNotSame(panel1, panel2);
        assertEquals(9, panel1.getComponentCount());
        assertEquals(9, panel2.getComponentCount());
    }

    @Test
    public void testCellEnablementState() {
        for (int i = 0; i < 9; i++) {
            JButton btn = (JButton) panel.getComponent(i);
            assertTrue("Cell " + i + " should be enabled", btn.isEnabled());
        }
    }
}
