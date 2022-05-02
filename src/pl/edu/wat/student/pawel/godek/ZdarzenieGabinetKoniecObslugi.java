package pl.edu.wat.student.pawel.godek;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZdarzenieGabinetKoniecObslugi extends BasicSimEvent<Gabinet, Object> {
    public ZdarzenieGabinetKoniecObslugi(Gabinet entity, double delay) throws SimControlException {
        super(entity, delay);
    }

    @Override
    protected void stateChange() throws SimControlException {
        Gabinet gabinet = getSimObj();
        if(gabinet.gabinet != null) {
            gabinet.out.czasPrzebywania.setValue(simTime() - gabinet.gabinet.czasWejscia);

            if(gabinet.nrGabinetu == 1) {
                gabinet.out.zajetoscGabinet1.setValue(0);
                gabinet.logger.log("{Gabinet 1} Pacjent o ID:" + gabinet.gabinet.ID + " opuscil przychodnie\n");
            } else {
                gabinet.out.zajetoscGabinet2.setValue(0);
                gabinet.logger.log("{Gabinet 2} Pacjent o ID:" + gabinet.gabinet.ID + " opuscil przychodnie\n");
            }

            gabinet.gabinet = null;
            gabinet.zajetosc = false;

            gabinet.out.iloscPacjentowWPrzychodni.setValue(gabinet.out.iloscPacjentowWPrzychodni.getValue() - 1);

            new ZdarzenieGabinetObsluga(gabinet, 0);
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
