package rvb.io;

import rvb.Stage;
import rvb.actors.Cat;
import rvb.actors.Dog;
import rvb.actors.Bird;
import rvb.grid.Cell;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class StageReader {


    public void load(String path, Stage stage) throws IOException, StageFormatException {
        File f = new File(path);
        if (!f.exists()) {
            System.err.println("[StageReader] File not found: " + f.getAbsolutePath());
            return;
        }

        System.out.println("[StageReader] Reading: " + f.getAbsolutePath());

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            int lineNo = 0;
            while ((line = br.readLine()) != null) {
                lineNo++;
                String s = line.trim();
                if (s.isEmpty() || s.startsWith("#")) continue;

                String[] parts = s.split("=");
                if (parts.length != 2)
                    throw new StageFormatException("Bad line " + lineNo + ": " + s);

                String left = parts[0].trim();   
                String right = parts[1].trim();  

                if (left.length() < 2)
                    throw new StageFormatException("Bad cell ref at line " + lineNo + ": " + left);

                char colChar = Character.toUpperCase(left.charAt(0));
                if (colChar < 'A' || colChar > 'T')
                    throw new StageFormatException("Column out of range at line " + lineNo + ": " + colChar);
                int col = colChar - 'A';

                int rowHuman;
                try {
                    rowHuman = Integer.parseInt(left.substring(1));
                } catch (NumberFormatException e) {
                    throw new StageFormatException("Bad row at line " + lineNo + ": " + left);
                }
                if (rowHuman < 1 || rowHuman > 20)
                    throw new StageFormatException("Row out of range at line " + lineNo + ": " + rowHuman);
                int row = rowHuman - 1;

                java.util.Optional<Cell> maybeCell = stage.getGrid().cellAtColRow(col, row);
                if (maybeCell.isEmpty())
                    throw new StageFormatException("Cell OOB at line " + lineNo + ": " + left);
                Cell cell = maybeCell.get();

                switch (right.toLowerCase()) {
                    case "cat":
                        stage.addActor(new Cat(cell));
                        System.out.println("Loaded cat at " + left);
                        break;
                    case "dog":
                        stage.addActor(new Dog(cell));
                        System.out.println("Loaded dog at " + left);
                        break;
                    case "bird":
                        stage.addActor(new Bird(cell));
                        System.out.println("Loaded bird at " + left);
                        break;
                    default:
                        throw new StageFormatException("Unknown actor at line " + lineNo + ": " + right);
                }
            }
        }
    }
}