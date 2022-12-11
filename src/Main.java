import packages.game.Board;

public class Main{
  public static void main(String[] args) throws Exception{
    Board board = new Board();
    board.setPices();
    board.makeMovement("A2", "A3");
    board.makeMovement("B1", "C3");
    //board.makeMovement();
    board.printRound();
  }
}
