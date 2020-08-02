import naturalselection.*;
import java.util.Random;
import java.lang.Math;

class Main{
  static int num_of_creatures = 10;
  static int num_of_food = 70; //Randomize later | Array containing all of the food is currently an Array, not an Arraylist. Change later for randomization.
  static int num_of_days = 100; //Number of days
  static int num_of_hours = 24; //Number of hours per day
  static int MAX_X = 10;
  static int MAX_Y = 10;
  static int hour = 0;
  static int day = 0;

  static float dead_speed = 0;
  static float dead_size = 0;
  static float dead_sense = 0;

  public static void main(String[] args){
    int i;
    Food.MAX_X = MAX_X;
    Food.MAX_Y = MAX_Y;
    Food.num_of_food = num_of_food;
    Creature.MAX_X = (short)MAX_X;
    Creature.MAX_Y = (short)MAX_Y;

    Random numbergenerator = new Random();
    //Initializing all of the food and creatures
    for(i=0;i<num_of_creatures;i++){
      int side = Math.abs(numbergenerator.nextInt()%4);
      switch(side){
        case 0:
          new Creature(0, Math.abs(numbergenerator.nextInt()%MAX_Y)+1);
          break;
        case 1:
          new Creature(MAX_X, Math.abs(numbergenerator.nextInt()%MAX_Y)+1);
          break;
        case 2:
          new Creature(Math.abs(numbergenerator.nextInt()%MAX_X)+1, 0);
          break;
        case 3:
          new Creature(Math.abs(numbergenerator.nextInt()%MAX_X)+1, MAX_Y);
          break;
      }
    }
    for(i=0;i<num_of_food;i++){
      new Food(Math.abs(numbergenerator.nextInt()%MAX_X)+1, Math.abs(numbergenerator.nextInt()%MAX_Y)+1);
    }
    while(day != num_of_days){
      for(i=0;i<Creature.creatures.size();i++){ //Executes action function for every creature
        if(Creature.creatures.get(i) != null){
          if(!Creature.creatures.get(i).atFrame){
            boolean atframevar = Creature.creatures.get(i).action();
            if(atframevar){
              Creature.creatures.get(i).atFrame = true;
            }
          }
        }
      }
      for(i=0;i<Creature.creatures.size();i++){ //Checks if creature is dead
        if(Creature.creatures.get(i) != null){
          if(Creature.creatures.get(i).getEnergy() == 0.f){
            Creature.creatures.set(i, null);
          }
        }
      }
      hour++;
      if(hour == 24){
        day++;
        hour = 0;
        for(i=0;i<Creature.creatures.size();i++){
          if(Creature.creatures.get(i) != null){
            if(!Creature.creatures.get(i).atFrame){
              dead_size+=Creature.creatures.get(i).getSize();
              dead_sense+=Creature.creatures.get(i).getSense();
              dead_speed+=Creature.creatures.get(i).getSpeed();
              Creature.creatures.set(i, null);
            } else{
              Creature.creatures.get(i).reset();
            }
          }
        }
        Food.resetFood();
      }
    }
    int dead_creature_count = 0, alive_creature_count = 0;
    float alive_size =  0, alive_speed =  0, alive_sense = 0;
    for(i=0;i<Creature.creatures.size();i++){
      if(Creature.creatures.get(i) == null){
        dead_creature_count++;
      } else{
        alive_creature_count++;
        alive_size += Creature.creatures.get(i).getSize();
        alive_sense += Creature.creatures.get(i).getSense();
        alive_speed += Creature.creatures.get(i).getSpeed();
      }
    }
    System.out.println("Average stats of alive creatures\nSize: " + alive_size/alive_creature_count + " Speed: " + alive_speed/alive_creature_count + " Sense: " + alive_sense/alive_creature_count);
    System.out.println("Average stats of dead creatures\nSize: " + dead_size/dead_creature_count + " Speed: " + dead_speed/dead_creature_count + " Sense: " + dead_sense/dead_creature_count);
  }
}
