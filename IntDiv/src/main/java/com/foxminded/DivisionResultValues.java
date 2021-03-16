package com.foxminded;

import java.util.Objects;

import gnu.trove.TCollections;
import gnu.trove.list.TIntList;
import gnu.trove.list.array.TIntArrayList;

public final class DivisionResultValues {
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
        this.intermediateDividends = calculateIntermediateDividends(this.dividend, this.divisor);
        this.intermediateDivisors = calculateIntermediateDivisors(this.dividend, this.divisor);
        this.firstIntermediateDividend = this.intermediateDividends.get(0);
        this.firstIntermediateDivisor = this.intermediateDivisors.get(0);
    }

    public static DivisionResultValues of(int dividend, int divisor) {
        return new DivisionResultValues(dividend, divisor);
    }

    private static TIntList calculateIntermediateDividends(int dividend, int divisor) {
        int[] dividendDigits = splitNumber(dividend);
        int intermediateDividend = 0;

        TIntList intermediateDividends = new TIntArrayList();

        for (int i = 0; i < dividendDigits.length; i++) {
            intermediateDividend = intermediateDividend * 10 + dividendDigits[i];

            if (intermediateDividend >= divisor || intermediateDividend == 0) {
                intermediateDividends.add(intermediateDividend);
                intermediateDividend = intermediateDividend % divisor;
            }
        }
        return TCollections.unmodifiableList(intermediateDividends);

    }

    private static TIntList calculateIntermediateDivisors(int dividend, int divisor) {
        int[] dividendDigits = splitNumber(dividend);
        int intermediateDividend = 0;

        TIntList intermediateDivisors = new TIntArrayList();

        for (int i = 0; i < dividendDigits.length; i++) {
            intermediateDividend = intermediateDividend * 10 + dividendDigits[i];

            if (intermediateDividend >= divisor || intermediateDividend == 0) {
                intermediateDivisors.add((intermediateDividend / divisor) * divisor);
                intermediateDividend = intermediateDividend % divisor;
            }
        }
        return TCollections.unmodifiableList(intermediateDivisors);

    }

    private static int[] splitNumber(int number) {
        if (number <= 9) {
            return new int[] { number };
        } else {
            int[] digits = new int[getCountsOfDigits(number)];
            for (int i = 0; i < digits.length; i++) {
                digits[digits.length - 1 - i] = number % 10;
                number = number / 10;
            }
            return digits;
        }
    }

    private static int getCountsOfDigits(int number) {
        return  (int) (Math.log10(number) + 1);
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

    public TIntList getIntermediateDividends() {
        return intermediateDividends;
    }

    public TIntList getIntermediateDivisors() {
        return intermediateDivisors;
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
