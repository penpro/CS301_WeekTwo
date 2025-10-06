//whoops I forgot to add the libraries for this

import java.util.Random;

public class Circles {
    public static void main(String[] args) {
        if (args.length < 4)throw new IllegalArgumentException("Must have four arguments, number of circles, percent black, min and max radius");

        //ingest arguments
        int NumberOfCircles = Integer.parseInt(args[0]);
        float PercentBlack = Integer.parseInt(args[1]);
        float MinimumRadius = Integer.parseInt(args[2]);
        float MaximumRadius = Integer.parseInt(args[3]);

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
            StdDraw.point(500, 500);
        }
    }

    public static void DrawCircles()


}
