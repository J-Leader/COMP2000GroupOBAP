package rvb.io;

import rvb.Stage;
import rvb.actors.*;
import rvb.grid.Cell;

import java.io.*;
import java.util.*;

public class StageReader {
    public void load(String filename, Stage stage) throws IOException, StageFormatException {
        File f = new File(filename);
        if (!f.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("#")) continue;
                String[] parts = line.split("=");
                if (parts.length != 2) throw new StageFormatException("Bad line: " + line);
                String left = parts[0].trim();
                String right = parts[1].trim();
                char colChar = left.charAt(0);
                int col = Character.toUpperCase(colChar) - 'A';
                int row = Integer.parseInt(left.substring(1));
                Cell cell = stage.getGrid().cellAtColRow(col, row).orElseThrow(() ->
                        new StageFormatException("Bad cell: " + left));
                switch (right.toLowerCase()) {
                    case "cat": stage.addActor(new Cat(cell)); 
		    System.out.println("Loaded cat at " + left);
		    break;
                    case "dog": stage.addActor(new Dog(cell)); 
		    System.out.println("Loaded dog at " + left);
		    break;
                    case "bird": stage.addActor(new Bird(cell)); 
		    System.out.println("Loaded bird at " + left);
		    break;
                    default: throw new StageFormatException("Unknown actor: " + right);
                }
            }
	}
    }
}