package com;

public class BoardLocation {
    private String   name;
    private String[] neighbors;
    private Area     area;
    private Area[]   takeAreas;
    private Part[]   setParts;
    

    public BoardLocation(String name, String[] neighbors, Area area, Area[] takeAreas, Part[] setParts) {
        this.name = name;
        this.neighbors = neighbors;
        this.area = area;
        this.takeAreas = takeAreas;
        this.setParts = setParts;
    }


    class CastingOffice {
        private int[] upgradeCreditCosts;
        private int[] upgradeDollarCosts;
    
        public CastingOffice(String name, String[] neighbors, Area area, Area[] takeAreas, Part[] setParts, int[] upgradeCreditCosts, int[] upgradeDollarCosts){
    
        }
    }
}