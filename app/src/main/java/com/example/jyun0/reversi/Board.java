package com.example.jyun0.reversi;

public class Board {

    public static final int COLS = 8;
    public static int ROWS = 8;

    private int width;
    private int height;
    private int top;
    private int left;

    private  Cell cells[][] = new Cell[ROWS][COLS];
    private Cell.E_STATUS turn;

    public Board(){
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){
                cells[i][j] = new Cell();
            }
        }

        //初期の配置をセット
        cells[ROWS/2 - 1][COLS/2 -1].setStatus(Cell.E_STATUS.Black);
        cells[ROWS/2 - 1][COLS/2].setStatus(Cell.E_STATUS.White);
        cells[ROWS/2][COLS/2 -1].setStatus(Cell.E_STATUS.White);
        cells[ROWS/2][COLS/2].setStatus(Cell.E_STATUS.Black);

        turn = Cell.E_STATUS.Black;
    }

    public void setSize(int width, int heigth){
        int sz = width < heigth ? width : heigth;

        setWidth(sz);
        setHeight(sz);
    }

    public void setWidth(int width) {
        this.width = (int)(width / Board.COLS) * Board.COLS;

        float cellW = this.getCellWidth();

        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){
                cells[i][j].setWidth(cellW);
                cells[i][j].setLeft((int)(j * cellW));
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public void setHeght(int height) {
        this.width = (int)(width / Board.ROWS) * Board.ROWS;

        float cellH = this.getCellheight();

        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j < COLS; j++){
                cells[i][j].setWidth(cellH);
                cells[i][j].setLeft((int)(j * cellH));
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
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

    public Cell[][] getCells(){
        return cells;
    }

    public float getCellWidth() {
        return (float)(this.width/COLS);
    }

    public float getCellheight() {
        return (float)(this.height/ROWS);
    }

    public void changeCell(int r, int c, Cell.E_STATUS newStatus) throws Exception{
        Cell cell = cells[r][c];
        if(cell.getStatus() != Cell.E_STATUS.None){
            throw new Exception("Cell is not empty.");
        }

        cell.setStatus(newStatus);
    }

    public Cell.E_STATUS getTurn(){
        return this.turn;
    }

    public void changeTurn(){
        if(this.turn == Cell.E_STATUS.Black){
            this.turn = Cell.E_STATUS.White;
        }else{
            this.turn = Cell.E_STATUS.Black;
        }
    }
}
