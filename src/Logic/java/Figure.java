public abstract class Figure {

  private boolean allied;
  private char nameChar;

  public boolean getAllied() {return allied; }
  public char getNameChar() { return nameChar; }
  public void setNameChar(char c) { nameChar = c; }

  protected Position position;

  public Figure(Position position, boolean allied) {
    this.position = position;
    this.allied = allied;
  }

  public Figure(String to, boolean allied) {
    this.position = new Position(to);
    this.allied = allied;
  }
  public abstract void move(Position targetPosition) throws InvalidMoveException;

  protected abstract boolean wrongMove(int x, int y);

  protected abstract boolean blockedMove(int x, int y);

  public Figure hitEnemy(Position targetPosition) {
    for (Figure target:Game.currentGame.figures) {
      if (target.position.getX() == targetPosition.getX()
              && target.position.getY() == targetPosition.getY()
              && target.getAllied() == false) {
        return target;
      }
    }
    return null;
  }

  public Figure hitAllied(Position targetPosition) {
    for (Figure target:Game.currentGame.figures) {
      if (target.position.getX() == targetPosition.getX()
              && target.position.getY() == targetPosition.getY()
              && target.getAllied() == true) {
        return target;
      }
    }
    return null;
  }
}
