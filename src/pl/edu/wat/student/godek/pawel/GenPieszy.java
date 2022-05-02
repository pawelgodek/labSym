package pl.edu.wat.student.godek.pawel;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class GenPieszy extends BasicSimEvent<Szlak, Object> {

    public GenPieszy(Szlak szlak, Object o, double period) throws SimControlException {
        super(szlak, o, period);
    }

    @Override
    protected void stateChange() throws SimControlException {
        Szlak szlak = getSimObj();

        double DNP = szlak.rng.uniform(szlak.minT, szlak.maxT);
        double predkosc = szlak.rng.uniform(szlak.minV, szlak.maxV);
        int id = 0;
        if(szlak.czasDNP <= simTime()) {
            if(!szlak.listaPieszych.isEmpty()) {
                id = szlak.listaPieszych.get(szlak.listaPieszych.size() - 1).id + 1;
            }
            Pieszy pieszy = new Pieszy(id, predkosc, simTime());
            szlak.listaPieszych.add(pieszy);

            System.out.format("[%f] Pojawil sie pieszy o id %d. \n", simTime(), pieszy.id, pieszy.przebytaDroga);
            szlak.liczbaPieszych.setValue(szlak.liczbaPieszych.getValue() + 1);
            szlak.czasDNP = simTime() + DNP;
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
