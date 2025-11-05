package rts.unit

import rts.core.AttackStrategy
import rts.core.BattleLogger
import rts.core.MoveStrategy
import rts.core.Position

/**
 * Knight (기사)
 * - 지상 근접 전투 유닛
 * - 기본적으로 isFlying = false
 * - HorseMove + LanceAttack 전략을 주입받는 식으로 생성됨
 */
class Knight(
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