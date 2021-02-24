package racing;

public class AnimalRacing implements Runnable {

    private String animal; // kind of animal
    private String nick_name; // the nick name
    // these variables will be calculated in the background by threads
    private double speed;
    private double distance;
    private double progress;
    private double maximum_speed;// the maximum amount of speed this type of animal can reach (km / h)
    private boolean start_moving = true; // boolean to start the moving of the animal
    private int place = 0; // the place it got at the end of the race

    // constructor
    public AnimalRacing(String name, String nick_nickame, double maximum_speed) {
        this.animal = name;
        this.nick_name = nick_nickame;
        this.maximum_speed = maximum_speed;
    }

    // getters & setters
    public int getPlace() {
        return place;
    }

    public double getSpeed() {
        return speed;
    }

    public String getAnimal() {
        return animal;
    }

    public String getNick_name() {
        return nick_name;
    }

    public double getDistance() {
        return distance;
    }

    public double getProgress() {
        return progress;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    // public methods

    /**
     * Start the moving of the animal
     */
    public void start_moving() {
        Thread thread = new Thread(this);
        thread.start(); // starting Thread
    }

    /**
     * Stop the move of the animal
     */
    public void stop_moving() {
        start_moving = false; // killing Thread
    }

    // private methods
    /**
     * update the speed of the animal with a random number between 1 and the maximum
     * speed value
     */
    private void update_Speed() {
        speed = (Math.random() * maximum_speed + 1);
    }

    /**
     * update the position of the animal
     */
    private void update_position() {
        update_Speed();
        progress = speed / 3.6; // km/h -> m/s
        distance += progress; // the displacement is saved
    }

    @Override
    public void run() {
        while (start_moving) {
            update_position();// calling this method to update the variable values
            try {
                Thread.sleep(1000); // sleeping for 1 second
            } catch (InterruptedException e) {
            } // 4 second
        }
    }
}
