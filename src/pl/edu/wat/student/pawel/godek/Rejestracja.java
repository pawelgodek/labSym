package pl.edu.wat.student.pawel.godek;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.simcore.BasicSimObj;

import java.util.ArrayList;

public class Rejestracja extends BasicSimObj {
    Config config;
    Out out;
    Logger logger;

    Otoczenie otoczenie;
    ArrayList<Pacjent> kolejka;

    Pacjent okienko;
    boolean zajetosc;

    Rejestracja(Config config, Out out, Logger logger, Otoczenie otoczenie) {
        this.config = config;
        this.out = out;
        this.logger = logger;
        this.otoczenie = otoczenie;
        this.kolejka = new ArrayList<>();
        this.okienko = null;
        this.zajetosc = false;
    }

    @Override
    public void reflect(IPublisher iPublisher, INotificationEvent iNotificationEvent) {

    }

    @Override
    public boolean filter(IPublisher iPublisher, INotificationEvent iNotificationEvent) {
        return false;
    }
}
