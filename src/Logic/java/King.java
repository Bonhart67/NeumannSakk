public class King extends Figure {

  public King(String to, boolean allied) {
    super(to, allied);
    setNameChar('K');
  }

  @Override
  public void move(Position targetPosition) throws InvalidMoveException {
    int vectorX = targetPosition.getX() - this.position.getX();
    int vectorY = targetPosition.getY() - this.position.getY();
    if (wrongMove(vectorX, vectorY)) throw new InvalidMoveException();

    this.position = targetPosition;
  }

  @Override
  protected boolean wrongMove(int x, int y) {
    int xAbs = Math.abs(x);
    int yAbs = Math.abs(y);
    return (xAbs > 1 || yAbs > 1 || (xAbs + yAbs == 0));
  }
}
