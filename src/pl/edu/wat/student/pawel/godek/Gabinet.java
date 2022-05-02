package pl.edu.wat.student.pawel.godek;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.simcore.BasicSimObj;

import java.util.ArrayList;

public class Gabinet extends BasicSimObj {
    Out out;
    Config config;
    Logger logger;
    ArrayList<Pacjent> kolejka;
    Pacjent gabinet;
    boolean zajetosc;

    int nrGabinetu;

    Gabinet(Out out, Config config, Logger logger, int nr) {
        this.config = config;
        this.out = out;
        this.kolejka = new ArrayList<>();
        this.gabinet = null;
        this.zajetosc = false;
        this.logger = logger;
        this.nrGabinetu = nr;
    }

    @Override
    public void reflect(IPublisher iPublisher, INotificationEvent iNotificationEvent) {

    }

    @Override
    public boolean filter(IPublisher iPublisher, INotificationEvent iNotificationEvent) {
        return false;
    }
}
