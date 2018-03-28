package ru.liga.songtask.parser;

public abstract class Job {
    String[] args;
    Job(String[] args){
        this.args = args;
    }
    public abstract void doJob();
}
