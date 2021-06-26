package com.expenses.currency;

import com.expenses.currency.Currency;

import java.io.IOException;
import java.math.BigDecimal;

public interface CurrencyService {
  BigDecimal convertToPln(BigDecimal amount, Currency currency) throws IOException, InterruptedException;
}
