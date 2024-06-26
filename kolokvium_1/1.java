import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

class Canvas {
    private String canvasId;
    private ArrayList<Integer> sizes;

    Canvas() {
        canvasId = "";
        sizes = new ArrayList<>();
    }

    Canvas(String canvasId, ArrayList<Integer> sizes) {
        this.canvasId = canvasId;
        this.sizes = sizes;
    }

    public int getSize() {
        return sizes.size();
    }

    public int getPerimeter() {
        int s = 0;
        for(int size : sizes) { s += size; }
        return s * 4;
    }

    @Override
    public String toString() {
        return canvasId + " " + getSize() + " " + getPerimeter();
    }
}

class ShapesApplication {
    private ArrayList<Canvas> list;

    ShapesApplication() {
        list = new ArrayList<>();
    }

    public int readCanvases(InputStream inputStream) {
        Scanner input = new Scanner(inputStream);

        int ct = 0;

        while(input.hasNextLine()) {
            String canvas = input.nextLine();

            String canvasId = canvas.split(" ")[0];
            String [] str = canvas.split(" ");

            ArrayList<Integer> sizes = new ArrayList<>();

            for(int i = 1; i < str.length; i++) {
                sizes.add(Integer.parseInt(str[i]));
            }

            ct += sizes.size();
            list.add(new Canvas(canvasId, sizes));
        }

        return ct;
    }

    public void printLargestCanvasTo(OutputStream outputStream) {
        int idx = -1;

        for(int i = 0; i < list.size(); i++) {
            if(idx == -1 || list.get(i).getPerimeter() > list.get(idx).getPerimeter()) {
                idx = i;
            }
        }

        if(idx == -1) { return; }

        PrintWriter print = new PrintWriter(outputStream);
        print.println(list.get(idx));
        print.flush();
    }
}

public class Shapes1Test {
    public static void main(String[] args) {
        ShapesApplication shapesApplication = new ShapesApplication();

        System.out.println("===READING SQUARES FROM INPUT STREAM===");
        System.out.println(shapesApplication.readCanvases(System.in));
        System.out.println("===PRINTING LARGEST CANVAS TO OUTPUT STREAM===");
        shapesApplication.printLargestCanvasTo(System.out);

    }
}