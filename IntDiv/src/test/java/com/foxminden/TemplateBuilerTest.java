package com.foxminden;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class TemplateBuilerTest {

    @Test
    public void whenOneOrTwoArgementsIsNull_throwIAException() {
        assertThrows(IllegalArgumentException.class, () -> TemplateBuilder.createTemplate(null, 5));
    }

    @Test
    public void whenDevisorIsZero_throwIAException() {
        assertThrows(IllegalArgumentException.class, () -> TemplateBuilder.createTemplate(1224, 0));
    }

    @Test
    public void whenOneIterationDivision_thenCreateTemplateTempArraysVariableSizeEquals1() {
        Template result = TemplateBuilder.createTemplate(25, 5);

        assertEquals(result.getDevident(), new Integer(25));
        assertEquals(result.getDevisor(), new Integer(5));
        assertEquals(result.getEndResult(), new Integer(5));
        assertEquals(result.getEndReminder(), new Integer(0));

        assertEquals(result.getTempDevident(), Arrays.asList(25));
        assertEquals(result.getTempDevisor(), Arrays.asList(25));
    }

    @Test
    public void whenMultiIterationDivision_thenCreateTemplateTempArraysVariableSizeMore1() {
        Template result = TemplateBuilder.createTemplate(518003, 42);

        assertEquals(result.getDevident(), new Integer(518003));
        assertEquals(result.getDevisor(), new Integer(42));
        assertEquals(result.getEndResult(), new Integer(12333));
        assertEquals(result.getEndReminder(), new Integer(17));

        assertEquals(result.getTempDevident(), Arrays.asList(51, 98, 140, 140, 143));
        assertEquals(result.getTempDevisor(), Arrays.asList(42, 84, 126, 126, 126));
    }

    @Test
    public void whenMultiIterationDivisionWithZeroTransReminder_thenCreateTemplateTempArraysVariableSizeMore1WithZerosIncluded() {
        Template result = TemplateBuilder.createTemplate(1001001, 1);

        assertEquals(result.getDevident(), new Integer(1001001));
        assertEquals(result.getDevisor(), new Integer(1));
        assertEquals(result.getEndResult(), new Integer(1001001));
        assertEquals(result.getEndReminder(), new Integer(0));

        assertEquals(result.getTempDevident(), Arrays.asList(1, 0, 0, 1, 0, 0, 1));
        assertEquals(result.getTempDevisor(), Arrays.asList(1, 0, 0, 1, 0, 0, 1));
    }

    @Test
    public void whenDevidentIsZero_thenCreateTemplateTempArraysVariableSizeEquals1() {
        Template result = TemplateBuilder.createTemplate(0, 100);

        assertEquals(result.getDevident(), new Integer(0));
        assertEquals(result.getDevisor(), new Integer(100));
        assertEquals(result.getEndResult(), new Integer(0));
        assertEquals(result.getEndReminder(), new Integer(0));

        assertEquals(result.getTempDevident(), Arrays.asList(0));
        assertEquals(result.getTempDevisor(), Arrays.asList(0));
    }

}
