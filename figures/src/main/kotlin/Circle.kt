import kotlin.math.PI


class Circle(var x: Int, var y: Int, var radius: Int) : Figure(0), Movable, Transforming{
    override fun area(): Float {
        return  (radius * radius * PI).toFloat();
    }
    override fun move(dx: Int, dy: Int) {
        x += dx; y += dy
    }
    override fun resize(zoom: Int) {
        radius *= zoom
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

    override fun toString(): String {
        return "x: $x, y: $y, radius: $radius"
    }
}