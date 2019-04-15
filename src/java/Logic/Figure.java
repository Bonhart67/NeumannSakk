package Logic;

public abstract class Figure {

  private String color;
  private char nameChar;

  public String getColor() {return color;}
  public char getNameChar() { return nameChar; }
  public void setNameChar(char c) { nameChar = c; }

  protected Position position;

  public Figure(Position position, String color) {
    this.position = position;
    this.color = color;
  }

  public Figure(String to, String color) {
    this.position = new Position(to);
    this.color = color;
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
