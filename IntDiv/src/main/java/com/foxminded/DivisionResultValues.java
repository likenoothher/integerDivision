package com.foxminded;

import gnu.trove.list.array.TIntArrayList;

public class DivisionResultValues {
    private int dividend;
    private int divisor;
    private TIntArrayList intermediateDividends;
    private TIntArrayList intermediateDivisors;
    private int finalResult;
    private int finalReminder;

    private DivisionResultValues() {

    }

    static DivisionResultValues createSetOfValues(int dividend, int divisor) {
        checkDivisor(divisor);
        DivisionResultValues setOfValues = new DivisionResultValues();
        initializesetOfValuesFields(setOfValues, dividend, divisor);
        fillIntermediateValues(setOfValues, setOfValues.dividend, setOfValues.divisor);
        return setOfValues;
    }

    private static void initializesetOfValuesFields(DivisionResultValues setOfValues, int dividend, int divisor) {
        setOfValues.dividend = Math.abs(dividend);
        setOfValues.divisor = Math.abs(divisor);
        setOfValues.finalResult = Math.abs(dividend / divisor);
        setOfValues.finalReminder = Math.abs(dividend % divisor);
        setOfValues.intermediateDividends = new TIntArrayList();
        setOfValues.intermediateDivisors = new TIntArrayList();
    }

    private static void fillIntermediateValues(DivisionResultValues setOfValues, int dividend, int divisor) {
        int[] dividendDigits = splitNumber(dividend);
        int intermediaeteDividend = 0;

        for (int i = 0; i < dividendDigits.length; i++) {
            intermediaeteDividend = intermediaeteDividend * 10 + dividendDigits[i];

            if (intermediaeteDividend >= divisor || intermediaeteDividend == 0) {
                putValuesTosetOfValues(setOfValues, intermediaeteDividend, divisor);
                intermediaeteDividend = intermediaeteDividend % divisor;
            }
        }
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

    private static void putValuesTosetOfValues(DivisionResultValues setOfValues, int dividend, int divisor) {
        setOfValues.addToIntermediateDividends(dividend);
        setOfValues.addToIntermediateDivisors((dividend / divisor) * divisor);
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

    public void addToIntermediateDividends(int num) {
        this.intermediateDividends.add(num);
    }

    public void addToIntermediateDivisors(int num) {
        this.intermediateDivisors.add(num);
    }

    public TIntArrayList getIntermediateDividends() {
        return new TIntArrayList(intermediateDividends);
    }

    public TIntArrayList getIntermediateDivisors() {
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

}
