import java.util.Scanner;
public class RSA {
    public static int p, q, n, t, flag, msg, temp;
    public static int[] e = new int[100];
    public static int[] d = new int[100];
    // Check if a number is prime
    public static int prime(int pr) {
        int m = (int) Math.sqrt(pr);
        for (int i = 2; i <= m; i++) {
            if (pr % i == 0)
                return 0;
        }
        return 1;
    }
    // Compute possible values of e and d
    public static void ce() {
        int k = 0;
        for (int i = 2; i < t; i++) {
            if (t % i == 0)
                continue;
            flag = prime(i);
            if (flag == 1 && i != p && i != q) {
                e[k] = i;
                flag = cd(e[k]);
                if (flag > 0) {
                    d[k] = flag;
                    k++;
                }
                if (k == 99)
                    break;
            }
        }
    }
    // Compute d (modular inverse)
    public static int cd(int x) {
        int k = 1;
        while (true) {
            k = k + t;
            if (k % x == 0)
                return (k / x);
        }
    }
    // Encrypt the message
    public static void encrypt() {
        int pt, ct, key = e[0], k;
        pt = msg;
        k = 1;
        for (int j = 0; j < key; j++) {
            k = (k * pt) % n;
        }
        ct = k;
        temp = ct;
System.out.println("\nThe Encrypted Message is: " + ct);
    }
    // Decrypt the message
    public static void decrypt() {
        int pt, ct, key = d[0], k;
        ct = temp;
        k = 1;
        for (int j = 0; j < key; j++) {
            k = (k * ct) % n;
        }
        pt = k;
        System.out.println("\nThe Decrypted Message is: " + pt);
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter first prime number:");
        p = sc.nextInt();
        flag = prime(p);
        if (flag == 0) { System.out.println("Wrong input (not prime)");
            System.exit(1);
        }
        System.out.println("Enter another prime number:");
        q = sc.nextInt();
        flag = prime(q);
        if (flag == 0 || p == q) {
System.out.println("Wrong input (not prime or equal to first)");
            System.exit(1);
        }
        System.out.println("Enter message (integer):");
        msg = sc.nextInt();
        n = p * q;
        t = (p - 1) * (q - 1);
        ce();
System.out.println("Possible values of e and d are:");
        for (int i = 0; e[i] != 0; i++) {  System.out.printf("\n%d\t%d", e[i], d[i]);
        }
        encrypt();
        decrypt();
        sc.close();
    }
}
