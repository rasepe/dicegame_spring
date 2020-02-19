package com.dicegame.application.domain;


import com.dicegame.application.domain.Player;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.beans.factory.annotation.Autowired;

import com.dicegame.application.persistence.GameRepository;

@Entity
@Table(name = "games")
public class Game {  //extends AuditModel
	// @Autowired
	 //   private GameRepository gameRepository;
	
	private static long COUNTER = 1;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    
    @NotNull
    private int dice1;
    
    @NotNull
    private int dice2;
    
    private boolean hasWon = false;

    private LocalDateTime createdAt;
    
    

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Player player;


    public Game(Player player)  {

    	this.id = COUNTER;
		COUNTER++;

    	this.player = player;
    	
    	
    	this.createdAt = LocalDateTime.now();
//    	this.dice1 = (@NotNull int) Math.floor(Math.random()*6+1);
//    	this.dice2 = (@NotNull int) Math.floor(Math.random()*6+1);
//    	
//    	if (this.dice1 + this.dice2 == 7) {
//    		this.hasWon = true;
//    	}
//    	double points = 0;
//    	double succesRate;
//    	List<Game> gamesByPlayer = new ArrayList<Game>();
//    	gamesByPlayer = gameRepository.findByPlayerId(player.getId(), null).getContent(); 
//    	for (int i=0; i<gamesByPlayer.size(); i++) {
//    		if(gamesByPlayer.get(i).isHasWon()) {
//    			points += 100;
//    		}
//    	}
//    	succesRate = points / gamesByPlayer.size();
//    	player.setSuccessRate(succesRate);
    }

	
	Game() {}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public int getDice1() {
		return dice1;
	}


	public void setDice1(int dice1) {
		this.dice1 = dice1;
	}


	public int getDice2() {
		return dice2;
	}


	public void setDice2(int dice2) {
		this.dice2 = dice2;
	}


	public boolean isHasWon() {
		return hasWon;
	}


	public void setHasWon(boolean hasWon) {
		this.hasWon = hasWon;
	}


	public Player getPlayer() {
		return player;
	}


	public void setPlayer(Player player) {
		this.player = player;
	}



    

    
}