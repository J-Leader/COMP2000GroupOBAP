package rvb;

import rvb.io.StageReader;
import rvb.io.StageFormatException;
import rvb.weather.WeatherService;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            System.out.println("Working dir: " + System.getProperty("user.dir"));

            final AppFrame frame = new AppFrame();
            final Stage stage = new Stage();

            try {
                System.out.println("Loading stage file: data/stage1.rvb");
                new StageReader().load("data/stage1.rvb", stage);
            } catch (StageFormatException | java.io.IOException e) {
                System.err.println("Stage load error: " + e.getMessage());
            }


            final String endpoint = "http://13.238.167.130/weather";
            System.out.println("Starting weather: " + endpoint);
            WeatherService weather = new WeatherService(endpoint);
            weather.addListener(stage);
            weather.start();


            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("Shutdown: closing WeatherService");
                weather.close();
            }));

            frame.setStage(stage);
            frame.setVisible(true);
        });
    }
}
