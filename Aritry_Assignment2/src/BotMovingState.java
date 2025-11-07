public class BotMovingState implements GameState {
    private Stage stage;

    public BotMovingState(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void handle() {
        System.out.println("Bot is moving based on stormy weather...");
    }
}
