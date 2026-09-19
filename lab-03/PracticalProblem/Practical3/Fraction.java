public class Fraction {

    private int num;
    private int den;

    public Fraction(int num, int den) {

        if (den == 0) {
            throw new ArithmeticException("Can't divide by 0");
        }

        // Keep denominator positive
        if (den < 0) {
            num = -num;
            den = -den;
        }

        int g = gcd(num, den);

        this.num = num / g;
        this.den = den / g;
    }

    private static int gcd(int a, int b) {
        return (b == 0) ? Math.abs(a) : gcd(b, a % b);
    }

    @Override
    public String toString() {
        return num + "/" + den;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || !(obj instanceof Fraction)) {
            return false;
        }

        Fraction other = (Fraction) obj;

        return this.num == other.num && this.den == other.den;
    }

    @Override
    public int hashCode() {
        return 31 * num + den;
    }
}
