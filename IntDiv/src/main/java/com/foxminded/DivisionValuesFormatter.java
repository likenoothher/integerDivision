package com.foxminded;

import java.util.HashMap;
import java.util.Map;

import gnu.trove.list.TIntList;

public class DivisionValuesFormatter {

    private static final String BEFORE_DIVISOR_INDENT = "beforeDivisorIndent";
    private static final String AFTER_DIVISOR_INDENT = "afterDivisorIndent";
    private static final String DIVISOR_UNDERLINE = "divisorUnderline";

    private static final String FIRST_LINE_TEMPLATE_CUT_HEADER = "%d|%d\n";
    private static final String SECOND_LINE_TEMPLATE_CUT_HEADER = "%s|%s\n";
    private static final String THIRD_LINE_TEMPLATE_CUT_HEADER = "%s|%d\n";

    private static final String FIRST_LINE_TEMPLATE_FULL_HEADER = "_%d|%d\n";
    private static final String SECOND_LINE_TEMPLATE_FULL_HEADER = " %s%d%s|%s\n";
    private static final String THIRD_LINE_TEMPLATE_FULL_HEADER = " %s%s%s|%d\n";

    private static final String FIRST_LINE_TEMPLATE_BLOCK = "%s_%d\n";
    private static final String SECOND_LINE_TEMPLATE_BLOCK = "%s %s%d\n";
    private static final String THIRD_LINE_TEMPLATE_BLOCK = "%s %s%s\n";

    private final Map<String, String> utilityHeaderStrings = new HashMap<>();

    private DivisionResultValues setOfValues;

    private int pointerPosition;

    public String getStringRepresentation(DivisionResultValues setOfValues) {
        pointerPosition = 0;
        utilityHeaderStrings.clear();
        this.setOfValues = setOfValues;
        StringBuilder setOfValuesRepresentation = new StringBuilder();

        fillHeaderUtilityStrings();

        appendHeader(setOfValuesRepresentation);

        if (setOfValues.getIntermediateDividends().size() > 1) {
            appendDivisionChain(setOfValuesRepresentation);
        }

        appendReminder(setOfValuesRepresentation);

        return setOfValuesRepresentation.toString();
    }

    private void fillHeaderUtilityStrings() {
        utilityHeaderStrings.put(BEFORE_DIVISOR_INDENT,
                " ".repeat(getDigitsNumberDifference(setOfValues.getFirstIntermediateDividend(),
                        setOfValues.getFirstIntermediateDivisor())));
        utilityHeaderStrings.put(AFTER_DIVISOR_INDENT,
                " ".repeat(getCountsOfDigits(setOfValues.getDividend())
                        - utilityHeaderStrings.get(BEFORE_DIVISOR_INDENT).length()
                        - getCountsOfDigits(setOfValues.getFirstIntermediateDivisor())));
        utilityHeaderStrings.put(DIVISOR_UNDERLINE,
                "-".repeat(getCountsOfDigits(setOfValues.getFirstIntermediateDivisor())));

    }

    private void appendHeader(StringBuilder setOfValuesRepresentation) {
        if (setOfValues.getDividend() != 0) {
            appendFullHeader(setOfValuesRepresentation);
        } else {
            appendCutHeader(setOfValuesRepresentation);
        }
        pointerPosition = getPointerPosition(setOfValues.getFirstIntermediateDividend(),
                setOfValues.getFirstIntermediateDivisor());

    }

    private void appendCutHeader(StringBuilder setOfValuesRepresentation) {
        String divisorResultSeparator = getDivisorResultSeparator();

        setOfValuesRepresentation
                .append(String.format(FIRST_LINE_TEMPLATE_CUT_HEADER, setOfValues.getDividend(),
                        setOfValues.getDivisor()))
                .append(String.format(SECOND_LINE_TEMPLATE_CUT_HEADER,
                        " ".repeat(getCountsOfDigits(setOfValues.getDividend())), divisorResultSeparator))
                .append(String.format(THIRD_LINE_TEMPLATE_CUT_HEADER,
                        " ".repeat(getCountsOfDigits(setOfValues.getDividend())), setOfValues.getFinalResult()));
    }

    private void appendFullHeader(StringBuilder setOfValuesRepresentation) {
        String beforeDivisorIndent = utilityHeaderStrings.get(BEFORE_DIVISOR_INDENT);
        String afterDivisorIndent = utilityHeaderStrings.get(AFTER_DIVISOR_INDENT);
        String divisorUnderline = utilityHeaderStrings.get(DIVISOR_UNDERLINE);
        String divisorResultSeparator = getDivisorResultSeparator();

        setOfValuesRepresentation
                .append(String.format(FIRST_LINE_TEMPLATE_FULL_HEADER, setOfValues.getDividend(),
                        setOfValues.getDivisor()))
                .append(String.format(SECOND_LINE_TEMPLATE_FULL_HEADER, beforeDivisorIndent,
                        setOfValues.getFirstIntermediateDivisor(), afterDivisorIndent, divisorResultSeparator))
                .append(String.format(THIRD_LINE_TEMPLATE_FULL_HEADER, beforeDivisorIndent, divisorUnderline,
                        afterDivisorIndent, setOfValues.getFinalResult()));
    }

    private void appendDivisionChain(StringBuilder setOfValuesRepresentation) {
        TIntList intermediateDividends = setOfValues.getIntermediateDividends();
        TIntList intermediateDivisors = setOfValues.getIntermediateDivisors();
        for (int i = 1; i < intermediateDividends.size(); i++) {
            if (intermediateDividends.get(i) != 0) {
                setOfValuesRepresentation.append(appendDivisionBlock(i));
                pointerPosition = getPointerPosition(intermediateDividends.get(i), intermediateDivisors.get(i));
            } else {
                pointerPosition = getPointerPosition(intermediateDividends.get(i), intermediateDivisors.get(i));
            }
        }
    }

    private String appendDivisionBlock(int stepOfCycle) {
        int dividend = setOfValues.getIntermediateDividends().get(stepOfCycle);
        int divisor = setOfValues.getIntermediateDivisors().get(stepOfCycle);
        String generalIndent = " ".repeat(pointerPosition);
        String divisorIndent = " ".repeat(getDigitsNumberDifference(dividend, divisor));
        String divisorUnderline = "-".repeat(getCountsOfDigits(divisor));

        return String.format(FIRST_LINE_TEMPLATE_BLOCK, generalIndent, dividend)
                + String.format(SECOND_LINE_TEMPLATE_BLOCK, generalIndent, divisorIndent, divisor)
                + String.format(THIRD_LINE_TEMPLATE_BLOCK, generalIndent, divisorIndent, divisorUnderline);
    }

    private void appendReminder(StringBuilder setOfValuesRepresentation) {
        if (setOfValues.getDividend() != 0) {
            setOfValuesRepresentation.append(" "
                    .repeat(getCountsOfDigits(setOfValues.getDividend())
                            - getCountsOfDigits(setOfValues.getFinalReminder()) + 1)
                    + setOfValues.getFinalReminder() + "\n");
        }
    }

    private int getCountsOfDigits(int number) {
        return (number == 0) ? 1 : (int) Math.log10(number) + 1;
    }

    private int getDigitsNumberDifference(int number1, int number2) {
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
