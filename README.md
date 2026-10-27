# 💱 Currency Converter — Java

A console-based Currency Converter developed as **Project 4** for the DecodeLabs Java Programming Internship.

The program converts an amount from one supported currency to another using a USD pivot rate. It uses `BigDecimal` to avoid common floating-point precision issues in financial calculations.

## 🚀 Features

- Supports USD, INR, EUR, GBP, JPY, and AUD
- Accepts source currency, target currency, and amount from the user
- Uses USD as a pivot for cross-currency conversion
- Uses `BigDecimal` and explicit rounding for monetary calculations
- Rejects unsupported currency codes
- Rejects zero, negative, and non-numeric amounts
- Handles invalid input without crashing
- Supports repeated conversions in one run
- Formats results to exactly two decimal places

## 🛠️ Technologies Used

- Java
- `BigDecimal` for decimal arithmetic
- `Scanner` for console input
- Visual Studio Code
- Git & GitHub

## 📁 Project Structure

```text
decodelabs-java-project-4/
├── src/
│   └── CurrencyConverter.java
├── sample-output.txt
├── .gitignore
└── README.md
```

## ▶️ How to Run in VS Code

### Requirements
1. Install a JDK (Java Development Kit).
2. Install Visual Studio Code.
3. Install the **Extension Pack for Java** in VS Code.

### Run from the terminal

Open this folder in VS Code, then run:

```bash
javac -d out src/CurrencyConverter.java
java -cp out CurrencyConverter
```

## 🧪 Sample Output

```text
=================================================
               CURRENCY CONVERTER
=================================================
Note: The exchange rates below are demo rates,
not live market rates. Results are for practice.

Supported currencies: USD, INR, EUR, GBP, JPY, AUD
Demo rates (units per 1 USD):
  1 USD = 1 USD
  1 USD = 83 INR
  1 USD = 0.92 EUR
  1 USD = 0.79 GBP
  1 USD = 150 JPY
  1 USD = 1.52 AUD

Enter source currency code: USD
Enter target currency code: INR
Enter amount to convert: 100

=============== CONVERSION RESULT ===============
From           : USD 100.00
To             : INR 8300.00
Exchange route : USD -> USD -> INR
=================================================

Would you like to convert another amount? (Y/N): N

Thank you for using Currency Converter!
```

## 📊 How Conversion Works

The program stores the number of units of each currency equivalent to 1 USD.

For example, with the demonstration rates:
- 1 USD = 83 INR
- 1 USD = 0.92 EUR

To convert INR to EUR, it first converts INR to USD, then USD to EUR.

These are **fixed demonstration rates**, not current exchange rates. The project brief describes live API integration as optional; this implementation intentionally works offline.

## 🧠 Concepts Demonstrated

- Variables, methods, loops, and conditional statements
- Console input using `Scanner`
- `Map` for storing currency rates
- `BigDecimal` for decimal precision
- `RoundingMode.HALF_EVEN` for rounding
- Input validation and exception handling
- Cross-rate conversion through a pivot currency

## ⚠️ Important Note

This is an educational project. It does not fetch live rates, perform financial transactions, or provide financial advice. Exchange rates are hard-coded examples and should be updated from a trusted source before any real-world use.

## 👨‍💻 Developed By

**Devkant Prajapati**  
DecodeLabs Java Programming Internship — Project 4
