package com.foxminded;

import gnu.trove.list.array.TIntArrayList;

public class TemplateFormatter {
    private static int pointerPosition = 1;

    private TemplateFormatter() {
    }

    public static String formatTemplate(Template template) {
        String result = getHeader(template);

        if (template.getIntermediateDividends().size() > 1) {
            result += getBody(template);
        }

        result += getReminder(template);
        pointerPosition = 1;

        return result;
    }

    private static String getHeader(Template template) {
        int dividend = template.getDividend();
        int firstIntermediateDividend = template.getIntermediateDividends().get(0);
        int firstIntermediateDivisor = template.getIntermediateDivisors().get(0);
        int finalResult = template.getFinalResult();

        int splitterLenght = (template.getFinalResult() > template.getDivisor()) ? getCountsOfDigits(finalResult)
                : getCountsOfDigits(template.getDivisor());

        StringBuilder header = new StringBuilder();
        header.append("_" + dividend + "|" + template.getDivisor() + "\n");

        header.append(addSomeSymbols(" ", getLengthDifferenceOfNumbers(firstIntermediateDividend, firstIntermediateDivisor) + 1) + firstIntermediateDivisor
                + addSomeSymbols(" ",
                        getLengthDifferenceOfNumbers(dividend, firstIntermediateDivisor) - getLengthDifferenceOfNumbers(firstIntermediateDividend, firstIntermediateDivisor))
                + "|" + addSomeSymbols("-", splitterLenght) + "\n");

        header.append(addSomeSymbols(" ", getLengthDifferenceOfNumbers(firstIntermediateDividend, firstIntermediateDivisor) + 1)
                + addSomeSymbols("-", getCountsOfDigits(firstIntermediateDivisor))
                + addSomeSymbols(" ",
                        getLengthDifferenceOfNumbers(dividend, firstIntermediateDivisor) - getLengthDifferenceOfNumbers(firstIntermediateDividend, firstIntermediateDivisor))
                + "|" + String.valueOf(finalResult) + "\n");

        return header.toString();
    }

    private static String getBody(Template template) {
        StringBuilder body = new StringBuilder();
        TIntArrayList intermediateDividends = template.getIntermediateDividends();
        TIntArrayList intermediateDivisors = template.getIntermediateDivisors();

        pointerPosition = shiftPosition(intermediateDividends.get(0), intermediateDivisors.get(0));

        for (int i = 1; i < intermediateDividends.size(); i++) {
            if (intermediateDividends.get(i) != 0) {
                body.append(addSomeSymbols(" ", pointerPosition - 1) + "_" + intermediateDividends.get(i) + "\n");
                body.append(addSomeSymbols(" ", pointerPosition) + intermediateDivisors.get(i) + "\n");
                body.append(addSomeSymbols(" ", pointerPosition) + addSomeSymbols("-", getCountsOfDigits(intermediateDivisors.get(i))) + "\n");
                pointerPosition = shiftPosition(intermediateDividends.get(i), intermediateDivisors.get(i));
            } else {
                pointerPosition = shiftPosition(intermediateDividends.get(i), intermediateDivisors.get(i));
            }
        }

        return body.toString();

    }

    private static String getReminder(Template template) {
        int dividend = template.getDividend();
        int finalReminder = template.getFinalReminder();
        StringBuilder reminder = new StringBuilder();
        reminder.append(addSomeSymbols(" ", getCountsOfDigits(dividend) - getCountsOfDigits(finalReminder) + 1) + finalReminder);
        return reminder.toString();
    }

    private static String addSomeSymbols(String symbol, int amount) {
        String symdolsToAdd = "";
        for (int i = 0; i < amount; i++) {
            symdolsToAdd = symdolsToAdd.concat(symbol);
        }
        return symdolsToAdd;
    }

    private static int getCountsOfDigits(int number) {
        return(number == 0) ? 1 : (int) Math.ceil(Math.log10(Math.abs(number) + 0.5));
    }

    private static int getLengthDifferenceOfNumbers(int num1, int num2) {
        return Math.abs(getCountsOfDigits(num1) - getCountsOfDigits(num2));
    }

    private static int shiftPosition(int dividend, int devisor) {
        if (dividend - devisor == 0) {
            return pointerPosition + getCountsOfDigits(dividend);
        } else
            return pointerPosition + (getCountsOfDigits(dividend) - getCountsOfDigits(dividend - devisor));
    }

}
