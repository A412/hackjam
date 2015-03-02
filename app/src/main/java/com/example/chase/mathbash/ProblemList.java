package com.example.chase.mathbash;

import java.util.ArrayList;
import java.util.Iterator;

public class ProblemList implements Iterable<Problem>{
  private ArrayList<Problem> problems;
  private int maxProblems;
  private int completedProblems;
  private int score;

  public ProblemList() {
    this(10);
  }

  public ProblemList(int max) {
    problems = new ArrayList<Problem>();
    maxProblems = max;
  }

  public int maxProblems() {
    return maxProblems;
  }

  public void setMaxProblems(int max) {
    maxProblems = max;
  }

  public int completedProblems() {
    return completedProblems;
  }

  public int score() {
    return score;
  }

  private void onCorrectAnswer() {
    completedProblems += 1;
    score += (int)(50 * Math.random());
    addProblem();
    if (problems.size() < maxProblems) {
      addProblem();
    }
  }

  public void addProblem() {
    problems.add(Problem.generate());
  }

  public void check(int ans) {
    for (int i = 0; i < problems.size(); i++) {
        if (problems.get(i).checkAnswer(ans)) {
            problems.remove(i);
            onCorrectAnswer();
        }
    }
  }

  public void check(String ans) {
    try {
      check(Integer.parseInt(ans));
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException();
    }
  }

  public Problem getProblem() {
      return problems.get(0);
  }

  public Iterator<Problem> iterator() {
      return problems.iterator();
  }

}
