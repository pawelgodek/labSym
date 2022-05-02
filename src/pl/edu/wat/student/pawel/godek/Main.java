package pl.edu.wat.student.pawel.godek;

import dissimlab.monitors.Diagram;
import dissimlab.monitors.Statistics;
import dissimlab.simcore.SimControlException;
import dissimlab.simcore.SimManager;

import java.awt.*;
import java.io.IOException;
import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) throws IOException, SimControlException {
		SimManager sm = SimManager.getInstance(); //Menager
		sm.setEndSimTime(200); //Czas symulacji

	    Config config = new Config();
	    Out out = new Out();
	    Logger logger = new Logger(config.con);
	    Otoczenie otoczenie = new Otoczenie(config, out, logger);
	    new ZdarzenieNowyPacjent(otoczenie,0);

	    sm.startSimulation();

		DecimalFormat pr = new DecimalFormat("#.#");
		DecimalFormat di = new DecimalFormat("#.##%");

		System.out.print("\n\n");
		logger.genWyn("Prawdopodobienstwo nieobsluzenia pacjenta: " + pr.format((double)(otoczenie.strata)/(double)(otoczenie.lID)*100) + "%\n");
		logger.genWyn("Sredni czas przebywania w przychodni: " + pr.format(Statistics.weightedMean(out.czasPrzebywania)) + "\n");
		logger.genWyn("Srednia dlugosc kolejki przy recepcji: " + pr.format(Statistics.weightedMean(out.kolejkaRejestracja)) + "\n");
		logger.genWyn("Srednia dlugosc kolejki przy gabinecie nr1: " + pr.format(Statistics.weightedMean(out.kolejkaGabinet1)) + "\n");
		logger.genWyn("Srednia dlugosc kolejki przy gabinecie nr2: " + pr.format(Statistics.weightedMean(out.kolejkaGabinet2)) + "\n");
		logger.genWyn("Srednia zajetosc recepcji: " + di.format(Statistics.weightedMean(out.zajetoscRejestracja)) + "\n");
		logger.genWyn("Srednia zajetosc gabinetu nr1: " + di.format(Statistics.weightedMean(out.zajetoscGabinet1)) + "\n");
		logger.genWyn("Srednia zajetosc gabinetu nr2: " + di.format(Statistics.weightedMean(out.zajetoscGabinet2)) + "\n");
		logger.genWyn("Srednia liczba pacjentow w przychodni: " + pr.format(Statistics.weightedMean(out.iloscPacjentowWPrzychodni)) + "\n");

		logger.closeFiles();

		Diagram dlKolejekD = new Diagram(Diagram.DiagramType.DISTRIBUTION, "Dlugosc kolejek");
		dlKolejekD.add(out.kolejkaRejestracja, Color.GREEN, "Recepcja");
		dlKolejekD.add(out.kolejkaGabinet1, Color.BLUE, "Gabinet nr1");
		dlKolejekD.add(out.kolejkaGabinet2, Color.CYAN, "Gabinet nr2");
		dlKolejekD.show();

		Diagram dlKolejekT = new Diagram(Diagram.DiagramType.TIME, "Dlugosc kolejek");
		dlKolejekT.add(out.kolejkaRejestracja, Color.GREEN, "Recepcja");
		dlKolejekT.add(out.kolejkaGabinet1, Color.BLUE, "Gabinet nr1");
		dlKolejekT.add(out.kolejkaGabinet2, Color.CYAN, "Gabinet nr2");
		dlKolejekT.show();

		Diagram czasPrzebywania = new Diagram(Diagram.DiagramType.DISTRIBUTION, "Czas przebywania pacjenta w przychodni");
		czasPrzebywania.add(out.czasPrzebywania, Color.MAGENTA);
		czasPrzebywania.show();
    }
}
