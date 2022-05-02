package com.company;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZdarzenieNowyInteresant extends BasicSimEvent<Otoczenie, Double> {

    public ZdarzenieNowyInteresant(Otoczenie otoczenie, double delay) throws  SimControlException{
        super(otoczenie,delay);
    }

    @Override
    protected void stateChange() throws SimControlException {
        Otoczenie otoczenie = getSimObj();

        Interesant interesant = new Interesant(otoczenie.licznikInteresantow, simTime(), otoczenie);
        otoczenie.licznikInteresantow++;

        double czasDoZniecierpliwienia = otoczenie.rng.exponential(otoczenie.ro);
        interesant.dodajZniecierpliwienie(new ZdarzenieZniecierpliwienie(interesant, czasDoZniecierpliwienia));

        new ZdarzenieRozpoczeciaObslugi(otoczenie.poczta, 0);


        System.out.format("[%f] Nowy interesant o ID: %d\n", simTime(), interesant.id);

        if(otoczenie.maxInteresantow > otoczenie.licznikInteresantow){
            double czasDoPojawienia = otoczenie.rng.exponential(otoczenie.lambda);
            if(!otoczenie.poczta.awariaKolejki) {
                if (otoczenie.poczta.kolejka.size() < otoczenie.poczta.L) {
                    otoczenie.poczta.kolejka.add(interesant);
                    otoczenie.poczta.dlugoscKolejki.setValue(otoczenie.poczta.kolejka.size());
                    new ZdarzenieNowyInteresant(otoczenie, czasDoPojawienia);
                } else {
                    otoczenie.strata++;
                    System.out.format("[%f] Interesant o ID: %d odszedl/Kolejka pelna/ \n", simTime(), interesant.id);
                    otoczenie.poczta.dlugoscKolejki.setValue(otoczenie.poczta.kolejka.size());
                    interesant.zniecierpliwienie.terminate();
                    new ZdarzenieNowyInteresant(otoczenie, czasDoPojawienia);
                }
            } else {
                System.out.format("[%f] Interesant o ID: %d odszedl /Awaria kolejki/ \n", simTime(), interesant.id);
                otoczenie.poczta.dlugoscKolejki.setValue(otoczenie.poczta.kolejka.size());
                otoczenie.strata++;
                interesant.zniecierpliwienie.terminate();
                new ZdarzenieNowyInteresant(otoczenie, czasDoPojawienia);
            }
        }
    }

    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    public Double getEventParams() {
        return this.eventParams;
    }

}
