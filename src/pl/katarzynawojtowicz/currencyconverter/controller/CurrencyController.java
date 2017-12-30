package pl.katarzynawojtowicz.currencyconverter.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.codehaus.jackson.map.ObjectMapper;

public class CurrencyController {
	private ObjectMapper mapper = new ObjectMapper();
	private String[] currencies = { "AUD", "BGN", "BRL", "CAD", "CHF", "CNY", "CZK", "DKK", "EUR", "GBP", "HKD", "HRK",
			"HUF", "IDR", "ILS", "INR", "JPY", "KRW", "MXN", "MYR", "NOK", "NZD", "PHP", "PLN", "RON", "RUB", "SEK",
			"SGD", "THB", "TRY", "USD", "ZAR" };

	public double convert(double amount, String sourceCurrency, String destinationCurrency) {

		StringBuilder result = new StringBuilder();
		try {
			URL url = new URL("https://api.fixer.io/latest?base=" + sourceCurrency);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			rd.close();
		} catch (Throwable e) {
			return 0;
		}
		String currencyJson = result.toString();
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

	public String[] getCurrencies() {
		return currencies;
	}
}
