package naturalselection.creature;

import naturalselection.food.Food;
import naturalselection.vector.Vector;
import java.util.Random;

public class Creature{
  private Vector location;
  private double speed;
  private double size;
  private double sense;
  private double intelligence;
  private double energy;

  public Creature(int x, int y){
    location = new Vector((short)x, (short)y);
    speed = 1.f;
    size = 1.f;
    sense = 1.f;
    intelligence = 1.f;
    energy = 1.f;
    //No need to calculate energy
  }
  public Creature(int x, int y, double speed, double size, double sense, double intelligence){
    location = new Vector((short)x, (short)y);
    this.speed = speed;
    this.size = size;
    this.sense = sense;
    this.intelligence = intelligence;
    energy = 1.f; //Remove later
    //Calculate energy
  }
  public Creature reproduce(int x, int y){
    Random numbergenerator = new Random();
    boolean isNegative = (numbergenerator.nextInt(10)+1)%2 == 1;
    double nspeed, nsize, nsense, nintelligence;
    if(isNegative){
      nspeed = this.speed + ((double)((1+numbergenerator.nextInt(5))*-1)/100.f);
    } else{
      nspeed = this.speed + ((double)(1+numbergenerator.nextInt(5))/100.f);
      System.out.println(nspeed);
    }
    isNegative = (numbergenerator.nextInt(10)+1)%2 == 1;
    if(isNegative){
      nsize = this.size + ((double)((1+numbergenerator.nextInt(5))*-1)/100.f);
    } else{
      nsize = this.size + ((double)(1+numbergenerator.nextInt(5))/100.f);
    }
    isNegative = (numbergenerator.nextInt(10)+1)%2 == 1;
    if(isNegative){
      nsense = this.sense + ((double)((1+numbergenerator.nextInt(5))*-1)/100.f);
    } else{
      nsense = this.sense + ((double)(1+numbergenerator.nextInt(5))/100.f);
    }
    isNegative = (numbergenerator.nextInt(10)+1)%2 == 1;
    if(isNegative){
      nintelligence = this.intelligence + ((double)((1+numbergenerator.nextInt(5))*-1)/100.f);
    } else{
      nintelligence = this.intelligence + ((double)(1+numbergenerator.nextInt(5))/100.f);
    }
    return new Creature(x, y, nspeed, nsize, nsense, nintelligence);
  }
  @Override
  public String toString(){
    return speed + " " + size + " " + sense + " " + intelligence;
  }
}
