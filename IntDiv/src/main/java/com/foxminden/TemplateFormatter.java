package com.foxminden;

import java.util.ArrayList;

public class TemplateFormatter {
    private static int pointerPosition = 1;

    public static String formatTemplate(Template template) {
        String result;
        result = getHeader(template);

        if (template.getTempDevident().size() > 1) {
            result += getBody(template);
        }

        result += getReminder(template);
        pointerPosition = 1;

        return result;
    }

    private static String getHeader(Template template) {
        Integer devident = template.getDevident();
        Integer firstTempDevident = template.getTempDevident().get(0);
        Integer firstTempDivisor = template.getTempDevisor().get(0);
        Integer endResult = template.getEndResult();

        int splitterLenght = (template.getEndResult() > template.getDevisor()) ? getLenght(endResult)
                : getLenght(template.getDevisor());

        StringBuilder header = new StringBuilder();
        header.append("_" + devident + "|" + template.getDevisor() + "\n");

        header.append(addSomeSymb(" ", getLenghtDiff(firstTempDevident, firstTempDivisor) + 1) + firstTempDivisor
                + addSomeSymb(" ",
                        getLenghtDiff(devident, firstTempDivisor) - getLenghtDiff(firstTempDevident, firstTempDivisor))
                + "|" + addSomeSymb("-", splitterLenght) + "\n");

        header.append(addSomeSymb(" ", getLenghtDiff(firstTempDevident, firstTempDivisor) + 1)
                + addSomeSymb("-", getLenght(firstTempDivisor))
                + addSomeSymb(" ",
                        getLenghtDiff(devident, firstTempDivisor) - getLenghtDiff(firstTempDevident, firstTempDivisor))
                + "|" + String.valueOf(endResult) + "\n");

        return header.toString();
    }

    private static String getBody(Template template) {
        StringBuilder body = new StringBuilder();
        ArrayList<Integer> tempDevident = template.getTempDevident();
        ArrayList<Integer> tempDivisor = template.getTempDevisor();

        pointerPosition = shiftPosition(tempDevident.get(0), tempDivisor.get(0));

        for (int i = 1; i < tempDevident.size(); i++) {
            if (tempDevident.get(i) != 0) {
                body.append(addSomeSymb(" ", pointerPosition - 1) + "_" + tempDevident.get(i) + "\n");
                body.append(addSomeSymb(" ", pointerPosition) + tempDivisor.get(i) + "\n");
                body.append(addSomeSymb(" ", pointerPosition) + addSomeSymb("-", getLenght(tempDivisor.get(i))) + "\n");
                pointerPosition = shiftPosition(tempDevident.get(i), tempDivisor.get(i));
            } else {
                pointerPosition = shiftPosition(tempDevident.get(i), tempDivisor.get(i));
            }
        }

        return body.toString();

    }

    private static String getReminder(Template template) {
        Integer devident = template.getDevident();
        Integer endReminder = template.getEndReminder();
        StringBuilder reminder = new StringBuilder();
        reminder.append(addSomeSymb(" ", getLenght(devident) - getLenght(endReminder) + 1) + endReminder);
        return reminder.toString();
    }

    private static String addSomeSymb(String symbol, int amount) {
        String str = "";
        for (int i = 0; i < amount; i++) {
            str = str.concat(symbol);
        }
        return str;
    }

    private static int getLenght(int num) {
        return String.valueOf(num).length();
    }

    private static int getLenghtDiff(int num1, int num2) {
        return Math.abs(getLenght(num1) - getLenght(num2));
    }

    private static int shiftPosition(int devident, int devisor) {
        if (devident - devisor == 0) {
            return pointerPosition + getLenght(devident);
        } else
            return pointerPosition + (getLenght(devident) - getLenght(devident - devisor));
    }

}
