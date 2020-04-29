package tacos;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(HomeController.class)//This arranges for the test to be run in the context of spring MVC
public class HomeControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	//This defines the test you want to perform against the homepage
	@Test
	public void testHomePage() throws Exception{
		mockMvc.perform(get("/"))
		
			.andExpect(status().isOk())//The response should have HTTP 200 status(OK)
			
			.andExpect(view().name("home"))//The view should have a logical name of home
			
			.andExpect(content().string(containsString("Welcome to...")));//The rendered view should contain this text
	}

}
