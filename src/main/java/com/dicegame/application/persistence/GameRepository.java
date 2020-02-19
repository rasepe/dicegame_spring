package com.dicegame.application.persistence;



import com.dicegame.application.domain.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    Page<Game> findByPlayerId(Long playerId, Pageable pageable);
   // Optional<Game> findByIdAndPlayerId(Long id, Long shopId); <---ERROR
   // List<Picture> deleteByShopId(Long shopId);
}