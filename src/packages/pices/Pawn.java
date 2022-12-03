package packages.pices;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

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
  protected ArrayList<ArrayList<Integer>> getPossibleMoves(Object[][] board){
    ArrayList<ArrayList<Integer>> possible_moves = this.diagonalCheck(board);
    possible_moves.add(this.frontCheck(board));
    //System.out.println(this.position[0]);
    //System.out.println(this.position[1]);
    //System.out.println(String.valueOf(this.position[0]) + " " + String.valueOf(this.position[1]));
    //for(ArrayList<Integer> move: possible_moves){
    //  if(move.size() > 0) System.out.println("X: " + String.valueOf(move.get(0)) + " | Y:" + String.valueOf(move.get(1)));
    //}
    return possible_moves;
  }
  
  @Override
  public boolean move(Object[][] board, ArrayList<Integer> move){
    ArrayList<ArrayList<Integer>> possible_moves = this.getPossibleMoves(board);
    if(possible_moves.contains(move)){
      this.position[0] = move.get(0); 
      this.position[1] = move.get(1);
      return true;
    }
    return false;
  }

  private ArrayList<ArrayList<Integer>> diagonalCheck(Object[][] board){
    ArrayList<Integer> left_diagonal = new ArrayList<Integer>(), right_diagonal = new ArrayList<Integer>();
    ArrayList<ArrayList<Integer>> return_array = new ArrayList<ArrayList<Integer>>();
    if(color == 1){
      Collections.addAll(left_diagonal, Integer.valueOf(this.position[0] + 1), Integer.valueOf(this.position[1] + 1)); // x + 1 | y + 1
      Collections.addAll(right_diagonal, Integer.valueOf(this.position[0] + 1), Integer.valueOf(this.position[1] - 1)); // x + 1 | y - 1
    } // White
    else{
      Collections.addAll(left_diagonal, Integer.valueOf(this.position[0] - 1), Integer.valueOf(this.position[1] + 1)); // x - 1 | y + a1
      Collections.addAll(right_diagonal, Integer.valueOf(this.position[0] - 1), Integer.valueOf(this.position[1] - 1)); // x - 1 | y - 1
    } // Black
    for(ArrayList<Integer> diagonal : Arrays.asList(left_diagonal, right_diagonal)){
      if(validateMoveByBoard(board, diagonal, this.color) && Objects.nonNull(board[diagonal.get(0)][diagonal.get(1)])){
        return_array.add(diagonal);
      }
    }
      return return_array;
  }

  private ArrayList<Integer> frontCheck(Object[][] board){
    ArrayList<Integer> position = new ArrayList<Integer>();
    if(color == 1){ // white
      Collections.addAll(position, Integer.valueOf(this.position[0] + 1), Integer.valueOf(this.position[1])); // x + 1
    }
    else{ // Black
      Collections.addAll(position, Integer.valueOf(this.position[0] - 1), Integer.valueOf(this.position[1])); // x - 1
    }
    //System.out.println(((AbstractPiece)board[position.get(0)][position.get(1)]).color == this.color);
    return validateMoveByBoard(board, position, this.color) && Objects.isNull(board[position.get(0)][position.get(1)]) ? position : new ArrayList<Integer>();
  }
}
