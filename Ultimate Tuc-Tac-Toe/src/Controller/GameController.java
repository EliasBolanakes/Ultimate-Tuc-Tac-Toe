package Controller;

import Model.AI.PerfectPlayer;
import Model.Game.Board;
import Model.Game.Move;
import View.GameBoardSmall;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class GameController {

    GameBoardSmall gameBoardSmall;
    public boolean XTurn;
    public boolean inPlay;
    public Board board = new Board();
    PerfectPlayer perfectPlayer;

    public GameController(GameBoardSmall gameBoardSmall){

        this.gameBoardSmall = gameBoardSmall;
        perfectPlayer = new PerfectPlayer();
        XTurn = true;
        inPlay = true;
    }

    public void buttonAction(Button btn ,int row,int col){

        System.out.println(btn.getFont());

        if(!btn.getText().equals(" ") || !inPlay)
            return;

        if(XTurn){
            AI_MOVE();
        }
        else{
            btn.setText("O");
            btn.setTextFill(Color.MIDNIGHTBLUE);
            gameBoardSmall.OTrans.play();
            XTurn=true;
            board.getBoard()[row][col] = 'O';
            gameBoardSmall.title.setText("X TURN");
        }
        if(gameBoardSmall.detectWin())
            inPlay = false;
    }

    private void AI_MOVE(){

        Move move = perfectPlayer.makeMoveSmall(board.getBoard(),'X');
        board.getBoard()[move.getRow()][move.getCol()] = 'X';
        gameBoardSmall.btn[move.getRow()][move.getCol()].setText("X");
        gameBoardSmall.btn[move.getRow()][move.getCol()].setTextFill(Color.DARKRED);
        gameBoardSmall.title.setText("O TURN");
        XTurn = false;
    }
}
