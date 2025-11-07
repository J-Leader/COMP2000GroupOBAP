public class Main {
    public static void main(String[] args) {
        Stage stage = new Stage(10, 10);
        WeatherClient client = new WeatherClient(stage);

        stage.setState(new ChoosingActorState(stage));
        stage.startGame();

        client.startFetchingWeather();
    }
}

