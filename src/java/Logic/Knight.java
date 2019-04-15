package Logic;

public class Knight extends Figure {

  public Knight(String to, String color) {
    super(to, color);
    setNameChar('H');
  }

  public void move(Position targetPosition) throws InvalidMoveException {
    int vectorX = targetPosition.getX() - this.position.getX();
    int vectorY = targetPosition.getY() - this.position.getY();
    if (wrongMove(vectorX, vectorY)) throw new InvalidMoveException();
    if (blockedMove(vectorX, vectorY)) throw new InvalidMoveException();

    this.position = targetPosition;
  }

  @Override
  protected boolean wrongMove(int x, int y) {
    int xAbs = Math.abs(x);
    int yAbs = Math.abs(y);
    return !((xAbs == 2 && yAbs == 1) || (xAbs == 1 && yAbs == 2));
  }

  @Override
  protected boolean blockedMove(int x, int y) {
    Position targetPosition = new Position(this.position.getX() + x, this.position.getY() + y);
    if (hitAllied(targetPosition) != null) return true;
    return false;
  }
}
