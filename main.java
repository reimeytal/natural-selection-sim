import naturalselection.*;
import java.util.Random;

class Main{
  static int num_of_creatures = 100;
  static int num_of_food = 175; //Randomize later | Array containing all of the food is currently an Array, not an Arraylist. Change later for randomization.
  static int num_of_days = 100; //Number of days
  static int num_of_hours = 24; //Number of hours per day
  static int MAX_X = 150;
  static int MAX_Y = 150;

  public static void main(String[] args){
    int i;
    Food.MAX_X = MAX_X;
    Food.MAX_Y = MAX_Y;
    Creature.MAX_X = (short)MAX_X;
    Creature.MAX_Y = (short)MAX_Y;
    Food.num_of_food = num_of_food;
    Random numbergenerator = new Random();
    //Initializing all the food and creatures
    for(i=0;i<num_of_creatures;i++){
      int side = numbergenerator.nextInt()%4;
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
      new Food((numbergenerator.nextInt()%MAX_X)+1, (numbergenerator.nextInt()%MAX_Y)+1);
    }

  }
}
//Check if dead
