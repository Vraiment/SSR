package vraiment.sage.ssr.io

import vraiment.sage.ssr.model.Conversation
import vraiment.sage.ssr.model.Conversations
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

private fun InputStream.readConversations() = Conversations(
        conditions = readInt(),
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
