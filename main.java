import naturalselection.*;
import java.util.Random;


class Main{
  static int num_of_creatures = 100;
  public static void main(String[] args){
    int i, x = 0;
    for(i=0;i<num_of_creatures;i++){
      Creature c = new Creature(x, 5);
      x++;
    }
    for(i=0;i<Creature.creatures.size();i++){
      System.out.println(Creature.creatures.get(i).getVector());
    }
  }
}
//Check if dead
