package com.dicegame.application.domain;



import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "players")
public class Player { // extends AuditModel


	/**
	 * 
	 */
	private static long COUNTER = 1L;

	//private static long COUNTER = 1;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

   // @NotNull
   // @Size(max = 100)
    @Column(unique = true)
    private String name;
    
    private double successRate;

	private LocalDateTime createdAt;

    
	public Player(String name) {
		this.id = COUNTER;
		COUNTER++;
		this.name = name;
		this.createdAt = LocalDateTime.now();
	}
	
	public Player() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSuccessRate() {
		return successRate;
	}

	public void setSuccessRate(double successRate) {
		this.successRate = successRate;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}




    
  
}