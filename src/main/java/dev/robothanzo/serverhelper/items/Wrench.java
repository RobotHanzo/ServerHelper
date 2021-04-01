package dev.robothanzo.serverhelper.items;

import dev.robothanzo.serverhelper.ServerHelper;
import io.github.thebusybiscuit.slimefun4.core.attributes.DamageableItem;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunPlugin;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import io.github.thebusybiscuit.slimefun4.implementation.items.cargo.TrashCan;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.protection.ProtectableAction;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.UUID;

public class Wrench extends SimpleSlimefunItem<ItemUseHandler> implements Listener, DamageableItem {


    private static final int WRENCH_DELAY = 200; // Not an itemsetting, too low causes dupes and no reason to increase
    private final HashMap<UUID, Long> cooldowns = new HashMap<>();

    public Wrench(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

        Bukkit.getPluginManager().registerEvents(this, ServerHelper.inst());
    }

    public static void breakBlock(Block block, Player p) {
        BlockBreakEvent breakEvent = new BlockBreakEvent(block, p);
        Bukkit.getPluginManager().callEvent(breakEvent);
        if (!breakEvent.isCancelled()) {
            BlockStorage.clearBlockInfo(block);
            block.setType(Material.AIR);
        }
    }

    @Nonnull
    @Override
    public ItemUseHandler getItemHandler() {
        return e -> e.setUseBlock(Event.Result.DENY);
    }

    @EventHandler
    public void onWrenchInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        Long cooldown = cooldowns.get(p.getUniqueId());
        if (isItem(e.getItem()) && cooldown != null
        ) {
            if ((System.currentTimeMillis() - cooldown) < WRENCH_DELAY) {
                return;
            }
        }
        cooldowns.put(p.getUniqueId(), System.currentTimeMillis());

        Block block = e.getClickedBlock();
        // Check if player has wrench and is left clicking block
        // Can't use offhand because a player can offhand the wrench to escape the event
        if (isItem(e.getItem()) && !isItem(p.getInventory().getItemInOffHand())
                && e.getAction().toString().endsWith("_BLOCK")
                && SlimefunPlugin.getProtectionManager().hasPermission(e.getPlayer(),
                block.getLocation(), ProtectableAction.BREAK_BLOCK)
        ) {
            e.setCancelled(true);
            SlimefunItem slimefunBlock = BlockStorage.check(block);

            // Check if slimefunBlock is not a machine or a cargo component
            if (slimefunBlock == null
                    || (!(slimefunBlock instanceof EnergyNetComponent)
                    && !slimefunBlock.getId().startsWith("CARGO_NODE")
                    && !slimefunBlock.getId().equals(SlimefunItems.CARGO_MANAGER.getItemId())
                    && !(slimefunBlock instanceof TrashCan))
            ) {
                return;
            }

            breakBlock(block, p);
        }
    }

    @Override
    public boolean isDamageable() {
        return false;
    }
}
