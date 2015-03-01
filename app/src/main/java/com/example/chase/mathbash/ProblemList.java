package com.example.chase.mathbash;

import java.util.ArrayList;
import java.util.Iterator;

public class ProblemList {
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
    addProblem();
    if (problems.size() < maxProblems) {
      addProblem();
    }
  }

  private void addProblem() {
    problems.add(Problem.generate());
  }

  public void check(int ans) {
    Iterator<Problem> it = problems.iterator();
    while (it.hasNext()) {
      if (it.next().checkAnswer(ans)) {
        it.remove();
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

}
