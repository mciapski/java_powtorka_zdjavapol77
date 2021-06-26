package com.expenses.currency;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class OnlineCurrencyService implements CurrencyService {
  public static void main(String[] args) throws IOException, InterruptedException {
    OnlineCurrencyService currencyService = new OnlineCurrencyService();
    BigDecimal dziesiecDolarow = currencyService.convertToPln(BigDecimal.TEN, Currency.US_DOLLAR);
    System.out.println(dziesiecDolarow);

  }

  @Override
  public BigDecimal convertToPln(BigDecimal amount, Currency currency) throws IOException, InterruptedException {
    String url = "http://api.nbp.pl/api/exchangerates/rates/A/USD/2021-06-24?format=json";

    HttpClient httpClient = HttpClient.newHttpClient();

    HttpRequest httpRequest = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .build();

    HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    System.out.println(response.body());

    // Dostajemy się do obiektu którzy zawiera naszą tabelę "rates"
    JSONObject object = new JSONObject(response.body());
    //String table = object.getString("table");
    //Dostajemy się do tabeli "rates"
    JSONArray rates = object.getJSONArray("rates");
    // Dostajemy się do jej '0' elementu, który tez jest tabelą
    JSONObject rateJson = rates.getJSONObject(0);
    // Pobieramy wartość dla "mid"
    BigDecimal rate = rateJson.getBigDecimal("mid");
    System.out.println(rate);
    //Przeliczenie i zaokrąglenie
    return amount.multiply(rate).setScale(2, RoundingMode.HALF_UP);
  }
}
