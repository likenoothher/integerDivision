package com.foxminded;

class IntegerDivisionApp {

    public static void main(String[] args) {
        DivisionResultValues drt = DivisionResultValues.createSetOfValues(0,25);
        DivisionResultValues drt2 = DivisionResultValues.createSetOfValues(7815,20);
        DivisionValuesFormatter tf = new DivisionValuesFormatter();
        System.out.println(tf.getStringRepresentation(drt));
        System.out.println(tf.getStringRepresentation(drt2));

    }
}
