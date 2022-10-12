package practise.pattern.builder;

public class Application {

	public static void main(String[] args) {
		
		
		
		NutritionFacts cocaCola = new NutritionFacts.Builder(240, 8)
				.calories(100).sodium(35).carbohydrate(27).build();
		
		NyPizza pizza = new NyPizza.Builder(NyPizza.Size.SMALL)
				.addTopping(Pizza.Topping.SAUSAGE)
				.addTopping(Pizza.Topping.ONION).build();
		
		Calzone calzone = new Calzone.Builder()
				.addTopping(Pizza.Topping.HAM)
				.sauceInside().build();
		
	}
}
