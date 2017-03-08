package com.viame.app.templates.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.viame.app.templates.dao.IBasicIdDAO;

public class ABasicService<T extends IBasicIdDAO>{
	@Autowired
	protected T dao;
	

	public T getDAO(){
		return dao;
	}
}
