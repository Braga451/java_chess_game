package packages.game;
import packages.pices.AbstractPiece;
import packages.pices.Pawn;
import java.util.Arrays;

public class Board{
  private Object[][] board = new Object[8][8];
  private int actual_player = 1; //0 = black | 1 = white
  private String[] white_eated_pices = new String[16];
  private String[] black_eated_pices = new String[16];

  public void printRound(){
    System.out.println("\033[H\033[2J");
    this.printStats();
    this.printBoard();
  }

  private void printBoard(){
    for(int x = 0; x < 9; x++){
      for(int y = 0; y < 8; y++){
        if(x == 0){
          System.out.print((char)(65 + y) + " ");
        }
        else{
          System.out.print(board[x - 1][y] == null ? "  " : AbstractPiece.class.cast(board[x - 1][y]).sprite + " ");
        }
      }
      System.out.println("");
    }
  }

  private void printStats(){
    System.out.println("Player: " + (actual_player == 1 ? "White" : "Black"));
    String white_eated_pices = String.join(", ", this.white_eated_pices).replace("null, ", "").replace("null", ""),
           black_eated_pices = String.join(", ", this.black_eated_pices).replace("null, ", "").replace("null", "");
    System.out.println("White eated pices: " + white_eated_pices);
    System.out.println("Black eated pices: " + black_eated_pices);
    System.out.println("Test: " + String.valueOf(this.white_eated_pices.length));
  }

  public void setPices() throws Exception{
    this.setPawn();
  }

  private void setPawn() throws Exception{
    for(int y = 0; y < 8; y++){
      this.board[1][y] = new Pawn(1, new int[]{1,y});
      this.board[6][y] = new Pawn(0, new int[]{6,y});
    }
  }

  public void makeMovement(String picePosition, String movement) throws Exception{
    if(movement.length() != 2 || picePosition.length() != 2){
      System.out.println("Invalid movement or pice!");
    }
    else{
      int[] pice_position = this.getPosition(picePosition),
        movement_position = this.getPosition(movement);
      //System.out.println("X: " + String.valueOf(pice_position[0]));
      //System.out.println("Y : " + String.valueOf(pice_position[1]));
      Object pice = this.board[pice_position[0]][pice_position[1]];
      if(pice == null){
        System.out.println("No pice at " + picePosition);
      }
      else{
        System.out.println(AbstractPiece.class.cast(pice).sprite);
        AbstractPiece.class.cast(pice).getPossibleMoves(this.board);
      }
    }
  }

  private int[] getPosition(String position) throws Exception{
    if(position.length() != 2){
      throw new Exception("Invalid position!");
    }
    int x = Integer.parseInt("" + position.charAt(1)) - 1,
        y = Integer.parseInt("" + (((char)position.charAt(0)) - 65));
    if(x > 8 || x < 0 || y > 8 || y < 0){
      throw new Exception("Invalid position");
    }
    return new int[]{x,y};
  }

  public void arbitraryMove(String piecePosition, String move) throws Exception{
    int[] piece_position = this.getPosition(piecePosition),
      move_position = this.getPosition(move);
    this.board[move_position[0]][move_position[1]] = this.board[piece_position[0]][piece_position[1]];
    this.board[piece_position[0]][piece_position[1]] = null;
  }
}
