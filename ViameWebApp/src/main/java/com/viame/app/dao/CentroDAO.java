package com.viame.app.dao;

import org.springframework.stereotype.Repository;

import com.viame.app.components.models.core.Centro;
import com.viame.app.templates.dao.IBasicIdDAO;

@Repository
public interface CentroDAO extends IBasicIdDAO<Centro>{
	public Centro findByCorreoAdmin(String correo);
}
