public class Player extends MoveablePiece {
  int maxHealth = 10;
  int health;
  int numKeys;
  Weapon curWeapon;

  Player(Square loc) {
    health = 10;
    numKeys = 0;

    this.setPosition(loc);
  }

  public void draw() {
    // draw player sprite
    ;
  }

  public void move() {
    // implement move based on user input
    ;
  }

  public void addHealth(int healthAdded) {
    health = health + healthAdded;
    if(health > maxHealth) {
      health = maxHealth;
    }
  }

  public void loseHealth(int healthLost) {
    health = health - healthLost;
    // check if health = 0 elsewhere, main loop?
  }

  public void setHealth(int newHealth) {
    health = newHealth;
    if(health > maxHealth) {
      health = maxHealth);
    }
  }

  public int getHealth() {
    return health;
  }

  public void addKey() {
    numKeys++;
  }

  public void setNumKeys(int i) {
    numKeys = i;
  }

  public int getNumKeys() {
    return numKeys;
  }
}