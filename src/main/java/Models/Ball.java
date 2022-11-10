package Models;

import java.awt.*;

public class Ball{
    private int ball_x;
    private int ball_y;
    private int ball_X_Speed;
    private int ball_Y_Speed;
    private final int ball_r;
    private boolean IsFireball;
    private Color color;
    public Ball(int ball_x , int ball_y , int ball_X_Speed , int ball_Y_Speed) {
        this.ball_r = 18;
        this.ball_x = ball_x;
        this.ball_y = ball_y;
        this.ball_X_Speed = ball_X_Speed;
        this.ball_Y_Speed = ball_Y_Speed;
        this.IsFireball = false;
        this.color = new Color(226, 241, 21);
    }

    public int getBall_x() {
        return ball_x;
    }

    public void setBall_x(int ball_x) {
        this.ball_x = ball_x;
    }

    public int getBall_y() {
        return ball_y;
    }

    public void setBall_y(int ball_y) {
        this.ball_y = ball_y;
    }

    public int getBall_r() {
        return ball_r;
    }

    public Color getColor() {
        return color;
    }

    public int getBall_X_Speed() {
        return ball_X_Speed;
    }

    public void setBall_X_Speed(int ball_X_Speed) {
        this.ball_X_Speed = ball_X_Speed;
    }

    public int getBall_Y_Speed() {
        return ball_Y_Speed;
    }

    public void setBall_Y_Speed(int ball_Y_Speed) {
        this.ball_Y_Speed = ball_Y_Speed;
    }

    public boolean isFireball() {
        return IsFireball;
    }

    public void setFireball(boolean fireball) {
        IsFireball = fireball;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
