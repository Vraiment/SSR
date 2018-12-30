package vraiment.sage.savereader.model

data class Id(val value: Int)

data class IdInArea(val id: Id, val area: Id)

data class Conversation(val area: Id, val id: Id, val number: Byte)

data class Conversations(val conditions: Int, val entries: List<Conversation>)

data class Quests(
        val quest0: Byte,
        val quest1: Byte,
        val quest2: Byte,
        val quest3: Byte,
        val quest4: Byte,
        val quest5: Byte
)

data class Time(val area: Id, val value: Float)

data class SaveFile(
        val version: Byte,
        val level: Byte,
        val area: Id,
        val position: Id,
        val look: Id,
        val toa: Id,
        val mask: Int,
        val glyph: Byte,
        val health: Byte,
        val energy: Byte,
        val tokens: List<IdInArea>,
        val maskList: List<IdInArea>,
        val glyphs: List<IdInArea>,
        val inventory: List<IdInArea>,
        val cinema: List<Id>,
        val conversations: Conversations,
        val times: List<Time>,
        val quests: Quests,
        val masks: Byte,
        val glyphFound: Byte,
        val stoneFound: Byte,
        val hookFound: Byte,
        val ammo: Short
)
