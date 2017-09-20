package br.com.facamp.com738;

public class Item {
    
    private int weight;
    private int profit;
    private boolean inside;

    public Item(int weight, int profit) {
        this.weight = weight;
        this.profit = profit;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public boolean isInside() {
        return inside;
    }

    public void setInside(boolean isInside) {
        this.inside = isInside;
    }
    
    
    
    
    
}
