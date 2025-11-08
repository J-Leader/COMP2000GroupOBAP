package rvb;

import rvb.io.StageReader;
import rvb.io.StageFormatException;
import rvb.weather.WeatherService;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppFrame frame = new AppFrame();
            Stage stage = new Stage();

            try {
                new StageReader().load("data/stage1.rvb", stage);
            } catch (StageFormatException e) {
                System.err.println("Stage file error: " + e.getMessage());
            } catch (IOException e) {
                System.err.println("Couldn't read stage file: " + e.getMessage());
            }

            WeatherService weather = new WeatherService("http://13.238.167.130/weather");
            weather.addListener(stage);
            weather.start();
            Runtime.getRuntime().addShutdownHook(new Thread(weather::close));

            frame.setStage(stage);
            frame.setVisible(true);
        });
    }
}


