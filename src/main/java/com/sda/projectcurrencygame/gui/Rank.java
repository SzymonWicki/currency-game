package com.sda.projectcurrencygame.gui;

import com.sda.projectcurrencygame.entity.CurrencyRank;
import com.sda.projectcurrencygame.repository.CurrencyRankRepository;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("rank")
public class Rank extends VerticalLayout {

    @Autowired
    public Rank(CurrencyRankRepository currencyRankRepository) {

        Grid<CurrencyRank> grid = new Grid<>(CurrencyRank.class);

        grid.setItems(currencyRankRepository.findAll());

        add(grid);
    }
}
