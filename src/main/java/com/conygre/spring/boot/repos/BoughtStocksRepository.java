package com.conygre.spring.boot.repos;

import com.conygre.spring.boot.entities.BoughtStocks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoughtStocksRepository extends JpaRepository<BoughtStocks, String> {
}
