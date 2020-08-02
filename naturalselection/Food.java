package naturalselection;
import java.util.Random;
import java.lang.Math;

public class Food{
  private Vector location;
  private boolean isEaten;
  private int index;
  static public Food[] food = new Food[75]; //Change amount of food here
  private static int counter = 0;
  public static int MAX_X, MAX_Y, num_of_food;


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
  public static void resetFood(){
    int i;
    for(i=0;i<num_of_food;i++){
      Random numbergenerator = new Random();
      Food.food[i].reset(Math.abs(numbergenerator.nextInt()%MAX_X), Math.abs(numbergenerator.nextInt()%MAX_Y));
    }
  }
}
