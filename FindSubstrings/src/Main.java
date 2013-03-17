import java.util.ArrayList;

/**
 * 
 * @author Rafael
 * 
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String target = "hola";
		String input = "holola";
		int pos = 0;
		int tmp = 0;
		int ocurrences = 0;
		boolean weAreDone = false;
		ArrayList<Integer> list = new ArrayList<Integer>(target.length() - 1);
		// System.out.println("Looking for " + target.charAt(pos));
		tmp = input.indexOf(target.charAt(pos));
		pos++;
		list.add(tmp);

		while (true) {
			System.out.println("Looking for " + target.charAt(pos) + " from "
					+ (pos - 1));
			tmp = input.indexOf(target.charAt(pos), pos - 1);
			pos++;
			if (tmp == -1)
				break;
			list.add(tmp);

			if (pos == target.length() - 1) {
				// We got 'hol'
				// we need to find the next occurrences of target last character
				System.out.println(list);
				ocurrences += findOcurrences(input,
						target.charAt(target.length() - 1), pos);

				// Restart from inner to outter
				int tmpBackAmount = 2;
				while (true) {
					tmp = input
							.indexOf(
									target.charAt(target.length()
											- tmpBackAmount), pos);
					if (tmp == -1) {
						tmpBackAmount++;

						if ((target.length() - tmpBackAmount) == 0) {
							weAreDone = true;
							System.out.println("We are done");
							break;
						}
						continue;
					}
					// System.out.println("Restarting with "
					// + target.charAt(target.length() - tmpBackAmount)
					// + " from " + pos + " IN " + tmp);

					list.set(target.length() - tmpBackAmount, tmp);
					pos = tmp;
					System.out.println("pos = " + pos);
					break;
				}
			}
			if (weAreDone)
				break;
		}
		// System.out.println(list);
		System.out.println(ocurrences);
	}

	public static int findOcurrences(String str, char c, int pos) {
		int t = 0;
		// System.out.println("Looking for " + c + " in " + str + " from " +
		// pos);
		for (int i = pos; i < str.length(); i++) {
			if (str.charAt(i) == c)
				t++;
		}
		return t;
	}

}
