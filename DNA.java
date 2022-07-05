import java.util.Random;

/**
 * DNA
 */
public class DNA {
    String phrase;
    double fitness;

    DNA(int n, String target) {
        this.phrase = this.generateRandomPhrase(n);
        this.fitness = calFitness(target);
    }

    String generateRandomPhrase(int n) {

        // chose a Character random from this String
        String AlphaNumericString = "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        System.out.println(sb.toString());
        System.exit(0);
        return sb.toString();
    }

    char generateRandomChar() {
        Random r = new Random();
        char c = (char) (r.nextInt(26) + 'a');
        return c;
    }

    int randint(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    double calFitness(String target) {
        int score = 0;
        for (int i = 0; i < target.length(); i++) {
            if (target.charAt(i) == this.phrase.charAt(i)) {
                score++;
            }
        }
        return score / target.length();
    }

    DNA crossover(DNA partner) {
        DNA child = new DNA(partner.phrase.length(), this.generateRandomPhrase(partner.phrase.length()));
        int mid = (int) Math.floor(randint(0, partner.phrase.length()));
        child.phrase = this.phrase.substring(0, mid) + partner.phrase.substring(mid);
        return child;
    }

    void mutate(double mr, String target) {
        String t = "";
        for (char c : this.phrase.toCharArray()) {
            if (Math.random() > mr) {
                t = t + this.generateRandomChar();
            } else {
                t = t + c;
            }
        }
        this.phrase = t;
        this.fitness = this.calFitness(target);
    }
}