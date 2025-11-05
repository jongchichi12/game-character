package rts.unit

import rts.core.BattleLogger
import rts.core.MoveStrategy
import rts.core.Position

/**
 * 모든 유닛의 공통 베이스(이름/좌표/비행 여부 보유 + 이동 위임).
 *
 * 불변/관례:
 * - isFlying은 유닛 유형 고유 속성(런타임 중 임의 변경 없음).
 * - 이동/공격/수송 로직은 각각 전략에 위임(중복 최소화).
 */
abstract class GameUnit(
    val name: String,
    var position: Position,
    private val moveStrategy: MoveStrategy,
    val isFlying: Boolean,
    protected val logger: BattleLogger
) {
    /**
     * move()
     * - 실제 이동 방식은 moveStrategy가 담당
     * - GameUnit은 "난 이동할 수 있음"만 알고 "어떻게 이동하는지"는 모른다.
     *   → DIP (추상 의존)
     *   → OCP (새 이동 방식 추가 시 GameUnit 수정 불필요)
     */
    open fun move(to: Position) {
        moveStrategy.move(this, to, logger)
        position = to
    }

    override fun toString(): String {
        return buildString {
            append("$name @ $position")
            if (isFlying) append(" [FLYING]")
        }
    }
}