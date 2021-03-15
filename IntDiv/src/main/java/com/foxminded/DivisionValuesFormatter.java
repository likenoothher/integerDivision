package com.foxminded;

import java.util.HashMap;
import java.util.Map;

import gnu.trove.list.TIntList;

public class DivisionValuesFormatter {

    private DivisionResultValues setOfValues;

    private int pointerPosition = 0;

    private final Map<String, String> utilityHeaderStrings = new HashMap<>();

    private static final String BEFORE_DIVISOR_INDENT = "beforeDivisorIndent";
    private static final String AFTER_DIVISOR_INDENT = "afterDivisorIndent";
    private static final String DIVISOR_UNDERLINE = "divisorUnderline";

    public String getStringRepresentation(DivisionResultValues setOfValues) {
        this.setOfValues = setOfValues;
        fillHeaderUtilityStrings();
        StringBuilder setOfValuesRepresentation = new StringBuilder();
        getHeader(setOfValuesRepresentation);

        if (setOfValues.getIntermediateDividends().size() > 1) {
            getDivisionChain(setOfValuesRepresentation);
        }

        getReminder(setOfValuesRepresentation);

        pointerPosition = 0;
        utilityHeaderStrings.clear();

        return setOfValuesRepresentation.toString();
    }

    private void fillHeaderUtilityStrings() {
        utilityHeaderStrings.put(BEFORE_DIVISOR_INDENT,
                " ".repeat(getLengthDifferenceOfNumbers(setOfValues.getFirstIntermediateDividend(),
                        setOfValues.getFirstIntermediateDivisor())));
        utilityHeaderStrings.put(AFTER_DIVISOR_INDENT,
                " ".repeat(getCountsOfDigits(setOfValues.getDividend())
                        - utilityHeaderStrings.get(BEFORE_DIVISOR_INDENT).length()
                        - getCountsOfDigits(setOfValues.getFirstIntermediateDivisor())));
        utilityHeaderStrings.put(DIVISOR_UNDERLINE,
                "-".repeat(getCountsOfDigits(setOfValues.getFirstIntermediateDivisor())));

    }

    private void getHeader(StringBuilder setOfValuesRepresentation) {
        if (setOfValues.getDividend() != 0) {
            getFullHeader(setOfValuesRepresentation);
        } else {
            getCutHeader(setOfValuesRepresentation);
        }
        pointerPosition = getPointerPosition(setOfValues.getFirstIntermediateDividend(),
                setOfValues.getFirstIntermediateDivisor());

    }

    private void getCutHeader(StringBuilder setOfValuesRepresentation) {
        String divisorResultSeparator = getDivisorResultSeparator();

        setOfValuesRepresentation.append(String.format("%d|%d\n", setOfValues.getDividend(), setOfValues.getDivisor()))
                .append(String.format("%s|%s\n", " ".repeat(getCountsOfDigits(setOfValues.getDividend())),
                        divisorResultSeparator))
                .append(String.format("%s|%d\n", " ".repeat(getCountsOfDigits(setOfValues.getDividend())),
                        setOfValues.getFinalResult()));
    }

    private void getFullHeader(StringBuilder setOfValuesRepresentation) {
        String beforeDivisorIndent = utilityHeaderStrings.get(BEFORE_DIVISOR_INDENT);
        String afterDivisorIndent = utilityHeaderStrings.get(AFTER_DIVISOR_INDENT);
        String divisorResultSeparator = getDivisorResultSeparator();
        String divisorUnderline = utilityHeaderStrings.get(DIVISOR_UNDERLINE);

        setOfValuesRepresentation.append(String.format("_%d|%d\n", setOfValues.getDividend(), setOfValues.getDivisor()))
                .append(String.format(" %s%d%s|%s\n", beforeDivisorIndent, setOfValues.getFirstIntermediateDivisor(),
                        afterDivisorIndent, divisorResultSeparator))
                .append(String.format(" %s%s%s|%d\n", beforeDivisorIndent, divisorUnderline, afterDivisorIndent,
                        setOfValues.getFinalResult()));
    }

    private void getDivisionChain(StringBuilder setOfValuesRepresentation) {
        TIntList intermediateDividends = setOfValues.getIntermediateDividends();
        TIntList intermediateDivisors = setOfValues.getIntermediateDivisors();
        for (int i = 1; i < intermediateDividends.size(); i++) {
            if (intermediateDividends.get(i) != 0) {
                setOfValuesRepresentation.append(getDivisionBlock(i));
                pointerPosition = getPointerPosition(intermediateDividends.get(i), intermediateDivisors.get(i));
            } else {
                pointerPosition = getPointerPosition(intermediateDividends.get(i), intermediateDivisors.get(i));
            }
        }
    }

    private String getDivisionBlock(int stepOfCycle) {
        String block;
        int dividend = setOfValues.getIntermediateDividends().get(stepOfCycle);
        int divisor = setOfValues.getIntermediateDivisors().get(stepOfCycle);
        String generalIndent = " ".repeat(pointerPosition);
        String divisorIndent = " ".repeat(getLengthDifferenceOfNumbers(dividend, divisor));
        String divisorUnderline = "-".repeat(getCountsOfDigits(divisor));

        block = String.format("%s_%d\n", generalIndent, dividend)
                + String.format("%s %s%d\n", generalIndent, divisorIndent, divisor)
                + String.format("%s %s%s\n", generalIndent, divisorIndent, divisorUnderline);

        return block;
    }

    private void getReminder(StringBuilder setOfValuesRepresentation) {
        if (setOfValues.getDividend() != 0) {
            setOfValuesRepresentation
                    .append(" "
                            .repeat(getCountsOfDigits(setOfValues.getDividend())
                                    - getCountsOfDigits(setOfValues.getFinalReminder()) + 1)
                            + setOfValues.getFinalReminder());
        }
    }

    private int getCountsOfDigits(int number) {
        return (number == 0) ? 1 : (int) Math.log10(number) + 1;
    }

    private int getLengthDifferenceOfNumbers(int number1, int number2) {
        return Math.abs(getCountsOfDigits(number1) - getCountsOfDigits(number2));
    }

    private String getDivisorResultSeparator() {
        if (getCountsOfDigits(setOfValues.getDivisor()) >= getCountsOfDigits(setOfValues.getFinalResult())) {
            return "-".repeat(getCountsOfDigits(setOfValues.getDivisor()));
        }
        return "-".repeat(getCountsOfDigits(setOfValues.getFinalResult()));
    }

    private int getPointerPosition(int dividend, int divisor) {
        if (dividend - divisor == 0) {
            return pointerPosition + getCountsOfDigits(dividend);
        }
        return pointerPosition + (getCountsOfDigits(dividend) - getCountsOfDigits(dividend - divisor));
    }

}
