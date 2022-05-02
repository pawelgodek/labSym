package pl.edu.wat.student.pawel.godek;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZdarzenieZniecierpliwienie extends BasicSimEvent<Pacjent, Object> {
    public ZdarzenieZniecierpliwienie(Pacjent entity, double delay) throws SimControlException {
        super(entity, delay);
    }

    @Override
    protected void stateChange() throws SimControlException {
        Pacjent pacjent = getSimObj();
        if(pacjent.pKolejka != null) {
            pacjent.pKolejka.remove(pacjent);
        } else if(pacjent.pRejestracja != null){
            pacjent.pRejestracja.okienko = null;
            pacjent.pRejestracja.zajetosc = false;
            pacjent.pRejestracja.out.zajetoscRejestracja.setValue(0);
        }
        pacjent.logger.log("{Zniecierpliwienie}Pacjent o ID:" + pacjent.ID + " opuscil przychodnie\n");

        pacjent.out.iloscPacjentowWPrzychodni.setValue(pacjent.out.iloscPacjentowWPrzychodni.getValue() - 1);

        pacjent.otoczenie.strata++;
        pacjent.out.czasPrzebywania.setValue(simTime() - pacjent.czasWejscia);
    }

    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    public Object getEventParams() {
        return null;
    }
}
