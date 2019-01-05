package vraiment.sage.ssr.io

import vraiment.sage.ssr.model.Conversation
import vraiment.sage.ssr.model.Conversations
import vraiment.sage.ssr.model.Conversations.Condition
import vraiment.sage.ssr.model.Id
import vraiment.sage.ssr.model.IdInArea
import vraiment.sage.ssr.model.Quests
import vraiment.sage.ssr.model.SaveFile
import vraiment.sage.ssr.model.Time
import java.io.File
import java.io.InputStream

/**
 * Reads the input file and stores its contents in an [SaveFile] instance.
 *
 * @param file The input file to be read.
 *
 * @return A [SaveFile] object with the values from the input file.
 *
 * @throws IllegalArgumentException In case the input file is incorrect.
 */
fun readSaveFile(file: File) = file.inputStream().readSaveFile()

private fun failReadingFile(): Nothing = throw IllegalArgumentException("The input file doesn't have the correct format")

private fun InputStream.readSaveFile() = SaveFile(
        version = readByte(),
        level = readByte(),
        area = readId(),
        position = readId(),
        look = readId(),
        toa = readId(),
        mask = readInt(),
        glyph = readByte(),
        health = readByte(),
        energy = readByte(),
        tokens = readIdInAreaList(),
        maskList = readIdInAreaList(),
        glyphs = readIdInAreaList(),
        inventory = readIdInAreaList(),
        cinema = readIdList(),
        conversations = readConversations(),
        times = readTimes(),
        quests = readQuests(),
        masks = tryReadByte(),
        glyphFound = tryReadByte(),
        stoneFound = tryReadByte(),
        hookFound = tryReadByte(),
        ammo = tryReadShort()
)

private fun InputStream.readByte() = tryReadByte() ?: failReadingFile()

private fun InputStream.readShort() = tryReadShort() ?: failReadingFile()

private fun InputStream.readInt() = tryReadInt() ?: failReadingFile()

private fun <T> InputStream.readList(count: Number, block: (InputStream) -> T) = List(count.toInt()) { block(this) }

private fun InputStream.readIdList() = readList(readByte()) { it.readId() }

private fun InputStream.readId() = Id(
        value = readInt()
)

private fun InputStream.readIdInArea() = IdInArea(
        id = readId(),
        area = readId()
)

private fun InputStream.readIdInAreaList() = readList(readByte()) { it.readIdInArea() }

private fun InputStream.readConversation() = Conversation(
        area = readId(),
        id = readId(),
        number = readByte()
)

private fun InputStream.readConversationsConditions(): Map<Condition, Boolean> = readInt().let { value ->
    Condition.values().associate { condition -> condition to condition.isTrue(value) }
}

// Returns true if the bit/flag for the condition is set in the input value
private fun Condition.isTrue(value: Int): Boolean = 0 != when (this) {
    Condition.khaka1 -> value and (1 shl 0)
    Condition.khaka2 -> value and (1 shl 1)
    Condition.khaka3 -> value and (1 shl 2)
    Condition.khakadone -> value and (1 shl 3)
    Condition.kmsk1 -> value and (1 shl 4)
    Condition.kmsk2 -> value and (1 shl 5)
    Condition.kmsk3 -> value and (1 shl 6)
    Condition.kgly1 -> value and (1 shl 7)
    Condition.kgly2 -> value and (1 shl 8)
    Condition.kgly3 -> value and (1 shl 9)
    Condition.kitem1_kitem3 -> value and (1 shl 10)
    Condition.kitem2 -> value and (1 shl 11)
    Condition.unused1 -> value and (1 shl 12)
    Condition.kquest1 -> value and (1 shl 13)
    Condition.kquest2 -> value and (1 shl 14)
    Condition.kquest3 -> value and (1 shl 15)
    Condition.kquest4 -> value and (1 shl 16)
    Condition.kquest5 -> value and (1 shl 17)
    Condition.kquest6 -> value and (1 shl 18)
    Condition.kquest7 -> value and (1 shl 19)
    Condition.kquest8 -> value and (1 shl 20)
    Condition.kquest9 -> value and (1 shl 21)
    Condition.kmisc1 -> value and (1 shl 22)
    Condition.kmisc2 -> value and (1 shl 23)
    Condition.kmisc3 -> value and (1 shl 24)
    Condition.kmisc4 -> value and (1 shl 25)
    Condition.kmisc5 -> value and (1 shl 26)
    Condition.kmisc6 -> value and (1 shl 27)
    Condition.kmisc7 -> value and (1 shl 28)
    Condition.kmisc8 -> value and (1 shl 29)
    Condition.kmisc9 -> value and (1 shl 30)
    Condition.krandom -> value and (1 shl 31)
}

private fun InputStream.readConversations() = Conversations(
        conditions = readConversationsConditions(),
        entries = readList(readShort()) { it.readConversation() }
)

private fun InputStream.readTime() = Time(
        area = readId(),
        value = readInt().bitsToFloat()
)

private fun InputStream.readTimes() = readList(readByte()) { it.readTime() }

private fun InputStream.readQuests() = Quests(
        quest0 = readByte(),
        quest1 = readByte(),
        quest2 = readByte(),
        quest3 = readByte(),
        quest4 = readByte(),
        quest5 = readByte()
)
