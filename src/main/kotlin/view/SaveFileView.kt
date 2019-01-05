package vraiment.sage.ssr.view

import vraiment.sage.ssr.model.Conversation
import vraiment.sage.ssr.model.Id
import vraiment.sage.ssr.model.IdInArea
import vraiment.sage.ssr.model.SaveFile
import vraiment.sage.ssr.model.Time
import vraiment.sage.ssr.view.dsl.treeItem
import java.nio.ByteBuffer

fun SaveFile.getView(saveFileName: String) = treeItem<String> {
    value = saveFileName

    treeItem { value = "version = ${version.displayString}" } // Byte
    treeItem { value = "level = $level" } // Byte
    treeItem { value = "area = \"${area.displayString}\"" } // Id
    treeItem { value = "position = \"${position.displayString}\"" } // Id
    treeItem { value = "look = \"${look.displayString}\"" } // Id
    treeItem { value = "toa = \"${toa.displayString}\"" } // Id
    treeItem { value = "mask = $mask" } // Int
    treeItem { value = "glyph = $glyph" } // Byte
    treeItem { value = "health = $health" } // Byte
    treeItem { value = "energy = $energy" } // Byte
    treeItem {
        value = "tokens (${tokens.size})" // List<IdInArea>
        tokens.map {
            treeItem { value = it.displayString }
        }
    }
    treeItem {
        value = "maskList (${maskList.size})" // List<IdInArea>
        maskList.map {
            treeItem { value = it.displayString }
        }
    }
    treeItem {
        value = "glyphs (${glyphs.size})" // List<IdInArea>
        glyphs.map {
            treeItem { value = it.displayString }
        }
    }
    treeItem {
        value = "inventory (${inventory.size})" // List<IdInArea>
        inventory.map {
            treeItem { value = it.displayString }
        }
    }
    treeItem {
        value = "cinema (${cinema.size})" // List<Id>
        cinema.map {
            treeItem { value = it.displayString }
        }
    }
    treeItem {
        value = "conversations" // Conversations
        treeItem {
            value = "conditions"

            conversations.conditions.forEach { condition ->
                treeItem { value = "${condition.key} = ${condition.value}"  }
            }
        }
        treeItem {
            value = "entries (${conversations.entries.size})"
            conversations.entries.map {
                treeItem { value = it.displayString }
            }
        }
    }
    treeItem {
        value = "times (${times.size})" // List<Time>
        times.map {
            treeItem { value = it.displayString }
        }
    }
    treeItem {
        value = "quests" // Quests
        treeItem { value = "quest 0 = ${quests.quest0}" }
        treeItem { value = "quest 1 = ${quests.quest1}" }
        treeItem { value = "quest 2 = ${quests.quest2}" }
        treeItem { value = "quest 3 = ${quests.quest3}" }
        treeItem { value = "quest 4 = ${quests.quest4}" }
        treeItem { value = "quest 5 = ${quests.quest5}" }
    }
    treeItem { value = "masks = $masks" } // Byte?
    treeItem { value = "glyphFound = $glyphFound" } // Byte?
    treeItem { value = "stoneFound = $stoneFound" } // Byte?
    treeItem { value = "hookFound = $hookFound" } // Byte?
    treeItem { value = "ammo = $ammo" } // Short?
}

private fun Int.toByteArray() = ByteBuffer.allocate(4).putInt(this).array()

private val Byte.displayString get() = "0x%02X".format(this)

private val Id.displayString get() = String(value.toByteArray())

private val IdInArea.displayString get() = "id = \"${id.displayString}\", area = \"${area.displayString}\""

private val Conversation.displayString get() = "area = \"${area.displayString}\", id = \"${id.displayString}\", number = $number"

private val Time.displayString get() = " area = \"${area.displayString}\" value = \"$value\""
