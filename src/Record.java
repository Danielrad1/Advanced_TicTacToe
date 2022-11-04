public class Record {

        private final String key;
        private final int score;
        private final int level;

        //Constructor
        public Record (String key, int score, int level){
            this.key = key;
            this.score = score;
            this.level = level;

        }

        //Getter method, gets the key.
        public String getKey(){
            return this.key;
        }
        //Getter method, gets the score.
        public int getScore(){
            return this.score;
        }
        //Getter method, gets the level
        public int getLevel(){
            return this.level;
        }
    }


