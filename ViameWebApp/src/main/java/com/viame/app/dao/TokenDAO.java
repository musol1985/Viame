package com.viame.app.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.viame.app.components.models.core.Token;

@Repository
public interface TokenDAO extends MongoRepository<Token, String>{
	public Token findBySeries(String series);
}
