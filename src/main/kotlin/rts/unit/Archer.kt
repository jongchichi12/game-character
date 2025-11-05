package rts.unit

import rts.core.AttackStrategy
import rts.core.BattleLogger
import rts.core.MoveStrategy
import rts.core.Position

/**
 * Archer (궁수)
 * - 지상 원거리 전투 유닛
 * - isFlying = false
 */
class Archer(
    name: String,
    start: Position,
    moveStrategy: MoveStrategy,
    attackStrategy: AttackStrategy,
    logger: BattleLogger
) : CombatUnit(
    name = name,
    position = start,
    moveStrategy = moveStrategy,
    attackStrategy = attackStrategy,
    isFlying = false,
    logger = logger
)