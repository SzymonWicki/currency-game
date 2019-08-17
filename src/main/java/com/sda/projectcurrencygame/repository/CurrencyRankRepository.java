package com.sda.projectcurrencygame.repository;

import com.sda.projectcurrencygame.entity.CurrencyRank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRankRepository extends JpaRepository<CurrencyRank, Long> {
}
