package com.example.chase.mathbash;

/**
 * Created by Chase on 3/6/15.
 */
public class LocationTimedProblem extends TimedProblem {
    private int x;
    private int y;

    public LocationTimedProblem(String q, int a){
        super(q, a);
        x = (int)(5*Math.random());
        y = 100;
    }

    public int getY(){
        return y;
    }

    public int getX(){
        return x;
    }


}
