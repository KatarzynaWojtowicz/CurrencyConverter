package pl.katarzynawojtowicz.currencyconverter.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.codehaus.jackson.map.ObjectMapper;

public class CurrencyController {
	
	private static final String URL = "https://api.fixer.io/latest?base=";
	private static final String[] CURRENCIES = { "AUD", "BGN", "BRL", "CAD", "CHF", "CNY", "CZK", "DKK", "EUR", "GBP", "HKD", "HRK",
			"HUF", "IDR", "ILS", "INR", "JPY", "KRW", "MXN", "MYR", "NOK", "NZD", "PHP", "PLN", "RON", "RUB", "SEK",
			"SGD", "THB", "TRY", "USD", "ZAR" };
	
	private ObjectMapper mapper = new ObjectMapper();

	public double convert(double amount, String sourceCurrency, String destinationCurrency) {
		String currencyJson = getCurrenciesJson(sourceCurrency);
		CurrencyResponseTO obj = null;
		try {
			obj = mapper.readValue(currencyJson, CurrencyResponseTO.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Double conversionRate = obj.getRates().get(destinationCurrency);

		return amount * conversionRate;
	}

	private String getCurrenciesJson(String sourceCurrency) {
		StringBuilder result = new StringBuilder();
		try {
			URL url = new URL(URL + sourceCurrency);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			rd.close();
		} catch (Throwable e) {
			return null;
		}
		return result.toString();
	}

	public String[] getCurrencies() {
		return CURRENCIES;
	}
}
