package rts.unit

import rts.core.AttackStrategy
import rts.core.BattleLogger
import rts.core.MoveStrategy
import rts.core.Position

/**
 * Griffin (그리폰)
 * - 공중 유닛
 * - 하늘을 날아다니며 번개 공격을 한다
 * - isFlying = true
 */
class Griffin(
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
    isFlying = true,
    logger = logger
)