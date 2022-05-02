package com.company;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZdarzenieRozpoczeciaObslugi extends BasicSimEvent<Poczta, Object> {


    public ZdarzenieRozpoczeciaObslugi(Poczta poczta, double delay) throws SimControlException
    {
        super(poczta, delay);
    }


    @Override
    protected void stateChange() throws SimControlException {
        Poczta poczta = getSimObj();

        if(!poczta.kolejka.isEmpty()){
            for (Okienko Tokienka:poczta.okienka) {
                if(!Tokienka.zajete) {
                    Tokienka.zajete =  true;
                    Interesant interesant = poczta.kolejka.remove(0);
                    Tokienka.poczta.dlugoscKolejki.setValue(Tokienka.poczta.kolejka.size());
                    interesant.zniecierpliwienie.terminate();

                    Tokienka.interesant = interesant;

                    double wybor = Tokienka.poczta.rng.exponential(poczta.mi);
                    Tokienka.pZakonczenieObslugi = new ZdarzenieZakonczenieObslugi(Tokienka, wybor,interesant);
                    Tokienka.poczta.iloscZajetychOkienek.setValue(poczta.iloscZajetychOkienek.getValue() + 1);

                    System.out.format("[%f] Interesant o ID: %d jest obslugiwany\n", simTime(), interesant.id);
                    break;
                }
            }
        }
    }

    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    public Interesant getEventParams() {
        return null;
    }
}
