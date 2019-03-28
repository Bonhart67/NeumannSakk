public class Pawn extends Figure {

  public Pawn(String to, boolean allied) {
    super(to, allied);
    setNameChar('P');

  }

  public void move(Position targetPosition) throws InvalidMoveException {
    int vectorX = targetPosition.getX() - this.position.getX();
    int vectorY = targetPosition.getY() - this.position.getY();
    if (wrongMove(vectorX, vectorY)) throw new InvalidMoveException();
    this.position = targetPosition;
  }

  protected boolean wrongMove(int x, int y) {
    int previousX = this.position.getX();
    int previousY = this.position.getY();
    Position left = new Position(previousX - 1, previousY + 1);
    Position right = new Position(previousX + 1, previousY + 1);
    if ((hit(left) != null) || (hit(right) != null)) return false;
    int xAbs = Math.abs(x);
    return (y != 1) || (xAbs > 0);
  }
}
