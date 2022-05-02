package com.company;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZdarzenieZniecierpliwienie extends BasicSimEvent<Interesant, Object> {
    public ZdarzenieZniecierpliwienie(Interesant entity, double delay) throws SimControlException {
        super(entity, delay);
    }

    @Override
    protected void stateChange() throws SimControlException {
        Interesant interesant = getSimObj();
        interesant.otoczenie.strata++;
        interesant.otoczenie.poczta.kolejka.remove(interesant);
        System.out.format("[%f] Interesant o ID: %d sie zniecierpliwil\n", simTime(), interesant.id);
    }

    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    public Object getEventParams() {
        return null;
    }
}
