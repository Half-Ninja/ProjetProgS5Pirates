package up.mi.pirates;

public class UtilPirates {

	private UtilPirates() {}
	
	public static String[] genererNoms(int nombre) {
		final char base = 'A';
		String[] res = new String[nombre];
		
		for(int i = 0; i < nombre; i++)
			res[i] = "" + (char)(base + i); //
		
		return res;
	}
}
