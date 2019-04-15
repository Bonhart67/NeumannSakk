package Logic;
import org.junit.*;

import java.util.Arrays;
import java.util.Vector;

import static org.junit.Assert.*;

public class TestLogic {

  Game game;

  public String printBoard() {
    StringBuilder sb = new StringBuilder();
    for (int y = 0; y < 8; y++) {
      for (int x = 0; x < 8; x++) {
        sb.append("O");
        Figure temp;
        if ((temp = checkForFigure(x, y)) != null) {
          sb.setCharAt(y*8+x+y, temp.getNameChar());
        }
      }
      sb.append("\n");
    }
    System.out.println(sb);
    return sb.toString();
  }

  public Figure checkForFigure(int x, int y) {
    Vector<Figure> figures = game.figures;
    for (Figure f: figures) {
      if (f.position.getX() == x && f.position.getY() == y) return f;
    }
    return null;
  }

  public boolean tryMove(Figure f, String to) {
    try {
      f.move(new Position(to));
    } catch (InvalidMoveException e) {
      return false;
    }
    return true;
  }

  public Vector<Figure> figureToPlace(Figure f) {
    Vector<Figure> figures = new Vector<Figure>();
    figures.add(f);
    return figures;
  }

  @Before
  public void setup() {
    game = new Game();
  }

  @Test
  public void testPosition() {
    Position position = new Position("E1");
    assertEquals(4, position.getX());
    assertEquals(0, position.getY());
  }

  @Test
  public void testPrint() {
    game.placeFigures(game.baseBoard());
    String board =  printBoard();
    assertEquals(64, board.length()-8);
    assertEquals(16, game.figures.stream().filter(c -> "white".equals(c.getColor())).count());
    assertEquals(16, game.figures.stream().filter(c -> "black".equals(c.getColor())).count());
  }

  @Test
  public void testKing() {
    King king = new King("D1", "white");
    game.placeFigures(figureToPlace(king));
    assertTrue(tryMove(king, "C2"));
    assertFalse(tryMove(king, "C2"));
    assertFalse(tryMove(king, "F3"));
  }

  @Test
  public void testKingBlocked() {
    King king = new King("D1", "white");
    Bishop bishop = new Bishop("D2", "white");
    Rook rook = new Rook("E1", "black");
    Figure[] bp = {king, bishop, rook};
    game.placeFigures(new Vector<Figure>(Arrays.asList(bp)));
    assertFalse(tryMove(king, "D2"));
    assertTrue(tryMove(king, "E1"));
  }

  @Test
  public void testRook() {
    Rook rook = new Rook("A1", "white");
    game.placeFigures(figureToPlace(rook));
    assertTrue(tryMove(rook, "A8"));
    assertFalse(tryMove(rook, "A8"));
    assertFalse(tryMove(rook, "F3"));
  }

  @Test
  public void testRookBlocked() {
    Rook rook = new Rook("A1", "white");
    Bishop bishop = new Bishop("A4", "white");
    Queen queen = new Queen("C3", "black");
    Figure[] bp = {rook, bishop, queen};
    game.placeFigures(new Vector<Figure>(Arrays.asList(bp)));
    assertFalse(tryMove(rook, "A8"));
    assertTrue(tryMove(rook, "A3"));
    assertFalse(tryMove(rook, "C8"));
    assertTrue(tryMove(rook, "C3"));
  }

  @Test
  public void testQueen() {
    Queen queen = new Queen("E1", "white");
    game.placeFigures(figureToPlace(queen));
    assertTrue(tryMove(queen, "E5"));
    assertTrue(tryMove(queen, "B8"));
    assertFalse(tryMove(queen, "B8"));
    assertFalse(tryMove(queen, "H4"));
  }

  @Test
  public void testQueenBlocked() {
    Queen queen = new Queen("E1", "white");
    Bishop bishop = new Bishop("E4", "white");
    Knight knight = new Knight("C5", "black");
    Figure[] bp = {queen, bishop, knight};
    game.placeFigures(new Vector<Figure>(Arrays.asList(bp)));
    assertFalse(tryMove(queen, "E8"));
    assertTrue(tryMove(queen, "E3"));
    assertFalse(tryMove(queen, "A7"));
    assertTrue(tryMove(queen, "C5"));
  }

  @Test
  public void testKnight() {
    Knight knight = new Knight("B1", "white");
    game.placeFigures(figureToPlace(knight));
    assertTrue(tryMove(knight, "C3"));
    assertTrue(tryMove(knight, "D5"));
    assertFalse(tryMove(knight, "D6"));
  }

  @Test
  public void testKnightBlocked() {
    Knight knight = new Knight("B1", "white");
    Bishop bishop = new Bishop("A3", "white");
    Queen queen = new Queen("C3", "black");
    Figure[] bp = {knight, bishop, queen};
    game.placeFigures(new Vector<Figure>(Arrays.asList(bp)));
    assertFalse(tryMove(knight, "A3"));
    assertTrue(tryMove(knight, "C3"));
  }

  @Test
  public void testBishop() {
    Bishop bishop = new Bishop("C1", "white");
    game.placeFigures(figureToPlace(bishop));
    assertTrue(tryMove(bishop, "F4"));
    assertFalse(tryMove(bishop, "F4"));
    assertFalse(tryMove(bishop, "F5"));
  }

  @Test
  public void testBishopBlocked() {
    Bishop bishop = new Bishop("C1", "white");
    Knight knight = new Knight("G5", "white");
    Queen queen = new Queen("E5", "black");
    Figure[] bp = {knight, bishop, queen};
    game.placeFigures(new Vector<Figure>(Arrays.asList(bp)));
    assertFalse(tryMove(bishop, "H6"));
    assertFalse(tryMove(bishop, "G5"));
    assertTrue(tryMove(bishop, "F4"));
    assertFalse(tryMove(bishop, "D6"));
    assertTrue(tryMove(bishop, "E5"));
  }

  @Test
  public void testPawn() {
    Pawn pawn1 = new Pawn("B2", "white");
    Pawn pawn2 = new Pawn("C2", "white");
    game.placeFigures(figureToPlace(pawn1));
    game.placeFigures(figureToPlace(pawn2));
    assertTrue(tryMove(pawn1, "B3"));
    assertFalse(tryMove(pawn1, "B5"));
    assertTrue(tryMove(pawn1, "B4"));
    assertTrue(tryMove(pawn2, "C4"));
    assertFalse(tryMove(pawn2, "C6"));
    assertTrue(tryMove(pawn2, "C5"));
  }

  @Test
  public void testPawnBlocked() {
    Pawn pawn1 = new Pawn("B2", "white");
    Pawn pawn2 = new Pawn("C2", "white");
    Bishop bishop1 = new Bishop("B3", "white");
    Bishop bishop2 = new Bishop("C4", "white");
    Bishop bishop3 = new Bishop("D4", "black");
    Figure[] bp = {pawn1, pawn2, bishop1, bishop2, bishop3};
    game.placeFigures(new Vector<Figure>(Arrays.asList(bp)));
    assertFalse(tryMove(pawn1, "B3"));
    assertFalse(tryMove(pawn1, "B4"));
    assertFalse(tryMove(pawn2, "B3"));
    assertFalse(tryMove(pawn2, "C4"));
    assertTrue(tryMove(pawn2, "C3"));
    assertTrue(tryMove(pawn2, "D4"));
  }
}
