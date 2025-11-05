package rts.core

/**
 * Position
 * - 유닛의 좌표 (간단한 2D 위치)
 */
data class Position(
    val x: Int,
    val y: Int
) {
    override fun toString(): String = "($x, $y)"
}