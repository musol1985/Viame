package com.viame.app.templates.service;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.viame.app.components.models.core.Centro;
import com.viame.app.templates.dao.INombreCentroIdDAO;
import com.viame.app.templates.model.AModelNombre;

@Service
public class ANombreCentroIdService<D extends INombreCentroIdDAO<I>, I extends AModelNombre> extends ACentroIdService<D, I>{
	
	public I getByCentroAndNombre(String centro, String nombre){
		Centro c=new Centro();
		c.setId(new ObjectId(centro));
		return getDAO().findByCentroAndNombre(c, nombre);
	}
	
}
