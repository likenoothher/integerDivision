package com.foxminded;

import java.util.Objects;

import gnu.trove.list.TIntList;
import gnu.trove.list.array.TIntArrayList;

public class DivisionResultValues {
    private final int dividend;
    private final int divisor;
    private final int firstIntermediateDividend;
    private final int firstIntermediateDivisor;
    private final TIntList intermediateDividends;
    private final TIntList intermediateDivisors;
    private final int finalResult;
    private final int finalReminder;

    private DivisionResultValues(int dividend, int divisor) {
        checkDivisor(divisor);
        this.dividend = Math.abs(dividend);
        this.divisor = Math.abs(divisor);
        this.finalResult = Math.abs(dividend / divisor);
        this.finalReminder = Math.abs(dividend % divisor);
        this.intermediateDividends = fillIntermediateDividendArray(this.dividend, this.divisor);
        this.intermediateDivisors = fillIntermediateDivisorsArray(this.dividend, this.divisor);
        this.firstIntermediateDividend = this.intermediateDividends.get(0);
        this.firstIntermediateDivisor = this.intermediateDivisors.get(0);
    }

    static DivisionResultValues initializeSetOfValuesFields(int dividend, int divisor) {
        checkDivisor(divisor);

        return new DivisionResultValues(dividend, divisor);
    }

    private static TIntList fillIntermediateDividendArray(int dividend, int divisor) {
        int[] dividendDigits = splitNumber(dividend);
        int intermediaeteDividend = 0;

        TIntList intermediaeteDividends = new TIntArrayList();

        for (int i = 0; i < dividendDigits.length; i++) {
            intermediaeteDividend = intermediaeteDividend * 10 + dividendDigits[i];

            if (intermediaeteDividend >= divisor || intermediaeteDividend == 0) {
                intermediaeteDividends.add(intermediaeteDividend);
                intermediaeteDividend = intermediaeteDividend % divisor;
            }
        }
        return intermediaeteDividends;

    }

    private static TIntList fillIntermediateDivisorsArray(int dividend, int divisor) {
        int[] dividendDigits = splitNumber(dividend);
        int intermediaeteDividend = 0;

        TIntList intermediaeteDivisors = new TIntArrayList();

        for (int i = 0; i < dividendDigits.length; i++) {
            intermediaeteDividend = intermediaeteDividend * 10 + dividendDigits[i];

            if (intermediaeteDividend >= divisor || intermediaeteDividend == 0) {
                intermediaeteDivisors.add((intermediaeteDividend / divisor) * divisor);
                intermediaeteDividend = intermediaeteDividend % divisor;
            }
        }
        return intermediaeteDivisors;

    }

    private static int[] splitNumber(int number) {
        if (number == 0) {
            return new int[] { 0 };
        } else {
            int[] digits = new int[(int) Math.log10(number) + 1];
            for (int i = 0; i < digits.length; i++) {
                digits[digits.length - 1 - i] = number % 10;
                number = number / 10;
            }
            return digits;
        }
    }

    private static void checkDivisor(int divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException("Divisor shouldn't be zero");
        }
    }

    public int getDividend() {
        return dividend;
    }

    public int getDivisor() {
        return divisor;
    }

    public int getFirstIntermediateDividend() {
        return firstIntermediateDividend;
    }

    public int getFirstIntermediateDivisor() {
        return firstIntermediateDivisor;
    }

    public void addToIntermediateDividends(int num) {
        this.intermediateDividends.add(num);
    }

    public void addToIntermediateDivisors(int num) {
        this.intermediateDivisors.add(num);
    }

    public TIntList getIntermediateDividends() {
        return new TIntArrayList(intermediateDividends);
    }

    public TIntList getIntermediateDivisors() {
        return new TIntArrayList(intermediateDivisors);
    }

    public int getFinalResult() {
        return finalResult;
    }

    public int getFinalReminder() {
        return finalReminder;
    }

    @Override
    public String toString() {
        return "setOfValues [dividend=" + dividend + ", divisor=" + divisor + ", intermediateDividends="
                + intermediateDividends + ", intermediateDivisors=" + intermediateDivisors + ", finalResult="
                + finalResult + ", finalReminder=" + finalReminder + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(dividend, divisor);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DivisionResultValues)) {
            return false;
        }
        DivisionResultValues other = (DivisionResultValues) obj;
        return dividend == other.dividend && divisor == other.divisor;
    }

}
