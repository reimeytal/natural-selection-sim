package naturalselection;

import java.util.*; //For Random and ArrayList
import java.lang.Math; //For Math.abs

public class Creature{
  public Vector location;
  private double speed;
  private double size;
  private double sense;
  private double energy;
  private int foodThisDay;
  private Target target;
  private int index;
  public boolean atFrame;

  //Static members
  private static int counter = 0;
  public static ArrayList<Creature> creatures = new ArrayList<Creature>();
  //Size of the world
  public static short MAX_X;
  public static short MAX_Y;

  /*This constructor is meant to be called at the start of the simulation.
  It is not meant to be called when mutations are supposed to occur.*/
  public Creature(int x, int y){
    //Setting values to default
    location = new Vector((short)x, (short)y);
    speed = 1.f;
    size = 1.f;
    sense = 1.f;
    energy = 2.5f;
    foodThisDay = 0;
    target = null;
    atFrame = false;

    //Adding to creatures ArrayList
    creatures.add(this);
    index = counter;
    counter++;
  }
  //Constructor with mutations. It is not meant to be called at the start of the simulation.
  private static Creature newCreature(int x, int y, double speed, double size, double sense){
    Creature c = new Creature((short)x, (short)y);
    c.speed = speed;
    c.size = size;
    c.sense = sense;
    c.energy = 2.5f;
    c.target = null; //Change to go to frame
    c.foodThisDay = 2;
    c.pickNewFrame();

    //Adding to creatures ArrayList
    creatures.add(c);
    c.index = counter;
    counter++;
    return c;
  }
  public double getSize(){
    return size;
  }
  public double getSpeed(){
    return speed;
  }
  public double getSense(){
    return sense;
  }
  private void reproduce(){
    Random numbergenerator = new Random();
    boolean isNegative = Math.abs((numbergenerator.nextInt(10)+1)%2) == 1;
    double nspeed, nsize, nsense;
    if(isNegative){
      nspeed = this.speed + ((double)(Math.abs(1+numbergenerator.nextInt(5))*-1)/100.f);
    } else{
      nspeed = this.speed + ((double)Math.abs(1+numbergenerator.nextInt(5))/100.f);
    }
    isNegative = Math.abs((numbergenerator.nextInt(10)+1)%2) == 1;
    if(isNegative){
      nsize = this.size + ((double)(Math.abs(1+numbergenerator.nextInt(5))*-1)/100.f);
    } else{
      nsize = this.size + ((double)Math.abs(1+numbergenerator.nextInt(5))/100.f);
    }
    isNegative = Math.abs((numbergenerator.nextInt(10)+1)%2) == 1;
    if(isNegative){
      nsense = this.sense + ((double)(Math.abs(1+numbergenerator.nextInt(5))*-1)/100.f);
    } else{
      nsense = this.sense + ((double)Math.abs(1+numbergenerator.nextInt(5))/100.f);
    }
    newCreature(location.getX(), location.getY(), nspeed, nsize, nsense);
  }
  @Override
  public String toString(){
    return "Creature #" + index + " | "+size + " " + " " + speed + " " + sense;
  }
  private boolean tryEat(Food f){
    if(f.tryEat()){
      energy+=2.5f;
      foodThisDay++;
      return true;
    } else{
      return false;
    }
  }
  private boolean tryEat(Creature otherCreature){
    if(otherCreature != null){
      if(this.size/otherCreature.size <= 0.8f){
        Creature.creatures.set(otherCreature.index, null);
        energy++;
        foodThisDay++;
        return true;
      } else{
        return false;
      }
    }
    return false;
  }
  private void move(Vector velocity){
    energy -= ((0.1f * size) * speed) * sense;
    if(this.location.getHypotenuse(target.location) <= speed*7){
      this.location = target.location.copy();
    } else{
      location.moveBy(velocity);
    }
  }
  private boolean inRange(Food targetFood){
    if(targetFood.getLocation().getHypotenuse(this.location) <= this.sense*21){
      if(!targetFood.getIsEaten()){
        return true;
      } else{
        return false;
      }
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
    for(int i=0;i<75;i++){ //Change when changing number of food
      if(Food.food[i] != null){
        if(inRange(Food.food[i])){
          target = new Target<Food>(Food.food[i]);
          return;
        }
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
  public void reset(){ //Gets called at the end of each day
    foodThisDay = 0;
    target = null;
    atFrame = false;
  }
  public boolean action(){
    if(atFrame){
      return true;
    }
    if(target == null && foodThisDay < 2){
      pickNewTarget();
      if(target != null){
        move(this.location.getVelocity(target.location, (float)speed*7));
      } else{
        pickNewFrame();
      }
    } else{
      if(this.location.atLocation(target.location)){
        if(target.targettype == TARGETTYPE.FOOD){
          tryEat(Food.food[target.index]);
          if(foodThisDay >= 2){
            reproduce();
            pickNewFrame();
          } else{
            target = null;
          }
        } else if(target.targettype == TARGETTYPE.CREATURE){
          tryEat(creatures.get(target.index));
          if(foodThisDay >= 2){
            reproduce();
            pickNewFrame();
          }
        } else{
          return true;
        }
      } else{
        if(target.targettype == TARGETTYPE.CREATURE){
          if(creatures.get(target.index) != null){
            if(!target.location.atLocation(creatures.get(target.index).location)){
              target = null;
            } else{
              move(this.location.getVelocity(target.location, (float)speed*7));
            }
          } else{
            pickNewTarget();
          }
        } else if(target.targettype == TARGETTYPE.FOOD){
          if(Food.food[target.index].getIsEaten()){
            target = null;
          } else{
              move(this.location.getVelocity(target.location, (float)speed*7));
          }
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
