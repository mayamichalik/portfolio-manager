package com.conygre.spring.boot.repos;

import com.conygre.spring.boot.entities.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WatchlistRepository extends JpaRepository<Watchlist, String> {

//    Optional<Watchlist> findById(Integer Id);
}
