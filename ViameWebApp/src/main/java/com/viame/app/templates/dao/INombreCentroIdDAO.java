package com.viame.app.templates.dao;

import com.viame.app.components.models.core.Centro;
import com.viame.app.templates.model.AModelNombre;

public interface INombreCentroIdDAO<I extends AModelNombre> extends ICentroIdDAO<I>{
	public I findByCentroAndNombre(Centro centro, String nombre);
}
