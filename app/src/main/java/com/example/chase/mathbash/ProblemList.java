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
    addProblem();
    maxProblems = max;
    completedProblems = 0;
    score = 0;
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
  }

  public void addProblem() {
    problems.add(Problem.generate());
  }

  public void addProblem(Problem p) {
    problems.add(p);
  }

  public void setProblem(int index, Problem p){
    problems.set(index, p);
  }

  public boolean addProblem(int max) {
    if (problems.size() < max) {
        addProblem();
        return true;
    }
    return false;
  }

  public boolean check(int ans) {
    boolean correct = false;
    for (int i = 0; i < problems.size(); i++) {
        if (problems.get(i).checkAnswer(ans)) {
            problems.remove(i);
            onCorrectAnswer();
            correct = true;
        }
    }
    return correct;
  }

  public void remove(int i){
      problems.remove(i);
  }

  public boolean check(String ans) {
    try {
      return(check(Integer.parseInt(ans)));
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException();
    }
  }

  public Problem getProblem(int i) {
      return problems.get(i);
  }

  public String getProblemsAsString() {
      StringBuffer total = new StringBuffer();
      for (Problem p : problems) {
          total.append(p.toString());
          total.append("\n");
      }
      return total.toString();
  }

  public Iterator<Problem> iterator() {
      return problems.iterator();
  }

}
