package com.viame.app.components.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viame.app.dao.TokenDAO;

@Service
public class TokenService {
	@Autowired
	private TokenDAO dao;

	public TokenDAO getDAO() {
		return dao;
	}
}
