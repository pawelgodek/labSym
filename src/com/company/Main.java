package com.company;

import dissimlab.monitors.Diagram;

import dissimlab.monitors.Statistics;
import dissimlab.random.RNGenerator;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimManager;

import java.awt.*;

public class Main {
    public static void main(String[] args) throws SimControlException {
        SimManager manager = SimManager.getInstance();
        RNGenerator rnGenerator = new RNGenerator();
        int M = 7;
        double lambda = 10;
        double mi = 1;
        double a = 0.01;
        double b = 0.07;
        double ro = 8;

        double p = 0.1;
        int L = 50;

        int maxInteresantow = 20000000;

        Otoczenie otoczenie = new Otoczenie(rnGenerator,  lambda, maxInteresantow, mi, M, L, p, ro, a, b);

        new ZdarzenieAwaria(otoczenie.poczta, rnGenerator.exponential(otoczenie.a));
        new ZdarzenieNowyInteresant(otoczenie, 0);

        manager.setEndSimTime(50);
        manager.startSimulation();

        Diagram dOne = new Diagram(Diagram.DiagramType.TIME, "dlugoscKolejki");
        dOne.add(otoczenie.poczta.dlugoscKolejki, Color.BLACK);
        dOne.show();

        Diagram dOne2 = new Diagram(Diagram.DiagramType.DISTRIBUTION, "czasPrzebywania");
        dOne2.add(otoczenie.poczta.czasPrzebywania, Color.BLUE);
        dOne2.show();

        Diagram dOne3 = new Diagram(Diagram.DiagramType.DISTRIBUTION, "iloscZajetychOkienek");
        dOne3.add(otoczenie.poczta.iloscZajetychOkienek, Color.MAGENTA);
        dOne3.show();

        System.out.format("\nSrednia dlugosc kolejki: %f\n", Statistics.arithmeticMean(otoczenie.poczta.dlugoscKolejki));
        System.out.format("Sredni czas przebywania interesanta: %f\n", Statistics.arithmeticMean(otoczenie.poczta.czasPrzebywania));
        System.out.format("Srednia ilosc zajetych okienek: %f\n", Statistics.arithmeticMean(otoczenie.poczta.iloscZajetychOkienek));
        System.out.format("Prawdopodobienstwo straty: %f%%\n", (double)otoczenie.strata/(double)otoczenie.licznikInteresantow * 100);
    }
}
