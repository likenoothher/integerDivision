package com.foxminded;

class IntegerDivisionApp {

    public static void main(String[] args) {
        buildDivision(500054, 100);

    }

    private static void buildDivision(int dividend, int divisor) {
        Template template = TemplateBuilder.createTemplate(dividend, divisor);
        System.out.println(TemplateFormatter.formatTemplate(template));
    }

}
