package rvb;

import rvb.actors.Actor;
import java.util.Random;

public class BotTurnState implements StageState {
    private final Random rnd = new Random();
    private long lastTime;

    @Override
    public void onEnter(Stage s) { lastTime = System.currentTimeMillis(); }

    @Override
    public void update(Stage s) {
        if (System.currentTimeMillis() - lastTime > 1000) {
            for (Actor a : s.getActors())
                s.tryNudgeActor(a, rnd.nextInt(3) - 1, rnd.nextInt(3) - 1);
            s.nextState();
        }
    }

    @Override
    public void next(Stage s) { s.setState(new HumanTurnState()); }
    @Override
    public String name() { return "Bot Turn"; }
}
