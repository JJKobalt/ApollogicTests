package Pages;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Language {
	PL, EN, DE;

	private static final List<Language> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	@Override
	public String toString() {
		String upperName = super.toString();
		return upperName.toLowerCase();

	}

	 public static String randomValue()  {
		    return VALUES.get(RANDOM.nextInt(SIZE)).toString();
		  }
}
