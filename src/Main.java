import packages.game.Board;

public class Main{
  public static void main(String[] args) throws Exception{
    Board board = new Board();
    board.setPices();
    board.arbitraryMove("D2", "C6");
    board.arbitraryMove("E2", "E6");
    //board.arbitraryMove("E2", "E6");
    board.printRound();
    board.makeMovement("D7", "E6");
    board.printRound();
  }
}
