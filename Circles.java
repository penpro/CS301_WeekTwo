//Wow it helps when you have the correct libraries!

import java.util.Random;

public class Circles {
    public static void main(String[] args) {
        // make sure the arguments exist
        if (args.length < 4)throw new IllegalArgumentException("Must have four arguments, number of circles, percent black, min and max radius");
        // ingest arguments
        int NumberOfCircles = Integer.parseInt(args[0]);
        float PercentBlack = Integer.parseInt(args[1]);
        float MinimumRadius = Integer.parseInt(args[2]);
        float MaximumRadius = Integer.parseInt(args[3]);
        // check the arguments
        if (NumberOfCircles < 1) throw new IllegalArgumentException("Must be at least one circle");
        if (PercentBlack < 0) throw new IllegalArgumentException("Black circle probability must be non negative");
        if (PercentBlack > 1) PercentBlack = 1.0f;
        if (MinimumRadius <= 0) throw new IllegalArgumentException("Minimum radius must be greater than zero");
        if (MaximumRadius < MinimumRadius) {
            float hold = MaximumRadius;
            MaximumRadius = MinimumRadius;
            MinimumRadius = hold;
        }

        DrawCircles(NumberOfCircles, PercentBlack, MinimumRadius, MaximumRadius);

    }

    public static void DrawCircles(int NumberOfCircles, float PercentBlack, float MinimumRadius, float MaximumRadius){
        Random Roll = new Random();
        float ThisRoll;
        StdDraw.setCanvasSize(1000, 1000);
        StdDraw.setScale(0, 1000);

        for (int i = 0; i < NumberOfCircles; i++) {
            ThisRoll = Roll.nextFloat();
            if (ThisRoll < PercentBlack){
                StdDraw.setPenColor(StdDraw.BLACK);
            }
            else StdDraw.setPenColor(StdDraw.WHITE);
            float PenRadius = Roll.nextFloat(MinimumRadius, MaximumRadius);
            StdDraw.setPenRadius(PenRadius);
            float RandomX = Roll.nextFloat()*1000;
            float RandomY = Roll.nextFloat()*1000;
            StdDraw.point(RandomX, RandomY);
        }
    }


}
