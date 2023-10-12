class Square(var x: Int, var y: Int, var width: Int) : Transforming, Figure(0), Movable  {
    // TODO: реализовать интерфейс Transforming
    override fun area(): Float {
        return (width*width).toFloat() // требуется явное приведение к вещественному числу
    }
    override fun move(dx: Int, dy: Int) {
        x += dx; y += dy
    }
    override fun resize(zoom: Int) {
        width *= zoom
    }
    override fun rotate(direction: RotateDirection, centerX: Int, centerY: Int) {
        var dx = 0
        var dy = 0
        if (centerX == x && centerY == y) return
        else if (direction == RotateDirection.Clockwise) {
            dx = y - centerY
            dy = centerX - x

        }
        else if (direction == RotateDirection.CounterClockwise) {
            dx = centerY - y
            dy = x - centerX
        }
        x = dx + centerX
        y = dy + centerY
    }
    override fun print() {
        println("x: $x, y: $y, width: $width")
    }
}