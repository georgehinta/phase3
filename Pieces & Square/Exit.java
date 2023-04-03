public class Exit extends Piece {
  private boolean isLocked;
  
  Exit(Square loc) {
    this.setPosition(loc);
    
    isLocked = true;
  }
  
  public void draw() {
    // draw the exit
    ;
  }

  public void unlock() {
    isLocked = false;
  }

  public void getLockStatus() {
    if(isLocked == true) {
      System.out.println("exit is locked");
    }
    else {
      System.out.println("exit is unlocked");
    }
  }
}