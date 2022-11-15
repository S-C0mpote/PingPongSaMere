import engine.GameEngine;
import window.GameWindow;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Main {
    public static void main(String[] args) {
        GameEngine engine = new GameEngine(new GameWindow("coucou"));
        engine.init();
    }
}