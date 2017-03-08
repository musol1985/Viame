package com.viame.app.templates.service;

import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.viame.app.components.models.core.Centro;
import com.viame.app.templates.dao.ICentroIdDAO;
import com.viame.app.templates.model.AModelCentro;

@Service
public class ACentroIdService<D extends ICentroIdDAO<I>, I extends AModelCentro> extends ABasicService<D>{

	public Page<I> getByCentro(String centro, Pageable page){
		Centro c=new Centro();
		c.setId(new ObjectId(centro));
		return dao.findByCentro(c, page);
	}
	
	/*public I getByCentroAndNombre(String centro, String nombre){
		Centro c=new Centro();
		c.setId(new ObjectId(centro));
		return dao.findByCentroAndNombre(c, nombre);
	}*/
	

}
