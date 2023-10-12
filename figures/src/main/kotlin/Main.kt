fun main() {
    println("Square")
    val f1 = Square(4,3,4)
    val f2 = Square(4,3,4)
    f1.print()
    f2.print()
    f1.rotate(RotateDirection.Clockwise, 3, -3)
    f1.print()
    f2.rotate(RotateDirection.CounterClockwise, 3, -3)
    f2.print()
    println("Circle")
    val f3 = Circle(4,3,4)
    val f4 = Circle(4,3,4)
    f3.print()
    f4.print()
    f3.rotate(RotateDirection.Clockwise, 3, -3)
    f3.print()
    f4.rotate(RotateDirection.CounterClockwise, 3, -3)
    f4.print()
    println("Rectangle")
    val f5 = Rect(4,3,4,2)
    val f6 = Rect(4,3,4,2)
    f5.print()
    f6.print()
    f5.rotate(RotateDirection.Clockwise, 3, -3)
    f5.print()
    f6.rotate(RotateDirection.CounterClockwise, 3, -3)
    f6.print()
}