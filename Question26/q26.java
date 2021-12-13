import java.io.File;
import java.util.*;
import java.util.regex.Pattern;

class Solution {
    public static void main(String[] args) {
        try {
            File file = new File("./data.txt");
            Scanner sc = new Scanner(file).useDelimiter("\\n");

            Set<Dot> dots = new HashSet<>();
            String line = "";
            while (!(line = sc.nextLine()).equals("")) {
                Scanner innerSc = new Scanner(line).useDelimiter("\\n|,");
                dots.add(new Dot(innerSc.nextInt(), innerSc.nextInt()));
            }
            sc.useDelimiter("");
            List<Fold> folds = new ArrayList<>();
            while (sc.hasNext()) {
                sc.skip(Pattern.compile("(\\W)*fold along "));
                boolean x = false;
                sc.useDelimiter("");
                String next = sc.next();
                if (next.equals("x")) {
                    x = true;
                }
                sc.reset();
                sc.skip("=");
                folds.add(new Fold(x, sc.nextInt()));
            }
            sc.close();

            boolean once = true;
            for (Fold fold : folds) {
                if (fold.x) {
                    foldAlongX(fold.coord, dots);
                } else {
                    foldAlongY(fold.coord, dots);
                }
                if(once) {
                    once = false;
                    System.out.println("After one fold, there are " + dots.size() + " elements");
                }
            }

            printResult(dots);

        } catch (
                Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return;
    }

    public static void foldAlongX(int coord, Set<Dot> dots) {
        Set<Dot> toRemove = new HashSet<>();
        Set<Dot> toAdd = new HashSet<>();
        for (Dot dot : dots) {
            if (dot.x > coord) {
                int newX = - dot.x + 2 * coord;
                toAdd.add(new Dot(newX, dot.y));
                toRemove.add(dot);
            }
        }
        dots.removeAll(toRemove);
        dots.addAll(toAdd);
    }

    public static void foldAlongY(int coord, Set<Dot> dots) {
        Set<Dot> toRemove = new HashSet<>();
        Set<Dot> toAdd = new HashSet<>();
        for (Dot dot : dots) {
            if (dot.y > coord) {
                toAdd.add(new Dot(dot.x, - dot.y + 2 * coord));
                toRemove.add(dot);
            }
        }
        dots.removeAll(toRemove);
        dots.addAll(toAdd);
    }

    public static void printResult(Set<Dot> dots) {
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 100; j++) {
                if(dots.contains(new Dot(j, i))) {
                    System.out.print("#");
                }
                else {
                    System.out.print(".");
                }
            }
            System.out.println("");
        }
    }
    public static class Dot {
        public int x;
        public int y;

        public Dot(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Dot dot = (Dot) o;
            return x == dot.x && y == dot.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static class Fold {
        public boolean x;
        public int coord;

        public Fold(boolean x, int coord) {
            this.x = x;
            this.coord = coord;
        }
    }
}