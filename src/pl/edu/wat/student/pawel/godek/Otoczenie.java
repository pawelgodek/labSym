package pl.edu.wat.student.pawel.godek;

import dissimlab.broker.INotificationEvent;
import dissimlab.broker.IPublisher;
import dissimlab.simcore.BasicSimObj;

public class Otoczenie extends BasicSimObj {
    Config config;
    Out out;
    Logger logger;
    Rejestracja rejestracja;
    Gabinet gabinet1;
    Gabinet gabinet2;

    int lID;
    int strata;

    Otoczenie(Config config, Out out, Logger logger) {
        this.config = config;
        this.out = out;
        this.logger = logger;
        this.rejestracja = new Rejestracja(config, out, logger, this);
        this.gabinet1 = new Gabinet(out, config, logger, 1);
        this.gabinet2 = new Gabinet(out, config, logger,2);

        this.lID = 0;
        this.strata = 0;

    }

    @Override
    public void reflect(IPublisher iPublisher, INotificationEvent iNotificationEvent) {

    }

    @Override
    public boolean filter(IPublisher iPublisher, INotificationEvent iNotificationEvent) {
        return false;
    }
}
