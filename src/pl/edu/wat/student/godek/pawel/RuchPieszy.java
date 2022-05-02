package pl.edu.wat.student.godek.pawel;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class RuchPieszy extends BasicSimEvent<Szlak, Object> {
    public RuchPieszy(Szlak szlak, Object o, double period) throws SimControlException {
        super(szlak, o, period);
    }

    @Override
    protected void stateChange() throws SimControlException {
        Szlak szlak = getSimObj();
        if(!szlak.listaPieszych.isEmpty()) {
            for (Pieszy pieszy : szlak.listaPieszych) {
                pieszy.przebytaDroga = pieszy.przebytaDroga + (simTime() - pieszy.czasD) * pieszy.predkosc;
                System.out.format("[%f] Pieszy o id %d przebyl droge %f\n", simTime(), pieszy.id, pieszy.przebytaDroga);
                if (pieszy.przebytaDroga >= szlak.dlSzlaku) {
                    szlak.liczbaPieszych.setValue(szlak.liczbaPieszych.getValue() - 1);
                    szlak.czasPrzemarszu.setValue(simTime() - pieszy.czasD);
                    szlak.listaPieszych.remove(pieszy);
                    System.out.format("[%f] Pieszy o id %d ukonczyl szlak\n", simTime(), pieszy.id);
                    break;
                }
            }
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
