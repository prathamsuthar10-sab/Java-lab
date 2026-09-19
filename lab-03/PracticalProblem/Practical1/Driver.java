public class Driver {
    public static void main(String args[]){
        Point[] points = {
            new Point(5,6),
            new Point(7,8),
            new Point(5,4),
            new Point(5,6),
            new Point(8,9)
        };

        int distinctCount = 0;
        for (int i = 0; i < points.length; i++){
            boolean alreadySeen = false;
            for (int j = 0; j < i; j++) {
                if (points[i].equals(points[j])){
                    alreadySeen = true;
                    break;
                }
            }
            if (!alreadySeen) {
                distinctCount++;
            }
        }
        System.out.println("Distinct: " + distinctCount);
    }
}
