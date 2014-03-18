public class Main {
	public static void main(String[] args) {
		double pi = 0;
		boolean add = true;
		for (double i = 1; i <= Double.MAX_VALUE; i += 2) {
			if (add) {
				pi += 1.0 / i;

			} else {
				pi -= 1.0 / i;
			}
			add = !add;
			if (i < 0) {
				break;
			}
		}
		System.out.println(pi * 4);
	}
}
