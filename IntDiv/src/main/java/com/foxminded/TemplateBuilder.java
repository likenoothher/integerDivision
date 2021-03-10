package com.foxminded;

public class TemplateBuilder {

    private TemplateBuilder() {
    }

    public static Template createTemplate(int dividend,int divisor) {
        validateInitialInput(dividend, divisor);
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        Template template = new Template(dividend, divisor);

        String[] dividendDigits = String.valueOf(dividend).split("");
        StringBuilder intermediateDividendSB = new StringBuilder();

        for (int i = 0; i < dividendDigits.length; i++) {
            intermediateDividendSB.append(dividendDigits[i]);
           int intermediaeteDividend = Integer.parseInt(intermediateDividendSB.toString());

            if (intermediaeteDividend >= divisor || intermediateDividendSB.toString().equals("0")) {
                template.addToIntermediateDividends(intermediaeteDividend);
                template.addToIntermediateDivisors((intermediaeteDividend / divisor) * divisor);
                intermediateDividendSB.delete(0, intermediateDividendSB.length());

                if (intermediaeteDividend % divisor != 0) {
                    intermediateDividendSB.append(String.valueOf(intermediaeteDividend % divisor));
                }
            }
        }
        return template;
    }

    private static void validateInitialInput(Integer dividend,Integer divisor) {
        if (dividend == null || divisor == null) {
            throw new IllegalArgumentException("Arguments shouldn't be null");
        }
        if (divisor == 0) {
            throw new IllegalArgumentException("Divisor shouldn't be zero");
        }
    }

}
