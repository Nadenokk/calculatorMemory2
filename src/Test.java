import static java.lang.Math.abs;

public class Test {
    public static void main(String[] args) {
        double a = -291.725;
        double b = abs(a);
        int left = (int) b;
        if (a == left)
        {
            System.out.println(Integer.toBinaryString(left));
        }
        else {
            String bin = "";
            bin = Integer.toBinaryString(left) + bin;
            bin += ".";
            double right = (float) b - (int) b;
            for (int i = 0; i < 5; i++) {
                right = right * 2 - (int) right * 2;
                bin = bin + (int) right;
                if (right == 1.0) {
                    break;
                }
            }
            System.out.println(bin);
        }
    }
}
