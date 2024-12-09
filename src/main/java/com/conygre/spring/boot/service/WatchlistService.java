package com.conygre.spring.boot.service;



import com.conygre.spring.boot.entities.Watchlist;

import java.util.List;
import java.util.Optional;

public interface WatchlistService {
    List<Watchlist> getWatchlist();
    void addToWatchlist(Watchlist watchlist);
    void removeFromWatchlist(String symbol);
    Optional<Watchlist> getWatchlistByid(String ticker);
}
