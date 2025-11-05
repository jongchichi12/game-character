package rts.core

import rts.unit.GameUnit
import rts.unit.TransportUnit

/**
 * 수송 규칙 전략.
 *
 * 규칙 요약:
 * - 정원(capacity) 초과 탑승 불가.
 * - unloadAll(): 모든 탑승자 위치를 셔틀의 현재 좌표로 동기화.
 *
 * 설계 의도:
 * - 수송 정책을 전략으로 분리하여 향후 지상 수송/특수 수송 추가 시 OCP 확보.
 */
interface TransportStrategy {
    fun board(transporter: TransportUnit, passenger: GameUnit, logger: BattleLogger)
    fun unloadAll(transporter: TransportUnit, logger: BattleLogger)
}


class ShuttleTransport(
    private val capacity: Int = 8
) : TransportStrategy {

    override fun board(transporter: TransportUnit, passenger: GameUnit, logger: BattleLogger) {
        if (transporter.passengers.size >= capacity) {
            logger.log("${transporter.name}: 더 이상 태울 수 없습니다. (정원 $capacity 명)")
            return
        }
        transporter.passengers.add(passenger)
        logger.log("${transporter.name}: ${passenger.name} 탑승 완료. (현재 ${transporter.passengers.size}명)")
    }

    override fun unloadAll(transporter: TransportUnit, logger: BattleLogger) {
        if (transporter.passengers.isEmpty()) {
            logger.log("${transporter.name}: 내릴 유닛이 없습니다.")
            return
        }

        logger.log("${transporter.name}: 착륙 지점에서 탑승 병력을 전원 하차시킵니다.")
        val dropPosition = transporter.position

        transporter.passengers.forEach { unit ->
            unit.position = dropPosition
            logger.log(" - ${unit.name} 가 ${dropPosition} 위치에 하차했습니다.")
        }

        transporter.passengers.clear()
    }
}