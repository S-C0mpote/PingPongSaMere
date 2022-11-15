package utils;

import core.Point;
import core.Vector2D;

import java.awt.*;

public abstract class GameObject {

    private Color color;
    private Vector2D velocity;
    private Point position;
    public GameObject(Point pos, Color color){
        this.position = pos;
        this.color = color;
        this.velocity = new Vector2D(0.0,0.0);
    }
    public Point getPosition() {
        return position;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public Color getColor() {
        return color;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    abstract void update(double delta);
    abstract void draw(Graphics g);
}
