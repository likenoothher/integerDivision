package com.foxminden;

import static org.junit.Assert.*;

import org.junit.Test;

public class TemplateFormatterTest {

    @Test
    public void whenOneIterationDivisionWthOutTransientZeroBlocks_thenOneBlockPrintSkipedGetBodyMethod() {
        Template template = TemplateBuilder.createTemplate(20, 20);
        String expected =
                  "_20|20\n"
                + " 20|--\n"
                + " --|1\n"
                + "  0";
        String result = TemplateFormatter.formatTemplate(template);
        assertEquals(expected, result);
    }

    @Test
    public void whenMultiIterationDivisionWthOutTransientZeroBlocks_thenMultiBlockPrint() {
        Template template = TemplateBuilder.createTemplate(78945, 4);
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
        String result = TemplateFormatter.formatTemplate(template);
        assertEquals(expected, result);
    }

    @Test
    public void whenMultiIterationDivisionWithTransientZeroBlocks_thenMultiBlockPrintSkipedBlocksWithZeroDivision() {
        Template template = TemplateBuilder.createTemplate(1001001, 1);
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
        String result = TemplateFormatter.formatTemplate(template);
        assertEquals(expected, result);
    }

    @Test
    public void whenMultiIterationDivisionOneOfComponentIsNegatinve_thenTransformNegativeComponentToPositiveMultiBlockPrint() {
        Template template = TemplateBuilder.createTemplate(78945, -4);
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
        String result = TemplateFormatter.formatTemplate(template);
        assertEquals(expected, result);
    }

}
