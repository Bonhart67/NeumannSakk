public class Queen extends Figure {

  public Queen(String to, boolean allied) {
    super(to, allied);
    setNameChar('Q');
  }

  public void move(Position targetPosition) throws InvalidMoveException {
    int vectorX = targetPosition.getX() - this.position.getX();
    int vectorY = targetPosition.getY() - this.position.getY();
    if (wrongMove(vectorX, vectorY)) throw new InvalidMoveException();

    this.position = targetPosition;
  }

  protected boolean wrongMove(int x, int y) {
    int xAbs = Math.abs(x);
    int yAbs = Math.abs(y);
    return ((  !((yAbs == 0) || (xAbs == 0))
            && !(yAbs == xAbs)
            || (xAbs+yAbs==0)));
  }
}
