package com.foxminded;

import gnu.trove.list.array.TIntArrayList;

public class Template {
    private int dividend;
    private int divisor;
    private TIntArrayList intermediateDividends = new TIntArrayList();
    private TIntArrayList intermediateDivisors = new TIntArrayList();
    private int finalResult;
    private int finalReminder;

    public Template(int dividend, int divisor) {
        this.dividend = dividend;
        this.divisor = divisor;
        this.finalResult = dividend / divisor;
        this.finalReminder = dividend % divisor;
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
        return intermediateDividends;
    }

    public TIntArrayList getIntermediateDivisors() {
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
        return "Template [dividend=" + dividend + ", divisor=" + divisor + ", intermediateDividends=" + intermediateDividends
                + ", intermediateDivisors=" + intermediateDivisors + ", finalResult=" + finalResult + ", finalReminder=" + finalReminder + "]";
    }

}
