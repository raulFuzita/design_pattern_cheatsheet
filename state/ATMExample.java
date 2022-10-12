package practise.pattern.state;

public class ATMExample {

	public static void main(String[] args) {
		ATMMachine atmMachine = new ATMMachine();
		atmMachine.insertCard();
		atmMachine.ejectCard();
		atmMachine.insertCard();
		atmMachine.insertPin(1234);
		atmMachine.requestCash(2000);
		atmMachine.insertCard();
		atmMachine.insertPin(1234);
	}
}

interface ATMState {
	void insertCard();
	void ejectCard();
	void insertPin(int pinEntered);
	void requestCash(int cashToWithdraw);
}

class ATMMachine {
	ATMState hasCard;
	ATMState noCard;
	ATMState hasCorrectPin;
	ATMState atmOutOfMoney;
	ATMState atmState;
	
	int cashInMachine = 2000;
	boolean correctPinEntered = false;
	
	public ATMMachine() {
		
		hasCard = new HasCard(this);
		noCard = new NoCard(this);
		hasCorrectPin = new HasPin(this);
		atmOutOfMoney = new NoCash(this);
		
		atmState = noCard;
		
		if (cashInMachine < 0) {
			atmState = atmOutOfMoney;
		}
	}
	
	void setATMState(ATMState newATMState) {
		atmState = newATMState;
	}
	
	public void setCashInMachine(int newCashInMachine) {
		cashInMachine = newCashInMachine;
	}
	
	public void insertCard() {
		atmState.insertCard();
	}
	
	public void ejectCard() {
		atmState.ejectCard();
	}
	
	public void requestCash(int cashToWithdraw) {
		atmState.requestCash(cashToWithdraw);
	}
	
	public void insertPin(int pinEntered) {
		atmState.insertPin(pinEntered);
	}
	
	public ATMState getYesCardState() { return hasCard; }
	public ATMState getNoCardState() { return noCard; }
	public ATMState getHasPin() { return hasCorrectPin; }
	public ATMState getNoCashSatate() { return atmOutOfMoney; }
}

class HasCard implements ATMState {
	
	ATMMachine atmMachine;
	
	public HasCard(ATMMachine newATMMachine) {
		atmMachine = newATMMachine;
	}

	@Override
	public void insertCard() {
		System.out.println("You can't enter more than one card");
	}

	@Override
	public void ejectCard() {
		System.out.println("Card Ejected");
		atmMachine.setATMState(atmMachine.getNoCardState());
	}

	@Override
	public void insertPin(int pinEntered) {
		if (pinEntered == 1234) {
			System.out.println("Correct Pin");
			atmMachine.correctPinEntered = true;
			atmMachine.setATMState(atmMachine.getHasPin());
		} else {
			System.out.println("Wrong Pin");
			atmMachine.correctPinEntered = false;
			ejectCard();
		}
	}

	@Override
	public void requestCash(int cashToWithdraw) {
		System.out.println("Enter PIN First");
	}
	
}

class NoCard implements ATMState {
	
	ATMMachine atmMachine;
	
	public NoCard(ATMMachine newATMMachine) {
		atmMachine = newATMMachine;
	}
	
	@Override
	public void insertCard() {
		System.out.println("Please Enter a PIN");
		atmMachine.setATMState(atmMachine.getYesCardState());
	}
	
	@Override
	public void ejectCard() {
		System.out.println("Enter a card first");
	}
	
	@Override
	public void insertPin(int pinEntered) {
		System.out.println("Enter a card first");
	}
	
	@Override
	public void requestCash(int cashToWithdraw) {
		System.out.println("Enter a card first");
	}
}

class HasPin implements ATMState {
	
	ATMMachine atmMachine;
	
	public HasPin(ATMMachine newATMMachine) {
		atmMachine = newATMMachine;
	}
	
	@Override
	public void insertCard() {
		System.out.println("You can't enter more than one card");
	}
	
	@Override
	public void ejectCard() {
		System.out.println("Card Ejected");
		atmMachine.setATMState(atmMachine.getNoCardState());
	}
	
	@Override
	public void insertPin(int pinEntered) {
		System.out.println("Already Entered PIN");
	}
	
	@Override
	public void requestCash(int cashToWithdraw) {
		if (cashToWithdraw > atmMachine.cashInMachine) {
			System.out.println("Don't have that cash");
			ejectCard();
		} else {
			System.out.println(cashToWithdraw + " is provided by the machine");
			atmMachine.setCashInMachine(atmMachine.cashInMachine - cashToWithdraw);
			ejectCard();
			
			if (atmMachine.cashInMachine <= 0) {
				atmMachine.setATMState(atmMachine.getNoCashSatate());
			}
		}
	}
}

class NoCash implements ATMState {

	ATMMachine atmMachine;

	public NoCash(ATMMachine newATMMachine) {
		atmMachine = newATMMachine;
	}

	@Override
	public void insertCard() {
		System.out.println("We don't have money");
	}

	@Override
	public void ejectCard() {
		System.out.println("We don't have money. You didn't enter a card");
	}

	@Override
	public void insertPin(int pinEntered) {
		System.out.println("We don't have money");
	}

	@Override
	public void requestCash(int cashToWithdraw) {
		System.out.println("We don't have money");
	}
}