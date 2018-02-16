package com.example.jyun0.reversi;

public class Cell {

    public enum E_STATUS{
        None,
        Black,
        White
    }

    private float width;
    private float heigth;
    private int top;
    private int left;
    private E_STATUS status = E_STATUS.None;

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeigth() {
        return heigth;
    }

    public void setHeigth(float heigth) {
        this.heigth = heigth;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public float getCx(){
        return (float)((float)this.left + (float)this.width/2.0);
    }

    public float getCy(){
        return (float)((float)this.top + (float)this.heigth/2.0);
    }

    public E_STATUS getStatus() {
        return status;
    }

    public void setStatus(E_STATUS status) {
        this.status = status;
    }

}
