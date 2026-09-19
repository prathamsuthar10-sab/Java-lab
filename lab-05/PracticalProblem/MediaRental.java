abstract class Media {
    String title;
    int daysLate;

    Media(String title, int daysLate) {
        this.title = title;
        this.daysLate = daysLate;
    }

    abstract int lateFee();
}

class Movie extends Media {

    Movie(String title, int daysLate) {
        super(title, daysLate);
    }

    @Override
    int lateFee() {
        return daysLate * 20;
    }
}

class Game extends Media {

    Game(String title, int daysLate) {
        super(title, daysLate);
    }

    @Override
    int lateFee() {
        return daysLate * 40;
    }
}

class Book extends Media {

    Book(String title, int daysLate) {
        super(title, daysLate);
    }

    @Override
    int lateFee() {
        return daysLate * 10;
    }
}

public class MediaRental {
    public static void main(String[] args) {

        Media[] returnedMedia = {
            new Movie("Avatar", 3),
            new Game("GTA", 2),
            new Book("Java OOP", 5),
            new Movie("Batman", 2),
            new Game("FIFA", 3)
        };

        int totalFee = 0;

        for (Media media : returnedMedia) {

            int fee = media.lateFee();

            System.out.println(
                media.title + " | Days Late: " +
                media.daysLate + " | Late Fee: ₹" + fee
            );

            totalFee += fee;
        }

        System.out.println("Total Late Fees: ₹" + totalFee);
    }
}