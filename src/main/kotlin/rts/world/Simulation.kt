package rts.world

import rts.core.BattleLogger
import rts.core.ConsoleLogger
import rts.core.Position
import rts.unit.*

/**
 * Simulation
 * - 과제 시나리오를 그대로 재현한다.
 *
 * 시나리오:
 * 1) Knight 16, Archer 16 만들고 Shuttle 4대에 태운다.
 *    Griffin 5기와 함께 일정 거리를 이동한다.
 *    이동 지역에서 Shuttle에 탄 모든 캐릭터를 내린다.
 *
 * 2) 전투 시연:
 *    Knight 한 기를 선택 -> Knight/Archer/Griffin/Shuttle 각각 공격 시도
 *    Archer 한 기를 선택 -> Archer/Knight/Griffin/Shuttle 각각 공격 시도
 *    Griffin 한 기를 선택 -> Griffin/Archer/Knight/Shuttle 각각 공격 시도
 *
 * logger.log()로 모든 상황을 "해설"처럼 출력한다.
 */


class Simulation {

    private val logger: BattleLogger = ConsoleLogger()

    fun run() {
        logger.log("=== 시뮬레이션 시작 ===")

        // -------------------------
        // 1. 초기 병력 편성
        // -------------------------
        val setup = ArmyFactory(logger).createInitialArmy()
        val knights = setup.knights
        val archers = setup.archers
        val griffins = setup.griffins
        val shuttles = setup.shuttles

        logger.log("\n초기 병력 편성 완료.")
        logger.log(" - 기사 ${knights.size}기, 궁수 ${archers.size}기, 그리폰 ${griffins.size}기, 셔틀 ${shuttles.size}대 준비됨.")

        // -------------------------
        // 2. 전 병력 이동
        //    Griffin 5기와 Shuttle 4대가 같은 목적지로 이동
        //    (Knight/Archer들은 셔틀 안에 탑승해 있으므로 셔틀이 운송)
        // -------------------------
        val dropPoint = Position(20, 5)
        logger.log("\n전 병력이 목적지 $dropPoint 로 이동을 시작합니다.")

        // 셔틀들 이동
        shuttles.forEach { shuttle ->
            shuttle.move(dropPoint)
        }

        // 그리폰들 이동
        griffins.forEach { griffin ->
            griffin.move(dropPoint)
        }

        // -------------------------
        // 3. 착륙 후 하차
        // -------------------------
        logger.log("\n셔틀이 착륙 지점에서 탑승 병력을 하차시킵니다.")
        shuttles.forEach { shuttle ->
            shuttle.unloadAll()
        }

        // 하차 후 실제 좌표 보고 (기사/궁수들 중 일부는 이동한 좌표로 업데이트됨)
        logger.log("\n하차 완료 후 병력 주요 위치 예시:")
        knights.take(4).forEach { logger.log(" - ${it}") }
        archers.take(4).forEach { logger.log(" - ${it}") }
        griffins.take(2).forEach { logger.log(" - ${it}") }
        shuttles.take(1).forEach { logger.log(" - ${it}") }

        // -------------------------
        // 4. 전투 시연
        // -------------------------
        logger.log("\n전투 시연을 시작합니다.")

        val knight1: Knight = knights[0]   // 공격자 Knight
        val knight2: Knight = knights[1]   // Knight 타겟용
        val archer1: Archer = archers[0]   // Archer 공격자 겸 타겟
        val archer2: Archer = archers[1]   // Archer 타겟용
        val griffin1: Griffin = griffins[0]// Griffin 공격자 겸 타겟
        val griffin2: Griffin = griffins[1]// Griffin 타겟용
        val shuttle1: Shuttle = shuttles[0]// Shuttle 타겟용

        // Knight -> Knight / Archer / Griffin / Shuttle
        logger.log("\n[기사의 공격 시연]")
        knight1.attack(knight2)
        knight1.attack(archer1)
        knight1.attack(griffin1)
        knight1.attack(shuttle1)

        // Archer -> Archer / Knight / Griffin / Shuttle
        logger.log("\n[궁수의 공격 시연]")
        archer1.attack(archer2)
        archer1.attack(knight1)
        archer1.attack(griffin1)
        archer1.attack(shuttle1)

        // Griffin -> Griffin / Archer / Knight / Shuttle
        logger.log("\n[그리폰의 공격 시연]")
        griffin1.attack(griffin2)
        griffin1.attack(archer1)
        griffin1.attack(knight1)
        griffin1.attack(shuttle1)

        logger.log("\n=== 시뮬레이션 종료 ===")
    }
}