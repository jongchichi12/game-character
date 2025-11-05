package rts.unit

import rts.core.BattleLogger
import rts.core.MoveStrategy
import rts.core.Position
import rts.core.TransportStrategy

/**
 * TransportUnit
 * - 수송 가능한 유닛의 공통 부모 (예: Shuttle)
 * - 공격 기능은 없음
 *
 * 책임:
 *  - 탑승 관리(board)
 *  - 하차 관리(unloadAll)
 */
abstract class TransportUnit(
    name: String,
    position: Position,
    moveStrategy: MoveStrategy,
    private val transportStrategy: TransportStrategy,
    isFlying: Boolean,
    logger: BattleLogger
) : GameUnit(
    name = name,
    position = position,
    moveStrategy = moveStrategy,
    isFlying = isFlying,
    logger = logger
) {
    // 현재 탑승 중인 유닛들
    val passengers: MutableList<GameUnit> = mutableListOf()

    fun board(unit: GameUnit) {
        transportStrategy.board(this, unit, logger)
    }

    fun unloadAll() {
        transportStrategy.unloadAll(this, logger)
    }
}