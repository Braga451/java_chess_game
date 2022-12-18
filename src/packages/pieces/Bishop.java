package packages.pieces;

import java.util.ArrayList;
import java.util.Arrays;

public class Bishop extends AbstractPiece {
    public Bishop(int color, int[]position) throws Exception{
      super(color, "♝", position);
    }

    @Override
    protected ArrayList<ArrayList<Integer>> getPossibleMoves(Object[][] board){
      return getDiagonals(board);
    }

    protected ArrayList<ArrayList<Integer>> getDiagonals(Object[][] board){
      ArrayList<ArrayList<Integer>> diagonals = getOneDiagonal(board, true);
      diagonals.addAll(getOneDiagonal(board, false));
      return diagonals;
    }

    protected ArrayList<ArrayList<Integer>> getOneDiagonal(Object[][] board, boolean isLeft){
      ArrayList<ArrayList<Integer>> possible_moves = new ArrayList<ArrayList<Integer>>();
      ArrayList<Integer> actual_move = new ArrayList<Integer>(Arrays.asList(new Integer[]{this.position[0], this.position[1]}));
      for(int x = 0; x < 2; x++){
        actual_move.set(0, this.position[0]);
        actual_move.set(1, this.position[1]);
        while(true){
          actual_move.set(0, x == 0 ? actual_move.get(0) - 1 : actual_move.get(0) + 1);
          actual_move.set(1, x == 0 ? (isLeft ? actual_move.get(1) - 1 : actual_move.get(1) + 1) : (isLeft ? actual_move.get(1) + 1 : actual_move.get(1) - 1));
        if(!validateMoveByBoard(board, actual_move, this.color)) break;
        possible_moves.add(new ArrayList<Integer>(Arrays.asList(new Integer[]{actual_move.get(0), actual_move.get(1)})));
        if(board[actual_move.get(0)][actual_move.get(1)] != null) break;
        }
      }
      return possible_moves;
    }
}
