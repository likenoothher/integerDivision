package com.foxminded;

class IntegerDivisionApp {

    public static void main(String[] args) {
        DivisionResultValues drt = DivisionResultValues.of(55,45);
        DivisionResultValues drt2 = DivisionResultValues.of(7815,4);
        DivisionValuesFormatter tf = new DivisionValuesFormatter();
        System.out.println(tf.getStringRepresentation(drt));
        System.out.println(tf.getStringRepresentation(drt2));

    }
}
