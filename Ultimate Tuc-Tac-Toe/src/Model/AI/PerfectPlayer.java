package Model.AI;

import Model.Game.Board;
import Model.Game.Move;

/**
 *  -> PerfectPlayer class serves as the AI
 *  -> The HashMaps are used for:
 *     memorizing the (best case and worst case respectively)
 *     evaluation of the board
 *     to prevent re-searching an already
 *     evaluated node of the game tree
 */

public class PerfectPlayer implements AI_MOVE {



    public PerfectPlayer(){

    }


    // returning score for a given move

    protected synchronized double minimax(char[][] board, int depth, Boolean maximizerTurn, char maximizerChar, double alpha, double beta) {

        char minimizerChar = (maximizerChar == 'X' ? 'O' : 'X');

        double score = Board.checkIfWon(board, maximizerChar);
        double evaluation;

        if (score == 10 || score == -10)
            return score;

        else if (Board.movesLeft(board) == 0)
            return 0;

        if (maximizerTurn) {
            double maxEvaluation = Double.NEGATIVE_INFINITY;

            outerMaxLoop:
            for (int i = 0; i < Board.ROWS; i++) {
                for (int j = 0; j < Board.COLS; j++) {
                    if (Board.isAvailable(board, i, j)) {

                        board[i][j] = maximizerChar;
                        evaluation = minimax(board, depth + 1, false, maximizerChar, alpha, beta);
                        maxEvaluation = Math.max(maxEvaluation, evaluation);
                        Board.undoMove(board, i, j);

                        alpha = Math.max(alpha, evaluation);
                        if (alpha >= beta) {
                            break outerMaxLoop;
                        }
                    }
                }
            }
            return maxEvaluation - (depth * 0.1);

        } else {
            double minEvaluation = Double.POSITIVE_INFINITY;

            outerMinLoop:
            for (int i = 0; i < Board.ROWS; i++) {
                for (int j = 0; j < Board.COLS; j++) {
                    if (Board.isAvailable(board, i, j)) {

                        board[i][j] = minimizerChar;
                        evaluation = minimax(board, depth + 1, true, maximizerChar, alpha, beta);
                        minEvaluation = Math.min(minEvaluation, evaluation);
                        Board.undoMove(board, i, j);

                        beta = Math.min(evaluation, beta);
                        if (beta <= alpha) {
                            break outerMinLoop;
                        }
                    }
                }
            }
            return minEvaluation + (depth * 0.1);
        }
    }

    // finding the score for each move and keeping the best

    @Override
    public Move makeMoveSmall(char[][] board,char maximizerChar) {

        double maxVal = Double.NEGATIVE_INFINITY;
        Move bestMove = new Move(0,0);

        for (int i = 0; i < Board.ROWS; i++){
            for (int j = 0; j < Board.COLS; j++){
                if (Board.isAvailable(board,i,j)){

                    board[i][j] = maximizerChar;
                    double moveVal = minimax(board,0,false,maximizerChar,
                            Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY);
                    Board.undoMove(board,i,j);
                    if (moveVal > maxVal){
                        bestMove.setRow(i);
                        bestMove.setCol(j);
                        maxVal = moveVal;
                    }
                }
            }
        }
        return bestMove;
    }

    @Override
    public Move makeMoveLarge(char[][] board, char maximizerChar) {
        return null;
    }
}
