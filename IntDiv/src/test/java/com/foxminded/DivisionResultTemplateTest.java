package com.foxminded;

import static org.junit.Assert.*;

import org.junit.Test;

public class DivisionResultTemplateTest {

    @Test
    public void whenDivisorIsZero_throwIAException() {
        assertThrows(IllegalArgumentException.class, () -> DivisionResultTemplate.createTemplate(1224,0));
    }

    @Test
    public void whenDividendSmallerDivisor_throwIAException() {
        assertThrows(IllegalArgumentException.class, () -> DivisionResultTemplate.createTemplate(1224,5000));
    }

    @Test
    public void whenOneIterationDivision_thenCreateTemplateIntermediateArraysVariableSizeEquals1() {
        DivisionResultTemplate result = DivisionResultTemplate.createTemplate(25, 5);

        assertEquals(Integer.valueOf(result.getDividend()), new Integer(25));
        assertEquals(Integer.valueOf(result.getDivisor()), new Integer(5));
        assertEquals(Integer.valueOf(result.getFinalResult()), new Integer(5));
        assertEquals(Integer.valueOf(result.getFinalReminder()), new Integer(0));

        assertEquals(result.getIntermediateDividends().get(result.getIntermediateDividends().size() - 1), 25);
        assertEquals(result.getIntermediateDivisors().get(result.getIntermediateDividends().size() - 1), 25);
    }

    @Test
    public void whenMultiIterationDivision_thenCreateIntermediateTempArraysVariableSizeMore1() {
        DivisionResultTemplate result = DivisionResultTemplate.createTemplate(518003, 42);

        assertEquals(Integer.valueOf(result.getDividend()), new Integer(518003));
        assertEquals(Integer.valueOf(result.getDivisor()), new Integer(42));
        assertEquals(Integer.valueOf(result.getFinalResult()), new Integer(12333));
        assertEquals(Integer.valueOf(result.getFinalReminder()), new Integer(17));

        int[] expectedArrayOfDividends = new int[] { 51, 98, 140, 140, 143 };
        int[] expectedArrayOfDivisors = new int[] { 42, 84, 126, 126, 126 };
        assertArrayEquals(result.getIntermediateDividends().toArray(), expectedArrayOfDividends);
        assertArrayEquals(result.getIntermediateDivisors().toArray(), expectedArrayOfDivisors);

    }

    @Test
    public void whenMultiIterationDivisionWithZeroTransReminder_thenCreateTemplateIntermediateArraysVariableSizeMore1WithZerosIncluded() {
        DivisionResultTemplate result = DivisionResultTemplate.createTemplate(1001001, 1);

        assertEquals(Integer.valueOf(result.getDividend()), new Integer(1001001));
        assertEquals(Integer.valueOf(result.getDivisor()), new Integer(1));
        assertEquals(Integer.valueOf(result.getFinalResult()), new Integer(1001001));
        assertEquals(Integer.valueOf(result.getFinalReminder()), new Integer(0));

        int[] expectedArrayOfDividends = new int[] { 1, 0, 0, 1, 0, 0, 1 };
        int[] expectedArrayOfDivisors = new int[] { 1, 0, 0, 1, 0, 0, 1 };
        assertArrayEquals(result.getIntermediateDividends().toArray(), expectedArrayOfDividends);
        assertArrayEquals(result.getIntermediateDivisors().toArray(), expectedArrayOfDivisors);
    }
}
