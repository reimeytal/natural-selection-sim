package naturalselection.food;

import naturalselection.vector.Vector;

public class Food{
  private Vector location;
  private boolean isEaten;
  public Food(int x, int y){
    location = new Vector((short)x, (short) y);
    isEaten = false;
  }
  public boolean getIsEaten(){
    return isEaten;
  }
  public void eat(){
    this.isEaten = true;
  }
  public void reset(int x, int y){
    this.isEaten = false;
    location = new Vector((short)x, (short) y);
  }
  public Vector getLocation(){
    return location;
  }
}
