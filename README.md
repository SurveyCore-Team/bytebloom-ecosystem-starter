🌱 ByteBloom Ecosystem – Kotlin Implementation

This repository contains our team’s implementation of the ByteBloom Ecosystem using Kotlin.
The project reads data from CSV files (mentees.csv, teams.csv, performance.csv), converts them into structured models, and generates simple insightful reports via a CLI interface.

🚀 How to Run the Project

Open the project in IntelliJ IDEA

Ensure Gradle syncs automatically

Navigate to:

src/main/kotlin/Main.kt


Press Run ▶

The CLI will launch and allow you to browse reports and data insights.

👥 Team Members

Raghad Abbas

Alaa Husam

Soad Alastal

Shahea Hassan

Shahed Musallm

🏗 Project Structure
project-root/
│
├── src/
│   ├── main/
│   │   ├── kotlin/
│   │   │   ├── Main.kt               → Application entry point
│   │   │   ├── data/                 → Raw models mapped from CSV
│   │   │   ├── parsers/              → CSV parsing logic
│   │   │   ├── models/               → Clean processed data models
│   │   └── resources/
│   │       ├── mentees.csv
│   │       ├── teams.csv
│   │       ├── performance.csv
│   │
│   └── test/                         → Unit tests (if added)
│
├── build.gradle.kts                  → Project dependencies & config
├── README.md                         → Project documentation
└── .gitignore
