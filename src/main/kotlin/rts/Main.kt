package rts

import rts.world.Simulation

/**
 * Main
 * - 프로그램 시작 지점.
 * - 전체 시나리오(생성 → 이동 → 하차 → 전투)를 실행한다.
 */
fun main() {
    Simulation().run()
}