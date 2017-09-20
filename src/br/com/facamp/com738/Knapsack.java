package br.com.facamp.com738;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Knapsack extends Chromosome {

    static List<Item> items = new LinkedList<>();
    List<Item> knapsack = new LinkedList<>();
    static int maxWeight;

    public Knapsack() {
        this.fitness = 0;
        for (int i = 0; i < items.size(); i++) {
            knapsack.add(items.get(i));
        }
    }

    @Override
    public void generateIndividual() {
        do {
            for (int i = 0; i < items.size(); i++) {
                if (Math.random() < 0.5) {
                    knapsack.get(i).setInside(false);
                } else {
                    knapsack.get(i).setInside(true);
                }
            }
        } while (!isValid());
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
        }
    }

    @Override
    public List<Knapsack> crossover(Chromosome other) {
        Knapsack child1 = new Knapsack();
        Knapsack child2 = new Knapsack();
        Knapsack parent = (Knapsack) other;
        int pos1 = (int) (knapsack.size() * Math.random());
        int pos2 = (int) (knapsack.size() * Math.random());
        do {
            for (int i = 0; i < knapsack.size(); i++) {
                if (pos1 < pos2) {
                    if (i <= pos1) {
                        child1.knapsack.get(i).setInside(knapsack.get(i).isInside());
                        child2.knapsack.get(i).setInside(parent.knapsack.get(i).isInside());
                    } else if (i <= pos2) {
                        child2.knapsack.get(i).setInside(knapsack.get(i).isInside());
                        child1.knapsack.get(i).setInside(parent.knapsack.get(i).isInside());
                    } else {
                        child1.knapsack.get(i).setInside(knapsack.get(i).isInside());
                        child2.knapsack.get(i).setInside(parent.knapsack.get(i).isInside());
                    }
                } else {
                    if (i <= pos2) {
                        child1.knapsack.get(i).setInside(knapsack.get(i).isInside());
                        child2.knapsack.get(i).setInside(parent.knapsack.get(i).isInside());
                    } else if (i <= pos1) {
                        child2.knapsack.get(i).setInside(knapsack.get(i).isInside());
                        child1.knapsack.get(i).setInside(parent.knapsack.get(i).isInside());
                    } else {
                        child1.knapsack.get(i).setInside(knapsack.get(i).isInside());
                        child2.knapsack.get(i).setInside(parent.knapsack.get(i).isInside());
                    }
                }
            }
        } while (!(child1.isValid() && child2.isValid()));

        List<Knapsack> children = new ArrayList<>();
        children.add(child1);
        children.add(child2);
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
        return getKnapsackProfit();
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
