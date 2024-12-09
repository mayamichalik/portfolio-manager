package com.conygre.spring.boot.repos;

import com.conygre.spring.boot.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, String> {
}
