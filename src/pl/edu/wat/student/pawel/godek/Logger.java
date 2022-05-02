package pl.edu.wat.student.pawel.godek;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.simcore.BasicSimObj;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class Logger extends BasicSimObj {
    FileWriter wyn;
    FileWriter log;
    DecimalFormat df;
    boolean con;

    Logger(boolean con) throws IOException {
        this.wyn = new FileWriter("wyniki.txt");
        this.log = new FileWriter("logi.txt");
        this.con = con;
        this.df = new DecimalFormat("#.##");
    }

    void log(String string) {
        if(this.con) {
            System.out.print("[" + df.format(simTime()) + "]" + string);
        }

        try {
            log.write("[" + df.format(simTime()) + "]" + string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void genWyn(String string) {
        if(this.con) {
            System.out.print(string);
        }

        try {
            wyn.write(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void closeFiles() throws IOException {
        this.log.close();
        this.wyn.close();
    }

    @Override
    public void reflect(IPublisher iPublisher, INotificationEvent iNotificationEvent) {

    }

    @Override
    public boolean filter(IPublisher iPublisher, INotificationEvent iNotificationEvent) {
        return false;
    }
}
