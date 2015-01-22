import java.util.Random;

public class Locker {

	public static void main(String[] args) {

		String[] smallLetters = { "a", "b", "c", "d", "e", "f", "g", "h", "i",
				"j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u",
				"v", "w", "x", "y", "z" };
		String[] bigLetters = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
				"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
				"V", "W", "X", "Y", "Z" };

		String password;
		
		Random random = new Random();

		StringBuilder sb = new StringBuilder();
		

		for (int i = 0; i < 12; i++) {
			
			int j = random.nextInt(3);
			if(j==0){
				int k = random.nextInt(26);
				sb.append(smallLetters[k]);
			}
			if(j==1){
				int k = random.nextInt(26);
				sb.append(bigLetters[k]);
			}
			if(j==2){
				int k = random.nextInt(10);
				sb.append(String.valueOf(k));
			}

		}
		password = sb.toString();
		System.out.println(password);
		

	}

}
