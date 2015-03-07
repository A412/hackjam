package com.example.chase.mathbash;

public class LocationTimedProblem extends TimedProblem {
    private int x;

    public LocationTimedProblem(String q, int a){
        super(q, a);
        x = (int)(4*Math.random());
    }

    public LocationTimedProblem(String q, int a, double initLife) {
        super(q, a, initLife);
        x = (int)(4*Math.random());
    }

    public int getX(){
        return x;
    }

    public static LocationTimedProblem generate() {
        String operation;
        int ans;
        int first = (int)(10 * Math.random());
        int second = (int)(10 * Math.random());
        if (Math.random() > 0.5) {
            operation = " + ";
            ans = first + second;
        } else {
            operation = " * ";
            ans = first * second;
        }
        return new LocationTimedProblem(first + operation + second, ans, 1);
    }
}
