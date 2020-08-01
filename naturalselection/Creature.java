package naturalselection;

import java.util.*; //For Random and ArrayList

public class Creature{
  private Vector location;
  private double speed;
  private double size;
  private double sense;
  private double efficiency;
  private double energy;
  private int foodThisDay;
  private Target target;
  private int index;

  //Static members
  private static int counter = 0;
  public static ArrayList<Creature> creatures = new ArrayList<Creature>();
  //Size of the world
  public static short MAX_X = 150;
  public static short MAX_Y = 150;

  /*This constructor is meant to be called at the start of the simulation.
  It is not meant to be called when mutations are supposed to occur.*/
  public Creature(int x, int y){
    //Setting values to default
    location = new Vector((short)x, (short)y);
    speed = 1.f;
    size = 1.f;
    sense = 1.f;
    efficiency = 1.f;
    energy = 1.f;
    foodThisDay = 0;
    target = null;

    //Adding to creatures ArrayList
    creatures.add(this);
    index = counter;
    counter++;
  }
  //Constructor with mutations. It is not meant to be called at the start of the simulation.
  private static Creature newCreature(int x, int y, double speed, double size, double sense, double efficiency){
    Creature c = new Creature((short)x, (short)y);
    c.speed = speed;
    c.size = size;
    c.sense = sense;
    c.efficiency = efficiency; //Add penalty later; Efficiency currently has no disadvantages/weaknesses.
    c.energy = 1.f;
    c.target = null; //Change to go to frame
    c.foodThisDay = 2;

    //Adding to creatures ArrayList
    creatures.add(c);
    c.index = counter;
    counter++;
    return c;
  }
  private void reproduce(){
    Random numbergenerator = new Random();
    boolean isNegative = (numbergenerator.nextInt(10)+1)%2 == 1;
    double nspeed, nsize, nsense, nefficiency;
    if(isNegative){
      nspeed = this.speed + ((double)((1+numbergenerator.nextInt(5))*-1)/100.f);
    } else{
      nspeed = this.speed + ((double)(1+numbergenerator.nextInt(5))/100.f);
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
      nefficiency = this.efficiency + ((double)((1+numbergenerator.nextInt(5))*-1)/100.f);
    } else{
      nefficiency = this.efficiency + ((double)(1+numbergenerator.nextInt(5))/100.f);
    }
    newCreature(location.getX(), location.getY(), nspeed, nsize, nsense, nefficiency);
  }
  @Override
  public String toString(){
    return speed + " " + size + " " + sense + " " + efficiency + " " + energy;
  }
  public Vector getVector(){
    return location;
  }
  private boolean tryEat(Food f){
    if(f.tryEat()){
      energy++;
      foodThisDay++;
      return true;
    } else{
      return false;
    }
  }
  private boolean tryEat(Creature otherCreature){
    if(this.size/otherCreature.size <= 0.8f){
      Creature.creatures.set(otherCreature.index, null);
      energy++;
      foodThisDay++;
      return true;
    } else{
      return false;
    }
  }
  private void move(Vector velocity){
    energy -= 0.1f;
    location.moveBy(velocity);
  }
  private boolean inRange(Food targetFood){
    if(targetFood.getLocation().getHypotenuse(this.location) <= this.sense*21){
      return true;
    } else{
      return false;
    }
  }
  private boolean inRange(Creature targetCreature){
    if(targetCreature.location.getHypotenuse(this.location) <= this.sense*21){
      return true;
    } else{
      return false;
    }
  }
  private void pickNewTarget(){
    for(int i=0;i<creatures.size(); i++){
      if(creatures.get(i) != null){
        if(inRange(creatures.get(i)) && this.size/creatures.get(i).size <= 0.8f){
          target = new Target<Creature>(creatures.get(i));
          return;
        }
      }
    }
    for(int i=0;i<175;i++){
      if(inRange(Food.food[i])){
        target = new Target<Food>(Food.food[i]);
        return;
      }
    }
  }
  private void pickNewFrame(){
    if(location.getX() >= MAX_X/2){
      if(location.getY() >= MAX_Y/2){
        if(location.getX() <= location.getY()){
          target = new Target<Vector>(new Vector(location.getX(), MAX_Y));
        } else{
          target = new Target<Vector>(new Vector(MAX_X, location.getY()));
        }
      } else{
        if(location.getY() <= location.getX()-MAX_X/2){
          target = new Target<Vector>(new Vector((short)0, location.getY()));
        } else{
          target = new Target<Vector>(new Vector(location.getX(), (short)0));
        }
      }
    } else{
      if(location.getY() >= MAX_Y/2){
        if(location.getX() <= location.getY()-MAX_Y/2){
          target = new Target<Vector>(new Vector((short)0, location.getY()));
        } else{
          target = new Target<Vector>(new Vector(location.getX(), (short)0));
        }
      } else{
        if(location.getX() >= location.getY()){
          target = new Target<Vector>(new Vector(location.getX(), MAX_Y));
        } else{
          target = new Target<Vector>(new Vector(MAX_X, location.getY()));
        }
      }
    }
  }
  public void reset(){
    foodThisDay = 0;
    target = null;
  }
  public boolean action(){
    if(target ==  null && foodThisDay < 2){
      pickNewTarget();
      if(target != null){
        move(this.location.getVelocity(target.location, (float)speed*7));
      }  else{
        pickNewFrame();
      }
    } else{
      if(this.location.atLocation(target.location)){
        if(target.targettype == TARGETTYPE.FOOD){
          if(tryEat(Food.food[target.index])){
            foodThisDay++;
          }
          if(foodThisDay >= 2){
            reproduce();
            pickNewFrame();
          } else{
            target = null;
          }
        } else if(target.targettype == TARGETTYPE.CREATURE){
          if(tryEat(creatures.get(target.index))){
            foodThisDay++;
          }
          if(foodThisDay >= 2){
            reproduce();
            pickNewFrame();
          }
        } else{
          return true;
        }
      } else{
        if(target.targettype == TARGETTYPE.CREATURE){
          if(!target.location.atLocation(creatures.get(target.index).location)){
            target = null;
          } else{
            move(this.location.getVelocity(target.location, (float)speed*7));
          }
        } else if(Food.food[target.index].getIsEaten()){
          target = null;
        } else{
          move(this.location.getVelocity(target.location, (float)speed*7));
        }
      }
    }
    return false;
  }
  public int getIndex(){
    return index;
  }
  public Vector getLocation(){
    return location;
  }
  public double getEnergy(){
    return energy;
  }
}
