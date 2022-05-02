package com.company;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZdarzenieZakonczenieObslugi  extends BasicSimEvent<Okienko, Interesant> {
    public ZdarzenieZakonczenieObslugi(Okienko okienko, double delay, Interesant interesant) throws SimControlException {
        super(okienko,delay,interesant);
    }

    @Override
    protected void stateChange() throws SimControlException {
        Okienko okienko = getSimObj();
        okienko.zajete = false;

        double praw = okienko.poczta.rng.uniform(0,1);

        if(praw < okienko.poczta.p) {
            if(okienko.poczta.kolejka.size() < okienko.poczta.L) {
                if(!okienko.poczta.awariaKolejki) {
                    okienko.poczta.kolejka.add(okienko.interesant);
                    okienko.poczta.iloscZajetychOkienek.setValue(okienko.poczta.iloscZajetychOkienek.getValue() - 1);
                    System.out.format("[%f] Interesant o ID: %d wrocil do kolejki\n", simTime(), okienko.interesant.id);
                } else {
                    System.out.format("[%f] Interesant o ID: %d wyszedl /Awaria Kolejki/\n", simTime(), okienko.interesant.id);
                    okienko.poczta.otoczenie.strata++;
                    okienko.interesant.zniecierpliwienie.terminate();
                }
            } else {
                okienko.poczta.czasPrzebywania.setValue(simTime()-getEventParams().czasWejscia);
                okienko.poczta.iloscZajetychOkienek.setValue(okienko.poczta.iloscZajetychOkienek.getValue() - 1);
                System.out.format("[%f] Interesant o ID: %d wyszedl /Kolejka Pelna/ \n", simTime(), okienko.interesant.id);
                okienko.poczta.otoczenie.strata++;
                okienko.interesant.zniecierpliwienie.terminate();
            }
        } else {
            okienko.poczta.czasPrzebywania.setValue(simTime()-getEventParams().czasWejscia);
            okienko.poczta.iloscZajetychOkienek.setValue(okienko.poczta.iloscZajetychOkienek.getValue() - 1);
            System.out.format("[%f] Interesant o ID: %d wyszedl\n", simTime(), okienko.interesant.id);
            okienko.interesant.zniecierpliwienie.terminate();
        }

        for(Okienko Tokienko:okienko.poczta.okienka) {
            if(!Tokienko.zajete) {
                new ZdarzenieRozpoczeciaObslugi(Tokienko.poczta, 0);
            }
        }
    }

    @Override
    protected void onTermination() throws SimControlException {
        System.out.format("[%f] Interesant czeka przy okienku\n", simTime());
    }

    @Override
    public Interesant getEventParams() {
        return this.eventParams;
    }
}
