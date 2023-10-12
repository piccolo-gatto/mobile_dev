// сочетание определения класса и конструктора одновременно объявляет переменные и задаёт их значения
class Rect(var x: Int, var y: Int, var width: Int, var height: Int) : Figure(0), Movable, Transforming {
    // TODO: реализовать интерфейс Transforming
    var color: Int = -1 // при объявлении каждое поле нужно инициализировать

    lateinit var name: String // значение на момент определения неизвестно (только для объектных типов)
    // дополнительный конструктор вызывает основной
    constructor(rect: Rect) : this(rect.x, rect.y, rect.width, rect.height)

    // нужно явно указывать, что вы переопределяете метод
    override fun move(dx: Int, dy: Int) {
        x += dx; y += dy
    }

    // для каждого класса area() определяется по-своему
    override fun area(): Float {
        return (width*height).toFloat() // требуется явное приведение к вещественному числу
    }

    override fun resize(zoom: Int) {
        height *= zoom
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
        var save_parm = width
        width = height
        height = save_parm
    }

    override fun print() {
        println("x: $x, y: $y, width: $width, height: $height")
    }
}