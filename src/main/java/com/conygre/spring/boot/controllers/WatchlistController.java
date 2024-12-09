package com.conygre.spring.boot.controllers;


import com.conygre.spring.boot.entities.Watchlist;
import com.conygre.spring.boot.service.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/watchlist")
public class WatchlistController {

    @Autowired
    private WatchlistService service;


    @RequestMapping(method = RequestMethod.GET)
    public List<Watchlist> findAll() {
        return (service).getWatchlist();
    }

    @GetMapping(value = "/{ticker}")
    public Optional<Watchlist> findById(@PathVariable("ticker")String ticker) {
        return (service).getWatchlistByid(ticker);
    }


    @DeleteMapping(value = "/{ticker}")
    public void removeFromWatchlist(@PathVariable("ticker") String ticker) {
        service.removeFromWatchlist(ticker);
    }

    @PostMapping()
    public void addToWatchlist(@RequestBody Watchlist watchlist) {
        service.addToWatchlist(watchlist);
    }

}
