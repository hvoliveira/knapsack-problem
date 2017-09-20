package br.com.facamp.com738;

import java.util.List;

public abstract class Chromosome {
    
    double fitness;
    
    public abstract void generateIndividual();
    
    public abstract void mutate();
    
    public abstract List<? extends Chromosome> crossover(Chromosome other);

    public abstract double getFitness();

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }
        
}