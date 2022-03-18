package com.twitter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twitter.model.Token;

public interface TokenRepository extends JpaRepository<Token, String>{

}
