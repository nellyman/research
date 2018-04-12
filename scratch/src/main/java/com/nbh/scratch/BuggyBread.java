package com.nbh.scratch;

public class BuggyBread {

        private String element1;

        private String element2;

        private BuggyBread(String element1, String element2){
            this.element1 = element1;
            this.element2 = element2;
        }

        public static class Builder {

            private String element1;

            private String element2;

            Builder(BuggyBread buggybread){
                element1 = buggybread.element1;
                element2 = buggybread.element2;
            }

            Builder withElement1(String element1){
                this.element1 = element1;
                return this;
            }

            Builder withElement2(String element2){
                this.element2 = element2;
                return this;
            }

            BuggyBread build(){
                BuggyBread buggybread = new BuggyBread(element1,element2);
                return buggybread;
            }
        }

}

class BuggyBreadBuilder{
    public static void main(String[] args) {
        //BuggyBread buggybread = new BuggyBread(); // no default constructor
        //BuggyBread buggybread = new BuggyBread("element1","element2"); // no access to the private constructor
        //BuggyBread.Builder builder = new BuggyBread.Builder();

        //BuggyBread.Builder builder = new BuggyBread.Builder("element1","element2"); //just doesn't work!!!
    }
}