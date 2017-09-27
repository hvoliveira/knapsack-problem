package br.com.facamp.com738;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Knapsack extends Chromosome {

    static List<Item> items = new LinkedList<>();
    List<Item> knapsack = new LinkedList<>();
    static int maxWeight;

    public Knapsack() {
        this.fitness = 0.0;
        this.normFitness = 0.0;
        for (int i = 0; i < items.size(); i++) {
            knapsack.add(items.get(i));
        }
    }

    @Override
    public void generateIndividual() {
        do {
            for (int i = 0; i < items.size(); i++) {
                double r = Math.random();
                if (r < 0.5) {
                    knapsack.get(i).setInside(false);
                } else {
                    knapsack.get(i).setInside(true);
                }
            }
        } while (!isValid());
        fitness = getFitness();
    }

    @Override
    public void mutate() {
        boolean invalid;
        if (Math.random() < GA.getMutationRate()) {
            do {
                invalid = false;
                int randomId = (int) (Math.random() * knapsack.size());
                knapsack.get(randomId).setInside(!knapsack.get(randomId).isInside());
                if (getKnapsackWeight() > maxWeight) {
                    knapsack.get(randomId).setInside(!knapsack.get(randomId).isInside());
                    invalid = true;
                }
            } while (!isValid());
            fitness = getFitness();
        }
    }

    @Override
    public List<Knapsack> crossover(Chromosome other) {
        Knapsack child1 = new Knapsack();
        Knapsack child2 = new Knapsack();
        Knapsack parent = (Knapsack) other;
        List<Knapsack> children = new ArrayList<>();
        do {
            int pos1 = (int) (Math.random() * parent.knapsack.size());
            int pos2 = (int) (Math.random() * parent.knapsack.size());
            while (pos1 == pos2) {
                pos2 = (int) (Math.random() * parent.knapsack.size());
            }
            if (pos1 > pos2) {
                int aux = pos1;
                pos1 = pos2;
                pos2 = aux;
            }

            for (int i = 0; i < parent.knapsack.size(); i++) {
                if (i > pos1 && i < pos2) {
                    child1.knapsack.get(i).setInside(this.knapsack.get(i).isInside());
                    child2.knapsack.get(i).setInside(parent.knapsack.get(i).isInside());
                } else {
                    child2.knapsack.get(i).setInside(this.knapsack.get(i).isInside());
                    child1.knapsack.get(i).setInside(parent.knapsack.get(i).isInside());
                }
            }

            children.add(child1);
            children.add(child2);
        } while (!(child1.isValid() && child2.isValid()));
        return children;
    }

    public int getKnapsackProfit() {
        int profit = 0;
        for (Item item : knapsack) {
            if (item.isInside()) {
                profit += item.getProfit();
            }
        }
        return profit;
    }

    public List<Item> getItems() {
        return knapsack;
    }

    public void setItems(List<Item> knapsack) {
        this.knapsack = knapsack;
    }

    @Override
    public double getFitness() {
        if (fitness == 0.0) {
            return getKnapsackProfit();
        }
        return fitness;
    }

    @Override
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public int getKnapsackWeight() {
        int weight = 0;
        for (Item item : knapsack) {
            if (item.isInside()) {
                weight += item.getWeight();
            }
        }
        return weight;
    }

    public boolean isValid() {
        if (getKnapsackWeight() > maxWeight) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        String geneString = "|";
        for (int i = 0; i < knapsack.size(); i++) {
            geneString += knapsack.get(i).isInside() + "|";
        }
        return geneString;
    }
}
