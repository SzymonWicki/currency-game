package com.sda.projectcurrencygame.controller;

import com.sda.projectcurrencygame.model.CurrencyModel;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@Controller
public class CurrencyClient {

    public CurrencyModel getCurrencyRates() {
        RestTemplate restTemplate = new RestTemplate();
        final CurrencyModel currencyModel = restTemplate.getForObject("https://api.exchangeratesapi.io/latest?base=PLN", CurrencyModel.class);

        return currencyModel;
    }

    @Override
    public String toString() {
        return "CurrencyClient{}";
    }
}
