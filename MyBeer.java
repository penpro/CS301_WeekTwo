// I don't get what the simulation runs are supposed to do and my head's killing me


public class MyBeer {


    public static void main(String[] args) {
        //int example = Integer.parseInt(args[0]);
        int example = 5;
        // 1/4+1/3+1/2
        System.out.println("Don't ever tell me the odds... what are the odds? Roughly " + 100*MyBeers(example) + "%");
    }

    private static float MyBeers(int NumberOfStudents){

        if (NumberOfStudents <= 1) return 1.0f;

        //insert math here
        //something something sum of odds i.e. 1/3+1/2
        float RunningOdds = 0.0f;
        for(int i = NumberOfStudents; i >= 2 ; i--){

            float Odds = (float) 1 /NumberOfStudents;
            RunningOdds = RunningOdds + Odds;

        }

        return RunningOdds;
    }
}
