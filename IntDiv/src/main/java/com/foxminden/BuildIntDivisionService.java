package com.foxminden;

class BuildIntDivisionService {

    public static void main(String[] args) {
        buildDivision(0, 100);

    }

    private static void buildDivision(Integer divident, Integer divisor) {
        Template template = TemplateBuilder.createTemplate(divident, divisor);
        System.out.println(TemplateFormatter.formatTemplate(template));
    }

}
