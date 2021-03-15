package com.foxminded;

import static org.junit.Assert.*;

import org.junit.Test;

public class DivisionValuesFormatterTest {

    @Test
    public void whenOneIterationDivisionWthOutTransientZeroBlocks_thenOneBlockPrintSkipedGetBodyMethod() {
        DivisionResultValues template = DivisionResultValues.initializeSetOfValuesFields(20, 20);
        String expected =
                  "_20|20\n"
                + " 20|--\n"
                + " --|1\n"
                + "  0";
        String result = new DivisionValuesFormatter().getStringRepresentation(template);
        assertEquals(expected, result);
    }

    @Test
    public void whenDividendIsZeroDivisorIsPositive_thenPrintInputAndResult() {
        DivisionResultValues template = DivisionResultValues.initializeSetOfValuesFields(0, 45);
        String expected =
                  "0|45\n"
                + " |--\n"
                + " |0\n";
        String result = new DivisionValuesFormatter().getStringRepresentation(template);
        assertEquals(expected, result);
    }

    @Test
    public void whenDividendIsZeroDivisorIsNegative_thenDivisorPositivePrintInputAndResult() {
        DivisionResultValues template = DivisionResultValues.initializeSetOfValuesFields(0, -45);
        String expected =
                  "0|45\n"
                + " |--\n"
                + " |0\n";
        String result = new DivisionValuesFormatter().getStringRepresentation(template);
        assertEquals(expected, result);
    }

    @Test
    public void whenMultiIterationDivisionWthOutTransientZeroBlocks_thenMultiBlockPrint() {
        DivisionResultValues template = DivisionResultValues.initializeSetOfValuesFields(78945, 4);
        String expected =
                    "_78945|4\n"
                  + " 4    |-----\n"
                  + " -    |19736\n"
                  + "_38\n"
                  + " 36\n"
                  + " --\n"
                  + " _29\n"
                  + "  28\n"
                  + "  --\n"
                  + "  _14\n"
                  + "   12\n"
                  + "   --\n"
                  + "   _25\n"
                  + "    24\n"
                  + "    --\n"
                  + "     1";
        String result = new DivisionValuesFormatter().getStringRepresentation(template);
        assertEquals(expected, result);
    }

    @Test
    public void whenMultiIterDivisionOneComponentNeg_thenNegComponentToPositiveMultiBlockPrint() {
        DivisionResultValues template = DivisionResultValues.initializeSetOfValuesFields(78945, -4);
        String expected =
                    "_78945|4\n"
                  + " 4    |-----\n"
                  + " -    |19736\n"
                  + "_38\n"
                  + " 36\n"
                  + " --\n"
                  + " _29\n"
                  + "  28\n"
                  + "  --\n"
                  + "  _14\n"
                  + "   12\n"
                  + "   --\n"
                  + "   _25\n"
                  + "    24\n"
                  + "    --\n"
                  + "     1";
        String result = new DivisionValuesFormatter().getStringRepresentation(template);
        assertEquals(expected, result);
    }

    @Test
    public void whenMultiIterDivisionTwoComponentNeg_thenNegeComponentToPositiveMultiBlockPrint() {
        DivisionResultValues template = DivisionResultValues.initializeSetOfValuesFields(-78945, -4);
        String expected =
                    "_78945|4\n"
                  + " 4    |-----\n"
                  + " -    |19736\n"
                  + "_38\n"
                  + " 36\n"
                  + " --\n"
                  + " _29\n"
                  + "  28\n"
                  + "  --\n"
                  + "  _14\n"
                  + "   12\n"
                  + "   --\n"
                  + "   _25\n"
                  + "    24\n"
                  + "    --\n"
                  + "     1";
        String result = new DivisionValuesFormatter().getStringRepresentation(template);
        assertEquals(expected, result);
    }

    @Test
    public void whenMultiIterationDivisionWithTransientZeroBlocks_thenMultiBlockPrintSkipedBlocksWithZeroDivision() {
        DivisionResultValues template = DivisionResultValues.initializeSetOfValuesFields(1001001, 1);
        String expected =
                  "_1001001|1\n"
                + " 1      |-------\n"
                + " -      |1001001\n"
                + "   _1\n"
                + "    1\n"
                + "    -\n"
                + "      _1\n"
                + "       1\n"
                + "       -\n"
                + "       0";
        String result = new DivisionValuesFormatter().getStringRepresentation(template);
        assertEquals(expected, result);
    }
}
