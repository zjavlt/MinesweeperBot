# Java Selenium Minesweeper Bot

## Project Overview

This project is a bot developed using **Java** and **Selenium WebDriver** to automatically play and solve a web-based version of the game Minesweeper.

Beyond simple web page manipulation, the bot analyzes the state of the game board in real-time, making decisions through logical deduction and heuristic-based algorithms. This project served as a practical application for learning object-oriented design, test automation, dynamic web page handling, and basic game AI logic.

## Key Features

* **Automated Gameplay**: Automatically navigates to the target website, starts a new game, and plays autonomously.
* **Real-time State Analysis**: Identifies each cell on the board and translates it into an internal data model, distinguishing between closed, flagged, and numbered (0-8) states.
* **Logical Deduction**: Finds 100% safe cells to click and certain mines to flag based on the information from surrounding numbered cells.
* **Heuristic-Based Guessing**: When no certain moves are available, the bot makes an intelligent guess to break the stalemate and continue the game.
* **Failure Detection & Auto-Restart**: Detects when a game is lost, automatically starts a new game, and repeats the process until a win is achieved.
* **Robust Exception Handling**: Manages unexpected browser events, such as JavaScript alerts, to ensure stable and continuous operation.

## Tech Stack

* **Language**: `Java`
* **Web Automation**: `Selenium WebDriver`
* **Development Environment**: Any IDE supporting Java (e.g., VS Code, Eclipse, IntelliJ IDEA)

## How to Run

1.  Clone the project repository to your local machine.
2.  Open the project in your preferred IDE and ensure the Selenium libraries are correctly added to the build path.
3.  Execute the `main` method in the `Main.java` class.
4.  The program will launch a new Chrome browser instance, and the bot will begin playing Minesweeper automatically. This project utilizes Selenium Manager, so manual `ChromeDriver` setup is not required.

## Key Learnings & Concepts

* **Object-Oriented Design (OOP)**: Successfully separated concerns into distinct classes: `GameDriver` for browser control, `GameBoard` for game state and logic, and `Main` for overall orchestration, resulting in maintainable and extensible code.
* **Advanced Selenium WebDriver Usage**: Implemented key Selenium features, including dynamic waits with `WebDriverWait`, precise element selection with `CSS Selectors` and `XPath`, complex inputs with the `Actions` class, and `Alert` handling.
* **Algorithms**:
    * **Flood Fill (BFS)**: Implemented an efficient flood-fill algorithm using a `Queue` to process the cascading reveals when a '0' cell is clicked.
    * **Heuristic-Based Decision Making**: Developed a decision-making process that uses simple heuristics, such as exploring near low-numbered cells or clicking corners, when faced with uncertainty.
* **Exception Handling**: Wrote robust code using multi-layered `try-catch` blocks to handle various runtime exceptions (`UnhandledAlertException`, `NoAlertPresentException`, etc.) gracefully.
