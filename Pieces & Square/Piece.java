public abstract class Piece {
  protected Square position;
  protected Img sprite;
  
  public abstract void draw();

  public Square getPosition() {
    return position;
  }

  public void setPosition(Square newPos) {
    position = newPos;
  }
}