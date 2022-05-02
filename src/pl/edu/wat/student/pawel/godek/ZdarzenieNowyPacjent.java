package pl.edu.wat.student.pawel.godek;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZdarzenieNowyPacjent extends BasicSimEvent<Otoczenie, Object> {
    public ZdarzenieNowyPacjent(Otoczenie entity, double delay) throws SimControlException {
        super(entity, delay);
    }

    @Override
    protected void stateChange() throws SimControlException {
        Otoczenie otoczenie = getSimObj();
        Pacjent nowyPacjent = new Pacjent(++otoczenie.lID, otoczenie.config, otoczenie.out, otoczenie.logger, otoczenie);

        if(otoczenie.rejestracja.kolejka.size() < otoczenie.rejestracja.config.rejL) {
            nowyPacjent.pKolejka = otoczenie.rejestracja.kolejka; //wskaznik na kolejke w ktorej jest pacjent
            otoczenie.rejestracja.kolejka.add(nowyPacjent);
            otoczenie.out.kolejkaRejestracja.setValue(otoczenie.rejestracja.kolejka.size());
            otoczenie.logger.log("{Otoczenie} Pacjent o ID:" + nowyPacjent.ID + " wszedl do przychodni\n");
            otoczenie.out.iloscPacjentowWPrzychodni.setValue(otoczenie.out.iloscPacjentowWPrzychodni.getValue() + 1);
        } else {
            nowyPacjent.zz.terminate();
            otoczenie.logger.log("{Otoczenie} Pacjent o ID:" + nowyPacjent.ID + " odszedl. Brak miejsca w kolejce\n");
            otoczenie.strata++;
        }

        new ZdarzenieNowyPacjent(otoczenie, otoczenie.config.rng.exponential(otoczenie.config.l));
        new ZdarzenieRejestracja(otoczenie.rejestracja, 0);
    }

    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    public Object getEventParams() {
        return null;
    }
}
