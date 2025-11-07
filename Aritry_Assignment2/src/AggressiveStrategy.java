public class AggressiveStrategy implements MovementStrategy {
    @Override
    public void move(String actorName) {
        System.out.println(actorName + " charges forward aggressively!");
    }
}


