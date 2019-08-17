package com.sda.projectcurrencygame.gui;

import com.sda.projectcurrencygame.controller.CurrencyClient;
import com.sda.projectcurrencygame.entity.CurrencyRank;
import com.sda.projectcurrencygame.repository.CurrencyRankRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

@Route("game")
public class CurrencyGui extends VerticalLayout {



    @Autowired
    public CurrencyGui(CurrencyClient currencyClient, CurrencyRankRepository currencyRankRepository) {

        AtomicInteger counter = new AtomicInteger();

        final Double eur = currencyClient.getCurrencyRates().getRates().getEUR();
        BigDecimal eurBig = BigDecimal.valueOf(1).divide(BigDecimal.valueOf(eur), 2, RoundingMode.CEILING);
        final BigDecimal currencyToGuess = eurBig.setScale(2, RoundingMode.CEILING);

        Label labelCurrencyFrom = new Label("Z waluty");
        labelCurrencyFrom.setText("EUR");
        Label labelCurrencyTo = new Label("Na walute");
        labelCurrencyTo.setText("PLN");

        HorizontalLayout hl1 = new HorizontalLayout();
        HorizontalLayout hl2 = new HorizontalLayout();

        TextField textFieldUserValue = new TextField("Podaj wartosc");
        Label labelResult = new Label("Wynik");
        Label labelFinalResult = new Label("Liczba podejsc");

        Button button = new Button("Click");

        button.addClickListener(click -> {
            BigDecimal userValue = new BigDecimal(textFieldUserValue.getValue());
            final int result = userValue.compareTo(currencyToGuess);
            String win = "Wartosci sa rowne";
            String toHigh = "Za duzo";
            String toLow = "Za malo";

            if (result == 0) {
                button.setEnabled(false);
                labelResult.setText(win);
                labelFinalResult.setText("GRATULACJE! udalo sie za " + counter.incrementAndGet() + " razem");
                add(new Image("https://media1.giphy.com/media/rypyVNU547qrC/source.gif", "GRATULACJE"));

                final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

                CurrencyRank currencyRank = new CurrencyRank(principal.toString(), counter.get(), LocalDate.now());

                currencyRankRepository.save(currencyRank);
            } else if (result >= 1) {
                labelResult.setText(toHigh);
                labelFinalResult.setText("Liczba podejsc: " + counter.incrementAndGet());
            } else {
                labelResult.setText(toLow);
                labelFinalResult.setText("Liczba podejsc: " + counter.incrementAndGet());
            }
        });

        hl1.add(labelCurrencyFrom, labelCurrencyTo);
        hl2.add(textFieldUserValue, button, labelResult);

        add(hl1, hl2, labelFinalResult);

    }
}
