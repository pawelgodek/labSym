package pl.edu.wat.student.pawel.godek;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZdarzenieGabinetObsluga extends BasicSimEvent<Gabinet, Object> {
    public ZdarzenieGabinetObsluga(Gabinet entity, double delay) throws SimControlException {
        super(entity, delay);
    }

    @Override
    protected void stateChange() throws SimControlException {
        Gabinet gabinet = getSimObj();
        if(!gabinet.kolejka.isEmpty()) {
            if(!gabinet.zajetosc) {
                gabinet.zajetosc = true;
                gabinet.gabinet = gabinet.kolejka.remove(0);
                gabinet.gabinet.zz.terminate();
                gabinet.gabinet.pRejestracja = null;
                gabinet.gabinet.pKolejka = gabinet.kolejka;
                if (gabinet.nrGabinetu == 1) {
                    gabinet.out.kolejkaGabinet1.setValue(gabinet.kolejka.size());
                    gabinet.out.zajetoscGabinet1.setValue(1);
                    gabinet.logger.log("{Gabinet 1} Pacjent o ID:" + gabinet.gabinet.ID + " jest obslugiwany\n");
                } else {
                    gabinet.out.kolejkaGabinet2.setValue(gabinet.kolejka.size());
                    gabinet.out.zajetoscGabinet2.setValue(1);
                    gabinet.logger.log("{Gabinet 2} Pacjent o ID:" + gabinet.gabinet.ID + " jest obslugiwany\n");
                }
                new ZdarzenieGabinetKoniecObslugi(gabinet, gabinet.config.rng.exponential(gabinet.config.a));
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
