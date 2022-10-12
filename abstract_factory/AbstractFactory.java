package practise.pattern.factory;

public class AbstractFactory {

	public static void main(String[] args) {
		
		Pizza pizza = null;
		PizzaStore nyPizzaStore = new NyPizzaStore();
		
		pizza = nyPizzaStore.orderPizza("cheese");
		System.out.println(pizza + "\n");
		
		PizzaStore chicagoPizzaStore = new ChicagoPizzaStore();
		pizza = chicagoPizzaStore.orderPizza("pepperoni");
		System.out.println(pizza + "\n");
	}
}

abstract class PizzaStore {
	
	Pizza orderPizza(String type) {
		
		Pizza pizza;
		pizza = createPizza(type);
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza;
	}
	
	protected abstract Pizza createPizza(String type);
}

class NyPizzaStore extends PizzaStore {

	@Override
	protected Pizza createPizza(String type) {
		Pizza pizza = null;
		PizzaIgredientFactory igredientFactory = new NYPizzaIgredientFactory();
		
		if(type.equals("cheese")) {
			pizza = new CheesePizza(igredientFactory);
			pizza.setName("New York Cheese Pizza");
		} else if(type.equals("pepperoni")) {
			pizza = new PepperoniPizza(igredientFactory);
			pizza.setName("New York Pepperoni Pizza");
		}
		return pizza;
	}
}

class ChicagoPizzaStore extends PizzaStore {

	@Override
	protected Pizza createPizza(String type) {
		Pizza pizza = null;
		PizzaIgredientFactory igredientFactory = new ChicagoPizzaIgredientFactory();
		
		if(type.equals("cheese")) {
			pizza = new CheesePizza(igredientFactory);
			pizza.setName("Chicago Cheese Pizza");
		} else if(type.equals("pepperoni")) {
			pizza = new PepperoniPizza(igredientFactory);
			pizza.setName("Chicago Pepperoni Pizza");
		}
		return pizza;
	}
}

abstract class Pizza {
	String name;
	
	Dough dough;
	Sauce sauce;
	Cheese cheese;
	Pepperoni pepperoni;
	
	abstract void prepare();
	
	void bake () { System.out.println("Bake: 25, Temp: 250"); }
	void cut () { System.out.println("Cut: diagonal slices"); }
	void box () { System.out.println("Place pizza in bok"); }
	
	void setName(String name) { this.name = name; }
	String getName() { return this.name; }

	@Override
	public String toString() {
		return "Pizza [name=" + name + ", dough=" + dough 
				+ ", sauce=" + sauce + ", cheese=" + cheese + "]";
	}
}

class CheesePizza extends Pizza {

	PizzaIgredientFactory igredientFactory;
	
	public CheesePizza(PizzaIgredientFactory igredientFactory) {
		this.igredientFactory = igredientFactory;
	}

	@Override
	void prepare() {
		System.out.println("Preparing " + name);
		dough = igredientFactory.createDough();
		sauce = igredientFactory.createSauce();
		cheese = igredientFactory.createCheese();
	}
}

class PepperoniPizza extends Pizza {

	PizzaIgredientFactory igredientFactory;
	
	public PepperoniPizza(PizzaIgredientFactory igredientFactory) {
		this.igredientFactory = igredientFactory;
	}

	@Override
	void prepare() {
		System.out.println("Preparing " + name);
		dough = igredientFactory.createDough();
		sauce = igredientFactory.createSauce();
		cheese = igredientFactory.createCheese();
		pepperoni = igredientFactory.createPepperoni();
	}
}


interface PizzaIgredientFactory {
	Dough createDough();
	Sauce createSauce();
	Cheese createCheese();
	Pepperoni createPepperoni();
}

class NYPizzaIgredientFactory implements PizzaIgredientFactory {

	@Override
	public Dough createDough() { return new ThinCrust(); }

	@Override
	public Sauce createSauce() { return new MarinaraSauce(); }

	@Override
	public Cheese createCheese() { return new Reggiano(); }

	@Override
	public Pepperoni createPepperoni() {
		return new ItalianPepperoni();
	}
}

class ChicagoPizzaIgredientFactory implements PizzaIgredientFactory {

	@Override
	public Dough createDough() { return new ThickCrust(); }

	@Override
	public Sauce createSauce() { return new PlumSauce(); }

	@Override
	public Cheese createCheese() { return new Mozzarella(); }

	@Override
	public Pepperoni createPepperoni() {
		return new GermanPepperoni();
	}
}

interface Cheese {}

class Mozzarella implements Cheese {
	@Override public String toString() { return "Mozzarella"; }
}
class Reggiano implements Cheese {
	@Override public String toString() { return "Reggiano"; }
}

interface Sauce {}

class PlumSauce implements Sauce {
	@Override public String toString() { return "PlumSauce"; }
}
class MarinaraSauce implements Sauce {
	@Override public String toString() { return "MarinaraSauce"; }
}

interface Dough {}

class ThickCrust implements Dough {
	@Override public String toString() { return "ThickCrust"; }
}
class ThinCrust implements Dough {
	@Override public String toString() { return "ThinCrust"; }
}

interface Pepperoni {}

class ItalianPepperoni implements Pepperoni {
	@Override public String toString() { return "ItalianPepperoni"; }
}
class GermanPepperoni implements Pepperoni {
	@Override public String toString() { return "GermanPepperoni"; }
}
