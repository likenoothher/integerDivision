package com.foxminden;

import java.util.ArrayList;

public class Template {
    private Integer devident;
    private Integer devisor;
    private ArrayList<Integer> tempDevident = new ArrayList();
    private ArrayList<Integer> tempDevisor = new ArrayList();
    private Integer endResult;
    private Integer endReminder;

    public Template(Integer devident, Integer devisor) {
        this.devident = devident;
        this.devisor = devisor;
        this.endResult = devident / devisor;
        this.endReminder = devident % devisor;
    }

    public void addToTempDevident(Integer num) {
        this.tempDevident.add(num);
    }

    public void addToTempDevisor(Integer num) {
        this.tempDevisor.add(num);
    }

    public Integer getDevident() {
        return devident;
    }

    public Integer getDevisor() {
        return devisor;
    }

    public ArrayList<Integer> getTempDevident() {
        return tempDevident;
    }

    public ArrayList<Integer> getTempDevisor() {
        return tempDevisor;
    }

    public void setTempDevisor(ArrayList<Integer> tempDevisor) {
        this.tempDevisor = tempDevisor;
    }

    public Integer getEndResult() {
        return endResult;
    }

    public Integer getEndReminder() {
        return endReminder;
    }

    @Override
    public String toString() {
        return "Template [devident=" + devident + ", devisor=" + devisor + ", tempDevident=" + tempDevident
                + ", tempDevisor=" + tempDevisor + ", endResult=" + endResult + ", endReminder=" + endReminder + "]";
    }

}
