package com.company;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.simcore.BasicSimObj;

public class Okienko extends BasicSimObj {
    boolean zajete;
    Poczta poczta;
    Interesant interesant;

    ZdarzenieZakonczenieObslugi pZakonczenieObslugi;

    Okienko(Poczta poczta) {
        this.poczta = poczta;
    }

    @Override
    public void reflect(IPublisher iPublisher, INotificationEvent iNotificationEvent) {

    }

    @Override
    public boolean filter(IPublisher iPublisher, INotificationEvent iNotificationEvent) {
        return false;
    }
}
