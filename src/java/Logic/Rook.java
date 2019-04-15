package Logic;

public class Rook extends Figure {

  public Rook(String to, String color) {
    super(to, color);
    setNameChar('R');
  }

  @Override
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
    return ((!((yAbs == 0) || (xAbs == 0))
            || (xAbs+yAbs==0)));
  }

  @Override
  protected boolean blockedMove(int x, int y) {
    int xPos = this.position.getX();
    int yPos = this.position.getY();
    Position nextPosition = new Position(xPos, yPos);
    Position targetPos = new Position(this.position.getX()+x, this.position.getY()+y);
    while (nextPosition.getX() != targetPos.getX()) {
      xPos = (x>0)?++xPos:--xPos;
      nextPosition = new Position(xPos, yPos);
      if (hitAllied(nextPosition) != null) return true;
      if (hitEnemy(nextPosition) != null) {
        return nextPosition.getX() != targetPos.getX();
      }
    }
    while (nextPosition.getY() != targetPos.getY()) {
      yPos = (y>0)?++yPos:--yPos;
      nextPosition = new Position(xPos, yPos);
      if (hitAllied(nextPosition) != null) return true;
      if (hitEnemy(nextPosition) != null) {
        return nextPosition.getY() != targetPos.getY();
      }
    }
    return false;
  }
}
