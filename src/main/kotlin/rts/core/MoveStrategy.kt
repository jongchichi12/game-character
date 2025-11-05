package rts.core

import rts.unit.GameUnit

/**
 * 이동 규칙 전략. (보행/기마/비행 등)
 *
 * 설계 의도:
 * - 유닛은 위치 상태만 보유하고, 실제 이동 방식은 전략 교체로 확장.
 * - 본 과제는 '이동 거리/경로탐색'이 아닌 '방식 차이' 시연에 초점.
 */
interface MoveStrategy {
    fun move(who: GameUnit, to: Position, logger: BattleLogger)
}

/** 걸어서 이동 (궁수 등 지상 보병) */
class WalkMove : MoveStrategy {
    override fun move(who: GameUnit, to: Position, logger: BattleLogger) {
        logger.log("${who.name} 은/는 걸어서 $to 로 이동합니다.")
    }
}

/** 말을 타고 이동 (기사 등 기동력 있는 지상 유닛) */
class HorseMove : MoveStrategy {
    override fun move(who: GameUnit, to: Position, logger: BattleLogger) {
        logger.log("${who.name} 은/는 말을 타고 $to 로 빠르게 이동합니다.")
    }
}

/** 비행 이동 (그리폰, 셔틀 등 공중 유닛) */
class FlyMove : MoveStrategy {
    override fun move(who: GameUnit, to: Position, logger: BattleLogger) {
        logger.log("${who.name} 은/는 하늘을 날아 $to 로 이동합니다.")
    }
}