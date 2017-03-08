


package test.viame.rest.templates;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.viame.app.config.AppConfig;
import com.viame.app.config.SecurityConfig;
import com.viame.app.json.ResponseJSON;
import com.viame.app.templates.REST.ANombreCentroIdREST;
import com.viame.app.templates.dao.INombreCentroIdDAO;
import com.viame.app.templates.model.AModelNombre;
import com.viame.app.templates.service.ANombreCentroIdService;

import test.viame.TestUtils;
import test.viame.config.TestDBConfig;

/**
 * Template para testear los maestros(prestaciones, parametros)
 * @author eduarmar
 *
 * @param <I> Model que se va a testear
 * @param <R> Rest del model que se va a testear
 * @param <D> Dao del model que se va a testear
 * @param <S> Service del model que se va a testear
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, TestDBConfig.class, SecurityConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class MantenimientosTemplateREST<I extends AModelNombre, D extends INombreCentroIdDAO<I>, S extends ANombreCentroIdService<D, I>, R extends ANombreCentroIdREST<S,I,D>> extends TestTemplateREST<I,D,S,R> {

	
	@Test
	public void nuevo() throws Exception {
		I nuevoItem=getNewModel(false);		
		nuevoItem.setNombre("TestItemNuevo");
				
	    mockMvc.perform(put("/"+getRestURL()).contentType(TestUtils.APPLICATION_JSON_UTF8)
                .content(TestUtils.convertObjectToJsonBytes(nuevoItem)))	    		
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.cod").value(ResponseJSON.OK))
		.andExpect(jsonPath("$.data.nombre").value("TestItemNuevo"))
		.andExpect(jsonPath("$.data.centro").value(model.getCentro().getId().toHexString()));

	    mockMvc.perform(put("/"+getRestURL()).contentType(TestUtils.APPLICATION_JSON_UTF8)
                .content(TestUtils.convertObjectToJsonBytes(nuevoItem)))	    		
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.cod").value(ResponseJSON.YA_EXISTE));
	}
	
	
	@Test
	public void getAll() throws Exception {

	    mockMvc.perform(MockMvcRequestBuilders.get("/"+getRestURL()+"/"+centro.getId().toHexString()+"/1/10"))
			.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$.data[0].id").value(model.getId().toHexString()));			
	}
	
	@Test
	public void getAllByCentro() throws Exception {

	    mockMvc.perform(MockMvcRequestBuilders.get("/"+getRestURL()+"/all/"+centro.getId().toHexString()))
			.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$.data[0].id").value(model.getId().toHexString()));			
	}

}
