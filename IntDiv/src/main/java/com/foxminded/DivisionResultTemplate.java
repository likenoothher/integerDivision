package com.foxminded;

import gnu.trove.list.array.TIntArrayList;

public class DivisionResultTemplate {
    private int dividend;
    private int divisor;
    private TIntArrayList intermediateDividends;
    private TIntArrayList intermediateDivisors;
    private int finalResult;
    private int finalReminder;

    private DivisionResultTemplate() {

    }

    static DivisionResultTemplate createTemplate(int dividend, int divisor) {
        checkInitialInput(dividend,divisor);
        DivisionResultTemplate template = new DivisionResultTemplate();
        initializeTemplateFields(template, dividend, divisor);
        fillIntermediateValues(template, template.dividend, template.divisor);
        return template;
    }

    private static void initializeTemplateFields(DivisionResultTemplate template, int dividend, int divisor) {
        template.dividend = Math.abs(dividend);
        template.divisor = Math.abs(divisor);
        template.finalResult = Math.abs(dividend / divisor);
        template.finalReminder = Math.abs(dividend % divisor);
        template.intermediateDividends = new TIntArrayList();
        template.intermediateDivisors = new TIntArrayList();
    }

    private static void fillIntermediateValues(DivisionResultTemplate template, int dividend, int divisor) {
        String[] dividendDigits = String.valueOf(dividend).split("");
        StringBuilder intermediateDividendSB = new StringBuilder();

        for (int i = 0; i < dividendDigits.length; i++) {
            intermediateDividendSB.append(dividendDigits[i]);
            int intermediaeteDividend = Integer.parseInt(intermediateDividendSB.toString());

            if (intermediaeteDividend >= divisor || isDividendZero(intermediateDividendSB)) {
                putValuesToTemplate(template, intermediaeteDividend, divisor);
                intermediateDividendSB.setLength(0);

                if (intermediaeteDividend % divisor != 0) {
                    intermediateDividendSB.append(String.valueOf(intermediaeteDividend % divisor));
                }
            }
        }
    }

    private static void putValuesToTemplate(DivisionResultTemplate template,int dividend, int divisor) {
        template.addToIntermediateDividends(dividend);
        template.addToIntermediateDivisors((dividend / divisor) * divisor);
    }

    private static boolean isDividendZero(StringBuilder intermediateDividend) {
        return intermediateDividend.toString().equals("0");
    }

    private static void checkInitialInput(int dividend, int divisor) {
        if (dividend < divisor) {
            throw new IllegalArgumentException("Divisor shouldn't be larger dividend");
        }
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
        return "Template [dividend=" + dividend + ", divisor=" + divisor + ", intermediateDividends="
                + intermediateDividends + ", intermediateDivisors=" + intermediateDivisors + ", finalResult="
                + finalResult + ", finalReminder=" + finalReminder + "]";
    }

}
