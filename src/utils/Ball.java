package utils;

import core.Point;
import core.Vector2D;
import engine.GameEngine;
import org.w3c.dom.css.RGBColor;
import window.GameWindow;

import java.awt.*;

public class Ball extends GameObject{
    private int radius;
    private Canvas canvas;
    public Ball(Point pos, Color color, int radius, Canvas canvas){
        super(pos, color);
        this.radius = radius;
        this.canvas = canvas;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public void update(double delta) {
        setPosition(new Point((int) (getPosition().getX() + (getVelocity().getX())),
                              (int) (getPosition().getY() + (getVelocity().getY()))));
        if(getPosition().getX() + radius >= canvas.getWidth() || getPosition().getX() - radius <= 0) {

           // setVelocity(new Vector2D(-getVelocity().getX(), getVelocity().getY()));
            GameEngine.RUNNING = false;
        }
        if(getPosition().getY() + (radius) >= canvas.getHeight() || getPosition().getY() - radius <= 0) {
          setVelocity(new Vector2D(getVelocity().getX(), -getVelocity().getY()));
        }

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillOval(getPosition().getX() - (radius), getPosition().getY() - (radius), radius*2, radius*2);
    }
}
