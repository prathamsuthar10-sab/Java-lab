abstract class Shape{
    abstract double area();
}

class Circle extends Shape{
    int radius = 0;
    Circle(int radius){
        this.radius = radius;
    }

    @Override
    double area() {
        return Math.PI*radius*radius;
    }
}

class Rectangle extends Shape{
    int length = 0;
    int width = 0;

    Rectangle(int length , int width){
        this.length = length;
        this.width = width;
    }

    @Override
    double area(){
        return length*width;
    }
}

class Triangle extends Shape{
    int height = 0;
    int base = 0;

    Triangle(int height, int base){
        this.height = height;
        this.base = base;
    }

    @Override
    double area(){
        return 0.5 * height * base;
    }
}

public class Shape1{
    public static void main(String[] args){
        Shape[] shapes = {
        new Circle(5),
        new Rectangle(10, 20),
        new Triangle(20, 5)
    };

    double total = 0;
    double largest = 0;
    Shape largestShape = null;

    for(Shape shape: shapes){
        double area = shape.area();
        total += area;
        System.out.println("Area : " + area);

        if(area>largest){
            largest = area;
            largestShape = shape;
        }
    }

    System.out.println("Total Area :" + total);
    System.out.println("Largest :" + largest);
    System.out.println("Largest Shape :" + largestShape.getClass().getSimpleName());
    }
}