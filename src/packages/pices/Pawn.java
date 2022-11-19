package packages.pices;
import java.util.ArrayList;
import java.util.Arrays;

public class Pawn extends AbstractPiece{
  public Pawn(int color, int[] position) throws Exception{
    if(color != 0 && color != 1){
      throw new Exception("Invalid color! Use 0 to black and 1 to white.");
    }
    this.color = color;
    this.sprite = this.color == 0 ? "♟" : "♙";
    this.position = position;
  }
  @Override 
  public int[][] getPossibleMoves(Object[][] board){
    ArrayList<Integer[]> possible_moves = new ArrayList<Integer[]>();
    System.out.println(String.valueOf(this.position[0]) + " " + String.valueOf(this.position[1]));
    possible_moves.addAll(this.diagonalCheck(board));
    for(Integer[] move: possible_moves){
      System.out.println("X: " + String.valueOf(move[0]) + " | Y:" + String.valueOf(move[1]));
    }
    return new int[0][0];
  }

  private ArrayList<Integer[]> diagonalCheck(Object[][] board){
    int[] left_diagonal = new int[0], right_diagonal = new int[0];
    ArrayList<Integer[]> return_array = new ArrayList<Integer[]>();
    if(color == 1){
      left_diagonal = new int[]{this.position[0] + 1, this.position[1] + 1}; // x + 1 | y + 1
      right_diagonal = new int[]{this.position[0] + 1, this.position[1] - 1}; // x + 1 | y - 1
    } // White
    else{
      left_diagonal = new int[]{this.position[0] - 1, this.position[1] + 1}; // x - 1 | y + 1
      right_diagonal = new int[]{this.position[0] - 1, this.position[1] - 1}; // x - 1 | y - 1
    } // Black
    for(int[] diagonal : new int[][]{left_diagonal, right_diagonal}){
      if(diagonalExists(diagonal)){
        if(board[diagonal[0]][diagonal[1]] != null && AbstractPiece.class.cast(board[diagonal[0]][diagonal[1]]).color != this.color){
          Integer[] toAdd = Arrays.stream(diagonal).boxed().toArray(Integer[]::new);
          return_array.add(toAdd);
        }
      }
    }
      return return_array;
  }

  private boolean diagonalExists(int[] diagonal){
    return (diagonal[0] > 0 && diagonal[0] < 8 && diagonal[1] > 0 && diagonal[1] < 8);
  }
}
