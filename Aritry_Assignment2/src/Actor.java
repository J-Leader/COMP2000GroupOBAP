public class Actor {
    private String name;
    private MovementStrategy strategy;

    public Actor(String name, MovementStrategy strategy) {
        this.name = name;
        this.strategy = strategy;
    }

    public void performMove() {
        strategy.move(name);
    }

    public void setStrategy(MovementStrategy strategy) {
        this.strategy = strategy;
    }
}


