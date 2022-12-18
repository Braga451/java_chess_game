package packages.pieces;
import java.util.ArrayList;

public abstract class AbstractPiece{
  public int color; // 0 = black | 1 = white
  public String sprite;
  public int[]  position = new int[2];
  
  protected abstract ArrayList<ArrayList<Integer>> getPossibleMoves(Object[][] board);
  public boolean move(Object[][] board, ArrayList<Integer> move){
    ArrayList<ArrayList<Integer>> possible_moves = this.getPossibleMoves(board);
    if(possible_moves.contains(move)){
      this.position[0] = move.get(0); 
      this.position[1] = move.get(1);
      return true;
    }
    return false;
  }
  protected boolean positionExists(ArrayList<Integer> position){
    return (position.get(0) > -1 && position.get(0) < 8 && position.get(1) > -1 && position.get(1) < 8);
  }
  protected boolean validateMoveByBoard(Object[][] board, ArrayList<Integer> position, int color){
    if(!positionExists(position)) return false;
    Object piece = board[position.get(0)][position.get(1)];
    //System.out.println(piece != null ? ((AbstractPiece)piece).color == color : "");
    return (piece == null || (((AbstractPiece)piece).color != color));
  }
}
