package practice.pattern.singleton;

public class SingletonExamples {

	public static void main(String[] args) {
		OldSigleton.getInstance().getMessage();
		Sigleton.getInstance().getMessage();
		SigletonEnum.INSTANCE.getMessage();
	}
}

class OldSigleton {
	private static OldSigleton instance = null;

	private OldSigleton() {}

	public static OldSigleton getInstance() {
		if (instance == null) {
			instance = new OldSigleton();
		}
		return instance;
	}
	public void getMessage() {
		System.out.println("Old Singleton");
	}
}

class Sigleton {
	
	private static final Sigleton INSTANCE = new Sigleton();
	private Sigleton() {}
	public static Sigleton getInstance() {
		return INSTANCE;
	}
	public void getMessage() {
		System.out.println("Singleton");
	}
}

enum SigletonEnum {
	INSTANCE;
	public void getMessage() {
		System.out.println("Enum Singleton");
	}
}