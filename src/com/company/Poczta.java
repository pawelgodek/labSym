package com.company;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.monitors.MonitoredVar;
import dissimlab.random.RNGenerator;
import dissimlab.simcore.BasicSimObj;

import java.util.ArrayList;
import java.util.List;

public class Poczta extends BasicSimObj {

    RNGenerator rng;
    public double mi;
    double p;

    List<Interesant> kolejka;
    int L;
    boolean awariaKolejki;
    List<Okienko> okienka;

    MonitoredVar iloscZajetychOkienek;
    MonitoredVar czasPrzebywania;
    MonitoredVar dlugoscKolejki;

    Otoczenie otoczenie;


    public Poczta(RNGenerator rng, double mi, int M, int L, double p, Otoczenie otoczenie) {
        this.otoczenie = otoczenie;
        this.rng = rng;
        this.mi = mi;
        this.p = p;
        this.okienka = new ArrayList<>();
        this.kolejka = new ArrayList<>();
        this.czasPrzebywania = new MonitoredVar();
        this.dlugoscKolejki = new MonitoredVar();
        this.L = L;
        this.iloscZajetychOkienek = new MonitoredVar();
        this.iloscZajetychOkienek.setValue(0);
        this.awariaKolejki = false;

        for(int i=0;i<M;i++) {
            okienka.add(new Okienko(this));
        }
    }

    @Override
    public void reflect(IPublisher iPublisher, INotificationEvent iNotificationEvent) {

    }

    @Override
    public boolean filter(IPublisher iPublisher, INotificationEvent iNotificationEvent) {
        return false;
    }


}
