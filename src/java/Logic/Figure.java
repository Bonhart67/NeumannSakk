package Logic;

public abstract class Figure {

  private String color;
  private char nameChar;
  private String name;

  public String getColor() {return color;}
  public char getNameChar() { return nameChar; }
  public void setNameChar(char c) { nameChar = c; }
  public String getName() { return name; }

  protected Position position;

  public Figure(Position position, String color, String name) {
    this.position = position;
    this.color = color;
    this.name = name;
  }

  public Figure(String to, String color, String name) {
    this.position = new Position(to);
    this.color = color;
    this.name = name;
  }
  public abstract void move(Position targetPosition) throws InvalidMoveException;

  protected abstract boolean wrongMove(int x, int y);

  protected abstract boolean blockedMove(int x, int y);

  public Figure hitEnemy(Position targetPosition) {
    for (Figure target:Game.currentGame.figures) {
      if (target.position.getX() == targetPosition.getX()
              && target.position.getY() == targetPosition.getY()
              && this.color != target.color) {
        return target;
      }
    }
    return null;
  }

  public Figure hitAllied(Position targetPosition) {
    for (Figure target:Game.currentGame.figures) {
      if (target.position.getX() == targetPosition.getX()
              && target.position.getY() == targetPosition.getY()
              && this.color == target.color) {
        return target;
      }
    }
    return null;
  }
}