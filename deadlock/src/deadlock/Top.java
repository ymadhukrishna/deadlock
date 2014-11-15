package deadlock;

class Bottom {
	public Bottom(String s) {
		System.out.println("Bottom");
	}
	
	public Bottom()
	{
		System.err.println("TEST");
	}

	
}

class Top1 extends Bottom {
	public Top1(String s) {
		System.err.println("TOP");
		
		
	}

}

public class Top {
	public static void main(String args[]) {
		new Top1("S");
	}
}
