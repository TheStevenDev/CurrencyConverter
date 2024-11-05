# Currency Converter

This is a simple Java program that allows you to convert currencies using real-time exchange rates.

## Prerequisites

Before running the program, you need to obtain a free API key from [Exchange Rate API](https://www.exchangerate-api.com):

1. Go to [Exchange Rate API](https://www.exchangerate-api.com).
2. Sign up for a free account.
3. After signing up, you will receive an API key.

## Setup

1. Clone the repository to your local machine using Git:
   ```bash
   git clone https://github.com/TheStevenDev/CurrencyConverter.git
   ```
2. Navigate to the project directory:
   ```bash
   cd CurrencyConverter
   ```
3. Replace `YOUR_KEY` in the `baseUrl` variable within the `myFrame` class in the code with your actual API key:
   ```java
   String baseUrl = "https://v6.exchangerate-api.com/v6/YOUR_KEY/latest/";
   ```

## Usage

1. Compile and run the `Main.java` file using a Java compiler.
2. Follow the instructions to input the amount to convert and select the source and target currencies.
3. Click the "CONVERTI" button to see the converted amount.

## License

This project is distributed under the MIT license. For more information, contact the owner.

## Version Control

You can find the project repository on GitHub: [Currency Converter Repository](https://github.com/TheStevenDev/CurrencyConverter/)
