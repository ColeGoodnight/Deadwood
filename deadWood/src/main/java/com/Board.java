package com;

class Board {

    private BoardLocation [] locations;

    public Board () {

    }

    public BoardLocation getBoard(String locationName) {
        return new BoardLocation();
    }

    class BoardLocation {
        private String   name;
        private String[] neighbors;
        private int[]    area;
        private int[][]  takeAreas;
        private Part[]   setParts;
        
        public BoardLocation() {

        }

        public void buildParts(Part[] parts) {

        }

        class Trailers {
            public Trailers() {
        
            }
        }

        class CastingOffice {
            private int[] upgradeCreditCosts;
            private int[] upgradeDollarCosts;
        
            public CastingOffice(int[] upgradeCreditCosts, int[] upgradeDollarCosts){
        
            }
        }
    }
}






