# **KnowledgeSweeper**
This game is just like a normal Minesweeper but it has a twist where if you pick a square with a bomb in it a question will pop up when you answer it correctly you don’t lose a life and you can continue but if answered wrong you lose a life as a consequence.

Every square has a random chance to have a bomb in it or a chest which may give you extra lives. You get 3 lives when you start a game, when all lives are lost it is game over. When you lose a life, a question pops out (the question depends on the subject you choose)

There are different categories:
- Math
- History
- Science


# **Changelogs:**
- ***V1.0 (Major Update)***
	- ***7/29/24***  
		- Successfully recoded the whole game to be readable and clean to look at
		- Implemented a state machine to change between game scenes
		- Implemented Full Screen Support
		- Added a settings menu to adjust the volume of the music and sound effects
- ***V1.1 (Minor Update)***
	- ***8/27/24***
		- Removed Full Screen Support and reverted back to a fixed window because of collisions inconsistences
		- Adjusted the collision shapes of the buttons
		- Bugs Fixed:
			- Offset mouse inside the board
			- Question Pops up even you win
- ***V1.2 (Minor Update)***
	- *8/29/24*
		- Flagging using right click is added
	- *8/31/24*
		- timer added, the time depends on the difficulty chosen
	- *9/2/24*
		- New Icons for Home and Restart Button
		- home button added
- ***V1.3 (Major Update)***
	- *9/3/24*
		- experimenting making a leaderboard
			- Adding a textbox for inputting the player name
			- Arranging the scores
	- *9/5/24*
		- Reverted the separation of choosing the Category and the Level of Difficulty
		- Improved the design of the textbox for inputting the player name
		- Made the leaderboard sort and show the winners
	- *9/10/24*
		- Added a parallax background
	- *9/13/24*
		- Empty tile will now be automatically be revealed
		- Warning of the number of bombs needed to be avoided
		- The size of the game board is shown when choosing a level
		- Updated some UI and Background Elements
	- *9/28/24*
		- implementation of the new Leaderboard design
		- Added a sorter for the level in the leaderboard
- ***V1.4 (Minor Update)***
	- *10/3/24*
		- Changed the UI of the quiz popup
		- Fixed the Timer taking all the lives
	- *10/17/24*
		- Fixed a bug when changing the leaderboard level fast it can crash the game
		- Made a page manager to change the pages in the Tutorial
- ***V1.5 (Finalizing Update)***
	- *10/31/24*
		- Added a filter tab in the leaderboard
	- *11/2/24*
		- Added Environmental Design in the Gameboard & Some UI changes
	-  *11/7/24*
		- Fixed a a bug on Flagged
		- Fixed a Bug the number of bombs indicator doesn't decrease
