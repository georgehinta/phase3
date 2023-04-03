public class Trap extends Piece {
  private int damage;

  Trap(Square loc) {
    this.setPosition(loc);
  }
  
  public void draw() {
    // draw a trap
    ;
  }

  public void setDamage(int dmg) {
    damage = dmg;
  }

  public int getDamage() {
    return damage;
  }
}