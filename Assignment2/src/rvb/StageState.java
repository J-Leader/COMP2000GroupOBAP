package rvb;

public interface StageState {
    void onEnter(Stage s);
    void update(Stage s);
    void next(Stage s);
    String name();
}
