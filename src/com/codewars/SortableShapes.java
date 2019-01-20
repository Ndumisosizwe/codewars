package com.codewars;


import java.util.Objects;

public class SortableShapes {
}

abstract class Shape implements Comparable<Shape> {
    protected double area;

    @Override
    public int compareTo(Shape o) {
        return Double.compare(this.area, o.area);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shape)) return false;
        Shape shape = (Shape) o;
        return Double.compare(shape.area, area) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(area);
    }
}


class Square extends Shape {
    public Square(double side) {
        this.area = Math.pow(side, 2);
    }
}

class CustomShape extends Shape {
    public CustomShape(double area) {
        this.area = area;
    }
}

class Triangle extends Shape {
    public Triangle(double base, double height) {
        this.area = (base * height) / 2;
    }
}

class Rectangle extends Shape {
    public Rectangle(double width, double height) {
        this.area = width * height;
    }
}

class Circle extends Shape {
    public Circle(double radius) {
        this.area = Math.pow(radius, 2) * Math.PI;
    }
}
