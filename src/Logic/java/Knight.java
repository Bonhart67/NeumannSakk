public class Knight extends Figure {

  public Knight(String to, boolean allied) {
    super(to, allied);
    setNameChar('H');
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
    return !((xAbs == 2 && yAbs == 1) || (xAbs == 1 && yAbs == 2));
  }
}
