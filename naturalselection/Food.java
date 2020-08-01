package naturalselection;

public class Food{
  private Vector location;
  private boolean isEaten;
  private int index;
  static public Food[] food = new Food[175]; //Change amount of food here
  static int counter = 0;


  public Food(int x, int y){
    location = new Vector((short)x, (short) y);
    isEaten = false;

    //Adding to food array
    food[counter] = this;
    index = counter;
    counter++;
  }
  public void reset(int x, int y){
    this.isEaten = false;
    location = new Vector((short)x, (short) y);
  }
  public Vector getLocation(){
    return location;
  }
  public int getIndex(){
    return index;
  }
  public boolean getIsEaten(){
      return isEaten;
  }
  public boolean tryEat(){
    if(!isEaten){
      isEaten = true;
      return true;
    } else{
      return false;
    }
  }
}
