package main.java.application.service;

import java.math.BigInteger;
import java.util.Scanner;

public class FormulaCalculator {

    public void run() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter m:");
        Integer m = Integer.parseInt(in.nextLine());
        System.out.println("Enter r:");
        Integer r = Integer.parseInt(in.nextLine());
        checkInputValues(m, r);
        BigInteger result = calculateByFormula(m, r);

        System.out.println("Result is: " + result);

    }

    private BigInteger calculateByFormula(Integer m, Integer r) {
        BigInteger result;
        BigInteger mFacorial = calculateFactorial(m);
        BigInteger rFacorial = calculateFactorial(r);
        BigInteger mMinusRFacturial = calculateFactorial((m - r));

        result = mFacorial.divide(rFacorial.multiply(mMinusRFacturial));
        return result;
    }

    private void checkInputValues(Integer m, Integer r) {
        if (!(r <= m)) {
            throw new IllegalArgumentException("Incoming parameters do not match the condition (r<=m) ");
        }
    }

    private BigInteger calculateFactorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}
