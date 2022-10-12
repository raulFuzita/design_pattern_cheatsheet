package practice.pattern.bridge;

import java.util.Optional;

public class BridgeExample {

	public static void main(String[] args) {
		
		PosProcessor compactor = new Compactor();
		PosProcessor encryptor = new Encryptor();
		
		GenerateFile generateXML = new GenerateXML();
		GenerateFile generateProperty = new GenerateProperties();
		GenerateFile plainXML = new GenerateXML();
		
		generateXML.setProcessor(compactor);
		generateProperty.setProcessor(encryptor);
		
		generateXML.generateContent();
		generateXML.generateFile();
		
		generateProperty.generateContent();
		generateProperty.generateFile();
		
		plainXML.generateContent();
		plainXML.generateFile();
		
	}
}

abstract class GenerateFile {
	
	private PosProcessor processor;
	
	public void setProcessor(PosProcessor processor) {
		this.processor = processor;
	}
	
	public final void generateFile() {
		Optional<PosProcessor> optional = Optional.ofNullable(processor);
		(optional.orElse(new NullProcessor())).process();
		System.out.println("File has been generated!");
	}
	
	abstract void generateContent();
}

class GenerateXML extends GenerateFile {
	@Override
	void generateContent() {
		System.out.println("Returning a XML");
	}
}

class GenerateProperties extends GenerateFile {
	@Override
	void generateContent() {
		System.out.println("Returning a Property File");
	}
}

interface PosProcessor {
	public void process();
}

class Compactor implements PosProcessor {
	@Override
	public void process() {
		System.out.println("Compacting file...");
	}
}

class Encryptor implements PosProcessor {
	@Override
	public void process() {
		System.out.println("Encrypting file...");
	}
}

class NullProcessor implements PosProcessor {
	@Override
	public void process() {
		System.out.println("Plain file...");
	}
}


