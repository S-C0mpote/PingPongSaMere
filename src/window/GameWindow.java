package window;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameWindow extends JFrame {
    public final static Dimension DIMENSION = new Dimension(1600,900);
    private BufferStrategy buffer;
    private Canvas canvas;

    public GameWindow(String title){
        super(title);
        setMinimumSize(DIMENSION);
        setMaximumSize(DIMENSION);
        setSize(DIMENSION);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        canvas = new Canvas();
        add(canvas);
        pack();
        setVisible(true);
        canvas.createBufferStrategy(2);
        buffer = canvas.getBufferStrategy();
        canvas.setBackground(Color.BLACK);
    }

    public Canvas getCanvas() {
        return canvas;
    }
    public Dimension getDimension(){
        return DIMENSION;
    }
    public BufferStrategy getBuffer() {
        return buffer;
    }
}
