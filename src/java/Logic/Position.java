package Logic;

public class Position {

  private int x, y;
  private static String letters = "ABCDEFGH";

  public int getX() {
    return x;
  }
  public int getY() { return y; }
  public String getCode() {
    char[] code = new char[2];
    code[0] = letters.charAt(x);
    code[1] = Character.forDigit(y, 10);
    return new String(code);
  }

  public Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public Position(String to) {
    char[] pos = to.toCharArray();
    try {
      this.x = checkBoundaries(toNumeric(pos[0]));
      this.y = checkBoundaries(Character.getNumericValue(pos[1]));
    } catch (InvalidPositionException e) {
      System.out.println("Invalid position.");
    }
  }

  private int toNumeric(char c) throws InvalidPositionException {
    int index;
    if ((index = letters.indexOf(c)) != -1) {
      return index + 1;
    } else {
      throw new InvalidPositionException();
    }
  }

  private int checkBoundaries(int value) throws InvalidPositionException {
    if (value <= 8 && value >= 1) {
      return value - 1;
    } else {
      throw new InvalidPositionException();
    }
  }

}
