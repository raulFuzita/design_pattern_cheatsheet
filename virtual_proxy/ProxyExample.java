package practise.pattern.virtualproxy;

public class ProxyExample {

	public static void main(String[] args) throws InterruptedException {
		
		String url = "http://virtual-proxy.com";
		ImageProxy imageProxy = new ImageProxy(url);
		
		// A message is displayed till image is loaded.
		while(!imageProxy.paintIcon()) {
			Thread.sleep(1000);
		}
	}
}

interface Image {
	boolean paintIcon();
}


class MockedImageIcon implements Image {

	public MockedImageIcon(String location, String description) {
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean paintIcon() {
		System.out.println("\n ʕ·͡ᴥ·ʔ ");
		return true;
	}
}


class ImageProxy implements Image {
	
	volatile MockedImageIcon mockedImageIcon;
	final String imageURL;
	Thread retrievalThead;
	boolean retrieving = false;
	
	public ImageProxy(String imageURL) {
		this.imageURL = imageURL;
	}

	synchronized void setImageIcon(MockedImageIcon mockedImageIcon) {
		this.mockedImageIcon = mockedImageIcon;
	}

	@Override
	public boolean paintIcon() {
		if (mockedImageIcon != null) {
			return mockedImageIcon.paintIcon();
		} else {
			System.out.println("Loading image, please wait...");
			if (!retrieving) {
				retrieving = true;
				
				retrievalThead = new Thread(new Runnable() {
					@Override
					public void run() {
						setImageIcon(new MockedImageIcon(imageURL, "Koala"));
					}
				});
				retrievalThead.start();
			}
		}
		return false;
	}
}



