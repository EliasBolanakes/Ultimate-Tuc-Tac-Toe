package Model.AI;

import Model.Game.Move;

public interface AI_MOVE {

    Move makeMoveSmall(char[][] board, char maximizerChar);

    Move makeMoveLarge(char[][] board,char maximizerChar);
}
