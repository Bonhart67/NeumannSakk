package Logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

public class Game {

  public static Game currentGame;
  public Vector<Figure> figures;
  public String round;

  public Game() {
    currentGame = this;
  }

  public static void main(String[] args) {
    Game game = new Game();
    game.placeFigures(game.baseBoard());
    game.round = "white";
    System.out.println("Game started, first moves the White");
    while (!game.gameOver()) {
      game.tick();
    }
  }

  public boolean gameOver() {
    return (Game.currentGame.figures.stream().filter(c -> "white".equals(c.getColor())).count() == 0) ||
            (Game.currentGame.figures.stream().filter(c -> "black".equals(c.getColor())).count() == 0);
  }

  public void tick() {
    boolean unsuccessfulMove = true;
    Figure figureToMove = null;
    while (unsuccessfulMove) {
      try {
        System.out.println("Round for: " + round);
        System.out.println("From - to (eg.: A2 A4): ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        String from = input.split(" ")[0];
        String to = input.split(" ")[1];
        figureToMove = getFigureAt(new Position(from));
        if (figureToMove.getColor() != round) throw new InvalidMoveException();
        figureToMove.move(new Position(to));
        unsuccessfulMove = false;
      } catch (Exception e) {
        System.out.println("That move is invalid!");
      }
    }
    System.out.println(figureToMove.getName() + " moved to " + figureToMove.position.getCode());
    round = (round == "white")?"black":"white";
  }

  public Figure getFigureAt(Position position) {
    for (Figure figure: Game.currentGame.figures) {
      if (figure.position.getX() == position.getX() && figure.position.getY() == position.getY()) return figure;
    }
    return null;
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
        temp.add(new Pawn(new Position(i, 1).getCode(), "white"));
      } else {
        temp.add(new Pawn(new Position(i-8, 6).getCode(), "black"));
      }
    }
    return temp;
  }

  public void placeFigures(Vector<Figure> placedFigures) {
    figures = new Vector<Figure>();
    figures.addAll(placedFigures);
  }
}
