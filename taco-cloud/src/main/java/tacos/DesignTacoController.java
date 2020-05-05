package tacos;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import tacos.Taco;
import tacos.Order;
import tacos.Ingredient;
import tacos.Ingredient.Type;

/*Its a lombok annotation that at runtime will automatically 
 * generate an slf4j (Simple Logging Facade for Java) Logger
 * in the class.
 */
@Slf4j
@Controller //this identifies the class as controller
@RequestMapping("/design") //It means this class will handle requests that begins with /design
public class DesignTacoController {

	@GetMapping // This means that when a HTTP GET request is received for /design, the below method will handle the request
	public String showDesignForm(Model model) {
		//Model is an object that ferries data between a controller and whatever view is charged with rendering the data
		//List of ingredients
		List<Ingredient> ingredients = Arrays.asList(
				new Ingredient("FLTO","Flour Tortilla",Type.WRAP),
				new Ingredient("COTO","Corn Tortilla",Type.WRAP),
				new Ingredient("GRBF","Ground Beef",Type.PROTEIN),
				new Ingredient("CARN","Carnitas",Type.PROTEIN),
				new Ingredient("TMTO","Diced Tomatos",Type.VEGGIES),
				new Ingredient("LETC","Lettuce",Type.VEGGIES),
				new Ingredient("CHED","Cheddar",Type.CHEESE),
				new Ingredient("JACK","Monterrey Jack",Type.CHEESE),
				new Ingredient("SLSA","Salsa",Type.SAUCE),
				new Ingredient("SRCR","Sour Cream",Type.SAUCE)
				);
		
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(),filterByType(ingredients,type));
		}
		
		model.addAttribute("design", new Taco());
		return "design";
	}
	
	//Check this method later on
	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		return ingredients
				.stream()
				.filter(x -> x.getType().equals(type))
				.collect(Collectors.toList());
	}
	
	@PostMapping
	public String processDesign(Taco design) {
		log.info("Processing Design: " + design);
		
		return "redirect:/orders/current";
	}

}
