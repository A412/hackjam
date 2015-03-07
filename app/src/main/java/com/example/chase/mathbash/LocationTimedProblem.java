package com.example.chase.mathbash;

/**
 * Created by Chase on 3/6/15.
 */
public class LocationTimedProblem extends TimedProblem {
    private int x;
    private int y;

    public LocationTimedProblem(String q, int a){
        super(q, a);
        x = (int)(4*Math.random()+1);
        y = 100;
    }

    public LocationTimedProblem(String q, int a, double initLife) {
        super(q, a, initLife);
        x = (int)(4*Math.random()+1);
        y = 100;
    }

    public int getY(){
        return y;
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
