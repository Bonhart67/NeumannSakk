import java.util.Vector;

public class Game {

  public static Game currentGame;
  public Vector<Figure> figures;

  public Game() {
    currentGame = this;
  }

  public Vector<Figure> baseBoard() {
    Vector<Figure> temp = new Vector<Figure>();
    temp.add(new Rook("A1", true));
    temp.add(new Knight("B1", true));
    temp.add(new Bishop("C1", true));
    temp.add(new King("D1", true));
    temp.add(new Queen("E1", true));
    temp.add(new Bishop("F1", true));
    temp.add(new Knight("G1", true));
    temp.add(new Rook("H1", true));

    temp.add(new Rook("A8", false));
    temp.add(new Knight("B8", false));
    temp.add(new Bishop("C8", false));
    temp.add(new King("D8", false));
    temp.add(new Queen("E8", false));
    temp.add(new Bishop("F8", false));
    temp.add(new Knight("G8", false));
    temp.add(new Rook("H8", false));

    for (int i = 0; i < 16; i++) {
      if (i<8) {
        temp.add(new Pawn(new Position(i, 2).getCode(), true));
      } else {
        temp.add(new Pawn(new Position(i-8, 7).getCode(), false));
      }
    }

    return temp;
  }

  public void placeFigures(Vector<Figure> placedFigures) {
    figures = new Vector<Figure>();
    figures.addAll(placedFigures);
  }
}
