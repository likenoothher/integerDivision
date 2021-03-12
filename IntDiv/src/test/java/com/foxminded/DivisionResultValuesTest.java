package com.foxminded;

import static org.junit.Assert.*;

import org.junit.Test;

public class DivisionResultValuesTest {

    @Test
    public void whenDividendIsPostiveDivisorIsZero_throwIAException() {
        assertThrows(IllegalArgumentException.class, () -> DivisionResultValues.createSetOfValues(1224, 0));
    }

    @Test
    public void whenDividendIsNegativeDivisorIsZero_throwIAException() {
        assertThrows(IllegalArgumentException.class, () -> DivisionResultValues.createSetOfValues(-1224, 0));
    }

    @Test
    public void whenTwoOfComponentsIsZero_throwIAException() {
        assertThrows(IllegalArgumentException.class, () -> DivisionResultValues.createSetOfValues(0, 0));
    }

    @Test
    public void whenDividendIsZeroDivisorNeg_thenDivisorPosTemplateIntermediateArraysHasOneZeroValue() {
        DivisionResultValues result = DivisionResultValues.createSetOfValues(0, 25);

        assertEquals(Integer.valueOf(result.getDividend()), new Integer(0));
        assertEquals(Integer.valueOf(result.getDivisor()), new Integer(25));
        assertEquals(Integer.valueOf(result.getFinalResult()), new Integer(0));
        assertEquals(Integer.valueOf(result.getFinalReminder()), new Integer(0));

        assertEquals(result.getIntermediateDividends().get(result.getIntermediateDividends().size() - 1), 0);
        assertEquals(result.getIntermediateDivisors().get(result.getIntermediateDividends().size() - 1), 0);
    }

    @Test
    public void whenDividendZeroDivisorNeg_thenDivisorPosTemplateIntermediateArraysHasOneZeroValue() {
        DivisionResultValues result = DivisionResultValues.createSetOfValues(0, -25);

        assertEquals(Integer.valueOf(result.getDividend()), new Integer(0));
        assertEquals(Integer.valueOf(result.getDivisor()), new Integer(25));
        assertEquals(Integer.valueOf(result.getFinalResult()), new Integer(0));
        assertEquals(Integer.valueOf(result.getFinalReminder()), new Integer(0));

        assertEquals(result.getIntermediateDividends().get(result.getIntermediateDividends().size() - 1), 0);
        assertEquals(result.getIntermediateDivisors().get(result.getIntermediateDividends().size() - 1), 0);
    }

    @Test
    public void whenOneIterationDivision_thenTemplateIntermediateArraysHasOneValueOfDivident() {
        DivisionResultValues result = DivisionResultValues.createSetOfValues(25, 5);

        assertEquals(Integer.valueOf(result.getDividend()), new Integer(25));
        assertEquals(Integer.valueOf(result.getDivisor()), new Integer(5));
        assertEquals(Integer.valueOf(result.getFinalResult()), new Integer(5));
        assertEquals(Integer.valueOf(result.getFinalReminder()), new Integer(0));

        assertEquals(result.getIntermediateDividends().get(result.getIntermediateDividends().size() - 1), 25);
        assertEquals(result.getIntermediateDivisors().get(result.getIntermediateDividends().size() - 1), 25);
    }

    @Test
    public void whenMultiIterationDivision_thenTemplateIntermediateArraysHasFewValues() {
        DivisionResultValues result = DivisionResultValues.createSetOfValues(518003, 42);

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
    public void whenMultiIterDivisionOneComponentNeg_thenComponentPosTemplateIntermediateArraysHasFewValues() {
        DivisionResultValues result = DivisionResultValues.createSetOfValues(-518003, 42);

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
    public void whenMultiIterDivisionTwoComponentIsNeg_thenComponentsPosTemplateIntermediateArraysHasFewValues() {
        DivisionResultValues result = DivisionResultValues.createSetOfValues(-518003, -42);

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
    public void whenMultiIterDivisionWithZeroTransReminder_thenTemplateIntermediateArraysHasFewValuesWithZerosIncluded() {
        DivisionResultValues result = DivisionResultValues.createSetOfValues(1001001, 1);

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
