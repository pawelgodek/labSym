package pl.edu.wat.student.godek.pawel;

import dissimlab.monitors.Diagram;
import dissimlab.monitors.Statistics;
import dissimlab.random.RNGenerator;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimManager;

import java.awt.*;

public class Main {

    public static void main(String[] args) throws SimControlException {
        double limit = 200;
        double okres = 0.1;

        double slo;
        double scp;

        SimManager sm = SimManager.getInstance();
        sm.setEndSimTime(limit);

        RNGenerator rng = new RNGenerator(RNGenerator.generateSeed());

        Szlak szlak = new Szlak(rng, 2,4,1,3,1000);
        GenPieszy genPieszy = new GenPieszy(szlak, null, okres);
        RuchPieszy ruchPieszy = new RuchPieszy(szlak, null, okres);

        sm.startSimulation();

        slo = Statistics.weightedMean(szlak.liczbaPieszych);
        scp = Statistics.arithmeticMean(szlak.czasPrzemarszu);

        System.out.format("Srednia liczba pieszych: %f\n", slo);
        System.out.format("Srednia czas przejscia: %f\n", scp);

        Diagram d1 = new Diagram(Diagram.DiagramType.TIME, "Liczba pieszych na szlaku...");
        d1.add(szlak.liczbaPieszych, Color.BLUE);
        d1.show();

        Diagram d2 = new Diagram(Diagram.DiagramType.DISTRIBUTION, "Czas przemarszu...");
        d2.add(szlak.czasPrzemarszu, Color.MAGENTA);
        d2.show();
    }
}
