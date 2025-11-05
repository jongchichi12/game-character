package rts.core

/**
 * BattleLogger
 * - 전투/이동/하차 등 게임 내에서 발생하는 사건을 기록하는 역할
 * - 지금은 콘솔로 찍지만, 나중에는 파일 저장, UI 출력 등으로 교체 가능
 *
 * SRP:
 *  출력 책임은 Logger가 전담하고,
 *  이동/전투/수송 클래스는 로직만 담당한다.
 */
interface BattleLogger {
    fun log(message: String)
}

class ConsoleLogger : BattleLogger {
    override fun log(message: String) {
        println(message)
    }
}