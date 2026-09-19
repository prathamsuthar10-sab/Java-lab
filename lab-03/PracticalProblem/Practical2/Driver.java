public class Driver {
    public static void main(String[] args){

        Card[] table = new Card[5];
        int count = 0;

        Card[] card = {
            new Card("Queen", "Heart"),
            new Card("Jack", "Speads"),
            new Card("Queen", "Heart"),
            new Card("TEN", "Clubs"),
            new Card("NINE","Clubs"),
        };

        for (Card c : card){
            boolean duplicate = false;
            for (int i=0; i< count; i++){
                if(table[i].equals(c)){
                    duplicate = true;
                    break;
                }
            }
        if(duplicate){
            System.out.println("Duplicate Found" + c);
        }

        else{
            table[count] = c;
            count++;
        }
        }
    }
}
