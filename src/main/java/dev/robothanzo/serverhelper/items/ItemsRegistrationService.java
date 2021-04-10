package dev.robothanzo.serverhelper.items;

import dev.robothanzo.serverhelper.ServerHelper;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import javax.annotation.Nonnull;

public class ItemsRegistrationService {
    public static void register(@Nonnull ServerHelper plugin) {
        Category hanzoSpecials;
        SlimefunItemStack WRENCH;
        ItemStack skull = new CustomItem(Material.PLAYER_HEAD, "&bRobotHanzo 特製物品");
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        meta.setOwner("RobotHanzo");
        skull.setItemMeta(meta);
        hanzoSpecials = new Category(new NamespacedKey(ServerHelper.inst(), "serverhelper"), skull);
        WRENCH = new SlimefunItemStack("WRENCH",
                Material.GOLDEN_HOE,
                "扳手",
                "",
                "&6立即拆除所選之 Slimefun 機器");
        final ItemStack ironIngot = new ItemStack(Material.IRON_INGOT);
        new Wrench(hanzoSpecials, WRENCH,
                RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                ironIngot, null, ironIngot,
                ironIngot, ironIngot, ironIngot,
                null, ironIngot, null
        }).register(plugin);
    }
}
