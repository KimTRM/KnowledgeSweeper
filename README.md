# ![alt text](GameEngine/Graphics/res/icon/Bomb.png) KnowledgeSweeper

**An Educational Twist on Classic Minesweeper**

KnowledgeSweeper combines the strategic gameplay of Minesweeper with educational trivia questions. Hit a bomb? Answer a question correctly to survive! This Java-based game challenges both your logic and knowledge across multiple subjects.

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=openjdk&logoColor=white)
![Version](https://img.shields.io/badge/version-1.5-blue)

---

## 📋 Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Gameplay](#gameplay)
- [Installation](#installation)
- [How to Play](#how-to-play)
- [Project Structure](#project-structure)
- [Technical Details](#technical-details)
- [Game Modes](#game-modes)
- [Categories](#categories)
- [Development](#development)
- [Changelog](#changelog)

---

## 🎯 Overview

KnowledgeSweeper is a Java Swing-based educational game that puts a unique spin on the classic Minesweeper formula. When you click on a bomb, instead of immediately losing, you're presented with a trivia question. Answer correctly to survive, or lose a life if you're wrong!

**Core Gameplay:**
- Clear the board while avoiding bombs
- Answer quiz questions when you hit a bomb
- Find treasure chests for extra lives
- Beat the timer on different difficulty levels
- Compete on the leaderboard

---

## ✨ Features

### 🎲 Game Mechanics
- **Lives System**: Start with 3 lives; lose one for wrong answers
- **Treasure Chests**: Random chance to find chests that grant extra lives
- **Smart Reveal**: Empty tiles automatically reveal adjacent safe tiles
- **Flagging System**: Right-click to flag suspected bombs
- **Timer Challenge**: Race against the clock (time varies by difficulty)

### 🎨 Visual & Audio
- **Parallax Background**: Dynamic scrolling backgrounds
- **Animated Elements**: Smooth animations for game board and transitions
- **Sound Effects**: Audio feedback for actions and events
- **Custom Graphics**: Hand-designed UI elements and icons
- **Environmental Design**: Enhanced gameboard with visual flair

### 🏆 Progression System
- **Leaderboard**: Track high scores across all difficulty levels and categories
- **Filter System**: Sort and filter leaderboard by category and difficulty
- **Player Names**: Personalized score tracking
- **Multiple Categories**: Choose from Math, History, or Science
- **Difficulty Tiers**: Easy, Normal, and Hard modes

### 📚 Educational Content
- **Quiz System**: Hundreds of questions across three subjects
- **Timed Questions**: 20-second timer for quiz answers
- **Multiple Choice**: Four answer options for each question

---

## 🎮 Gameplay

### Core Rules
1. **Starting Lives**: Begin each game with 3 lives
2. **Bomb Encounter**: Clicking a bomb triggers a quiz question
3. **Answer Correctly**: Keep your life and continue playing
4. **Answer Incorrectly**: Lose 1 life
5. **Treasure Chests**: Find them for bonus lives
6. **Victory**: Clear all safe tiles without losing all lives
7. **Defeat**: Lose all 3 lives or run out of time

### Game Flow
```
Start Game → Choose Category → Choose Difficulty → Enter Name → Play → Quiz (on bomb) → Continue/Lose Life → Victory/Defeat → Leaderboard
```

---

## 💻 Installation

### Prerequisites
- **Java Development Kit (JDK)**: Version 8 or higher
- **Operating System**: Windows, macOS, or Linux

### Steps

1. **Clone the Repository**
   ```bash
   git clone https://github.com/KimTRM/KnowledgeSweeper.git
   cd KnowledgeSweeper
   ```

2. **Compile the Project**
   ```bash
   javac -d bin GameEngine/GameLauncher.java
   ```

3. **Run the Game**
   ```bash
   java -cp bin GameEngine.GameLauncher
   ```

   **Or use the JAR file (if available):**
   ```bash
   java -jar KnowledgeSweeper.jar
   ```

---

## 🕹️ How to Play

### Controls
- **Left Click**: Reveal a tile
- **Right Click**: Flag/Unflag a tile
- **Mouse**: Navigate menus and UI

### Tips for Success
1. **Use Flags**: Mark suspected bombs to avoid accidentally clicking them
2. **Read Numbers**: Numbers indicate adjacent bombs (just like classic Minesweeper)
3. **Study Up**: Familiarize yourself with your chosen category
4. **Manage Time**: Keep an eye on both the game timer and quiz timer
5. **Find Chests**: Extra lives can save you from mistakes

---

## 📁 Project Structure

```
KnowledgeSweeper/
├── GameEngine/
│   ├── GameLauncher.java          # Main entry point
│   ├── GamePanel.java              # Main game panel and rendering
│   │
│   ├── Graphics/                   # Visual components
│   │   ├── AnimatedBackground.java # Animated background system
│   │   ├── AssetManager.java      # Asset loading and management
│   │   ├── GameBoardAnimation.java # Board animation effects
│   │   ├── ParallaxBackground.java # Parallax scrolling effect
│   │   └── res/                    # Resource files (images, sounds, fonts)
│   │       ├── background/         # Background images
│   │       ├── icon/               # Game icons
│   │       ├── font/               # Custom fonts
│   │       ├── sounds/             # Sound effects and music
│   │       ├── title/              # Title screen assets
│   │       └── files/              # Game data files
│   │           ├── Questions/      # Question databases
│   │           │   ├── Math/
│   │           │   ├── History/
│   │           │   └── Science/
│   │           └── Subjects/       # Subject files
│   │
│   ├── States/                     # Game state management
│   │   ├── GameStateManager.java  # State machine controller
│   │   ├── State.java             # Base state class
│   │   └── GameStates/
│   │       ├── MenuState.java          # Main menu
│   │       ├── GameOptionsState.java   # Category/Difficulty selection
│   │       ├── PlayerNameState.java    # Name entry screen
│   │       ├── GameState.java          # Main game logic
│   │       ├── QuizState.java          # Quiz question overlay
│   │       ├── EndingState.java        # Game over/win screen
│   │       ├── LeaderboardState.java   # Score display
│   │       ├── TutorialsState.java     # Tutorial pages
│   │       ├── StartingState.java      # Opening animation
│   │       └── GMenuState.java         # In-game menu
│   │
│   └── Util/                       # Utility classes
│       ├── Game/
│       │   ├── GameBoard.java     # Board logic and rendering
│       │   └── QuizManager.java   # Quiz system management
│       ├── Input/
│       │   ├── KeyHandler.java    # Keyboard input
│       │   └── MouseHandler.java  # Mouse input
│       ├── Leaderboard/
│       │   ├── Leaderboard.java   # Score tracking and display
│       │   └── PlayerName.java    # Player name management
│       ├── Options/
│       │   ├── Category.java      # Subject selection
│       │   └── Level.java         # Difficulty management
│       ├── Tutorial/
│       │   ├── PageManager.java   # Tutorial navigation
│       │   ├── Page.java          # Base page class
│       │   └── Pages/             # Individual tutorial pages
│       │       ├── Page1.java
│       │       ├── Page2.java
│       │       ├── Page3.java
│       │       ├── Page4.java
│       │       ├── Page5.java
│       │       ├── Page6.java
│       │       └── Page7.java
│       └── End/
│           └── LossEnd.java       # Game over logic
│
├── Data/                           # Leaderboard data files
│   ├── Math/
│   │   ├── EasyLeaderboard.txt
│   │   ├── NormalLeaderboard.txt
│   │   └── HardLeaderboard.txt
│   ├── History/
│   │   ├── EasyLeaderboard.txt
│   │   ├── NormalLeaderboard.txt
│   │   └── HardLeaderboard.txt
│   └── Science/
│       ├── EasyLeaderboard.txt
│       ├── NormalLeaderboard.txt
│       └── HardLeaderboard.txt
│
├── bin/                            # Compiled classes
├── META-INF/
│   └── MANIFEST.MF                # JAR manifest
└── README.md                       # This file
```

---

## 🔧 Technical Details

### Architecture
- **Design Pattern**: State Machine Pattern
  - Manages transitions between different game screens
  - Clean separation of concerns for each game state
  
- **Rendering**: Custom 2D Graphics using Java Swing
  - Resolution: 1280x720 (fixed window)
  - Double buffering for smooth animations
  - 60 FPS game loop
  
- **Asset Management**: Centralized resource loading
  - Images, fonts, sounds loaded once and cached
  
### Key Classes

#### `GameStateManager`
Manages the state stack and transitions between different game screens. Implements a stack-based state system for overlay states (like quiz popups).

#### `GameBoard`
Handles the Minesweeper grid logic:
- Bomb placement (random distribution)
- Neighbor counting algorithm
- Recursive tile revealing for empty tiles
- Victory/defeat conditions
- Timer management

#### `QuizManager`
Manages the quiz system:
- Question loading from text files
- Random question selection
- Answer validation
- 20-second timer per question
- Subject and difficulty filtering

#### `Leaderboard`
Handles score persistence:
- Reads/writes to category-specific text files
- Sorts scores by performance
- Displays top players
- Filter system for viewing specific categories/difficulties

#### `AssetManager`
Centralized asset loading:
- Image management
- Sound effect playback
- Font loading
- Resource caching

---

## 🎯 Game Modes

### Easy Mode
- **Grid Size**: 11 columns × 7 rows (77 tiles)
- **Bombs**: 20
- **Safe Tiles to Win**: 36
- **Time Limit**: 61 seconds

### Normal Mode
- **Grid Size**: 13 columns × 9 rows (117 tiles)
- **Bombs**: ~30-35
- **Safe Tiles to Win**: ~60-70
- **Time Limit**: ~90 seconds

### Hard Mode
- **Grid Size**: 15 columns × 11 rows (165 tiles)
- **Bombs**: ~45-50
- **Safe Tiles to Win**: ~100-110
- **Time Limit**: ~120 seconds

---

## 📚 Categories

### Mathematics
Questions covering:
- Arithmetic and algebra
- Geometry
- Mathematical concepts
- Problem-solving

### History
Topics include:
- World history
- Historical events
- Important figures
- Cultural milestones

### Science
Subjects covering:
- Biology
- Chemistry
- Physics
- General science facts

**Question Format**: All questions are stored in text files located in:
```
GameEngine/Graphics/res/files/Questions/
├── Math/
│   ├── Easy.txt
│   ├── Normal.txt
│   └── Hard.txt
├── History/
│   ├── Easy.txt
│   ├── Normal.txt
│   └── Hard.txt
└── Science/
    ├── Easy.txt
    ├── Normal.txt
    └── Hard.txt
```

---

## 🛠️ Development

### Built With
- **Java SE**: Core programming language
- **Java Swing**: GUI framework
- **Java AWT**: Graphics and event handling

### Adding New Questions

1. Navigate to: `GameEngine/Graphics/res/files/Questions/[Category]/[Difficulty].txt`
2. Follow this format:
   ```
   Question text here?
   A) Option A
   B) Option B
   C) Option C
   D) Option D
   Correct Answer: A
   ```

### Modifying Difficulty Settings

Edit the difficulty methods in `GameBoard.java`:
- `EasyMode()`
- `MediumMode()`
- `HardMode()`

Adjust parameters: `cols`, `rows`, `NumBomb`, `TotalBoxes`, `TimerSeconds`

### Contributing

Contributions are welcome! Feel free to:
- Add more questions to any category
- Improve graphics and animations
- Report bugs
- Suggest new features

---

## 📝 Changelog

### V1.5 (Finalizing Update)
- *11/7/24*
  - Fixed a bug on Flagged tiles
  - Fixed a Bug where the number of bombs indicator doesn't decrease
- *11/2/24*
  - Added Environmental Design in the Gameboard & Some UI changes
- *10/31/24*
  - Added a filter tab in the leaderboard

### V1.4 (Minor Update)
- *10/17/24*
  - Fixed a bug when changing the leaderboard level fast it can crash the game
  - Made a page manager to change the pages in the Tutorial
- *10/3/24*
  - Changed the UI of the quiz popup
  - Fixed the Timer taking all the lives

### V1.3 (Major Update)
- *9/30/24*
  - Successfully added a quiz with a 20s timer
- *9/28/24*
  - Implementation of the new Leaderboard design
  - Added a sorter for the level in the leaderboard
  - Added an ending animation when you lose or win
  - Added a tutorial page
- *9/13/24*
  - Empty tile will now be automatically revealed
  - Warning of the number of bombs needed to be avoided
  - The size of the game board is shown when choosing a level
  - Updated some UI and Background Elements
- *9/10/24*
  - Added a parallax background
- *9/5/24*
  - Reverted the separation of choosing the Category and the Level of Difficulty
  - Improved the design of the textbox for inputting the player name
  - Made the leaderboard sort and show the winners
- *9/3/24*
  - Experimenting making a leaderboard
  - Adding a textbox for inputting the player name
  - Arranging the scores

### V1.2 (Minor Update)
- *9/2/24*
  - New Icons for Home and Restart Button
  - Home button added
- *8/31/24*
  - Timer added, the time depends on the difficulty chosen
- *8/29/24*
  - Flagging using right click is added

### V1.1 (Minor Update)
- *8/27/24*
  - Removed Full Screen Support and reverted back to a fixed window because of collision inconsistencies
  - Adjusted the collision shapes of the buttons
  - Bugs Fixed:
    - Offset mouse inside the board
    - Question pops up even when you win

### V1.0 (Major Update)
- *7/29/24*  
  - Successfully recoded the whole game to be readable and clean to look at
  - Implemented a state machine to change between game scenes
  - Implemented Full Screen Support
  - Added a settings menu to adjust the volume of the music and sound effects

---

## 📄 License

This project is available for educational purposes. 

---

## 👤 Author

**KimTRM**
- GitHub: [@KimTRM](https://github.com/KimTRM)

---

## 🎓 Acknowledgments

- Inspired by the classic Minesweeper game
- Built as an educational project to combine gaming with learning
- Special thanks to the Java Swing community for documentation and resources

---

**Enjoy playing KnowledgeSweeper! Test your logic and expand your knowledge!** 🎮📚
