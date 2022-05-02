package com.company;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZdarzenieNaprawa extends BasicSimEvent<Poczta, Object> {
    public ZdarzenieNaprawa(Poczta entity, double delay) throws SimControlException {
        super(entity, delay);
    }

    @Override
    protected void stateChange() throws SimControlException {
        Poczta poczta = getSimObj();

        poczta.awariaKolejki = false;

        System.out.format("[%f] Kolejka naprawiona \n", simTime());
        new ZdarzenieAwaria(poczta, poczta.rng.exponential(poczta.otoczenie.a));
    }

    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    public Object getEventParams() {
        return null;
    }
}
