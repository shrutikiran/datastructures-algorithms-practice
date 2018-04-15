package misc;

public class NumberTests {

    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }

        if (n == 2) {
            return true;
        }

        int sqrtN = (int) Math.sqrt(n);
        for (int i = 2; i <= sqrtN; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static void primeFactors(int n) {
        while ((n % 2) == 0) {
            System.out.print(2 + " ");
            n /= 2;
        }

        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            while ((n % i) == 0) {
                System.out.print(i + " ");
                n /= i;
            }
        }

        if (n > 2) {
            System.out.print(n);
        }
    }

    public static int factorial_Iterative(int n) {
        if (n <= 2) {
            return n;
        }

        int factorial = 2;
        for (int i = 3; i <= n; i++) {
            factorial *= i;
        }

        return factorial;
    }

    public static int factorial_Recursive(int n) {
        if (n <= 2) {
            return n;
        }

        return n * factorial_Recursive(n - 1);
    }

    public static int fibonacci_Iterative(int n) {
        if (n <= 1) {
            return n;
        }

        int sum = 0;
        int a = 0, b = 1;
        while (--n > 0) {
            sum = a + b;
            a = b;
            b = sum;
        }

        return sum;
    }

    public static int fibonacci_Recursive(int n) {
        if (n <= 1) {
            return n;
        }

        return fibonacci_Recursive(n - 1) + fibonacci_Recursive(n - 2);
    }

    public static void main(String[] arg) {

        {
            /* Primality check */
            System.out.println("Primality Check:");
            System.out.println("isPrime(4): " + isPrime(4));
            System.out.println("isPrime(7): " + isPrime(7));
            System.out.println("isPrime(9): " + isPrime(9));
            System.out.println();
        }

        {
            /* Prime Factors */
            System.out.println("Prime Factors:");
            System.out.print("prime factors of 12: ");primeFactors(12);System.out.println();
            System.out.print("prime factors of 102: ");primeFactors(102);System.out.println();
            System.out.print("prime factors of 2: ");primeFactors(2);System.out.println();
            System.out.println();
        }

        {
            /* Factorial */
            System.out.println("Factorials");
            System.out.println("factorial(5): " + factorial_Iterative(5));
            System.out.println("factorial(5): " + factorial_Recursive(5));
            System.out.println();
        }

        {
            /* Fibonacci */
            System.out.println("Fibonacci");
            System.out.print("fibonacci 10 (iterative): " + fibonacci_Iterative(10));System.out.println();
            System.out.print("fibonacci 10 (recursive): " + fibonacci_Recursive(10));System.out.println();
        }
    }
}
