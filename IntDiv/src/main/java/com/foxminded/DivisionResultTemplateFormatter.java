package com.foxminded;

import java.util.HashMap;
import java.util.Map;

import gnu.trove.list.array.TIntArrayList;

public class DivisionResultTemplateFormatter {

    private int dividend;
    private int divisor;
    private int firstIntermediateDividend;
    private int firstIntermediateDivisor;
    private TIntArrayList intermediateDividends;
    private TIntArrayList intermediateDivisors;
    private int finalResult;
    private int finalReminder;

    private int pointerPosition = 0;

    Map<String, String> utilityBodyStrings;

    public String getStringRepresentation(DivisionResultTemplate template) {
        extractTemplateVariables(template);
        createHeaderUtilityStrings();
        String templateRepresentation = getHeader();

        if (template.getIntermediateDividends().size() > 1) {
            templateRepresentation += getDivisionChain();
        }

        templateRepresentation += getReminder();
        pointerPosition = 0;

        return templateRepresentation;
    }

    private void extractTemplateVariables(DivisionResultTemplate template) {
        this.dividend = template.getDividend();
        this.divisor = template.getDivisor();
        this.firstIntermediateDividend = template.getIntermediateDividends().get(0);
        this.firstIntermediateDivisor = template.getIntermediateDivisors().get(0);
        this.finalResult = template.getFinalResult();
        this.finalReminder = template.getFinalReminder();
        this.intermediateDividends = template.getIntermediateDividends();
        this.intermediateDivisors = template.getIntermediateDivisors();
    }

    private void createHeaderUtilityStrings() {
        Map<String, String> utilityBodyStrings = new HashMap();
        utilityBodyStrings.put("beforeDivisorIndent",
        addSomeSymbols(" ", getLengthDifferenceOfNumbers(firstIntermediateDividend, firstIntermediateDivisor)));
        utilityBodyStrings.put("afterDivisorIndent",
                addSomeSymbols(" ", getCountsOfDigits(dividend) - utilityBodyStrings.get("beforeDivisorIndent").length()
                        - getCountsOfDigits(firstIntermediateDivisor)));
        utilityBodyStrings.put("divisorUnderline", addSomeSymbols("-", getCountsOfDigits(firstIntermediateDivisor)));
        this.utilityBodyStrings = utilityBodyStrings;

    }

    private String getHeader() {
        String beforeDivisorIndent = utilityBodyStrings.get("beforeDivisorIndent");
        String afterDivisorIndent = utilityBodyStrings.get("afterDivisorIndent");
        String divisorResultSeparator = getDivisorResultSeparator();
        String divisorUnderline = utilityBodyStrings.get("divisorUnderline");

        StringBuilder header = new StringBuilder();
        header.append(String.format("_%d|%d\n", dividend, divisor));
        header.append(String.format(" %s%d%s|%s\n", beforeDivisorIndent, firstIntermediateDivisor, afterDivisorIndent,
                divisorResultSeparator));
        header.append(
                String.format(" %s%s%s|%d\n", beforeDivisorIndent, divisorUnderline, afterDivisorIndent, finalResult));

        pointerPosition = getPointerPosition(firstIntermediateDividend, firstIntermediateDivisor);

        return header.toString();
    }

    private String getDivisionChain() {
        StringBuilder chain = new StringBuilder();

        for (int i = 1; i < intermediateDividends.size(); i++) {
            if (intermediateDividends.get(i) != 0) {
                chain.append(getDivisionBlock(intermediateDividends, intermediateDivisors, i));
                pointerPosition = getPointerPosition(intermediateDividends.get(i), intermediateDivisors.get(i));
            } else {
                pointerPosition = getPointerPosition(intermediateDividends.get(i), intermediateDivisors.get(i));
            }
        }

        return chain.toString();

    }

    private String getDivisionBlock(TIntArrayList intermediateDividends, TIntArrayList intermediateDivisors,
            int stepOfCycle) {
        StringBuilder block = new StringBuilder();
        int dividend = intermediateDividends.get(stepOfCycle);
        int divisor = intermediateDivisors.get(stepOfCycle);
        String generalIndent = addSomeSymbols(" ", pointerPosition);
        String divisorIndent = addSomeSymbols(" ", getLengthDifferenceOfNumbers(dividend, divisor));
        String divisorUnderline = addSomeSymbols("-", getCountsOfDigits(intermediateDivisors.get(stepOfCycle)));

        block.append(String.format("%s_%d\n", generalIndent, dividend));
        block.append(String.format("%s %s%d\n", generalIndent, divisorIndent, divisor));
        block.append(String.format("%s %s%s\n", generalIndent, divisorIndent, divisorUnderline));

        return block.toString();

    }

    private String getReminder() {
        return addSomeSymbols(" ", getCountsOfDigits(dividend) - getCountsOfDigits(finalReminder) + 1) + finalReminder;
    }

    private String addSomeSymbols(String symbol, int amount) {
        String symbolsToAdd = "";
        for (int i = 0; i < amount; i++) {
            symbolsToAdd = symbolsToAdd.concat(symbol);
        }
        return symbolsToAdd;
    }

    private int getCountsOfDigits(int number) {
        return (number == 0) ? 1 : (int) Math.log10(number) + 1;
    }

    private int getLengthDifferenceOfNumbers(int number1, int number2) {
        return Math.abs(getCountsOfDigits(number1) - getCountsOfDigits(number2));
    }

    private String getDivisorResultSeparator() {
        if (getCountsOfDigits(divisor) >= getCountsOfDigits(finalResult)) {
            return new String(addSomeSymbols("-", getCountsOfDigits(divisor)));
        } else {
            return new String(addSomeSymbols("-", getCountsOfDigits(finalResult)));
        }
    }

    private int getPointerPosition(int dividend, int divisor) {
        if (dividend - divisor == 0) {
            return pointerPosition + getCountsOfDigits(dividend);
        } else
            return pointerPosition + (getCountsOfDigits(dividend) - getCountsOfDigits(dividend - divisor));
    }

}
