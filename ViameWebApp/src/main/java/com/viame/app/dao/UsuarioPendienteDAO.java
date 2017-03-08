package com.viame.app.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.viame.app.components.models.core.Centro;
import com.viame.app.components.models.core.UsuarioPendiente;
import com.viame.app.templates.dao.IBasicIdDAO;



@Repository
public interface UsuarioPendienteDAO extends IBasicIdDAO<UsuarioPendiente>{
	public UsuarioPendiente findByCorreo(String correo);
	public Page<UsuarioPendiente> findByCentro(Centro centro, Pageable pageable);
	public List<UsuarioPendiente> findByCentro(Centro centro);
}
