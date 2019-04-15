package Logic;

import java.util.Vector;

public class Game {

  public static Game currentGame;
  public Vector<Figure> figures;

  public Game() {
    currentGame = this;
  }

  public Vector<Figure> baseBoard() {
    Vector<Figure> temp = new Vector<Figure>();
    temp.add(new Rook("A1", "white"));
    temp.add(new Knight("B1", "white"));
    temp.add(new Bishop("C1", "white"));
    temp.add(new King("D1", "white"));
    temp.add(new Queen("E1", "white"));
    temp.add(new Bishop("F1", "white"));
    temp.add(new Knight("G1", "white"));
    temp.add(new Rook("H1", "white"));

    temp.add(new Rook("A8", "black"));
    temp.add(new Knight("B8", "black"));
    temp.add(new Bishop("C8", "black"));
    temp.add(new King("D8", "black"));
    temp.add(new Queen("E8", "black"));
    temp.add(new Bishop("F8", "black"));
    temp.add(new Knight("G8", "black"));
    temp.add(new Rook("H8", "black"));

    for (int i = 0; i < 16; i++) {
      if (i<8) {
        temp.add(new Pawn(new Position(i, 2).getCode(), "white"));
      } else {
        temp.add(new Pawn(new Position(i-8, 7).getCode(), "black"));
      }
    }

    return temp;
  }

  public void placeFigures(Vector<Figure> placedFigures) {
    figures = new Vector<Figure>();
    figures.addAll(placedFigures);
  }
}
