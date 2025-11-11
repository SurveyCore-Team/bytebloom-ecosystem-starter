# The ByteBloom Ecosystem - Official Project Starter

Welcome to the official starter project for the ByteBloom Academy's Kotlin Software Engineering mentorship program. This repository contains the foundational code and data assets for your team's main project.

## Project Vision

The goal of this project is to build a robust, well-tested, and professionally architected command-line application. This system will process data from multiple sources to model a mentee ecosystem, generating insightful reports based on user commands. This project is the canvas on which you will apply all the concepts learned during the 8-week program, from basic syntax to SOLID principles, TDD, and concurrency.

## Getting Started

1.  **Clone This Starter Project:** Use `git clone` to download this starter project to your local machine.
2.  **Open in IntelliJ IDEA:** Open the cloned project folder in IntelliJ. It will automatically detect and sync the `build.gradle.kts` file.
3.  **Run the `main` function:** Navigate to `src/main/kotlin/Main.kt` and run the `main` function to confirm your setup is correct.

## Project Structure

-   `src/main/kotlin/Main.kt`: The main entry point for the application.
-   `src/main/resources/`: Contains all raw data files (`mentees.csv`, `teams.csv`, `performance.csv`).
-   `src/test/kotlin/`: This directory is where all your unit tests will live.
-   `build.gradle.kts`: The heart of the project. This file manages dependencies (like JUnit, Koin, etc.) and build settings.
-   `.gitignore`: Specifies which files and directories to ignore in version control.
-   `README.md`: This file! The primary documentation for the project.