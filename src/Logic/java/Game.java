import java.util.Vector;

public class Game {
  public static Vector<Figure> figures;

  public Game() {

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
    for (Figure f: figures) {
      if (f.position.getX() == x && f.position.getY() == y) return f;
    }
    return null;
  }
}
