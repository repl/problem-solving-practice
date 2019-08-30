package dynamicprogramming;

public class Fibonacci {
	final int MAX = 1001, NIL = -1;
        long lookup[] = new long[MAX];
	
	public static void main(String[] args) {
		Fibonacci fibonacci = new Fibonacci();
		fibonacci.initialize();
                long startTime = System.nanoTime();
		System.out.println(fibonacci.fibRecursive(100) + ", computed in " + (System.nanoTime()- startTime));

		fibonacci.initialize();
        startTime = System.nanoTime();
		System.out.println(fibonacci.fibIterative1(100) + ", computed in " + (System.nanoTime()- startTime));
        startTime = System.nanoTime();
        System.out.println(fibonacci.fibIterative2(100) + ", computed in " + (System.nanoTime()- startTime));
	}

	void initialize() {
                for (int i = 0; i < MAX; i++) {
                        lookup[i] = NIL;
		}
	}

	long fibRecursive(int n) {
		if (lookup[n] == NIL) {
			if (n <= 1) {
				lookup[n] = n;
			} else {
				lookup[n] = fibRecursive(n - 2) + fibRecursive(n - 1);
			}
		}
		return lookup[n];
	}

	private long fibIterative1(int n) {
		if (n <= 1) return n;
		lookup[0] = 0; lookup[1] = 1;
		for (int i = 2; i<=n; i++) {
			lookup[i] = lookup[i-1] + lookup[i-2];
		}
		return lookup[n];
	}

	private long fibIterative2(int n) {
		if (n <= 1) return n;
		long prev = 0, curr = 1;
		for (int i = 2; i<=n; i++) {
			long next = prev + curr;
			prev = curr;
			curr = next;
		}
		return curr;
	}
}
