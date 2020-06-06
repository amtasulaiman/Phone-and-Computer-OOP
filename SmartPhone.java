public class SmartPhone extends Landline implements Computer {

    private int screenSize;
    private int RAM;
    private int processorSpeed;
    State state;
    private String[] games;   //ref to the String array of installed games
    private int no_of_games;  //count for the games installed

    /*
     * SmartPhone Constructor--
     * sets all the instances to their respective passed arguments, it has
     * an array of length 5 to store games for the smartphone, no of games
     * are also kept in check.
     * @param total 5 parameters owner, number, screenSize, RAM & speed
     */
    public SmartPhone(String owner, long number, int screenSize, int RAM,
                      int processorSpeed) {
        super(owner, number);
        this.screenSize = screenSize;
        this.RAM = RAM;
        this.processorSpeed = processorSpeed;
        state = State.ON; //Smartphone is ON by default
        games = new String[5];
        no_of_games = 0;
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
            System.out.println(this.getOwner() + "'s Smartphone is OFF. " +
                    "TURN ON to intall " + gameName);
        }//install only if phone has capacity & game isnt installed already
        else if (no_of_games < 5 && !this.hasGame(gameName)) {
            games[no_of_games] = gameName;
            no_of_games++; //increments the count of installed games
            System.out.println("Installing " + gameName + " on " +
                    this.getOwner() + "'s Smartphone.");
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
            System.out.println(this.getOwner() + "'s Smartphone is OFF. " +
                    "TURN ON to play " + gameName);
        } //if the game exists then you may play
        else if (this.hasGame(gameName)) {
            System.out.println(this.getOwner() + " is playing " + gameName);
        } else {
            System.out.println("Can't play " + gameName + " on " +
                    this.getOwner() + "'s Smartphone. INSTALL it first!");
        }
    }

    public String toString() {
        return super.toString() + "|Screen:" + this.getScreenSize() + "|RAM: "
                + this.getRAM() + "|Speed: " + this.getProcessorSpeeed();
    }
}
