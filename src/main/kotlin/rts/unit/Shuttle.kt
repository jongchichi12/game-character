package rts.unit

import rts.core.BattleLogger
import rts.core.MoveStrategy
import rts.core.Position
import rts.core.TransportStrategy

/**
 * Shuttle (셔틀)
 * - 수송 전용 공중 유닛
 * - 병력을 태우고 특정 위치로 이동한 뒤 하차시킨다
 * - 공격 기능은 없다 (CombatUnit 아님)
 * - isFlying = true
 */
class Shuttle(
    name: String,
    start: Position,
    moveStrategy: MoveStrategy,
    transportStrategy: TransportStrategy,
    logger: BattleLogger
) : TransportUnit(
    name = name,
    position = start,
    moveStrategy = moveStrategy,
    transportStrategy = transportStrategy,
    isFlying = true,
    logger = logger
)