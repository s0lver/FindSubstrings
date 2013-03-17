package perez.torres.rafael;

/***
 * 
 * @author Rafael
 *
 */
public class Subcadenas {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		boolean finalizado = false;
		String original = "hola";
		String muestra = "hhhhhhhhoooooooolllllllaaaaaaa";
		// Partiendo de todo en minusculas
		muestra = muestra.toLowerCase();

		// pivotes almacena la posici�n de cada letra en un momento determinado
		int[] pivotes = new int[original.length() - 1];

		int idx = 0;
		int posActual = 0;
		int ocurrencias = 0;

		// Inicio, encontrar la primer letra del original en la muestra y
		// almacenar su posici�n en pivotes
		int tmp = muestra.indexOf(original.charAt(idx));
		if (tmp != -1) {
			pivotes[idx] = tmp;
			posActual = tmp;
			idx++;
		}

		while (!finalizado) {
			// Buscar la siguiente letra seg�n nuestra pila/pivote
			tmp = muestra.indexOf(original.charAt(idx), posActual);
			if (tmp != -1) {
				pivotes[idx] = tmp;
				posActual = tmp;
				idx++;
			} else {
				finalizado = true;
				break;
			}
			if (idx == pivotes.length) {
				// Encontr�ndonos en la pen�ltima letra del original, lo que
				// sigue es buscar las apariciones del �ltio caracter. Todas sus
				// apariciones ser�n una aparici�n/ocurrencia m�s.
				ocurrencias += encontrarOcurrencias(muestra,
						original.charAt(original.length() - 1), posActual);

				// "Simulando un backtracking"
				// Regresar a buscar la repetici�n de una letra de la palabra
				// original en la muestra

				int auxRegreso = 0;
				while (true) {
					tmp = muestra.indexOf(original.charAt(idx - auxRegreso),
							pivotes[idx - auxRegreso] + 1);

					// Si no se encuentra un caracter, se retrocede hasta
					// encontrar uno anterior
					if (tmp == -1) {
						auxRegreso++;
						// Si de plano no se encuentra ninguno, se termina la
						// ejecuci�n
						if ((idx - auxRegreso) < 0) {
							finalizado = true;
							break;
						}
						continue;
					} else {
						// Al encontrar una nueva, de ah� se parte para buscar a
						// la siguiente en la cadena de muestra
						posActual = tmp;
						idx = idx - auxRegreso;
						break;
					}
				}
			}
		}
		System.out.println("Ocurrencias " + ocurrencias);
	}

	/***
	 * Peque�o auxiliar para encontrar cu�ntas veces se repite un caracter a
	 * partir de determinada posici�n en un String
	 * 
	 * @param str
	 *            El String donde se buscar�
	 * @param c
	 *            El caracter a buscar
	 * @param pos
	 *            La posici�n de b�squeda inicial
	 * @return La cantidad de ocasiones que aparece el caracter
	 */
	public static int encontrarOcurrencias(String str, char c, int pos) {
		int t = 0;
		for (int i = pos; i < str.length(); i++) {
			if (str.charAt(i) == c)
				t++;
		}
		return t;
	}
}
