public class DefensiveStrategy implements MovementStrategy {
    @Override
    public void move(String actorName) {
        System.out.println(actorName + " takes a defensive position!");
    }
}

