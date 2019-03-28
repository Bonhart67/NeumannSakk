import org.junit.*;

import java.util.Arrays;
import java.util.Vector;

import static org.junit.Assert.*;

public class TestLogic {

  Game game;

  public boolean tryMove(Figure f, String to) {
    try {
      f.move(new Position(to));
    } catch (InvalidMoveException e) {
      return false;
    }
    return true;
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
    String board =  game.printBoard();
    int breaklines = 8;
    assertEquals(64, board.length()-breaklines);
  }

  @Test
  public void testKing() {
    King king = new King("D1", true);
    assertTrue(tryMove(king, "C2"));
    assertFalse(tryMove(king, "C2"));
    assertFalse(tryMove(king, "F3"));
  }

  @Test
  public void testRook() {
    Rook rook = new Rook("A1", true);
    assertTrue(tryMove(rook, "A8"));
    assertFalse(tryMove(rook, "A8"));
    assertFalse(tryMove(rook, "F3"));
  }

  @Test
  public void testQueen() {
    Queen queen = new Queen("E1", true);
    assertTrue(tryMove(queen, "E5"));
    assertTrue(tryMove(queen, "B8"));
    assertFalse(tryMove(queen, "B8"));
    assertFalse(tryMove(queen, "H4"));
  }

  @Test
  public void testKnight() {
    Knight knight = new Knight("B1", true);
    assertTrue(tryMove(knight, "C3"));
    assertTrue(tryMove(knight, "D5"));
    assertFalse(tryMove(knight, "D6"));
  }

  @Test
  public void testBishop() {
    Bishop bishop = new Bishop("C1", true);
    assertTrue(tryMove(bishop, "F4"));
    assertFalse(tryMove(bishop, "F4"));
    assertFalse(tryMove(bishop, "F5"));
  }

  @Test
  public void testPawn() {
    Pawn pawn = new Pawn("B2", true);
    Bishop bishop = new Bishop("C4", false);
    Knight knight = new Knight("B5", true);
    Figure[] bp = {pawn, bishop, knight};
    game.placeFigures(new Vector(Arrays.asList(bp)));
    assertTrue(tryMove(pawn, "B3"));
    assertTrue(tryMove(pawn, "C4"));
    assertFalse(tryMove(pawn, "B5"));
  }
}
