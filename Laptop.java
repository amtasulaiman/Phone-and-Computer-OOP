public class Laptop implements Computer {

    private String brand;  //brand of the laptop
    private String hostname; //Owner followed by the brand
    private int screenSize;
    private int RAM;
    private int processorSpeed;
    private State state;    //ON or OFF status of the laptop
    private String[] games;   //ref to the String array of installed games
    private int no_of_games;  //count for the games installed

    /*
     * Laptop Constructor--
     * sets all the instances to their respective passed arguments, it has
     * an array of same(5)length to store games for the Laptop, no of games
     * are also kept in check.
     * @param total 5 parameters brand, hostname, screenSize, RAM & speed
     */
    public Laptop(String brand, String hostname, int screenSize, int RAM,
                  int processorSpeed) {
        this.brand = brand;
        this.hostname = hostname + "'s " + brand + " Laptop";
        this.screenSize = screenSize;
        this.RAM = RAM;
        this.processorSpeed = processorSpeed;
        state = State.ON;   //Laptop is ON by default
        games = new String[5];
        no_of_games = 0;
    }

    public String getBrand() {
        return this.brand;
    }

    public String getHostname() {
        return this.hostname;
    }

    @Override
    public int getScreenSize() {
        return this.screenSize;
    }

    @Override
    public int getRAM() {
        return this.RAM;
    }

    @Override
    public int getProcessorSpeeed() {
        return this.processorSpeed;
    }

    @Override
    public State getState() {
        return this.state;
    }

    public int getNo_of_games() {
        return this.no_of_games;
    }

    @Override
    public void setState(String to) {
        //checks for string NO, no, off,OFF only then it changes the state
        if (to.compareToIgnoreCase("ON") == 0)
            this.state = State.ON;
        else if (to.compareToIgnoreCase("OFF") == 0)
            this.state = State.OFF;
        else //otherwise throw exception
            throw new IllegalArgumentException("Illegal Argument for the state");
    }


    @Override
    public void installGame(String gameName) {
        //if the phone is OFF, dont install
        if (this.state.equals(State.OFF)) {
            System.out.println(this.getHostname() + " is OFF. " +
                    "TURN ON to intall " + gameName);
        }//install only if phone has capacity & game isn't installed already
        else if (no_of_games < 5 && !this.hasGame(gameName)) {
            games[no_of_games] = gameName;
            no_of_games++; //increments the count of installed games
            System.out.println("Installing " + gameName + " on " +
                    this.getHostname());
        }
    }

    @Override
    public boolean hasGame(String gameName) {
        //constant time checking of a particular game in existing game array
        for (int index = 0; index < no_of_games; index++)
            if (games[index].compareToIgnoreCase(gameName) == 0) {
                return true;
            }
        return false;
    }

    @Override
    public void playGame(String gameName) {
        //if the phone is off, can't play any game
        if (this.state.equals(State.OFF)) {
            System.out.println(this.getHostname() + " is OFF. TURN ON to play " +
                    gameName);
        }//if the game exists then you may play
        else if (this.hasGame(gameName)) {
            System.out.println(this.getHostname() + " is now playing " + gameName);
        } else {
            System.out.println("Can't play " + gameName + " on " +
                    this.getHostname() + ". INSTALL it first!");
        }
    }

    public String toString() {
        return this.brand + "|" + this.hostname + "|Screen:" + this.getScreenSize()
                + "|RAM: " + this.getRAM() + "|Speed: " + this.getProcessorSpeeed();
    }
}
