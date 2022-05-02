package com.company;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.random.RNGenerator;
import dissimlab.simcore.BasicSimObj;

import java.util.Random;


public class Otoczenie  extends BasicSimObj {
    RNGenerator rng;
    Double lambda;
    Poczta poczta;
    int licznikInteresantow;
    int maxInteresantow;

    int strata;

    double ro;
    double a;
    double b;

    public Otoczenie(RNGenerator rng, Double lambda, int maxInteresantow, double mi, int M, int maxDlugoscKolejki, double p, double ro, double a, double b) {
        this.poczta = new Poczta(rng, mi, M, maxDlugoscKolejki, p, this);
        this.rng = rng;
        this.lambda = lambda;
        this.licznikInteresantow = 0;
        this.maxInteresantow = maxInteresantow;
        this.strata = 0;
        this.ro = ro;
        this.a = a;
        this.b = b;
    }

    @Override
    public void reflect(IPublisher iPublisher, INotificationEvent iNotificationEvent) {

    }

    @Override
    public boolean filter(IPublisher iPublisher, INotificationEvent iNotificationEvent) {
        return false;
    }
}
