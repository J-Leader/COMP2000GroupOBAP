package rvb;

public class HumanTurnState implements StageState {
    @Override
    public void onEnter(Stage s) {}
    @Override
    public void update(Stage s) {}
    @Override
    public void next(Stage s) { s.setState(new BotTurnState()); }
    @Override
    public String name() { return "Human Turn"; }
}
