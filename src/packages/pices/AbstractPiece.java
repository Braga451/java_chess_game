package packages.pices;

public abstract class AbstractPiece{
  public int color; // 0 = black | 1 = white
  public String sprite;
  public int[]  position = new int[2];
  
  public abstract int[][] getPossibleMoves(Object[][] board);
}
