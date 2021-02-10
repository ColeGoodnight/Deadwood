class Board{

}

class BoardLocation{
    private String name;
    private String[] neighbors;
    private int[] area;
    private int[][] takeAreas;
    private Parts[] setParts;

    public void buildParts(nodeList){

    }
    public void setBoard(nodeList){

    }
}

class Trailers{
    public Trailers(){

    }
}

class CastingOffice{
    private int[] upgradeCostsCredit;
    private int[] upgradeCostsDollars;

    public CastingOffice(nodeList){

    }
}

class Bank{
    public Bank(){

    }

    public boolean payPlayer(Player currentPlayer, String currency, int amount){
        return true;
    }

    public boolean debtPlayer(Player currentPlayer, String currency, int amount){
        return true;
    }
}
