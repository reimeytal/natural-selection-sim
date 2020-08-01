import naturalselection.*;
import java.util.Random;

class Main{
  static int num_of_creatures = 100;
  static int num_of_food = 175; //Randomize later | Array containing all of the food is currently an Array, not an Arraylist. Change later for randomization.
  static int num_of_days = 100; //Number of days
  static int num_of_hours = 24; //Number of hours per day
  static short MAX_X = 150, MAX_Y = 150; //In order to change, one must change the MAX_X and MAX_Y variables in the Creature class along with the ones here.

  public static void resetFood(){
    int i;
    for(i=0;i<num_of_food;i++){
      Random numbergenerator = new Random();
      Food.food[i].reset(numbergenerator.nextInt()%(int)MAX_X, numbergenerator.nextInt()%(int)MAX_Y);
    }
  }
  public static void main(String[] args){
    int i;
    Random numbergenerator = new Random();
    //Initializing all the food and creatures
    for(i=0;i<num_of_creatures;i++){
      int side = numbergenerator.nextInt()%4
      switch(side){
        case 0:
          new Creature(0, (numbergenerator.nextInt()%MAX_Y)+1);
          break;
        case 1:
          new Creature(MAX_X, (numbergenerator.nextInt()%MAX_Y)+1);
          break;
        case 2:
          new Creature((numbergenerator.nextInt()%MAX_X)+1, 0);
          break;
        case 3:
          new Creature((numbergenerator.nextInt()%MAX_X)+1, MAX_Y);
          break;
      }
    }
    for(i=0;i<num_of_food;i++){
      new Food=(numbergenerator.nextInt()%MAX_X)+1, (numbergenerator.nextInt()%MAX_Y)+1); //Change to random coordinates
    }

  }
}
//Check if dead
