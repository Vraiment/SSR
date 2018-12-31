package vraiment.sage.ssr.view

import vraiment.sage.ssr.model.Conversation
import vraiment.sage.ssr.model.Id
import vraiment.sage.ssr.model.IdInArea
import vraiment.sage.ssr.model.SaveFile
import vraiment.sage.ssr.model.Time
import vraiment.sage.ssr.view.dsl.treeItem
import java.nio.ByteBuffer

fun SaveFile.getView(saveFileName: String) = treeItem(saveFileName) {
    children {
        treeItem("version = ${version.displayString}") // Byte
        treeItem("level = $level") // Byte
        treeItem("area = ${area.displayString}") // Id
        treeItem("position = ${position.displayString}") // Id
        treeItem("look = ${look.displayString}") // Id
        treeItem("toa = ${toa.displayString}") // Id
        treeItem("mask = $mask") // Int
        treeItem("glyph = $glyph") // Byte
        treeItem("health = $health") // Byte
        treeItem("energy = $energy") // Byte
        treeItem("tokens (${tokens.size})") { // List<IdInArea>
            children {
                tokens.map { treeItem(it.displayString) }
            }
        }
        treeItem("maskList (${maskList.size})") { // List<IdInArea>
            children {
                maskList.map { treeItem(it.displayString) }
            }
        }
        treeItem("glyphs (${glyphs.size})") { // List<IdInArea>
            children {
                glyphs.map { treeItem(it.displayString) }
            }
        }
        treeItem("inventory (${inventory.size})") { // List<IdInArea>
            children {
                inventory.map { treeItem(it.displayString) }
            }
        }
        treeItem("cinema (${cinema.size})") { // List<Id>
            children {
                cinema.map { treeItem(it.displayString) }
            }
        }
        treeItem("conversations") { // Conversations
            children {
                treeItem("conditions = ${conversations.conditions}")
                treeItem("entries (${conversations.entries.size})") {
                    children {
                        conversations.entries.map { treeItem(it.displayString) }
                    }
                }
            }
        }
        treeItem("times (${times.size})") { // List<Time>
            children {
                times.map { treeItem(it.displayString) }
            }
        }
        treeItem("quests") { // Quests
            children {
                treeItem("quest 0 = ${quests.quest0}")
                treeItem("quest 1 = ${quests.quest1}")
                treeItem("quest 2 = ${quests.quest2}")
                treeItem("quest 3 = ${quests.quest3}")
                treeItem("quest 4 = ${quests.quest4}")
                treeItem("quest 5 = ${quests.quest5}")

            }
        }
        treeItem("masks = $masks") // Byte?
        treeItem("glyphFound = $glyphFound") // Byte?
        treeItem("stoneFound = $stoneFound") // Byte?
        treeItem("hookFound = $hookFound") // Byte?
        treeItem("ammo = $ammo") // Short?
    }
}


private fun Int.toByteArray() = ByteBuffer.allocate(4).putInt(this).array()

private val Byte.displayString get() = "0x%02X".format(this)

private val Id.displayString get() = value.toByteArray().map { it.displayString }.toString()

private val IdInArea.displayString get() = "id = ${id.displayString} area = ${area.displayString}"

private val Conversation.displayString get() = "area = ${area.displayString} id = ${id.displayString} number = $number"

private val Time.displayString get() = " area = ${area.displayString} value = $value "
