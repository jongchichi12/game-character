## 실행 방법(필수)

### Gradle로 바로 실행
macOS / Linux
./gradlew clean run
windows
gradlew.bat clean run

### JAR로 실행
macOS / Linux
./gradlew clean jar
java -jar build/libs/RTS-1.0.0.jar
windows
gradlew.bat clean jar
java -jar build/libs/RTS-1.0.0.jar

# 최소 요건
JDK 17+ (Gradle Wrapper 포함)


## 핵심 파일/클래스 역할(필수 요약)

### 엔트리
| 구분 | 파일/클래스 | 역할 |
|---|---|---|
| 엔트리 | `rts/Main.kt` | 프로그램 시작점. `Simulation().run()` 호출 |

### 조립/시나리오 (`rts.world`)
| 구분 | 파일/클래스 | 역할 |
|---|---|---|
| 조립 | `ArmyFactory` | 유닛 생성·전략 주입·탑승(정원 체크)까지 **한 곳에서 조립** |
| 시나리오 | `Simulation` | 시나리오 실행(편성 → 이동 → 하차 → 공격 규칙 시연) |

### 유닛 계층 (`rts.unit`)
| 구분 | 파일/클래스 | 역할 |
|---|---|---|
| 베이스 | `GameUnit` | 모든 유닛의 **공통 상태(이름/좌표/비행 여부)** 보유, **이동 전략 위임** |
| 전투 부모 | `CombatUnit` | 전투 가능한 유닛의 부모, **공격 전략 위임** |
| 수송 부모 | `TransportUnit` | 수송 유닛의 부모, **탑승/하차 전략 위임** |
| 전투 유닛 | `Knight` / `Archer` / `Griffin` | `CombatUnit`의 구체 유닛(**Griffin은 공중**) |
| 수송 유닛 | `Shuttle` | `TransportUnit`의 구체 유닛(**공중**, 공격 없음) |

### 전략/공통 (`rts.core`)
| 구분 | 파일/클래스 | 역할 |
|---|---|---|
| 이동 전략 | `MoveStrategy` (+ `WalkMove`/`HorseMove`/`FlyMove`) | **이동 방식만** 다름 |
| 공격 전략 | `AttackStrategy` (+ `LanceAttack`/`ArrowAttack`/`LightningAttack`) | 규칙: **Lance/Lightning → 공중 대상 공격 불가**, **Arrow → 공중/지상 모두 가능** |
| 수송 전략 | `TransportStrategy` (+ `ShuttleTransport`) | **정원(capacity) 초과 탑승 불가**, `unloadAll()` 시 **탑승자 좌표를 셔틀 위치로 동기화** |
| 로깅 | `BattleLogger` (+ `ConsoleLogger`) | 로그 출력 인터페이스/구현 |
| 좌표 | `Position` | 좌표 값 객체 |
