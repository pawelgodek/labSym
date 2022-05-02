package pl.edu.wat.student.pawel.godek;

import dissimlab.monitors.MonitoredVar;

public class Out {
    MonitoredVar kolejkaRejestracja;
    MonitoredVar zajetoscRejestracja;

    MonitoredVar kolejkaGabinet1;
    MonitoredVar zajetoscGabinet1;

    MonitoredVar kolejkaGabinet2;
    MonitoredVar zajetoscGabinet2;

    MonitoredVar czasPrzebywania;
    MonitoredVar iloscPacjentowWPrzychodni;

    Out() {
        this.kolejkaRejestracja = new MonitoredVar();
        this.zajetoscRejestracja = new MonitoredVar();

        this.kolejkaGabinet1 = new MonitoredVar();
        this.zajetoscGabinet1 = new MonitoredVar();

        this.kolejkaGabinet2 = new MonitoredVar();
        this.zajetoscGabinet2 = new MonitoredVar();

        this.czasPrzebywania = new MonitoredVar();
        this.iloscPacjentowWPrzychodni = new MonitoredVar();
        this.iloscPacjentowWPrzychodni.setValue(0);
    }
}
