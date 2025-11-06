# COMP2000 - Object Oriented Programming Practices

# Instructions to compile and run the Program
Step 1: Download the repository file(This file should be a zip file)
Step 2: Unzip the repository file(To do so ensure you have either winrar or 7zip to do so)
Step 3: Open your sorucecode editor (For me VS CODE)
Step 4: Open the folder SRC folder in the HassanShoubail folder
Step 5: Open every File within the SRC folder
Step 6: Ensure your are on the Main.java file before running the program otherwise it wont work
Congracts thats everystep completed

# Project Overiew
This Project/code contains a window the size of 1024 by 720 with a even 20 by 20 grid, similar to a simluating game in which tiles/cells can contain items such as animals or housing in each cell That incoprates all practises of inheritance, interfaces, and generics.

# Features/Functionalities
The grid is a 20x20 grid on a window the size of 1024x720  
When hovering over a cell it changes colours to grey
Includes Objects such as 'Cat','Dog','Bird','ScratchPost', 'DogHouse', and 'Birdcage'
Shows a small icon/polygon image of what is in each cell and is colored to show

# Inheritance
Inhertiance is a principle that allows the a singular class to reuse code to achieve the same output without the tediness of copying.
Inhertiance is used for both the 'Animal' and 'House' classes that allows for simple, less reduandant and more efficent by being able to reuse the class for each animal and/or House to add or remove while also through the use of subclasses providing each object with its own position of the indivdual object aswell as the colour.
The use of the parent-child relationship allows for the simple and understandable changes to be performed to either add or remove with simple changes.

# Generics
Generics is the principle that allows for the use of other data types whether for methods, functions or classes to be used as a placeholder not matter the situation allowing for a simpler, less error enduced project that allows for the correct data type to be used.
Generics is used in my code through the the Itemlits.java file. This file allows for the input of any data type of objects to be passed through into a list that can be stored and used at any point allowing for a simple and efficent way to correct hold and use data. This method creats an arraylist that when passed an object holds only the data type. It is later used in the Stage.java file to add each indivdual Animal or House to each list to be extracted and used. This allows for less errors to occur through human error by getting the wrong data type will also being flexible to be used for any data type.