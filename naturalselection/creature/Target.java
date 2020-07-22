package naturalselection.creature;

import naturalselection.vector.Vector;
import naturalselection.creature.Creature;
import naturalselection.food.Food;

class final Target <T>{
  enum TARGETTYPE{
      FOOD, CREATURE
  }
  protected Vector location;
  protected TARGETTYPE targettype;
  protected int index;

  protected Target(T target){
    if(target instanceof Creature){
      this.location = (Creature)target.getLocation().copy();
      this.targettype = TARGETTYPE.CREATURE;
      this.index = (Creature)target.getIndex();
    } else if(target instanceof Food){
      this.location = (Food)target.getLocation().copy();
      this.targettype = TARGETTYPE.FOOD;
      this.index = (Food)target.getIndex();
    }
  }

}
