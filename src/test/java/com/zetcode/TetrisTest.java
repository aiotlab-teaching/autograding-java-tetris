package com.zetcode;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import java.awt.event.WindowEvent;
public class TetrisTest {
    static private Tetris tetris;
    @BeforeClass
    public static void beforeTest() {
        tetris = new Tetris();
        tetris.setVisible(true);
    }

    @Test
    public void testRandomMove() {
        tetris.restart();
// Random move
        int t = 0;
        try {
            while (t < 100) {
                if (Math.random() > 0.5)
                    tetris.move(1);
                else
                    tetris.move(-1);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
                if (Math.random() > 0.5)
                    tetris.rotate(false);
                else
                    tetris.rotate(true);
                t++;
            }
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testGameOver() {
        for (int i=0; i<10; i++)
            tetris.dropDown();
        boolean ret = tetris.isGameOver();
        assertTrue(ret);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
    }
    @Test
    public void testSquareShapes() {
        tetris.restart();
        for (int x=-6; x<=4; x += 2) {
            tetris.newPiece(Shape.Tetrominoe.SquareShape);
            tetris.move(x);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
            tetris.dropDown();
        }
        int lines = tetris.getLinesRemoved();
        assertTrue(lines == 2);
    }
    @Test
    public void testLineShapes() {
        tetris.restart();
        tetris.newPiece(Shape.Tetrominoe.LineShape);
        tetris.move(-6);
        tetris.dropDown();

        tetris.newPiece(Shape.Tetrominoe.LineShape);
        tetris.move(-5);
        tetris.dropDown();

        tetris.newPiece(Shape.Tetrominoe.LineShape);
        tetris.rotate(true);
        tetris.move(-3);
        tetris.dropDown();

        tetris.newPiece(Shape.Tetrominoe.LineShape);
        tetris.rotate(true);
        tetris.move(1);
        tetris.dropDown();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }

        int lines = tetris.getLinesRemoved();
        assertTrue(lines == 1);
    }
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TetrisTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
// Closing the window after the final result is printed
        tetris.dispatchEvent(new WindowEvent(tetris, WindowEvent.WINDOW_CLOSING));
    }


}
