package com.viame.app.components.services;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viame.app.components.models.core.Centro;
import com.viame.app.components.models.core.UsuarioPendiente;
import com.viame.app.dao.CentroDAO;
import com.viame.app.exceptions.ExceptionREST;
import com.viame.app.json.ResponseJSON;
import com.viame.app.json.request.NuevoCentroJSON;
import com.viame.app.templates.service.ABasicService;

@Service
public class CentroService extends  ABasicService<CentroDAO>{	
	final static Logger log = LogManager.getLogger(CentroService.class);
	
	@Autowired
	private UsuarioService usuarios;
	@Autowired
	private ConfigCentroService config;

	public Centro nuevoCentro(NuevoCentroJSON nuevoCentro)throws ExceptionREST{
		
		log.debug("Creando centro con nombre "+nuevoCentro.centro.getNombre()+" para el usuario "+nuevoCentro.centro.getCorreoAdmin());
		
		if(usuarios.getUsuarioPendienteByCorreo(nuevoCentro.centro.getCorreoAdmin())==null){

			Centro centro=getDAO().save(nuevoCentro.centro);
			
			config.iniciarSequenciasCentro(centro);
			
			UsuarioPendiente usuario=usuarios.getUsuarioPendienteByCorreo(centro.getCorreoAdmin());
			
			if(usuario==null){
				usuario=new UsuarioPendiente();
				usuario.setCorreo(centro.getCorreoAdmin());
				usuario.setNombre(nuevoCentro.nombreAdmin);
				usuario.setFechaEnvio(new Date());
				usuario.setCentro(centro);
				
				usuarios.getPendientesDAO().save(usuario);
			}
			
			usuarios.enviarEmail(usuario);
			
			return centro;
		}
		
		log.error("Ya existe un usuario con el correo "+nuevoCentro.centro.getCorreoAdmin());
		throw new ExceptionREST(ResponseJSON.YA_EXISTE, "Ya existe usuario con el correo "+nuevoCentro.centro.getCorreoAdmin());
	}
}
