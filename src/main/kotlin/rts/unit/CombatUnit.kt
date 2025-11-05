package rts.unit

import rts.core.AttackStrategy
import rts.core.BattleLogger
import rts.core.MoveStrategy
import rts.core.Position

/**
 * CombatUnit
 * - 공격 가능한 유닛의 공통 부모
 * - Knight / Archer / Griffin 등이 상속
 *
 * 책임:
 *  - 공격(attack)
 *  - 이동(move)은 GameUnit에서 상속
 */
abstract class CombatUnit(
    name: String,
    position: Position,
    moveStrategy: MoveStrategy,
    private val attackStrategy: AttackStrategy,
    isFlying: Boolean,
    logger: BattleLogger
) : GameUnit(
    name = name,
    position = position,
    moveStrategy = moveStrategy,
    isFlying = isFlying,
    logger = logger
) {
    /**
     * attack()
     * - 실제 공격 규칙은 attackStrategy가 담당
     * - CombatUnit은 "난 공격할 수 있음"만 알고
     *   '누굴 공격 가능한지'는 전략이 책임
     */
    fun attack(target: GameUnit) {
        attackStrategy.attack(this, target, logger)
    }
}