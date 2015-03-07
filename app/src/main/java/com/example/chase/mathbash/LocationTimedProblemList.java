package com.example.chase.mathbash;

/**
 * Created by Chase on 3/6/15.
 */
public class LocationTimedProblemList extends TimedProblemList {
    @Override
    public void addProblem() {
        super.addProblem(LocationTimedProblem.generate());
    }
}
