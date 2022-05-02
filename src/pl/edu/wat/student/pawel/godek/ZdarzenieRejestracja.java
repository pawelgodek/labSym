package pl.edu.wat.student.pawel.godek;

import dissimlab.simcore.BasicSimEvent;
import dissimlab.simcore.SimControlException;

public class ZdarzenieRejestracja extends BasicSimEvent<Rejestracja, Object> {
    public ZdarzenieRejestracja(Rejestracja entity, double delay) throws SimControlException {
        super(entity, delay);
    }

    @Override
    protected void stateChange() throws SimControlException {
        Rejestracja rejestracja = getSimObj();

        if(!rejestracja.kolejka.isEmpty() && !rejestracja.zajetosc) {
            rejestracja.zajetosc = true;

            rejestracja.okienko = rejestracja.kolejka.remove(0);

            rejestracja.okienko.pKolejka = null;//p na kolejke
            rejestracja.okienko.pRejestracja = rejestracja;//p na rejestracje

            rejestracja.out.kolejkaRejestracja.setValue(rejestracja.kolejka.size());//MVars
            rejestracja.out.zajetoscRejestracja.setValue(1);//end MVars

            rejestracja.logger.log("{Rejestracja} Pacjent o ID:" + rejestracja.okienko.ID + " jest obslugiwany\n");
            new ZdarzenieKoniecRejestracji(rejestracja, rejestracja.config.rng.exponential(rejestracja.config.mi));
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
