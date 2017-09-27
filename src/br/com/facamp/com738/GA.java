package br.com.facamp.com738;

import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RefineryUtilities;

public class GA {

    private static final double mutationRate = 0.015;
    private static final int tournamentSize = 5;
    private static int elitismOffset = 1;
    
    private static XYSeries graphDataset = new XYSeries("Profit", false);

    public static double getMutationRate() {
        return mutationRate;
    }

    public static int getTournamentSize() {
        return tournamentSize;
    }

    public static int getElitismOffset() {
        return elitismOffset;
    }

    public static void main(String[] args) {

//        Knapsack.items.add(new Item(23, 92));
//        Knapsack.items.add(new Item(31, 57));
//        Knapsack.items.add(new Item(29, 49));
//        Knapsack.items.add(new Item(44, 68));
//        Knapsack.items.add(new Item(53, 60));
//        Knapsack.items.add(new Item(38, 43));
//        Knapsack.items.add(new Item(63, 67));
//        Knapsack.items.add(new Item(85, 84));
//        Knapsack.items.add(new Item(89, 87));
//        Knapsack.items.add(new Item(82, 72));
//        Knapsack.maxWeight = 165;
//
        Population pop = new Population(100, true);
        Knapsack fittest = (Knapsack) pop.getFittest();
//        System.out.println("PROBLEM 1\nMAX CAPACITY = 165 Kg\n");
//        System.out.println("Initial profit: " + fittest.getKnapsackProfit());
//        System.out.println("Initial weight: " + fittest.getKnapsackWeight());
//
//        // Evolve population for 100 generations
//        pop.evolve();
//        for (int i = 0; i < 200; i++) {
//            pop.evolve();
//            Knapsack t = (Knapsack) pop.getFittest();
//            graphDataset.add(i, t.getKnapsackProfit());
//        }
//
//        // Print final results
//        System.out.println("Finished");
//        fittest = (Knapsack) pop.getFittest();
//        System.out.println("Final profit: " + fittest.getKnapsackProfit());
//        System.out.println("Final weight: " + fittest.getKnapsackWeight());
//        System.out.println("Solution:");
//        System.out.println(pop.getFittest());
//        
//        displayGraph();
        
//        graphDataset.clear();
//        
//        Knapsack.items.clear();
        
        Knapsack.items.add(new Item(12, 24));
        Knapsack.items.add(new Item(7, 13));
        Knapsack.items.add(new Item(11, 23));
        Knapsack.items.add(new Item(8, 15));
        Knapsack.items.add(new Item(9, 16));
        Knapsack.maxWeight = 26;
        
        System.out.println("\nPROBLEM 2\nMAX CAPACITY = 26 Kg\n");
        
        pop = new Population(50, true);
        fittest = (Knapsack) pop.getFittest();
        System.out.println("Initial profit: " + fittest.getKnapsackProfit());
        System.out.println("Initial weight: " + fittest.getKnapsackWeight());

        // Evolve population for 100 generations
        pop.evolve();
        for (int i = 0; i < 200; i++) {
            pop.evolve();
            Knapsack t = (Knapsack) pop.getFittest();
            graphDataset.add(i, t.getKnapsackProfit());
        }

        // Print final results
        System.out.println("Finished");
        fittest = (Knapsack) pop.getFittest();
        System.out.println("Final profit: " + fittest.getKnapsackProfit());
        System.out.println("Final weight: " + fittest.getKnapsackWeight());
        System.out.println("Solution:");
        System.out.println(pop.getFittest());
        
        displayGraph();

    }
    
        public static void displayGraph() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(graphDataset);
        LineChart chart = new LineChart(
                "Fitness evolution",
                "Fitness evolution", dataset);

        chart.pack();
        RefineryUtilities.centerFrameOnScreen(chart);
        chart.setVisible(true);
    }
}
