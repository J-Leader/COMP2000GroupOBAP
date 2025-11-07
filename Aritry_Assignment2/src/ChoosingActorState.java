public class ChoosingActorState implements GameState {
    private Stage stage;

    public ChoosingActorState(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void handle() {
        System.out.println("Player choosing actor...");
    }
}
