package com.dicegame.application.controller;


import com.dicegame.application.exception.ResourceNotFoundException;
import com.dicegame.application.domain.Game;
import com.dicegame.application.domain.Player;
import com.dicegame.application.persistence.GameRepository;
import com.dicegame.application.persistence.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
public class GameController {

	@Autowired
	private GameRepository gameRepository;

	@Autowired
	private PlayerRepository playerRepository;

	@CrossOrigin(origins = "http://localhost")
	@GetMapping("/players/{playerId}/games")
	public Page<Game> getAllGamesByPlayerId(@PathVariable (value = "playerId") Long playerId,
			Pageable pageable) {
		return gameRepository.findByPlayerId(playerId, null);  //pageable
	}

	@CrossOrigin(origins = "http://localhost")
	@PostMapping("/players/{playerId}/games")
	public Game createGame(@PathVariable (value = "playerId") Long playerId, Game game)  { //@RequestBody Game game

		return playerRepository.findById(playerId).map(player -> {
			game.setPlayer(player);

			int dice1 = (@NotNull int) Math.floor(Math.random()*6+1);
			int dice2 = (@NotNull int) Math.floor(Math.random()*6+1);
			game.setDice1(dice1);
			game.setDice2(dice2);

			if (game.getDice1() + game.getDice2() == 7) {
				game.setHasWon(true); 
			}



			double points = 0;
			double successRate;
			List<Game> gamesByPlayer = new ArrayList<Game>();
			gamesByPlayer = gameRepository.findByPlayerId(player.getId(), null).getContent(); 

			//suma els jocs anteriors
			for (int i=0; i<gamesByPlayer.size(); i++) {
				if(gamesByPlayer.get(i).isHasWon()) {
					points += 100;
				}
			}

			//i falta el joc actual

			if (game.isHasWon()) {
				points += 100;
			}

			successRate = points / (gamesByPlayer.size()+1);

			// 	game.getPlayer().setSuccessRate(successRate);
			player.setSuccessRate(successRate);

			// if (game.getAuthor()=="") {
			//  picture.setAuthor("ANONYMOUS");
			//  };
			//  shop.setNumPictures(shop.getNumPictures()+1);
			player.setHasGames(true); 

			//return gameRepository.save(game);
			gameRepository.save(game);
			return game;
		}).orElseThrow(() -> new ResourceNotFoundException("PlayerId " + playerId + " not found"));


	}


	//	@CrossOrigin(origins = "http://localhost")
	//    @PutMapping("/shops/{shopId}/pictures/{pictureId}")
	//    public Picture updatePicture(@PathVariable (value = "shopId") Long shopId,
	//                                 @PathVariable (value = "pictureId") Long pictureId,
	//                                 @Valid @RequestBody Picture pictureRequest) {
	//        if(!shopRepository.existsById(shopId)) {
	//            throw new ResourceNotFoundException("ShopId " + shopId + " not found");
	//        }
	//
	//        return pictureRepository.findById(pictureId).map(picture -> {
	//        	picture.setAuthor(pictureRequest.getAuthor());
	//            picture.setName(pictureRequest.getName());
	//            picture.setPrice(pictureRequest.getPrice());
	//            return pictureRepository.save(picture);
	//        }).orElseThrow(() -> new ResourceNotFoundException("PictureId " + pictureId + "not found"));
	//    }



	@CrossOrigin(origins = "http://localhost")
	@DeleteMapping("/players/{playerId}/games")
	public void deleteAllGamesByPlayerId(@PathVariable (value = "playerId") Long playerId) {

		gameRepository.deleteAll(gameRepository.findByPlayerId(playerId, null));
		//POSAR RATE A NULL:
		Player player = playerRepository.getOne(playerId);
		player.setSuccessRate(0);
		playerRepository.save(player);
	}



}