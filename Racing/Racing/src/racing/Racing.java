
package racing;

import java.util.ArrayList;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Racing implements Runnable {

    private double racetrack; // meters
    private int race_id; // the id of the race
    private int final_place = 1; // increases each time an animal crosses the final line
    private boolean start_race = true; // to start the reach
    private ArrayList<AnimalRacing> competitors = new ArrayList<>(); // array to save my objects (AnimalRacing)
    // this is optional
    private char char_race = '-'; // char using when typing to console
    private final int char_icon = 20; // limit of icon

    // constructor
    public Racing(int racetrack, ArrayList<AnimalRacing> competitors, int race_id) {
        this.racetrack = racetrack;
        this.race_id = race_id;
        this.competitors = competitors;
    }

    // optional
    public void setIcon_race(char icon_race) {// allow user to change the char icon of console
        this.char_race = icon_race;
    }

    private StringBuilder icon_race() {
        StringBuilder new_icon = new StringBuilder();
        for (int i = 0; i < char_icon; i++) {
            new_icon.append(char_race);
        }
        return new_icon;
    }

    public void competitors_presentation() { // printing a presentantion on console (optional)
        System.out.print("Welcome to the race #" + race_id + ", we are pleased to have you here"
                + "\n The racetrack will be " + racetrack + " meters "
                + "\n We introduce you to the competitors of this race. \n" + icon_race() + "\n");
        competitors.forEach(comp -> System.out
                .println("I am a(n) " + comp.getAnimal() + " but you can call me " + comp.getNick_name()));
    }

    public void start_race() {// starting the race
        competitors_presentation();
        Thread thread_race = new Thread(this);
        competitors.forEach(comp -> comp.start_moving());// starting the threads of all competitors
        thread_race.start();// starting the race thread
    }

    public void print_status() {
        competitors.forEach(comp -> {
            if (comp.getDistance() <= racetrack) {// if the distance of this animal < racetrack
                NumberFormat formatter = new DecimalFormat("#0.00");// write in console it status
                System.out.println(comp.getNick_name() + " goes with a speed of " + formatter.format(comp.getSpeed())
                        + " km/h " + "and moved " + formatter.format(comp.getProgress()) + "m/s" + " to complete "
                        + formatter.format(comp.getDistance()) + "m of track");
            } else if (comp.getPlace() == 0) { // if i crossed the final line
                comp.stop_moving(); // killing the thread of the animal
                comp.setPlace(final_place);// updating the reached place
                final_place += 1;// updating the global value, no ones can't reach the same places
                // typing who crossed the finish line on the console
                System.out.println(icon_race() + comp.getNick_name() + " reach " + comp.getPlace() + icon_race());
            }
            ;
        });
        System.out.println(icon_race());// simple visual
    }

    @Override
    public void run() {// Thread of the race
        while (start_race && final_place <= competitors.size()) { // while a race start and a competitor still runing
            print_status(); // calling print method
            try {
                Thread.sleep(1000);//sleeping thread by 1 second
            } catch (InterruptedException e) {
            } 
        }
    }

    public static void main(String[] args) {
        // animal speeds km/h
        final double hourse_speed = 88;

        // creating my competitors
        AnimalRacing hourse1 = new AnimalRacing("Hourse", "Gojo", hourse_speed);
        AnimalRacing hourse2 = new AnimalRacing("Hourse", "Black", hourse_speed);
        AnimalRacing hourse3 = new AnimalRacing("Hourse", "Maximus", hourse_speed);

        // creating groups
        ArrayList<AnimalRacing> first_group = new ArrayList<>();
        first_group.add(hourse1);
        first_group.add(hourse2);
        first_group.add(hourse3);

        // creating careers
        Racing race_1 = new Racing(500, first_group, 1);
        race_1.start_race();//starting race

    }

}