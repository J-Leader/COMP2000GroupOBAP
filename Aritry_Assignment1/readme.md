
Student Name: Aritry Nag Othoi
ID- 60884517
Branch used for submission: Aritry_Assignment1
Date: 2025-11-06

GRID GAME PROJECT

Hi! This is my personal project for the Week 5 submission of the COMP2000 assignment.
I worked on top of the classwork repository and extended my grid game from previous weeks by adding some new functionality, along with using inheritance, interfaces, and generics in a more meaningful way.

Project Idea -------------

I imagined that the grid represents a simple world where each cell can have a terrain type (like grass, sand, or water), and now I added a Weather system that affects how cells appear.
For example:
Sunny weather brightens the cells.
Rainy weather makes water cells look darker.
Windy weather shakes things up visually.


Inheritance -----------

Inheritance really helped me make the code cleaner.
Earlier, every cell was just a Cell class, but now I made a base Cell class and extended it into a WeatherCell class.
This way, the regular grid code doesn’t need to know how the cell behaves in different weather — it just calls the cell’s own paint method, and each type draws itself differently.

This saved me from writing multiple if-statements and gave each cell a clear “role”.
The same thing applied to the characters (Cat, Dog, Bird) — they all extend the Actor superclass.
It’s simple but powerful because I can now easily add more characters later without touching other code.

Interfaces -----------

I created a small WeatherEffect interface that defines a method applyWeather(Graphics g).
This lets any class that implements it (like WeatherCell or even Actor later) have its own way of reacting to weather changes.


Generics ------------

In my Stage class, I used an ArrayList<Actor> to store all the different actors like the cat, dog, and bird.
This is a generic collection that ensures I don’t accidentally mix in other object types.

Later, I even experimented with Optional<Cell> in the Grid class (as we were taught in week 5).
It’s a generic wrapper that helps avoid null pointer errors — for example, when trying to find what cell is under the mouse, it either gives back a Cell or an empty result safely.

What the program does --------------

When I run my program, it opens a 1024x720 window with a 20x20 grid.
Each cell is drawn with a small border and can be highlighted when the mouse hovers over it.
Then, I have the three characters — the cat, dog, and bird — drawn in different colours and shapes.

The new feature is that now each cell can change its appearance depending on the Weather.
For example, I set the weather randomly when the program starts, and you can see how the grid colour slightly changes across the terrain types.

It doesn’t yet have a scoring system or movement (that might come later!), but visually it feels much more alive than before.

Java Libraries ----------


java.awt.Graphics for drawing shapes and colours.

java.awt.Point and java.awt.Polygon for positioning and drawing polygons for the animals.

java.util.ArrayList and java.util.Optional for handling collections and safe object returns.

These are all part of the standard Java library, so the program works fine with both Java 11 and Java 21, which were mentioned in the assignment.

Git and GitHub --------------

I built everything inside my classwork repository.
Here’s what I did using Git Bash:

Cloned the original repo from GitHub using "git clone".

Created a new branch for my submission "git checkout".

Edited files using VS Code and Git Bash (nano Cell.java, nano Actor.java, etc.)

Added my changes using "git add".

Committed my progress "git commit".

Pushed it to GitHub using "git push".

After that, I merged it into the main branch when everything worked properly.
Then I zipped the entire repository (including the .git folder) for submission.

How to Compile and Run the Program-------------

Compilation:
Open Git Bash or Command Prompt inside the repository folder and run:

javac *.java


Run:

java Stage

This will open the game window showing the grid and the animal characters.
If everything is correct, you’ll see the weather effects changing the grid’s look.
