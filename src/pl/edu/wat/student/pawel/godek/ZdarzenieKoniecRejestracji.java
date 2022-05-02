package pl.edu.wat.student.pawel.godek;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZdarzenieKoniecRejestracji extends BasicSimEvent<Rejestracja, Object> {
    public ZdarzenieKoniecRejestracji(Rejestracja entity, double delay) throws SimControlException {
        super(entity, delay);
    }

    @Override
    protected void stateChange() throws SimControlException {
        Rejestracja rejestracja = getSimObj();
        Otoczenie otoczenie = rejestracja.otoczenie;

        if(rejestracja.okienko != null) {
            if (otoczenie.gabinet1.kolejka.size() < otoczenie.gabinet2.kolejka.size()) {
                rejestracja.okienko.pKolejka = otoczenie.gabinet1.kolejka; //pKolejka

                otoczenie.gabinet1.kolejka.add(rejestracja.okienko);
                otoczenie.out.kolejkaGabinet1.setValue(otoczenie.gabinet1.kolejka.size());
                rejestracja.logger.log("{Rejestracja} Pacjent o ID:" + rejestracja.okienko.ID + " przeszedl do kolejki {Gabinet 1}\n");
                new ZdarzenieGabinetObsluga(otoczenie.gabinet1, 0);
            } else if (otoczenie.gabinet1.kolejka.size() > otoczenie.gabinet2.kolejka.size()) {
                rejestracja.okienko.pKolejka = otoczenie.gabinet2.kolejka; //pKolejka

                otoczenie.gabinet2.kolejka.add(rejestracja.okienko);
                otoczenie.out.kolejkaGabinet2.setValue(otoczenie.gabinet2.kolejka.size());
                rejestracja.logger.log("{Rejestracja} Pacjent o ID:" + rejestracja.okienko.ID + " przeszedl do kolejki {Gabinet 2}\n");
                new ZdarzenieGabinetObsluga(otoczenie.gabinet2, 0);
            } else {
                if (otoczenie.config.rng.probability(0.5)) {
                    rejestracja.okienko.pKolejka = otoczenie.gabinet1.kolejka; //pKolejka
                    otoczenie.gabinet1.kolejka.add(rejestracja.okienko);
                    otoczenie.out.kolejkaGabinet1.setValue(otoczenie.gabinet1.kolejka.size());
                    rejestracja.logger.log("{Rejestracja} Pacjent o ID:" + rejestracja.okienko.ID + " przeszedl do kolejki {Gabinet 1}\n");
                    new ZdarzenieGabinetObsluga(otoczenie.gabinet1, 0);
                } else {
                    rejestracja.okienko.pKolejka = otoczenie.gabinet2.kolejka; //pKolejka
                    otoczenie.gabinet2.kolejka.add(rejestracja.okienko);
                    otoczenie.out.kolejkaGabinet2.setValue(otoczenie.gabinet2.kolejka.size());
                    rejestracja.logger.log("{Rejestracja} Pacjent o ID:" + rejestracja.okienko.ID + " przeszedl do kolejki {Gabinet 2}\n");
                    new ZdarzenieGabinetObsluga(otoczenie.gabinet2, 0);
                }
            }
            rejestracja.okienko.pRejestracja = null; //pRej
            rejestracja.okienko = null;
            otoczenie.out.zajetoscRejestracja.setValue(0);
            rejestracja.zajetosc = false;
        }

        new ZdarzenieRejestracja(rejestracja, 0);
    }

    @Override
    protected void onTermination() throws SimControlException {

    }

    @Override
    public Object getEventParams() {
        return null;
    }
}
