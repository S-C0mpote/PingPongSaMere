package utils;

import core.Point;

import java.awt.*;
import java.util.AbstractMap;
import java.util.Map;

public class Racket extends GameObject{
    private Dimension dimension;
    private Canvas canvas;

    private Map.Entry<Boolean, Direction> entry = new AbstractMap.SimpleEntry<>(false, null);
    public Racket(Point pos, Color color, Dimension dimension, Canvas canvas) {
        super(pos, color);
        this.dimension = dimension;
        this.canvas = canvas;
    }

    public Dimension getDimension() {
        return dimension;
    }

    @Override
    public void update(double delta) {
        if(entry.getKey()){
            if(entry.getValue() == Direction.DOWN){
                if(getPosition().getY() + dimension.height / 2 < canvas.getHeight())
                setPosition(new Point(getPosition().getX(), (int) (getPosition().getY() + (10 * delta))));
            }
            if(entry.getValue() == Direction.UP){
                if(getPosition().getY() - dimension.height / 2 > 0)
                    setPosition(new Point(getPosition().getX(), (int) (getPosition().getY() - (10 * delta))));
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getColor());
        g.fillRect(getPosition().getX() - dimension.width / 2, getPosition().getY() - dimension.height / 2,
                dimension.width, dimension.height);
    }

    public void setEntry(Map.Entry<Boolean, Direction> entry) {
        this.entry = entry;
    }

    public Map.Entry<Boolean, Direction> getEntry() {
        return entry;
    }

}
