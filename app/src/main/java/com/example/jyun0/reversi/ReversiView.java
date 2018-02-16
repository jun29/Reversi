package com.example.jyun0.reversi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class ReversiView extends View {
    private Board mBoard = new Board();

    public ReversiView(Context context){
        super(context);
        setFocusable(true);
    }

    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        mBoard.setSize(getWidth(),getHeight());
        drawBoard(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH){
        super.onSizeChanged(w,h,oldW,oldH);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
                int r = (int)(y/mBoard.getCellheight());
                int c = (int)(x/mBoard.getCellWidth());
                if(r < Board.ROWS && c < Board.COLS){
                    try{
                        mBoard.changeCell(r, c, mBoard.getTurn());
                        mBoard.changeTurn();
                    }catch (Exception e){
                        //Toast.makeText(this.getContext(), e.getMessage(), 300).show();
                    }
                    invalidate();
                }
                break;
            default:
                return true;
        }
        return true;
    }

    private void drawBoard(Canvas canvas){
        int bw = mBoard.getWidth();
        int bh = mBoard.getHeight();
        float cw = mBoard.getCellWidth();
        float ch = mBoard.getCellheight();

        if(mBoard.getWidth() <= 0) return;

        Paint paint = new Paint();
        paint.setColor(Color.rgb(0,180,0));

        canvas.drawRect(0,0,bw,bh,paint);
        paint.setColor(Color.rgb(40,40,40));  // Grid color

        //縦線
        for(int i = 0; i < Board.COLS; i++){
            canvas.drawLine(cw * (i + 1), 0, cw * (i+1), bh, paint);
        }

        //横線
        for(int i = 0; i < Board.ROWS; i++){
            canvas.drawLine(0, ch * (i + 1), bw, ch * (i+1), paint);
        }

        //円を描く前にアンチエイリアスを指定。これをしないと円がギザギザになる。
        paint.setAntiAlias(true);

        Cell[][] cells = mBoard.getCells();
        for(int i = 0; i < Board.ROWS; i++){
            for(int j = 0; j < Board.COLS; j++){
                Cell cell = cells[i][j];
                Cell.E_STATUS st = cell.getStatus();

                if(st == Cell.E_STATUS.Black){
                    paint.setColor(Color.BLACK);
                }else if(st == Cell.E_STATUS.White){
                    paint.setColor(Color.WHITE);
                }

                if(st != Cell.E_STATUS.None){
                    canvas.drawCircle(cell.getCx(),cell.getCy(),(float)(cw * 0.46), paint);
                }
            }
        }
    }

}
