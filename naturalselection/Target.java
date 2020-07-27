package naturalselection;

enum TARGETTYPE{
    FOOD, CREATURE, FRAME
}

class Target<T>{
  protected Vector location;
  protected TARGETTYPE targettype;
  protected int index;

  Target(T target){
    if(target instanceof Creature){
      this.location = ((Creature)target).getLocation().copy();
      this.targettype = TARGETTYPE.CREATURE;
      this.index = ((Creature)target).getIndex();
    } else if(target instanceof Food){
      this.location = ((Food)target).getLocation().copy();
      this.targettype = TARGETTYPE.FOOD;
      this.index = ((Food)target).getIndex();
    } else if(target instanceof Vector){
      this.location = (Vector)target;
      this.targettype = TARGETTYPE.FRAME;
      this.index = -1;
    }
  }
}
