Assignment 1 Description



Student Name: Aritry Nag Othoi

Branch used for submission: submission-<yourname>

Date: 2025-11-06







This project extends Week 3–5 classwork by adding a Terrain + Weather + Items system to the 20×20 grid and demonstrates inheritance, interfaces, and generics. Actors (Cat, Dog, Bird) draw themselves using polygons and react when you hover over cells; cells can hold passive Item<?> objects (Bone, Fish, Seed).



Newly added:



Cell<T> — a custom generic cell type. In this submission T = CellInfo.



CellInfo — holds terrain, weather, and a list of Item<?> objects.



TerrainWeatherCell — subclass of Cell<CellInfo>, implements the Interactive interface and paints itself using terrain + weather.



Item<T> — another custom generic class for passive items (Bone, Fish, Seed); implements Drawable.



Drawable \& Interactive — interfaces actively used in code:



Drawable standardizes drawing for Item and Cell.



Interactive allows cells to react when an actor interacts (e.g., Bird collects a Seed).



Actor superclass with Cat, Dog, Bird subclasses — demonstrates inheritance and polymorphism.



Stage, Grid, GridPanel, AppWindow, Main — glue code for UI, painting, and interactions.



Java file in src folder:

&nbsp; Main.java

&nbsp; AppWindow.java

&nbsp; GridPanel.java

&nbsp; Grid.java

&nbsp; Cell.java

&nbsp; CellInfo.java

&nbsp; TerrainWeatherCell.java

&nbsp; TerrainType.java

&nbsp; WeatherType.java

&nbsp; Stage.java

&nbsp; Actor.java

&nbsp; Cat.java

&nbsp; Dog.java

&nbsp; Bird.java

&nbsp; Item.java

&nbsp; Bone.java

&nbsp; Fish.java

&nbsp; Seed.java

&nbsp; Drawable.java

&nbsp; Interactive.java





Java libraries:



javax.swing — JFrame, JPanel, SwingUtilities (GUI)



java.awt — Graphics, Color, Polygon, Rectangle, Point (drawing \& geometry)



java.awt.event — mouse event listeners (MouseAdapter, MouseEvent)



java.util — ArrayList, List, Optional (collections \& safe return)





Explanation:

Generics ------



Cell<T> is a custom generic class that stores typed domain data (T). Here T is CellInfo, which aggregates terrain, weather and items.

Reason: Using a generic Cell<T> makes the core container flexible and type-safe. If later we want Cell<ElevationInfo>, we simply reuse the same structure. This shows more depth than just using ArrayList<T>.



Item<T> is a custom generic class for passive items. Items can carry a typed payload (T) and implement Drawable.

Reason: Demonstrates generics beyond collections and models real game objects (food, tools, etc.) in a type-safe way.



Inheritance ---------



Actor superclass defines the common contract and data (location) for game characters.



Cat, Dog, Bird extend Actor and override paint(Graphics) and reactToCell(...).

Why: Actors share behaviour and data (location, paint contract), but each subclass customises appearance and response to terrain/weather. This reduces duplication and makes adding new actors trivial.



TerrainWeatherCell extends Cell<CellInfo> and specialises painting and interactions (implements Interactive).

Reason: The base generic cell contains data; the specialised subclass uses that data to draw richer visuals and respond to actors.



Interfaces -----------



Drawable — implemented by Item and Cell classes (via Cell.draw and Item.draw). This standardizes drawing behavior across different classes.

Reason: Using Drawable shows knowledge of interfaces as contracts and enables polymorphic drawing (a Stage or Grid can draw a mix of Drawable objects).



Interactive — implemented by TerrainWeatherCell. Its interact(Actor) method encodes interactions such as a Bird collecting a Seed.

Reason: Interfaces separate what an object can do from how it does it; Interactive is a good fit for any cell or object that should respond to actor actions.





