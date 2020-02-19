package com.dicegame.application.controller;



import com.dicegame.application.exception.ResourceNotFoundException;
import com.dicegame.application.domain.Player;
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

	
    @GetMapping("/players")
    public Page<Player> getAllPlayers(Pageable pageable) {
        return playerRepository.findAll(pageable);
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
    	player.setCreatedAt(LocalDateTime.now());
        return playerRepository.save(player);
    }


//    @PutMapping("/shops/{shopId}")
//    public Shop updateShop(@PathVariable Long shopId, @Valid @RequestBody Shop shopRequest) {
//        return shopRepository.findById(shopId).map(shop -> {
//            shop.setName(shopRequest.getName());
//            shop.setMaxPictures(shopRequest.getMaxPictures());
//            return shopRepository.save(shop);
//        }).orElseThrow(() -> new ResourceNotFoundException("ShopId " + shopId + " not found"));
//    }
//
//
//    @DeleteMapping("/shops/{shopId}")
//    public ResponseEntity<?> deleteShop(@PathVariable Long shopId) {
//        return shopRepository.findById(shopId).map(shop -> {
//            shopRepository.delete(shop);
//            return ResponseEntity.ok().build();
//        }).orElseThrow(() -> new ResourceNotFoundException("ShopId " + shopId + " not found"));
//    }

}