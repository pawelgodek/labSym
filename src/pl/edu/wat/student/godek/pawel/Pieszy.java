package pl.edu.wat.student.godek.pawel;

public class Pieszy {
    int id;
    double przebytaDroga;
    double predkosc;
    double czasD;

    Pieszy(int id, double predkosc, double simTime) {
        this.id = id;
        this.predkosc = predkosc;
        this.przebytaDroga = 0;
        this.czasD = simTime;
    }
}
