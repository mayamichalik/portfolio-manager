package com.conygre.spring.boot.repos;

import com.conygre.spring.boot.entities.InfoStocks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface InfoStocksRepository extends JpaRepository<InfoStocks, Integer> {

    @Query(nativeQuery = true,
            value ="SELECT * FROM info_stocks s WHERE s.ticker_symbol = :ticker_symbol ORDER BY s.date_time ASC LIMIT 1")
    InfoStocks min(@Param("ticker_symbol") String symbol);

    @Query(nativeQuery = true,
            value ="SELECT * FROM info_stocks s WHERE s.ticker_symbol = :ticker_symbol ORDER BY s.date_time DESC LIMIT 1")
    InfoStocks max(@Param("ticker_symbol") String symbol);

    @Query(nativeQuery = true,
            value ="SELECT * FROM info_stocks s WHERE s.ticker_symbol = :ticker_symbol && s.date_time >= :start && s.date_time <= :end ORDER BY s.date_time ASC")
    List<InfoStocks> findInInterval(@Param("ticker_symbol") String symbol, @Param("start") String start, @Param("end") String end);

    @Query(nativeQuery = true,
            value ="SELECT * FROM info_stocks s WHERE s.ticker_symbol = :ticker_symbol ORDER BY s.date_time ASC")
    List<InfoStocks> findBySymbol(@Param("ticker_symbol") String symbol);
}