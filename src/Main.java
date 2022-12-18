import packages.game.Board;

public class Main{
  public static void main(String[] args) throws Exception{
    Board board = new Board();
    board.setPices();
    board.makeMovement("D2", "D3");
    board.printRound();
    board.makeMovement("C1", "F4");
    board.printRound();
  }
}
