package pl.edu.wat.student.pawel.godek;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.simcore.BasicSimObj;
import dissimlab.simcore.SimControlException;

import java.util.ArrayList;

public class Pacjent extends BasicSimObj {
    ArrayList<Pacjent> pKolejka;
    Rejestracja pRejestracja;

    Config config;
    Out out;
    Logger logger;

    Otoczenie otoczenie;

    ZdarzenieZniecierpliwienie zz;

    int ID;
    double czasWejscia;

    Pacjent(int id, Config config, Out out, Logger logger, Otoczenie otoczenie) throws SimControlException {
        this.pKolejka = null;
        this.pRejestracja = null;
        this.ID = id;
        this.czasWejscia = simTime();
        this.config = config;
        this.out = out;
        this.logger = logger;
        this.otoczenie = otoczenie;
        this.zz = new ZdarzenieZniecierpliwienie(this, config.rng.uniform(config.low, config.high));
    }
    @Override
    public void reflect(IPublisher iPublisher, INotificationEvent iNotificationEvent) {

    }

    @Override
    public boolean filter(IPublisher iPublisher, INotificationEvent iNotificationEvent) {
        return false;
    }
}
