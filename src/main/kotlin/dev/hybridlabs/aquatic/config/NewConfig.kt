package dev.hybridlabs.aquatic.config

import eu.midnightdust.lib.config.MidnightConfig

object NewConfig : MidnightConfig() {
    @Entry(min = 0.0, max = 100.0)
    var JELLYFISH_SPAWNWEIGHT = 50.0

    @Entry(min = 0.0, max = 100.0)
    var HERMITCRAB_SPAWNWEIGHT = 50.0

    @Entry
    var SUNFISH_HEATH = 30.0

    @Entry
    var SUNFISH_SPEED = 2.5

    @Entry(min = 0.0, max = 100.0)
    var SUNFISH_SPAWNWEIGHT = 50.0

    @Entry
    var SHARK_HEALTH = 30.0

    @Entry(min = 0.0, max = 100.0)
    var SHARK_SPAWNWEIGHT = 50.0

    @Entry
    var SHARK_ATTACK_FISH = false

    @Entry
    var SHARK_SPEED = 2.5

    @Entry
    var SHARK_ATTACK_SPEED = 4.0

    @Entry
    var SHARK_FOLLOW = 4.0

    @Entry
    var SHARK_DAMAGE = 8.0

    @Entry
    var SHARK_KNOCKBACK = 0.1

    @Entry(min = 0.0, max = 100.0)
    var WHALESHARK_SPAWNWEIGHT = 50

    @Entry
    var WHALESHARK_HEALTH = 30

    @Entry
    var WHALESHARK_ATTACK_FISH = false

    @Entry
    var WHALESHARK_SPEED = 2.0

    @Entry
    var WHALESHARK_ATTACK_SPEED = 4.0

    @Entry
    var WHALESHARK_FOLLOW = 32.0

    @Entry
    var WHALESHARK_DAMAGE = 8.0

    @Entry
    var WHALESHARK_KNOCKBACK = 0.1

    @Entry(min = 0.0, max = 100.0)
    var MOONJELLY_SPAWNWEIGHT = 50

    @Entry
    var YELLOWFIN_HEALTH = 15

    @Entry
    var YELLOWFIN_SPEED = 1.8

    @Entry(min = 0.0, max = 100.0)
    var YELLOWFIN_SPAWNWEIGHT = 50

    @Entry
    var CUTTLEFISH_HEALTH = 15

    @Entry
    var CUTTLEFISH_SPEED = 1.9

    @Entry(min = 0.0, max = 100.0)
    var CUTTLEFISH_SPAWNWEIGHT = 50

    @Entry
    var OPAH_HEALTH = 15

    @Entry
    var OPAH_SPEED = 1.8

    @Entry(min = 0.0, max = 100.0)
    var OPAH_SPAWNWEIGHT = 50

    @Entry
    var LIONFISH_HEALTH = 15

    @Entry
    var LIONFISH_SPEED = 1.9

    @Entry(min = 0.0, max = 100.0)
    var LIONFISH_SPAWNWEIGHT = 50

    @Entry
    var CLOWNFISH_HEALTH = 15

    @Entry
    var CLOWNFISH_SPEED = 1.8

    @Entry(min = 0.0, max = 100.0)
    var CLOWNFISH_SPAWNWEIGHT = 50

    @Entry
    var SPOTTEDSTINGRAY_HEALTH = 15

    @Entry
    var SPOTTEDSTINGRAY_SPEED = 1.8

    @Entry(min = 0.0, max = 100.0)
    var SPOTTEDSTINGRAY_SPAWNWEIGHT = 50

    @Entry(min = 0.0, max = 100.0)
    var PIRANHA_SPAWNWEIGHT = 50

    @Entry(min = 0.0, max = 100.0)
    var TETRA_SPAWNWEIGHT = 50

    @Entry(min = 0.0, max = 100.0)
    var BETTA_SPAWNWEIGHT = 50

    @Entry
    var ANGLERFISH_HEALTH = 6

    @Entry
    var ANGLERFISH_KNOCKBACK = 0.1

    @Entry
    var ANGLERFISH_FOLLOW = 10.0

    @Entry
    var ANGLERFISH_SPEED = 1.8

    @Entry(min = 0.0, max = 100.0)
    var ANGLERFISH_SPAWNWEIGHT = 50

    @Entry
    var LEVIATHAN_HEALTH = 60

    @Entry
    var LEVIATHAN_SPEED = 1.5

    @Entry(min = 0.0, max = 100.0)
    var LEVIATHAN_SPAWNWEIGHT = 1

    @Entry(min = 0.0, max = 100.0)
    var GIANTSQUID_SPAWNWEIGHT = 10

    @Entry
    var PIRANHA_DAMAGE = 2.0

    @Entry
    var PIRANHA_HEALTH = 10

    @Entry
    var PIRANHA_KNOCKBACK = 0.1

    @Entry
    var PIRANHA_FOLLOW = 10.0

    @Entry
    var PIRANHA_SPEED = 1.8

    @Entry(min = 0.0, max = 100.0)
    var SEAHORSE_SPAWNWEIGHT = 50.0

    @Entry
    var VAMPIRESQUID_SPEED = 1.2

    @Entry
    var VAMPIRESQUID_HEALTH = 10.0

    @Entry
    var VAMPIRESQUID_SPAWNWEIGHT = 10

    @Entry
    var OARFISH_HEALTH = 10

    @Entry
    var OARFISH_KNOCKBACK = 0.1

    @Entry
    var OARFISH_FOLLOW = 10.0

    @Entry
    var OARFISH_SPEED = 1.8

    @Entry
    var BEHOLDER_TPS = 15

    @Entry
    var BEHOLDER_MAX_RANGE = 40

    @Entry
    var SQUIDLING_SPEED = 1.2

    @Entry
    var SQUIDLING_HEALTH = 10.0

    @Entry
    var SEAHORSE_SPEED = 1.2

    @Entry
    var SEAHORSE_HEALTH = 2.0

    @Entry
    var SEAANGLE_SPEED = 1.8

    @Entry
    var SEAANGLE_HEALTH = 3.0

    @Entry
    var SEAANGLE_FOLLOWRANGE = 3.0

    @Entry
    var FLASHLIGHT_SPEED = 1.8

    @Entry
    var FLASHLIGHT_HEALTH = 4.0

    @Entry
    var FLASHLIGHT_FOLLOWRANGE = 3.0

    @Entry
    var BARRELEYE_SPEED = 1.8

    @Entry
    var BARRELEYE_HEALTH = 6.0

    @Entry
    var BARRELEYE_FOLLOWRANGE = 3.0

    @Entry(min = 0.0, max = 100.0)
    var OARFISH_SPAWNWEIGHT = 25

    @Entry
    var waterToggle = true

    @Entry(min = -200.0, max = 200.0)
    var waterStartDeep = -100.0f

    @Entry(min = -200.0, max = 200.0)
    var waterEndDeep = 65.0f

    @Entry(min = -200.0, max = 200.0)
    var waterEndSwamp = 50.0f
}
