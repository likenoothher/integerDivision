package com.foxminded;

import static org.junit.Assert.*;

import org.junit.Test;

public class DivisionResultValuesTest {

    @Test
    public void whenDividendIsPostiveDivisorIsZero_throwIAException() {
        assertThrows(IllegalArgumentException.class, () -> DivisionResultValues.initializeSetOfValuesFields(1224, 0));
    }

    @Test
    public void whenDividendIsNegativeDivisorIsZero_throwIAException() {
        assertThrows(IllegalArgumentException.class, () -> DivisionResultValues.initializeSetOfValuesFields(-1224, 0));
    }

    @Test
    public void whenTwoOfComponentsIsZero_throwIAException() {
        assertThrows(IllegalArgumentException.class, () -> DivisionResultValues.initializeSetOfValuesFields(0, 0));
    }

    @Test
    public void whenDividendIsZeroDivisorNeg_thenDivisorPosTemplateIntermediateArraysHasOneZeroValue() {
        DivisionResultValues result = DivisionResultValues.initializeSetOfValuesFields(0, 25);

        assertEquals(result.getDividend(), 0);
        assertEquals(result.getDivisor(), 25);
        assertEquals(result.getFinalResult(), 0);
        assertEquals(result.getFinalReminder(), 0);

        assertEquals(result.getIntermediateDividends().get(result.getIntermediateDividends().size() - 1), 0);
        assertEquals(result.getIntermediateDivisors().get(result.getIntermediateDividends().size() - 1), 0);
    }

    @Test
    public void whenDividendZeroDivisorNeg_thenDivisorPosTemplateIntermediateArraysHasOneZeroValue() {
        DivisionResultValues result = DivisionResultValues.initializeSetOfValuesFields(0, -25);

        assertEquals(result.getDividend(), 0);
        assertEquals(result.getDivisor(), 25);
        assertEquals(result.getFinalResult(), 0);
        assertEquals(result.getFinalReminder(), 0);

        assertEquals(result.getIntermediateDividends().get(result.getIntermediateDividends().size() - 1), 0);
        assertEquals(result.getIntermediateDivisors().get(result.getIntermediateDividends().size() - 1), 0);
    }

    @Test
    public void whenOneIterationDivision_thenTemplateIntermediateArraysHasOneValueOfDivident() {
        DivisionResultValues result = DivisionResultValues.initializeSetOfValuesFields(25, 5);

        assertEquals(result.getDividend(), 25);
        assertEquals(result.getDivisor(), 5);
        assertEquals(result.getFinalResult(), 5);
        assertEquals(result.getFinalReminder(), 0);

        assertEquals(result.getIntermediateDividends().get(result.getIntermediateDividends().size() - 1), 25);
        assertEquals(result.getIntermediateDivisors().get(result.getIntermediateDividends().size() - 1), 25);
    }

    @Test
    public void whenMultiIterationDivision_thenTemplateIntermediateArraysHasFewValues() {
        DivisionResultValues result = DivisionResultValues.initializeSetOfValuesFields(518003, 42);

        assertEquals(result.getDividend(), 518003);
        assertEquals(result.getDivisor(), 42);
        assertEquals(result.getFinalResult(), 12333);
        assertEquals(result.getFinalReminder(), 17);

        int[] expectedArrayOfDividends = new int[] { 51, 98, 140, 140, 143 };
        int[] expectedArrayOfDivisors = new int[] { 42, 84, 126, 126, 126 };
        assertArrayEquals(result.getIntermediateDividends().toArray(), expectedArrayOfDividends);
        assertArrayEquals(result.getIntermediateDivisors().toArray(), expectedArrayOfDivisors);
    }

    @Test
    public void whenMultiIterDivisionOneComponentNeg_thenComponentPosTemplateIntermediateArraysHasFewValues() {
        DivisionResultValues result = DivisionResultValues.initializeSetOfValuesFields(-518003, 42);

        assertEquals(result.getDividend(), 518003);
        assertEquals(result.getDivisor(), 42);
        assertEquals(result.getFinalResult(), 12333);
        assertEquals(result.getFinalReminder(), 17);

        int[] expectedArrayOfDividends = new int[] { 51, 98, 140, 140, 143 };
        int[] expectedArrayOfDivisors = new int[] { 42, 84, 126, 126, 126 };
        assertArrayEquals(result.getIntermediateDividends().toArray(), expectedArrayOfDividends);
        assertArrayEquals(result.getIntermediateDivisors().toArray(), expectedArrayOfDivisors);
    }

    @Test
    public void whenMultiIterDivisionTwoComponentIsNeg_thenComponentsPosTemplateIntermediateArraysHasFewValues() {
        DivisionResultValues result = DivisionResultValues.initializeSetOfValuesFields(-518003, -42);

        assertEquals(result.getDividend(), 518003);
        assertEquals(result.getDivisor(), 42);
        assertEquals(result.getFinalResult(), 12333);
        assertEquals(result.getFinalReminder(), 17);

        int[] expectedArrayOfDividends = new int[] { 51, 98, 140, 140, 143 };
        int[] expectedArrayOfDivisors = new int[] { 42, 84, 126, 126, 126 };
        assertArrayEquals(result.getIntermediateDividends().toArray(), expectedArrayOfDividends);
        assertArrayEquals(result.getIntermediateDivisors().toArray(), expectedArrayOfDivisors);

    }

    @Test
    public void whenMultiIterDivisionWithZeroTransReminder_thenTemplateIntermediateArraysHasFewValuesWithZerosIncluded() {
        DivisionResultValues result = DivisionResultValues.initializeSetOfValuesFields(1001001, 1);

        assertEquals(result.getDividend(), 1001001);
        assertEquals(result.getDivisor(), 1);
        assertEquals(result.getFinalResult(), 1001001);
        assertEquals(result.getFinalReminder(), 0);

        int[] expectedArrayOfDividends = new int[] { 1, 0, 0, 1, 0, 0, 1 };
        int[] expectedArrayOfDivisors = new int[] { 1, 0, 0, 1, 0, 0, 1 };
        assertArrayEquals(result.getIntermediateDividends().toArray(), expectedArrayOfDividends);
        assertArrayEquals(result.getIntermediateDivisors().toArray(), expectedArrayOfDivisors);
    }
}
