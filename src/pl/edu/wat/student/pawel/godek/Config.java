package pl.edu.wat.student.pawel.godek;

import dissimlab.random.RNGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class Config {
    public RNGenerator rng;
    public boolean con;
    public int rejL;
    public double l;
    public double mi;
    public double a;
    public double low;
    public double high;

    Config() {
        rng = new RNGenerator();

        try {
            File config = new File("config.txt");
            Scanner reader = new Scanner(config);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String [] temp = data.split("=");
                if(temp[0].equals("czyLogowacNaKonsole")) {
                    if(temp[1].equals("tak")) {
                        this.con = true;
                    } else {
                        this.con = false;
                    }
                } else if(temp[0].equals("dlugoscKolejki")) {
                    this.rejL = parseInt(temp[1]);
                } else if(temp[0].equals("lambda")) {
                    this.l = parseDouble(temp[1]);
                } else if(temp[0].equals("mi")) {
                    this.mi = parseDouble(temp[1]);
                } else if(temp[0].equals("alpha")) {
                    this.a = parseDouble(temp[1]);
                } else if(temp[0].equals("low")) {
                    this.low = parseDouble(temp[1]);
                } else if(temp[0].equals("high")) {
                    this.high = parseDouble(temp[1]);
                }

            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.print("Blad odczytu pliku konfiguracyjnego!\n");
            e.printStackTrace();
        }
    }
}
