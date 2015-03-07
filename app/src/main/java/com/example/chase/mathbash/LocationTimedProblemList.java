package com.example.chase.mathbash;

/**
 * Created by Chase on 3/6/15.
 */
public class LocationTimedProblemList extends TimedProblemList {

    public LocationTimedProblemList(int max) {
        super(max);
    }

    @Override
    public void addProblem() {
        super.addProblem(LocationTimedProblem.generate());
    }
}
