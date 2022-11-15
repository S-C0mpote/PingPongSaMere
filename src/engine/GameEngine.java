package engine;

import core.Point;
import core.Vector2D;
import listener.GameListener;
import listener.IGameListener;
import utils.Ball;
import utils.Direction;
import utils.Racket;
import window.GameWindow;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.AbstractMap;

public class GameEngine {
    public static boolean RUNNING = true;
    private int frames = 0;
    private int FFRAMES = 0;
    private GameWindow window;
    private Racket player1, player2;
    private Ball ball;
    public GameEngine(GameWindow window){
        this.window = window;
        ball = new Ball(new Point(window.getDimension().width / 2,
                                  window.getDimension().height / 2), Color.WHITE, 15, window.getCanvas());
        ball.setVelocity(new Vector2D(15,2));
        player2 = new Racket(new Point(window.getCanvas().getWidth() - 25, window.getCanvas().getHeight() / 2), Color.WHITE,
                new Dimension(50, 200), window.getCanvas());
        player1 = new Racket(new Point(25, window.getCanvas().getHeight() / 2), Color.WHITE,
                new Dimension(50, 200), window.getCanvas());

        GameListener gameListener = new GameListener(new IGameListener() {
            @Override
            public void onEvent(KeyEvent event) {
                switch (event.getKeyCode()){
                    case 38 -> {
                        if(!player2.getEntry().getKey())
                            player2.setEntry(new AbstractMap.SimpleEntry<>(true, Direction.UP));
                    }
                    case 40 -> {
                        if(!player2.getEntry().getKey())
                            player2.setEntry(new AbstractMap.SimpleEntry<>(true, Direction.DOWN));
                    }
                    case 90 -> {
                        if(!player1.getEntry().getKey())
                            player1.setEntry(new AbstractMap.SimpleEntry<>(true, Direction.UP));
                    }
                    case 83 -> {
                        if(!player1.getEntry().getKey())
                            player1.setEntry(new AbstractMap.SimpleEntry<>(true, Direction.DOWN));
                    }
                }
            }

            @Override
            public void stopEvent(KeyEvent event) {
                if(event.getKeyCode() == 38 || event.getKeyCode() == 40)
                    player2.setEntry(new AbstractMap.SimpleEntry<>(false, null));
                if(event.getKeyCode() == 90 || event.getKeyCode() == 83)
                    player1.setEntry(new AbstractMap.SimpleEntry<>(false, null));
            }
        });
        window.addKeyListener(gameListener);
    }

    public void init() {

        long initialTime = System.nanoTime();
        final double timeU = 1000000000 / 60.0;
        final double timeF = 1000000000 / 60.0;
        double deltaU = 0, deltaF = 0;
        int frames = 0, ticks = 0;
        long timer = System.currentTimeMillis();

        while (RUNNING) {

            long currentTime = System.nanoTime();
            deltaU += (currentTime - initialTime) / timeU;
            deltaF += (currentTime - initialTime) / timeF;
            initialTime = currentTime;

            if (deltaU >= 1) {
                update(deltaF);
                ticks++;
                deltaU--;
            }

            if (deltaF >= 1) {
                render();
                Toolkit.getDefaultToolkit().sync();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                frames = 0;
                ticks = 0;
                timer += 1000;
            }
        }
    }

    private synchronized void update(double delta){
        checkCollisions();
        ball.update(delta);
        player1.update(delta);
        player2.update(delta);
    }

    private synchronized void render(){
        Graphics2D g = (Graphics2D) window.getBuffer().getDrawGraphics();
        g.setColor(new Color(0x000000));
        g.fillRect(0,0, window.getWidth(), window.getHeight());
        g.setColor(new Color(0xFFFFFF));
        ball.draw(g);
        player1.draw(g);
        player2.draw(g);
        g.dispose();
        window.getBuffer().show();
    }
    public void checkCollisions(){
        if(ball.getPosition().getX() >= (player2.getPosition().getX() - player2.getDimension().width / 2)
                && ball.getPosition().getX() <= (player2.getPosition().getX() + player2.getDimension().width / 2)){
            if(ball.getPosition().getY() >= player2.getPosition().getY() - player2.getDimension().height / 2
                    && ball.getPosition().getY() <= player2.getPosition().getY() + player2.getDimension().height / 2){

                ball.getVelocity().setX(-ball.getVelocity().getX());
                if (player2.getEntry().getValue() == Direction.UP) {
                    ball.getVelocity().setY(ball.getVelocity().getY() + 1);
                } else {
                    ball.getVelocity().setY(ball.getVelocity().getY() - 1);
                }
            }
        }

        if(ball.getPosition().getX() <= (player1.getPosition().getX() + player1.getDimension().width / 2)
                && ball.getPosition().getX() >= (player1.getPosition().getX() - player1.getDimension().width / 2)){
            if(ball.getPosition().getY() >= player1.getPosition().getY() - player1.getDimension().height / 2
                    && ball.getPosition().getY() <= player1.getPosition().getY() + player1.getDimension().height / 2){
                ball.getVelocity().setX(-ball.getVelocity().getX());
                if (player1.getEntry().getValue() == Direction.UP) {
                    ball.getVelocity().setY(ball.getVelocity().getY() + 1);
                } else {
                    ball.getVelocity().setY(ball.getVelocity().getY() - 1);
                }
            }
        }
    }
}
