package com.example.chase.mathbash;

public class TimedProblemList extends ProblemList {

    public TimedProblemList(int max) {
        super(max);
    }

    public TimedProblemList() {
        this(10);
    }

    @Override
    public void addProblem() {
        super.addProblem(TimedProblem.generate());
    }

    public void decrementAll(double dec) {
        for (Problem p : this) {
            ((TimedProblem)p).decrementLife(dec);
        }
    }

    public boolean stillAlive() {
        for (Problem tp : this) {
            if (!((TimedProblem)tp).alive()) {
                return false;
            }
        }
        return true;
    }
}
