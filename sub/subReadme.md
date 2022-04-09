# Project Title

*War and Agricultural Games*

# Purpose of Project

During this project, we will be able to run and execute a programm, generate its documentation, create and execute test methods.
For that being said, we will create two java games that share three packages and some sub-packages.

1. In **game** package :

This package contains in general the common bases of our two games as well as their specificity.

* Board : an _abstract_ class that defines a common board represented by its _tiles_, _length_ and _width_.
* Tile : an _abstract_ class that defines a common tile represented by its _resources_, _land_ and _character_.
* Player : an _abstract_ class that defines common characteristics of a player such as his _name_, _gold_ and _resources_.
* Character : an _abstract_ class that represents a common character defined by his _gold_.
* Game : defines a basic interface for game bases
* WarMain : main class of war game
* FarmerMain : main class of farmer game
  1. In **boardTypes** sub-package
  * FarmersBoard : defines a board for the farmer game
  * WarBoard : defines a board for the war game
  2. In **characters** sub-package
  * Farmer : defines a character of type _Farmer_ 
  * Military : represents an army defined by its _number of warriors_
  3. In **lands** sub-package
  * WarTile : an _abstract_ class that models a war tile defined by a _military_ and a _land_
  * FarmersTile : an _abstract_ class that models a farmer tile defined by a _farmer_ and a _land_
  	1. In **WarLand** sub-sub-package
  	* WarDesert : defines a tile of type desert
  	* WarPlain : defines a tile of type plain
  	* WarMountain : defines a tile of type mountain
  	* WarWoodland : defines a tile of type woodland
  	* WarOcean : defines a tile of type ocean
  	2. In **FarmersLand** sub-sub-package
  	* FarmerDesert : defines a tile of type desert
  	* FarmerPlain : defines a tile of type plain
  	* FarmerMountain : defines a tile of type mountain
  	* FarmerWoodland : defines a tile of type woodland
  	* FarmerOcean : defines a tile of type ocean

2. In **TypePlayers** package : 

This package contains classes that models two type of players.
* WarPlayer : models a war player defined by his _number of warriors_, _stock of food_, his _territories_ owned, his _troops_ and _tiles_
* FarmerPlayer : represents a player for the agricultural game defined the _tiles_ owned

3. In **gameTypes** package : 

This package contains classes that models our two games.
* WarGame : models the war game and is represented by a list of _players_ and a _board_
* FarmerGame : models the agricultural game and is represented by a list of _players_ and a _board_


# User Instructions

Here are some useful instructions to get the project :

To get the project, do a "clone" and enter the command `$ git clone xxx` in the terminal (xxx = the link you copied). If the project was already "cloned", move into the root of the project and execute this command : `$ git pull` to get the lastest version.


For the following instructions, you must be in the folder that contains the _Makefile_ otherwise anything willl work.
1. Java doc

To get the java doc of the project, execute the command : `$ make doc`. To see the documentation, move into _docs_ folder and open the file _index.html_ in a browser.

2. Run classes

To run all classes from the project, execute the following command : `$ make cls`

3. Generate executable jar file(s)

*  **WARNINGS**: It is necessary to generate classes (`$ make cls`) before using the following commands
	* **War Game** : You just have to execute the command `$ make guerre.jar` in your terminal
	* **Agricultural Game** : Execute the command `$ make agricole.jar` in your terminal
4. Run the programm with jar file(s) created

*  You need to be in _jar_ folder and then execute one of the following command line : 
  * **War Game** : Now that everything is set, you can execute in your terminal the following command : `$ java -jar guerre.jar xxx`
  * **Agricultural Game** : And also for this one : `$ java -jar agricole.jar xxx`
	NB : xxx = any names for the players to play the game, but you need at least two names
5. (Optional)

* If you want, you can skip instructions **1**, **2** and **3** below and just enter the command `$ make all` in your terminal to get the _doc_, _run_ classes
and generate the executable _jar_ file(s). But you still need instruction **4**
* If you want to delete the folders _docs_ and _classes_  and the _jar files_ you can just make `$ make clean` in the terminal.



# Authors

**AICI Halima - BENIDER Imad - BIVEGUE NGOA Isabelle - KEBE Moustapha**

