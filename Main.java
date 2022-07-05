public class Main {
    public static void main(String[] args) {
        String target = "jeeloworld";
        int popmax = 10000;
        double mutationRate = 2.0;
        Pop p = new Pop(popmax, target, mutationRate);

        p.naturalSelection();
        p.generate();
        p.evaluate();
        System.out.println(p.getBest());

    }
}
