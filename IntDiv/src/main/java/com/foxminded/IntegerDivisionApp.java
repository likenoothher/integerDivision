package com.foxminded;

class IntegerDivisionApp {

    public static void main(String[] args) {
        DivisionResultValues drt = DivisionResultValues.createTemplate(0,25);
        DivisionResultValues drt2 = DivisionResultValues.createTemplate(7815,20);
        DivisionValuesFormatter tf = new DivisionValuesFormatter();
        System.out.println(tf.getStringRepresentation(drt));
        System.out.println(tf.getStringRepresentation(drt2));

    }
}
