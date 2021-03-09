package com;

public class Player {

    private int           credits;
    private int           dollars;
    private int           rank;
    private BoardLocation location;
    private Part          currentPart;
    private boolean       hasMoved;
    private boolean       hasActedOrRehearsed;


    public Player(int credits, int rank) {
        this.credits = credits;
        this.rank = rank;
    }


    public int getCredits() {
        return this.credits;
    }

    public void setCredits(int credits) {
        if (credits >= 0) {
            this.credits = credits;
        } else {
            throw new IllegalArgumentException();
        } 
    }

    public int getDollars() {
        return this.dollars;
    }

    public void setDollars(int dollars) throws IllegalArgumentException {
        if (dollars >= 0) {
            this.dollars = dollars;
        } else {
            throw new IllegalArgumentException();
        } 
    }

    public BoardLocation getLocation() {
        return this.location;
    }

    public void setLocation(BoardLocation location) {
        this.location = location;
    }

    public Part getCurrentPart() {
        return this.currentPart;
    }

    public void setCurrentPart(Part currentPart) {
        this.currentPart = currentPart;
    }

    public void setRank(int rank) throws IllegalArgumentException {
        if (rank >= 1 && rank <= 6) {
            this.rank = rank;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public int getRank() {
        return this.rank;
    }

    public void setHasMoved(boolean bool){
        this.hasMoved = bool;
    }

    public boolean getHasMoved(){
        return this.hasMoved;
    }

    public void setHasActedOrRehearsed(boolean bool){
        this.hasActedOrRehearsed = bool;
    }

    public boolean getHasActedOrRehearsed(){
        return this.hasActedOrRehearsed;
    }
}
