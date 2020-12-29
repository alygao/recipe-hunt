package services;

public class test {

	public static void main(String[] args) {
		String name = " .. ";
		
		boolean hasLetter = false;
		for (char ch : name.toCharArray()) {
		  if (Character.isLetter(ch)) {
			  System.out.println("has letter");
			  hasLetter = true;
			  break;
		  }
		}
		if (!hasLetter) {
			System.out.println("has no letter");
		}
		
	}

}
