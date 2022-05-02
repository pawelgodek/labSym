package com.company;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.simcore.BasicSimObj;

public class Interesant extends BasicSimObj {
    int id;
    double czasWejscia;
    Otoczenie otoczenie;
    ZdarzenieZniecierpliwienie zniecierpliwienie;

    public Interesant(int id, double czasWejscia, Otoczenie otoczenie) {
        this.id = id;
        this.czasWejscia = czasWejscia ;
        this.otoczenie = otoczenie;
    }

    void dodajZniecierpliwienie(ZdarzenieZniecierpliwienie zniecierpliwienie) {
        this.zniecierpliwienie = zniecierpliwienie;
    }

    @Override
    public void reflect(IPublisher iPublisher, INotificationEvent iNotificationEvent) {

    }

    @Override
    public boolean filter(IPublisher iPublisher, INotificationEvent iNotificationEvent) {
        return false;
    }
}
