package com.projecturanus.betterp2p.client.gui

import com.projecturanus.betterp2p.network.P2PInfo
import net.minecraft.client.gui.GuiButton
import net.minecraft.client.resources.I18n
import net.minecraft.util.EnumFacing
import java.util.*

class InfoWrapper(info: P2PInfo) {
    // Basic information
    val index: Int = info.index
    val frequency: Long = info.frequency
    val hasChannel = info.hasChannel
    val posX: Int = info.posX
    val posY: Int = info.posY
    val posZ: Int = info.posZ
    val world: Int = info.world
    val facing: EnumFacing = info.facing
    val description: String
    val output: Boolean = info.output
    var name: String = info.name
    var error: Boolean = false

    // Widgets
    val selectButton = GuiButton(0, 0, 0, 34, 20, I18n.format("gui.advanced_memory_card.select"))
    val bindButton = GuiButton(0, 0, 0, 34, 20, I18n.format("gui.advanced_memory_card.bind"))
    val renameButton = GuiButton(0, 0, 0, 0, 0,"")
    init {
        description = buildString {
            append("P2P ")
            if (output)
                append(I18n.format("gui.advanced_memory_card.desc.mode.output"))
            else
                append(I18n.format("gui.advanced_memory_card.desc.mode.input"))
            append(" - ")
            if (info.frequency.toInt() == 0)
                append(I18n.format("gui.advanced_memory_card.desc.not_set"))
            else
                append(info.frequency.toHexString().format4())
        }
    }
}

fun Long.toHexString(): String {
    var tmp = this
    var hex = String()
    while (tmp != 0.toLong()) {
        hex += Integer.toHexString((tmp % 16).toInt())
        tmp /= 16
    }
    return hex.uppercase(Locale.getDefault()).reversed()
}

fun String.format4(): String {
    var format = String()
    for (index in this.indices) {
        if (index % 4 == 0 && index != 0) {
            format += " "
        }
        format += this[index]
    }
    return format
}
