package packages.game;
import packages.pices.AbstractPiece;
import packages.pices.Pawn;
import packages.pices.Horse;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Objects;

public class Board{
  private Object[][] board = new Object[8][8];
  private int actual_player = 1; //0 = black | 1 = white
  private String[] white_eated_pices = new String[16];
  private String[] black_eated_pices = new String[16];

  public void printRound(){
    System.out.print("\033[0m\033[H\033[2J");
    this.printStats();
    this.printBoard();
    System.out.print("\033[0m");
  }

  private void printBoard(){
    for(int x = 0; x < 10; x++){
      System.out.print("\t".repeat(10) + "\033[0m" + (x > 1 ? ((x - 1) + "\u2502") : "  "));
      for(int y = 0; y < 8; y++){
        if(x == 0){
          System.out.print((char)(65 + y));
        }
        else if(x == 1){
          System.out.print("\u2500");
        }
        else{
          Object piece = board[x - 2][y];
          System.out.print((x % 2 == 0 ? (y % 2 == 0 ? "\033[0;47m" : "\033[0;100m") : (y % 2 == 0 ? "\033[0;100m" : "\033[0;47m")) + (Objects.isNull(piece) ? " " : AbstractPiece.class.cast(piece).sprite));
        }
      }
      System.out.println(x > 1 ? "\033[0m\u2502" : "");
    }
    System.out.println("\t".repeat(10) + "  " + "\u2500".repeat(8));
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
    this.setHorse();
  }

  private void setPawn() throws Exception{
    for(int y = 0; y < 8; y++){
      this.board[1][y] = new Pawn(1, new int[]{1,y});
      this.board[6][y] = new Pawn(0, new int[]{6,y});
    }
  }
  
  private void setHorse() throws Exception{
    this.board[0][1] = new Horse(1, new int[]{0,1});
    this.board[0][6] = new Horse(1, new int[]{0,6});
    this.board[7][1] = new Horse(0, new int[]{7,1});
    this.board[7][6] = new Horse(0, new int[]{7,6});
  }

  public void makeMovement(String piecePosition, String movement) throws Exception{
    if(movement.length() != 2 || piecePosition.length() != 2){
      System.out.println("Invalid movement or pice!");
    }
    else{
      ArrayList<Integer> piece_position = this.getPosition(piecePosition),
        movement_position = this.getPosition(movement);
      //System.out.println("X: " + String.valueOf(pice_position[0]));
      //System.out.println("Y : " + String.valueOf(pice_position[1]));
      Object piece = this.board[piece_position.get(0)][piece_position.get(1)];
      if(piece == null){
        System.out.println("No pice at " + piecePosition);
      }
      else{
        //System.out.println(AbstractPiece.class.cast(piece).sprite);
        boolean move = AbstractPiece.class.cast(piece).move(this.board, movement_position);
        if(move){
          System.out.println("[+] Valid movement");
          this.makeMovement(piece_position, movement_position);
        }
        else{
          System.out.println("[-] Invalid movement!");
        }
      }
    }
  }

  private ArrayList<Integer> getPosition(String position) throws Exception{
    if(position.length() != 2){
      throw new Exception("Invalid position!");
    }
    int x = Integer.parseInt("" + position.charAt(1)) - 1,
        y = Integer.parseInt("" + (((char)position.charAt(0)) - 65));
    if(x > 8 || x < 0 || y > 8 || y < 0){
      throw new Exception("Invalid position");
    }
    return new ArrayList<Integer>(Arrays.asList(x, y));
  }
  
  private void makeMovement(ArrayList<Integer> piecePosition, ArrayList<Integer> movementPosition){
    this.board[movementPosition.get(0)][movementPosition.get(1)] = this.board[piecePosition.get(0)][piecePosition.get(1)];
    this.board[piecePosition.get(0)][piecePosition.get(1)] = null;
  }

  public void arbitraryMove(String piecePosition, String move) throws Exception{
    ArrayList<Integer> piece_position = this.getPosition(piecePosition),
      move_position = this.getPosition(move);
    this.board[move_position.get(0)][move_position.get(1)] = this.board[piece_position.get(0)][piece_position.get(1)];
    this.board[piece_position.get(0)][piece_position.get(1)] = null;
  }
}
