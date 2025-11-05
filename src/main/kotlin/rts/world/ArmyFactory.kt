package rts.world

import rts.core.*
import rts.unit.*

/**
 * 유닛 편성/전략 주입을 한 곳에서 담당하는 조립자.
 *
 * 의도:
 * - 생성/초기화 중복 제거, 테스트 용이성 향상.
 * - 전략/수량/탑승 조합을 한 곳에서 변경 가능.
 * 요구:
 *  - Knight 16기
 *  - Archer 16기
 *  - Shuttle 4대 (Knight/Archer를 나눠 태움)
 *  - Griffin 5기
 *
 * 또한:
 *  - Shuttle에 Knight/Archer를 태우는 작업까지 여기서 처리한다.
 */

data class ArmySetup(
    val knights: List<Knight>,
    val archers: List<Archer>,
    val griffins: List<Griffin>,
    val shuttles: List<Shuttle>
)

class ArmyFactory(
    private val logger: BattleLogger
) {

    fun createInitialArmy(): ArmySetup {
        // 전략 인스턴스들 (재사용 가능하도록 한 번만 만든다)
        val horseMove = HorseMove()
        val walkMove = WalkMove()
        val flyMove = FlyMove()

        val lanceAttack = LanceAttack()
        val arrowAttack = ArrowAttack()
        val lightningAttack = LightningAttack()

        val shuttleTransport = ShuttleTransport(capacity = 8)

        // Knight 16기
        val knights = (1..16).map { i ->
            Knight(
                name = "기사#$i",
                start = Position(0, 0),
                moveStrategy = horseMove,
                attackStrategy = lanceAttack,
                logger = logger
            )
        }

        // Archer 16기
        val archers = (1..16).map { i ->
            Archer(
                name = "궁수#$i",
                start = Position(0, 0),
                moveStrategy = walkMove,
                attackStrategy = arrowAttack,
                logger = logger
            )
        }

        // Griffin 5기
        val griffins = (1..5).map { i ->
            Griffin(
                name = "그리폰#$i",
                start = Position(10, 10),
                moveStrategy = flyMove,
                attackStrategy = lightningAttack,
                logger = logger
            )
        }

        // Shuttle 4대
        val shuttles = (1..4).map { i ->
            Shuttle(
                name = "셔틀#$i",
                start = Position(-5, -5),
                moveStrategy = flyMove,
                transportStrategy = shuttleTransport,
                logger = logger
            )
        }

        // Knight/Archer를 셔틀에 태운다.
        // 간단하게: 앞쪽 기사/궁수 몇 명만 탑승시켜서 "셔틀 수송 중"이라는 시나리오만 보여주자.
        // 셔틀당 최대 8명 탑승 가능하니까, 4 + 4씩 넣는 식으로 예시로 구성.
        // 기사1~4 + 궁수1~4 -> 셔틀#1
        // 기사5~8 + 궁수5~8 -> 셔틀#2
        // 기사9~12 + 궁수9~12 -> 셔틀#3
        // 기사13~16 + 궁수13~16 -> 셔틀#4
        for (s in 0 until 4) {
            val shuttle = shuttles[s]
            val kStart = s * 4
            val aStart = s * 4
            for (offset in 0 until 4) {
                shuttle.board(knights[kStart + offset])
                shuttle.board(archers[aStart + offset])
            }
        }

        return ArmySetup(
            knights = knights,
            archers = archers,
            griffins = griffins,
            shuttles = shuttles
        )
    }
}