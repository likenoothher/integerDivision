package com.foxminded;

class IntegerDivisionApp {

    public static void main(String[] args) {
        DivisionResultTemplate drt = DivisionResultTemplate.createTemplate(78945,45);
        DivisionResultTemplate drt2 = DivisionResultTemplate.createTemplate(7815,20);
        DivisionResultTemplateFormatter tf = new DivisionResultTemplateFormatter();
        System.out.println(tf.getStringRepresentation(drt));
        System.out.println(tf.getStringRepresentation(drt2));

    }
}
