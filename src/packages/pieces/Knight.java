package packages.pieces;

import java.util.ArrayList;
import java.util.Arrays;

public class Knight extends AbstractPiece {
    public Knight(int color, int[]position) throws Exception{
        if(color != 0 && color != 1){
            throw new Exception("Invalid color! Use 0 to black and 1 to white.");
          }
          this.color = color;
          this.sprite = (this.color == 0 ? "\033[30m" : "\033[97m") + "♞" + "\033[0m";
          this.position = position;
    }

    @Override
    protected ArrayList<ArrayList<Integer>> getPossibleMoves(Object[][] board) {
        ArrayList<ArrayList<Integer>> possibleMoves = partialCircle(true, board);
        possibleMoves.addAll(partialCircle(false, board));
        return possibleMoves;
    }

    private ArrayList<ArrayList<Integer>> partialCircle(boolean isBottom, Object[][] board){
      ArrayList<ArrayList<Integer>> toReturn = new ArrayList<ArrayList<Integer>>(),
        possibleMoves = new ArrayList<ArrayList<Integer>>();
      Integer x = this.position[0];
      Integer y = this.position[1];
      for(int i = 0; i < 2; i++){
        ArrayList<Integer> possiblePosition = new ArrayList<Integer>(), 
          possiblePositionMirror = new ArrayList<Integer>();
        x = isBottom ? x + 1 : x - 1;
        if(i == 0){
          y = y + 2; // -4
          possiblePosition.addAll(Arrays.asList(new Integer[]{x, y}));
          possiblePositionMirror.addAll(Arrays.asList(new Integer[]{x, y - 4}));
        }
        else{
          y = y - 1; // -2
          possiblePosition.addAll(Arrays.asList(new Integer[]{x ,y}));
          possiblePositionMirror.addAll(Arrays.asList(new Integer[]{x, y - 2}));
        }
        possibleMoves.add(possiblePosition);
        possibleMoves.add(possiblePositionMirror);
      }
      System.out.println(possibleMoves);
      for(ArrayList<Integer> move : possibleMoves){
        if(validateMoveByBoard(board, move, this.color)) toReturn.add(move);
      }
      return toReturn;
    }
}
