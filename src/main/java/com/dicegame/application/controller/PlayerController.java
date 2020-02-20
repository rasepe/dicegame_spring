package com.dicegame.application.controller;



import com.dicegame.application.exception.ResourceNotFoundException;
import com.dicegame.application.domain.Player;
import com.dicegame.application.persistence.GameRepository;
import com.dicegame.application.persistence.PlayerRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost")
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;
    
    @Autowired
    private GameRepository gameRepository;

	
    @GetMapping("/players")
    public Page<Player> getAllPlayers(Pageable pageable) {
        return playerRepository.findAll(pageable);
    }
    
    
    @GetMapping("/players/ranking")
    public double getAllPlayersRanking(Pageable pageable) {
       // return playerRepository.findAll(pageable);
        
       // playerRepository.findAll(pageable).getNumberOfElements();
       // playerRepository.findAll(pageable).getContent();
        
        double addition = 0;
        
        for (int i=0; i<gameRepository.findAll(pageable).getNumberOfElements(); i++) {
        	if (gameRepository.findAll(pageable).getContent().get(i).isHasWon()) {
        		addition +=100;
        	}
        	
        }
        
        
        double average = addition / gameRepository.findAll(pageable).getNumberOfElements();
        
        return average;
        
    }
	
    
    
    @GetMapping("/players/ranking/loser")
    public Player getLoserRanking(Pageable pageable) {
       // return playerRepository.findAll(pageable);
        
        playerRepository.findAll(pageable).getNumberOfElements();
        playerRepository.findAll(pageable).getContent();
        
        double playeraverage = 0;
        double minimum = 1000;
       // long loserId;
        Player loser = null;
        
        for (int i=0; i<playerRepository.findAll(pageable).getNumberOfElements(); i++) {
//        	if (gameRepository.findAll(pageable).getContent().get(i).isHasWon()) {
//        		addition +=100;
//        	}
        	
        	if (playerRepository.findAll(pageable).getContent().get(i).isHasGames()) {
        		playeraverage = playerRepository.findAll(pageable).getContent().get(i).getSuccessRate();
        		if (minimum > playeraverage) {
            		minimum=playeraverage;
            		//loserId = playerRepository.findAll(pageable).getContent().get(i).getId();
            		loser = playerRepository.findAll(pageable).getContent().get(i);
            		}
        		
        	}
        		
        	
        }
        
        
       // double average = addition / gameRepository.findAll(pageable).getNumberOfElements();
        
        return loser;
        
    }
    
    
    
    @GetMapping("/players/ranking/winner")
    public Player getWinnerRanking(Pageable pageable) {
       // return playerRepository.findAll(pageable);
        
        playerRepository.findAll(pageable).getNumberOfElements();
        playerRepository.findAll(pageable).getContent();
        
        double playeraverage = 0;
        double maximum = 0;
       // long loserId;
        Player winner = null;
        
        for (int i=0; i<playerRepository.findAll(pageable).getNumberOfElements(); i++) {
//        	if (gameRepository.findAll(pageable).getContent().get(i).isHasWon()) {
//        		addition +=100;
//        	}
        	
        	if (playerRepository.findAll(pageable).getContent().get(i).isHasGames()) {
        		playeraverage = playerRepository.findAll(pageable).getContent().get(i).getSuccessRate();
        		if (maximum < playeraverage) {
            		maximum=playeraverage;
            		//loserId = playerRepository.findAll(pageable).getContent().get(i).getId();
            		winner = playerRepository.findAll(pageable).getContent().get(i);
            		}
        		
        	}
        		
        	
        }
        
        
       // double average = addition / gameRepository.findAll(pageable).getNumberOfElements();
        
        return winner;
        
    }
	
//	// Single item
//	@GetMapping("/shops/{shopId}")
//	Shop one(@PathVariable Long shopId) {
//		
//		return shopRepository.findById(shopId)
//			.orElseThrow(() -> new ResourceNotFoundException("ShopId " + shopId + " not found"));
//	}
//	
	
    @PostMapping("/players")
    public Player createPlayer(@RequestBody Player player) {
    	  
             // player.setName("ANONYMOUS");
              //}; }
    	player.setCreatedAt(LocalDateTime.now());
    	  if (player.getName()=="") {
              player.setName(null);
              };
        return playerRepository.save(player);
    	 
    	 
    }

//    @CrossOrigin(origins = "http://localhost")
//    @PutMapping("/players/{playerId}")
//   public Player updatePlayer(@PathVariable Long playerId, Player playerRequest) {  //@Valid @RequestBody
//        return playerRepository.findById(playerId).map(player -> {
//           player.setName(playerRequest.getName());
//            return playerRepository.save(player);
//        }).orElseThrow(() -> new ResourceNotFoundException("PlayerId " + playerId + " not found"));
//  }
    
	
	  @CrossOrigin(origins = "http://localhost")
	  @PutMapping("/players/{id}") Player replacePlayer(@RequestBody Player
	  newPlayer, @PathVariable Long id) {
	  
	  return playerRepository.findById(id) .map(player -> {
	  player.setName(newPlayer.getName()); return playerRepository.save(player); })
	  .orElseGet(() -> { newPlayer.setId(id); return
	  playerRepository.save(newPlayer); }); }
	 
    
    

//
//    @DeleteMapping("/shops/{shopId}")
//    public ResponseEntity<?> deleteShop(@PathVariable Long shopId) {
//        return shopRepository.findById(shopId).map(shop -> {
//            shopRepository.delete(shop);
//            return ResponseEntity.ok().build();
//        }).orElseThrow(() -> new ResourceNotFoundException("ShopId " + shopId + " not found"));
//    }

}