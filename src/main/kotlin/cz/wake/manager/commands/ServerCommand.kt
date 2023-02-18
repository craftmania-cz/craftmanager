package cz.wake.manager.commands

import co.aikar.commands.*
import co.aikar.commands.annotation.*
import cz.craftmania.crafteconomy.objects.EconomyType
import cz.craftmania.craftlibs.utils.ChatInfo
import org.bukkit.command.CommandSender
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import java.util.*
import java.util.stream.Collectors

@CommandAlias("server")
@CommandPermission("craftmanager.admin")
@Description("Příkaz na správu serveru a možných utilit pro AT")
class ServerCommand(paperCommandManager: PaperCommandManager) : BaseCommand() {

    init {
        paperCommandManager.commandCompletions.registerCompletion("entityTypes"
        ) {
            Arrays.stream(EntityType.values()).map { obj: EntityType -> obj.name }
                .collect(Collectors.toList())
        }
    }

    @HelpCommand
    fun helpCommand(sender: CommandSender, help: CommandHelp) {
        sender.sendMessage("§e§lServer commands:")
        help.showHelp()
    }

    @Subcommand("clearEntities")
    @CommandPermission("craftmanager.admin.commands.clearentity")
    fun clearEntities(sender: CommandSender) {
        if (sender !is Player) {
            sender.sendMessage("Tento příkaz lze použít pouze, pokud jsi připojený na serveru.")
            return
        }
        clearEntitiesAction(sender, null, null)
    }

    @Subcommand("clearEntities")
    @CommandCompletion("[radius]")
    @Syntax("[radius=1]")
    @CommandPermission("craftmanager.admin.clearentity")
    fun clearEntities(sender: CommandSender, radius: Int) {
        if (sender !is Player) {
            sender.sendMessage("Tento příkaz lze použít pouze, pokud jsi připojený na serveru.")
            return
        }
        clearEntitiesAction(sender, radius, null)
    }

    @Subcommand("clearEntities")
    @CommandCompletion("[radius] @entityTypes")
    @Syntax("[radius=1] [entityType=ARMOR_STAND]")
    @CommandPermission("craftmanager.admin.commands.clearentity")
    fun clearEntities(sender: CommandSender, radius: Int, entity: EntityType) {
        if (sender !is Player) {
            sender.sendMessage("Tento příkaz lze použít pouze, pokud jsi připojený na serveru.")
            return
        }
        clearEntitiesAction(sender, radius, entity)
    }

    private fun clearEntitiesAction(player: Player, radius: Int?, entityType: EntityType?) {
        val fixedRadius = radius ?: 1
        if (fixedRadius > 30) {
            ChatInfo.ERROR.send(player, "Nelze mazat entity ve vzdálenosti větší jak 30 bloků.")
            return
        }
        val fixedEntity = entityType ?: EntityType.ARMOR_STAND
        val entityList: List<Entity> = player.getNearbyEntities(fixedRadius.toDouble(), fixedRadius.toDouble(), fixedRadius.toDouble())
        if (entityList.isEmpty()) {
            ChatInfo.ERROR.send(player, "Žádná z požadovaných entit (${fixedEntity}) není v tvé blízkosti ${fixedRadius} bloků, nic nebylo smazáno.")
            return
        }
        var clearCount = 0
        entityList.forEach(action = {
            it.remove()
            clearCount++
        })
        ChatInfo.SUCCESS.send(player, "Smazal jsi ${clearCount}x $fixedEntity v radiusu $fixedRadius bloků.")
    }
}