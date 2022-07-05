import java.util.ArrayList;
import java.util.Random;

public class Pop {
    ArrayList<DNA> population;
    ArrayList<DNA> matingPool;
    String target;
    int generations = 0;
    double mutationRate;
    boolean finished = false;
    String bphrase = "";

    Pop(int num, String target, double mr) {
        this.population = new ArrayList<DNA>();
        this.target = target;
        this.matingPool = new ArrayList<DNA>();
        this.generations = 0;
        this.mutationRate = mr;
        this.finished = false;
        this.bphrase = "";

    }

    double scaleNumber(double unscaled, double to_min, double to_max, double from_min, double from_max) {
        return (to_max - to_min) * (unscaled - from_min) / (from_max - from_min) + to_min;
    }

    void naturalSelection() {
        this.matingPool.clear();
        ArrayList<Double> f = new ArrayList<Double>();
        double maxFitness = 0.0;
        for (DNA d : this.population) {
            if (d.fitness > maxFitness)
                maxFitness = d.fitness;
            f.add(d.fitness);
        }
        double fitness = 0.0;
        int n = 0;
        for (DNA d : this.population) {

            fitness = this.scaleNumber(d.fitness, 0.0, maxFitness, 0.0, 1.0);
            n = (int) Math.floor(fitness * 100);
            for (int i = 0; i < n; i++) {
                this.matingPool.add(d);
            }
        }
        // System.out.println(this.matingPool);
        // System.out.println("this is target" + this.target);

    }

    void generate() {
        ArrayList<DNA> nextpop = new ArrayList<DNA>();
        for (int i = 0; i < this.population.size(); i++) {
            Random randomizer = new Random();
            DNA p1 = this.matingPool.get(randomizer.nextInt(this.matingPool.size()));
            DNA p2 = this.matingPool.get(randomizer.nextInt(this.matingPool.size()));
            DNA c = p1.crossover(p2);
            c.mutate(this.mutationRate, this.target);
            nextpop.add(c);
        }
        this.generations++;
        this.population = nextpop;
    }

    void evaluate() {
        ArrayList<Double> f = new ArrayList<Double>();
        double maxFitness = 0.0;
        String temp = "";
        for (DNA d : this.population) {
            if (d.fitness > maxFitness) {
                maxFitness = d.fitness;
                temp = d.phrase;
            }
            f.add(d.fitness);
        }
        this.bphrase = temp;
        if (maxFitness == 1.0)
            this.finished = true;
    }

    String getBest() {
        return this.bphrase;
    }

    boolean isFin() {
        return this.finished;
    }

    int getGen() {
        return this.generations;
    }

}
