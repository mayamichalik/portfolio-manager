package com.conygre.spring.boot.service;

import com.conygre.spring.boot.entities.Watchlist;
import com.conygre.spring.boot.repos.WatchlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class WatchlistServiceImpl implements WatchlistService{

    @Autowired
    private WatchlistRepository repo;

    @Autowired
    public void setRepo(WatchlistRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Watchlist> getWatchlist() {
        return repo.findAll();
    }

    @Override
    public void addToWatchlist(Watchlist watchlist) {
        repo.save(watchlist);
    }

    @Override
    public void removeFromWatchlist(String symbol) {
        repo.deleteById(symbol);
    }

    @Override
    public Optional<Watchlist> getWatchlistByid(String ticker) { return repo.findById(ticker); }

}
