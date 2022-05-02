package com.company;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZdarzenieAwaria extends BasicSimEvent<Poczta, Object> {
    public ZdarzenieAwaria(Poczta entity, double delay) throws SimControlException {
        super(entity, delay);
    }

    @Override
    protected void stateChange() throws SimControlException {
        Poczta poczta = getSimObj();

        if(poczta.otoczenie.licznikInteresantow < poczta.otoczenie.maxInteresantow) {
            poczta.awariaKolejki = true;
            System.out.format("[%f] Awaria kolejki! \n", simTime());

            for (Interesant interesant : poczta.kolejka) {
                System.out.format("[%f] Interesant o ID: %d zostal wyrzucony z kolejki! \n", simTime(), interesant.id);
                poczta.otoczenie.strata++;
                interesant.zniecierpliwienie.terminate();
            }
            poczta.kolejka.removeAll(poczta.kolejka);
            poczta.dlugoscKolejki.setValue(0);

            new ZdarzenieNaprawa(poczta, poczta.rng.exponential(poczta.otoczenie.b));
        }
    }

    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    public Object getEventParams() {
        return null;
    }
}
