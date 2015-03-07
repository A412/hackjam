package com.example.chase.mathbash;

/**
 * Created by Chase on 3/6/15.
 */
public class LocationTimedProblemList extends TimedProblemList {

    public LocationTimedProblemList(int max) {
        super(max);
    }

    public LocationTimedProblem removeReplace(int i){
        LocationTimedProblem newProb=LocationTimedProblem.generate();
        setProblem(i, newProb);
        return newProb;
    }

    public static void updateProblems(LocationTimedProblemList tpl){
        int  i=0;
        for (Problem p: tpl ){
            LocationTimedProblem tp=(LocationTimedProblem) p;
            if (!tp.alive()){
                tpl.remove(i);
                tpl.addProblem(LocationTimedProblem.generate());
            }
            i+=1;
        }
    }

    @Override
    public void addProblem() {
        super.addProblem(LocationTimedProblem.generate());
    }
}
