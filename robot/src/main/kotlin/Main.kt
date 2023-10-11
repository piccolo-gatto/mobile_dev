enum class Direction {
    UP, DOWN, LEFT, RIGHT
}


fun moveRobot(r: Robot, toX: Int, toY: Int) {
    while (r.x != toX || r.y != toY) {
        if (toX > r.x) {
            while (r.direction != Direction.RIGHT) {
                r.turnRight()
            }
        } else if (toX < r.x) {
            while (r.direction != Direction.LEFT) {
                r.turnLeft()
            }
        } else if (toY > r.y) {
            while (r.direction != Direction.UP) {
                r.turnRight()
            }
        } else if (toY < r.y) {
            while (r.direction != Direction.DOWN) {
                r.turnLeft()
            }
        }
        r.stepForward()
    }
}


fun main() {
    val r1 = Robot(3, 4, Direction.RIGHT)
    val r2 = Robot(0, 0, Direction.DOWN)
    println("Робот r1: $r1")
    println("Робот r2: $r2")
    r1.stepForward()
    r2.turnLeft()
    println("Робот r1 сделал шаг вперёд: $r1")
    println("Робот r2 повернулся влево: $r2")
    moveRobot(r1, -6, 4)
    moveRobot(r2, 1, 5)
    println("Робот r1 переместился в точку (-6, 4): $r1")
    println("Робот r2 переместился в точку (1, 5): $r2")
}