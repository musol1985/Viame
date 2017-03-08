package test.viame.rest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.viame.app.components.models.core.Centro;
import com.viame.app.components.rest.CentroREST;
import com.viame.app.components.services.CentroService;
import com.viame.app.config.AppConfig;
import com.viame.app.config.SecurityConfig;
import com.viame.app.dao.CentroDAO;
import com.viame.app.json.ResponseJSON;
import com.viame.app.json.request.NuevoCentroJSON;

import test.viame.TestUtils;
import test.viame.config.TestDBConfig;
import test.viame.rest.templates.TestTemplateREST;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, TestDBConfig.class, SecurityConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCentros extends TestTemplateREST<Centro, CentroDAO, CentroService, CentroREST>{


	@After
	public void  drop(){
		usuarios.getDAO().deleteAll();
		centros.getDAO().deleteAll();
		usuarios.getPendientesDAO().deleteAll();
	}
	
	
	
	@Test
	public void nuevo() throws Exception {
		Centro nuevoItem=TestUtils.getNewCentro();
		nuevoItem.setNombre("NuevoCentro");

		NuevoCentroJSON req=new NuevoCentroJSON();
		req.centro=nuevoItem;
		req.centro.setCorreoAdmin("testNuevo@gmail.com");
		req.nombreAdmin="Juancho";
				
	    mockMvc.perform(MockMvcRequestBuilders.put("/"+getRestURL()+"/nuevo").contentType(TestUtils.APPLICATION_JSON_UTF8)
                .content(TestUtils.convertObjectToJsonBytes(req)))	    		
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.cod").value(ResponseJSON.OK))
		.andExpect(jsonPath("$.data.nombre").value("NuevoCentro"));

	    mockMvc.perform(MockMvcRequestBuilders.put("/"+getRestURL()+"/nuevo").contentType(TestUtils.APPLICATION_JSON_UTF8)
                .content(TestUtils.convertObjectToJsonBytes(req)))	    		
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.cod").value(ResponseJSON.YA_EXISTE));
	}
	
	@Test
	public void getAll() throws Exception {

	    mockMvc.perform(MockMvcRequestBuilders.get("/centro/all"))
			.andExpect(status().isOk())
			.andDo(print())
			.andExpect(jsonPath("$.data[0].id").value(centro.getId().toHexString()));
	}
	
	
	@Override
	@Test
	public void delete() throws Exception {		
	    mockMvc.perform(MockMvcRequestBuilders.delete("/"+getRestURL()+"/"+model.getId().toHexString()))
		.andDo(print())
		.andExpect(status().isOk())
	    .andExpect(jsonPath("$.cod").value(ResponseJSON.ACCION_PROHIBIDA_REST));
	}



	@Override
	public Centro getModel() {
		return TestUtils.getNewCentro();
	}



	@Override
	public String getRestURL() {
		return "centro";
	}



	@Override
	public Centro getModelTestModificar(Centro modelAModificar) {		
		try{
			System.out.println(TestUtils.convertObjectToString(modelAModificar));
			//modelAModificar.setUbicacion(null);
			modelAModificar.setNombre("CentroCambiado");
			System.out.println("**********************");
			System.out.println(TestUtils.convertObjectToString(modelAModificar));
		}catch(Exception e){
			e.printStackTrace();
		}
		return modelAModificar;
	}



	@Override
	public void postTestModificar(ResultActions res) throws Exception {
		res.andExpect(jsonPath("$.data.nombre").value("CentroCambiado"));
	}
	
}
