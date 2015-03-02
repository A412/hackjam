package com.example.chase.mathbash;

public class TimedProblem extends Problem{
    private double life;

    public TimedProblem(String q, String a) {
        super(q, a);
        life = 1;
    }

    public TimedProblem(String q, int a) {
        super(q, a);
        life = 1;
    }

    public TimedProblem(String q, int a, double initLife) {
        super(q, a);
        life = initLife;
    }

    public void decrementLife(double amount) {
        life -= amount;
    }

    public double life() {
        return life;
    }

    public boolean alive() {
        return (life > 0);
    }

    public String toString() {
        return super.toString() + ", Life: " + life;
    }

    public static TimedProblem generate() {
        String operation;
        int ans;
        int first = (int)(50 * Math.random());
        int second = (int)(50 * Math.random());
        if (Math.random() > 0.5) {
            operation = " + ";
            ans = first + second;
        } else {
            operation = " * ";
            ans = first * second;
        }
        return new TimedProblem(first + operation + second, ans, 1);
    }
}
