package pl.katarzynawojtowicz.currencyconverter.controller;

public class CurrencyController {
	private String[] currencies = { "AUD", "BGN", "BRL", "CAD", "CHF", "CNY", "CZK", "DKK", "EUR", "GBP", "HKD", "HRK", "HUF",
			"IDR", "ILS", "INR", "JPY", "KRW", "MXN", "MYR", "NOK", "NZD", "PHP", "PLN", "RON", "RUB", "SEK", "SGD",
			"THB", "TRY", "USD", "ZAR" };
	
	public double convert (double amount, String sourceCurrency, String destinationCurrency) {
		amount *= 2;
		return amount;
	}

	public String[] getCurrencies() {
		return currencies;
	}
}
