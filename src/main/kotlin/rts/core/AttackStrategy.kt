package rts.core

import rts.unit.CombatUnit
import rts.unit.GameUnit

/**
 * 공격 규칙을 외부화한 전략 인터페이스.
 *
 * 규칙 요약:
 * - LanceAttack  : 공중 대상 공격 불가 (지상 전용 창).
 * - ArrowAttack  : 지상/공중 모두 가능.
 * - LightningAttack : 공중 대상 공격 불가 (지상 전용 번개).
 *
 * 설계 의도:
 * - 유닛은 '전투 가능' 여부만 가지고, 실제 규칙은 전략으로 교체/확장.
 * - 사정거리(range) 개념은 본 과제에서 사용하지 않음.
 */
interface AttackStrategy {
    /**
     * attack
     * @param attacker 실제 공격 시도하는 유닛
     * @param target 공격 대상
     * @param logger 로그 출력
     */
    fun attack(attacker: CombatUnit, target: GameUnit, logger: BattleLogger)
}

/**
 * Knight 전용 공격 (창 찌르기 느낌)
 *
 * 룰(예시 가정):
 *  - 기사는 근접 지상 전투 유닛이다.
 *  - 공중 유닛(그리폰, 셔틀 등 isFlying == true)은 공격할 수 없다.
 */
class LanceAttack : AttackStrategy {
    override fun attack(attacker: CombatUnit, target: GameUnit, logger: BattleLogger) {
        // Knight는 공중 유닛 공격 불가
        if (target.isFlying) {
            logger.log("${attacker.name} (기사)는 ${target.name} 을/를 공격할 수 없습니다. (공중 유닛)")
            return
        }
        logger.log("${attacker.name} (기사)이/가 창으로 ${target.name} 을/를 공격합니다!")
    }
}

/**
 * Archer 전용 공격 (원거리 활)
 *
 * 룰(예시 가정):
 *  - 궁수는 지상/공중 모두 공격 가능하다고 하자.
 *    (원거리 활로 공중 유닛도 쏠 수 있다)
 */
class ArrowAttack : AttackStrategy {
    override fun attack(attacker: CombatUnit, target: GameUnit, logger: BattleLogger) {
        logger.log("${attacker.name} (궁수)이/가 화살을 날려 ${target.name} 을/를 공격합니다!")
    }
}

/**
 * Griffin 전용 공격 (번개)
 *
 * 룰(예시 가정):
 *  - 그리폰은 공중 전투 위주라 지상 유닛만 노린다.
 *  - 즉, 공중 유닛(다른 Griffin, Shuttle 등)은 공격 불가로 둔다.
 *
 *  (이건 수업에서 따로 정해졌다면 그 쪽으로 바꿔도 됨.
 *   여기선 Knight/Archer는 공격 가능, Griffin/Shuttle은 불가능으로 간다.)
 */
class LightningAttack : AttackStrategy {
    override fun attack(attacker: CombatUnit, target: GameUnit, logger: BattleLogger) {
        if (target.isFlying) {
            logger.log("${attacker.name} (그리폰)은 ${target.name} 을/를 공격할 수 없습니다. (공중 유닛 대상 불가)")
            return
        }
        logger.log("${attacker.name} (그리폰)이/가 번개로 ${target.name} 을/를 공격합니다!! ⚡")
    }
}