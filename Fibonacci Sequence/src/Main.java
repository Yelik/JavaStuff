public class Main {
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++)
			System.out.println(fib(i));
	}

	public static int fib(int i) {
		if (i == 0) {
			return 0;
		}
		if (i == 1) {
			return 1;
		}
		return fib(i - 1) + fib(i - 2);
	}
}
