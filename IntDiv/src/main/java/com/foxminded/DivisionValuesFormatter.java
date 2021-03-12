package com.foxminded;

import java.util.HashMap;
import java.util.Map;

import gnu.trove.list.array.TIntArrayList;

public class DivisionValuesFormatter {

    private int dividend;
    private int divisor;
    private int firstIntermediateDividend;
    private int firstIntermediateDivisor;
    private TIntArrayList intermediateDividends;
    private TIntArrayList intermediateDivisors;
    private int finalResult;
    private int finalReminder;

    private int pointerPosition = 0;

    Map<String, String> utilityHeaderStrings = new HashMap<String, String>();

    private final String BEFORE_DIVISOR_INDENT = "beforeDivisorIndent";
    private final String AFTER_DIVISOR_INDENT = "afterDivisorIndent";
    private final String DIVISOR_UNDERLINE = "divisorUnderline";

    public String getStringRepresentation(DivisionResultValues template) {
        extractTemplateVariables(template);
        fillHeaderUtilityStrings();
        StringBuilder templateRepresentation = new StringBuilder();
        getHeader(templateRepresentation);

        if (template.getIntermediateDividends().size() > 1) {
            getDivisionChain(templateRepresentation);
        }

        getReminder(templateRepresentation);

        pointerPosition = 0;
        utilityHeaderStrings.clear();

        return templateRepresentation.toString();
    }

    private void extractTemplateVariables(DivisionResultValues template) {
        this.dividend = template.getDividend();
        this.divisor = template.getDivisor();
        this.firstIntermediateDividend = template.getIntermediateDividends().get(0);
        this.firstIntermediateDivisor = template.getIntermediateDivisors().get(0);
        this.finalResult = template.getFinalResult();
        this.finalReminder = template.getFinalReminder();
        this.intermediateDividends = template.getIntermediateDividends();
        this.intermediateDivisors = template.getIntermediateDivisors();
    }

    private void fillHeaderUtilityStrings() {
        utilityHeaderStrings.put(BEFORE_DIVISOR_INDENT,
                addSymbols(" ", getLengthDifferenceOfNumbers(firstIntermediateDividend, firstIntermediateDivisor)));
        utilityHeaderStrings.put(AFTER_DIVISOR_INDENT,
                addSymbols(" ", getCountsOfDigits(dividend) - utilityHeaderStrings.get("beforeDivisorIndent").length()
                        - getCountsOfDigits(firstIntermediateDivisor)));
        utilityHeaderStrings.put(DIVISOR_UNDERLINE, addSymbols("-", getCountsOfDigits(firstIntermediateDivisor)));

    }

    private void getHeader(StringBuilder templateRepresentation) {
        if (dividend != 0) {
            getFullHeader(templateRepresentation);
        } else {
            getCutHeader(templateRepresentation);
        }
        pointerPosition = getPointerPosition(firstIntermediateDividend, firstIntermediateDivisor);

    }

    private void getCutHeader(StringBuilder templateRepresentation) {
        String divisorResultSeparator = getDivisorResultSeparator();

        templateRepresentation.append(String.format("%d|%d\n", dividend, divisor));
        templateRepresentation
                .append(String.format("%s|%s\n", addSymbols(" ", getCountsOfDigits(dividend)), divisorResultSeparator));
        templateRepresentation
                .append(String.format("%s|%d\n", addSymbols(" ", getCountsOfDigits(dividend)), finalResult));
    }

    private void getFullHeader(StringBuilder templateRepresentation) {
        String beforeDivisorIndent = utilityHeaderStrings.get(BEFORE_DIVISOR_INDENT);
        String afterDivisorIndent = utilityHeaderStrings.get(AFTER_DIVISOR_INDENT);
        String divisorResultSeparator = getDivisorResultSeparator();
        String divisorUnderline = utilityHeaderStrings.get(DIVISOR_UNDERLINE);

        templateRepresentation.append(String.format("_%d|%d\n", dividend, divisor));
        templateRepresentation.append(String.format(" %s%d%s|%s\n", beforeDivisorIndent, firstIntermediateDivisor,
                afterDivisorIndent, divisorResultSeparator));
        templateRepresentation.append(
                String.format(" %s%s%s|%d\n", beforeDivisorIndent, divisorUnderline, afterDivisorIndent, finalResult));
    }

    private void getDivisionChain(StringBuilder templateRepresentation) {
        for (int i = 1; i < intermediateDividends.size(); i++) {
            if (intermediateDividends.get(i) != 0) {
                templateRepresentation.append(getDivisionBlock(intermediateDividends, intermediateDivisors, i));
                pointerPosition = getPointerPosition(intermediateDividends.get(i), intermediateDivisors.get(i));
            } else {
                pointerPosition = getPointerPosition(intermediateDividends.get(i), intermediateDivisors.get(i));
            }
        }
    }

    private String getDivisionBlock(TIntArrayList intermediateDividends, TIntArrayList intermediateDivisors,
            int stepOfCycle) {
        StringBuilder block = new StringBuilder();
        int dividend = intermediateDividends.get(stepOfCycle);
        int divisor = intermediateDivisors.get(stepOfCycle);
        String generalIndent = addSymbols(" ", pointerPosition);
        String divisorIndent = addSymbols(" ", getLengthDifferenceOfNumbers(dividend, divisor));
        String divisorUnderline = addSymbols("-", getCountsOfDigits(intermediateDivisors.get(stepOfCycle)));

        block.append(String.format("%s_%d\n", generalIndent, dividend));
        block.append(String.format("%s %s%d\n", generalIndent, divisorIndent, divisor));
        block.append(String.format("%s %s%s\n", generalIndent, divisorIndent, divisorUnderline));

        return block.toString();
    }

    private void getReminder(StringBuilder templateRepresentation) {
        if (dividend != 0) {
            templateRepresentation
                    .append(addSymbols(" ", getCountsOfDigits(dividend) - getCountsOfDigits(finalReminder) + 1)
                            + finalReminder);
        }
    }

    private String addSymbols(String symbol, int amount) {
        String repeated = symbol.repeat(amount);
        return repeated;
    }

    private int getCountsOfDigits(int number) {
        return (number == 0) ? 1 : (int) Math.log10(number) + 1;
    }

    private int getLengthDifferenceOfNumbers(int number1, int number2) {
        return Math.abs(getCountsOfDigits(number1) - getCountsOfDigits(number2));
    }

    private String getDivisorResultSeparator() {
        if (getCountsOfDigits(divisor) >= getCountsOfDigits(finalResult)) {
            return new String(addSymbols("-", getCountsOfDigits(divisor)));
        } else {
            return new String(addSymbols("-", getCountsOfDigits(finalResult)));
        }
    }

    private int getPointerPosition(int dividend, int divisor) {
        if (dividend - divisor == 0) {
            return pointerPosition + getCountsOfDigits(dividend);
        } else {
            return pointerPosition + (getCountsOfDigits(dividend) - getCountsOfDigits(dividend - divisor));
        }
    }

}
