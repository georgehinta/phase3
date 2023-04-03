public class Enemy extends MoveablePiece {
  private int damage;

  Enemy(Square loc) {
    this.setPosition(loc);
  }

  public void draw() {
    // draw an enemy
    ;
  }

  public void setDamage(int dmg) {
    damage = dmg;
  }

  public int getDamage() {
    return damage;
  }

  public void move() {
    // implement move towards player? in set pattern?
    ;
  }

  public void despawn() {
    // delete enemy, sprite and damage-dealing capability
    ;
  }
}