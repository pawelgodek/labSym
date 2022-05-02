package pl.edu.wat.student.godek.pawel;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.monitors.MonitoredVar;
import dissimlab.random.RNGenerator;
import dissimlab.simcore.BasicSimObj;

import java.util.ArrayList;

public class Szlak extends BasicSimObj {
    RNGenerator rng;
    double minV;
    double maxV;
    double minT;
    double maxT;

    double dlSzlaku;
    ArrayList<Pieszy> listaPieszych;

    MonitoredVar czasPrzemarszu;
    MonitoredVar liczbaPieszych;

    double czasDNP;

    Szlak(RNGenerator rng, double minV, double maxV, double minT, double maxT, double dlSzlaku) {
        this.rng = rng;
        this.maxV = maxV;
        this.minV = minV;
        this.minT = minT;
        this.maxT = maxT;
        this.dlSzlaku = dlSzlaku;

        this.listaPieszych = new ArrayList<Pieszy>();
        this.liczbaPieszych = new MonitoredVar();
        this.liczbaPieszych.setValue(0);

        this.czasPrzemarszu = new MonitoredVar();
    }

    @Override
    public void reflect(IPublisher iPublisher, INotificationEvent iNotificationEvent) {

    }

    @Override
    public boolean filter(IPublisher iPublisher, INotificationEvent iNotificationEvent) {
        return false;
    }
}
