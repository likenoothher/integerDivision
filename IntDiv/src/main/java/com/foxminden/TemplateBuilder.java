package com.foxminden;

public class TemplateBuilder {

    private TemplateBuilder() {
    }

    public static Template createTemplate(Integer divident, Integer divisor) {
        validateInitialInput(divident, divisor);
        divident = Math.abs(divident);
        divisor = Math.abs(divisor);
        Template template = new Template(divident, divisor);

        String[] dividentDigits = String.valueOf(divident).split("");
        StringBuilder transientDividentSB = new StringBuilder();

        for (int i = 0; i < dividentDigits.length; i++) {
            transientDividentSB.append(dividentDigits[i]);
            Integer transDivident = Integer.parseInt(transientDividentSB.toString());

            if (transDivident >= divisor || transientDividentSB.toString().equals("0")) {
                template.addToTempDevident(transDivident);
                template.addToTempDevisor((transDivident / divisor) * divisor);
                transientDividentSB.delete(0, transientDividentSB.length());

                if (transDivident % divisor != 0) {
                    transientDividentSB.append(String.valueOf(transDivident % divisor));
                }
            }
        }
        return template;
    }

    private static void validateInitialInput(Integer divident, Integer divisor) {
        if (divident == null || divisor == null) {
            throw new IllegalArgumentException("Arguments shouldn't be null");
        }
        if (divisor == 0) {
            throw new IllegalArgumentException("Divisor shouldn't be zero");
        }
    }

}
