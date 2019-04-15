package Logic;

public class Pawn extends Figure {

  private boolean moved = false;

  public Pawn(String to, boolean allied) {
    super(to, allied);
    setNameChar('P');
  }

  public void move(Position targetPosition) throws InvalidMoveException {
    int vectorX = targetPosition.getX() - this.position.getX();
    int vectorY = targetPosition.getY() - this.position.getY();
    if (wrongMove(vectorX, vectorY)) throw new InvalidMoveException();
    if (blockedMove(vectorX, vectorY)) throw new InvalidMoveException();

    this.position = targetPosition;
    moved = true;
  }

  @Override
  protected boolean wrongMove(int x, int y) {
    int previousX = this.position.getX();
    int previousY = this.position.getY();
    Position left = new Position(previousX - 1, previousY + 1);
    Position right = new Position(previousX + 1, previousY + 1);
    if ((hitEnemy(left) != null) || (hitEnemy(right) != null)) return false;
    int xAbs = Math.abs(x);
    int moveLength = (moved)?1:2;
    return !(y > 0 && y <= moveLength) || (xAbs > 0);
  }

  @Override
  protected boolean blockedMove(int x, int y) {
    if (x==0) {
      for (int i = y; i >= 1; i--) {
        Position nextPosition = new Position(this.position.getX(), this.position.getY() + i);
        if (hitAllied(nextPosition) != null || hitEnemy(nextPosition) != null) return true;
      }
    }
    return false;
  }
}
