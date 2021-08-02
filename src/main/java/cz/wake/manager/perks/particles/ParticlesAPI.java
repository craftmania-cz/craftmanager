package cz.wake.manager.perks.particles;

import cz.craftmania.craftcore.inventory.builder.SmartInventory;
import cz.wake.manager.menu.CosmeticMainGUI;
import cz.wake.manager.perks.particles.capes.ChristmasCape;
import cz.wake.manager.perks.particles.capes.SpookyCape;
import cz.wake.manager.perks.particles.capes.SummerSplash;
import cz.wake.manager.perks.particles.special.FireWalk;
import cz.wake.manager.perks.particles.special.SantaHat;
import cz.wake.manager.perks.particles.vip.*;
import cz.wake.manager.perks.particles.vip.Void;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ParticlesAPI implements Listener {

    private Hearts h = new Hearts();
    private Angry a = new Angry();
    private WhiteMagic w = new WhiteMagic();
    private WitchMagic m = new WitchMagic();
    private Slime s = new Slime();
    private Flame f = new Flame();
    private Redstone r = new Redstone();
    private Cloud l = new Cloud();
    private Enchanted e = new Enchanted();
    private EndRod er = new EndRod();
    private Firework k = new Firework();
    private DragonBreath db = new DragonBreath();
    private Portal p = new Portal();
    private Lava la = new Lava();
    private Smoke sm = new Smoke();
    private Happy ha = new Happy();
    private Snowball sn = new Snowball();
    private BlackHearts bh = new BlackHearts();
    private cz.wake.manager.perks.particles.vip.Void vo = new cz.wake.manager.perks.particles.vip.Void();

    public void openParticlesMenu(final Player p) {

        Inventory inv = Bukkit.createInventory(null, 54, "VIP Particles");

        if (p.hasPermission("craftmanager.particles.hearts")) {
            if (Hearts.e.containsKey(p.getName())) {
                ItemStack i = ItemFactory.create(Material.APPLE, "§eHearts", "§7Kliknutim deaktivujes!");
                i = ItemFactory.addGlow(i);
                inv.setItem(10, i);
            } else {
                ItemStack i = ItemFactory.create(Material.APPLE, "§eHearts", "§7Kliknutim aktivujes!");
                inv.setItem(10, i);
            }
        } else {
            ItemStack i = ItemFactory.create(Material.LIGHT_GRAY_DYE, "§cHearts", "", "§7Tento efekt smi aktivovat pouze: §aVIP");
            inv.setItem(10, i);
        }
        if (p.hasPermission("craftmanager.particles.angry")) {
            if (Angry.e.containsKey(p.getName())) {
                ItemStack i = ItemFactory.create(Material.BLAZE_POWDER, "§eAngry", "§7Kliknutim deaktivujes!");
                i = ItemFactory.addGlow(i);
                inv.setItem(11, i);
            } else {
                ItemStack i = ItemFactory.create(Material.BLAZE_POWDER, "§eAngry", "§7Kliknutim aktivujes!");
                inv.setItem(11, i);
            }
        } else {
            ItemStack i = ItemFactory.create(Material.LIGHT_GRAY_DYE, "§cAngry", "", "§7Tento efekt smi aktivovat pouze: §aVIP");
            inv.setItem(11, i);
        }
        if (p.hasPermission("craftmanager.particles.whitemagic")) {
            if (WhiteMagic.e.containsKey(p.getName())) {
                ItemStack i = ItemFactory.create(Material.BONE, "§eWhite Magic", "§7Kliknutim deaktivujes!");
                i = ItemFactory.addGlow(i);
                inv.setItem(12, i);
            } else {
                ItemStack i = ItemFactory.create(Material.BONE, "§eWhite Magic", "§7Kliknutim aktivujes!");
                inv.setItem(12, i);
            }
        } else {
            ItemStack i = ItemFactory.create(Material.LIGHT_GRAY_DYE, "§cWhite Magic", "", "§7Tento efekt smi aktivovat pouze: §aVIP");
            inv.setItem(12, i);
        }
        if (p.hasPermission("craftmanager.particles.witchmagic")) {
            if (WitchMagic.e.containsKey(p.getName())) {
                ItemStack i = ItemFactory.create(Material.FERMENTED_SPIDER_EYE, "§eWitch Magic", "§7Kliknutim deaktivujes!");
                i = ItemFactory.addGlow(i);
                inv.setItem(13, i);
            } else {
                ItemStack i = ItemFactory.create(Material.FERMENTED_SPIDER_EYE, "§eWitch Magic", "§7Kliknutim aktivujes!");
                inv.setItem(13, i);
            }
        } else {
            ItemStack i = ItemFactory.create(Material.LIGHT_GRAY_DYE, "§cWitch Magic", "", "§7Tento efekt smi aktivovat pouze: §aVIP");
            inv.setItem(13, i);
        }
        if (p.hasPermission("craftmanager.particles.slime")) {
            if (Slime.e.containsKey(p.getName())) {
                ItemStack i = ItemFactory.create(Material.SLIME_BALL, "§eSlime", "§7Kliknutim deaktivujes!");
                i = ItemFactory.addGlow(i);
                inv.setItem(14, i);
            } else {
                ItemStack i = ItemFactory.create(Material.SLIME_BALL, "§eSlime", "§7Kliknutim aktivujes!");
                inv.setItem(14, i);
            }
        } else {
            ItemStack i = ItemFactory.create(Material.LIGHT_GRAY_DYE, "§cSlime", "", "§7Tento efekt smi aktivovat pouze: §aVIP");
            inv.setItem(14, i);
        }
        if (p.hasPermission("craftmanager.particles.snowball")) {
            if (Snowball.e.containsKey(p.getName())) {
                ItemStack i = ItemFactory.create(Material.SNOWBALL, "§eSnow", "§7Kliknutim deaktivujes!");
                i = ItemFactory.addGlow(i);
                inv.setItem(15, i);
            } else {
                ItemStack i = ItemFactory.create(Material.SNOWBALL, "§eSnow", "§7Kliknutim aktivujes!");
                inv.setItem(15, i);
            }
        } else {
            ItemStack i = ItemFactory.create(Material.LIGHT_GRAY_DYE, "§cSnow", "", "§7Tento efekt smi aktivovat pouze: §aVIP");
            inv.setItem(15, i);
        }
        if (p.hasPermission("craftmanager.particles.flame")) {
            if (Flame.e.containsKey(p.getName())) {
                ItemStack i = ItemFactory.create(Material.BLAZE_POWDER, "§eFlame", "§7Kliknutim deaktivujes!");
                i = ItemFactory.addGlow(i);
                inv.setItem(16, i);
            } else {
                ItemStack i = ItemFactory.create(Material.BLAZE_POWDER, "§eFlame", "§7Kliknutim aktivujes!");
                inv.setItem(16, i);
            }
        } else {
            ItemStack i = ItemFactory.create(Material.LIGHT_GRAY_DYE, "§cFlame", "", "§7Tento efekt smi aktivovat pouze: §aVIP");
            inv.setItem(16, i);
        }
        if (p.hasPermission("craftmanager.particles.redstone")) {
            if (Redstone.e.containsKey(p.getName())) {
                ItemStack i = ItemFactory.create(Material.REDSTONE, "§eRedstone", "§7Kliknutim deaktivujes!");
                i = ItemFactory.addGlow(i);
                inv.setItem(19, i);
            } else {
                ItemStack i = ItemFactory.create(Material.REDSTONE, "§eRedstone", "§7Kliknutim aktivujes!");
                inv.setItem(19, i);
            }
        } else {
            ItemStack i = ItemFactory.create(Material.LIGHT_GRAY_DYE, "§cRedstone", "", "§7Tento efekt smi aktivovat pouze: §aVIP");
            inv.setItem(19, i);
        }
        if (p.hasPermission("craftmanager.particles.cloud")) {
            if (Cloud.e.containsKey(p.getName())) {
                ItemStack i = ItemFactory.create(Material.QUARTZ, "§eCloud", "§7Kliknutim deaktivujes!");
                i = ItemFactory.addGlow(i);
                inv.setItem(20, i);
            } else {
                ItemStack i = ItemFactory.create(Material.QUARTZ, "§eCloud", "§7Kliknutim aktivujes!");
                inv.setItem(20, i);
            }
        } else {
            ItemStack i = ItemFactory.create(Material.LIGHT_GRAY_DYE, "§cCloud", "", "§7Tento efekt smi aktivovat pouze: §aVIP");
            inv.setItem(20, i);
        }
        if (p.hasPermission("craftmanager.particles.enchanted")) {
            if (Enchanted.e.containsKey(p.getName())) {
                ItemStack i = ItemFactory.create(Material.BOOK, "§eEnchanted", "§7Kliknutim deaktivujes!");
                i = ItemFactory.addGlow(i);
                inv.setItem(21, i);
            } else {
                ItemStack i = ItemFactory.create(Material.BOOK, "§eEnchanted", "§7Kliknutim aktivujes!");
                inv.setItem(21, i);
            }
        } else {
            ItemStack i = ItemFactory.create(Material.LIGHT_GRAY_DYE, "§cEnchanted", "", "§7Tento efekt smi aktivovat pouze: §aVIP");
            inv.setItem(21, i);
        }
        if (p.hasPermission("craftmanager.particles.endrod")) {
            if (EndRod.e.containsKey(p.getName())) {
                ItemStack i = ItemFactory.create(Material.END_ROD, "§eEndRod", "§7Kliknutim deaktivujes!");
                i = ItemFactory.addGlow(i);
                inv.setItem(22, i);
            } else {
                ItemStack i = ItemFactory.create(Material.END_ROD, "§eEndRod", "§7Kliknutim aktivujes!");
                inv.setItem(22, i);
            }
        } else {
            ItemStack i = ItemFactory.create(Material.LIGHT_GRAY_DYE, "§cEnd Rod", "", "§7Tento efekt smi aktivovat pouze: §aVIP");
            inv.setItem(22, i);
        }
        if (p.hasPermission("craftmanager.particles.firework")) {
            if (Firework.e.containsKey(p.getName())) {
                ItemStack i = ItemFactory.create(Material.FIREWORK_ROCKET, "§eFirework", "§7Kliknutim deaktivujes!");
                i = ItemFactory.addGlow(i);
                inv.setItem(23, i);
            } else {
                ItemStack i = ItemFactory.create(Material.FIREWORK_ROCKET, "§eFirework", "§7Kliknutim aktivujes!");
                inv.setItem(23, i);
            }
        } else {
            ItemStack i = ItemFactory.create(Material.LIGHT_GRAY_DYE, "§cFirework", "", "§7Tento efekt smi aktivovat pouze: §aVIP");
            inv.setItem(23, i);
        }
        if (p.hasPermission("craftmanager.particles.dragonbreath")) {
            if (DragonBreath.e.containsKey(p.getName())) {
                ItemStack i = ItemFactory.create(Material.DRAGON_BREATH, "§eDragonBreath", "§7Kliknutim deaktivujes!");
                i = ItemFactory.addGlow(i);
                inv.setItem(24, i);
            } else {
                ItemStack i = ItemFactory.create(Material.DRAGON_BREATH, "§eDragonBreath", "§7Kliknutim aktivujes!");
                inv.setItem(24, i);
            }
        } else {
            ItemStack i = ItemFactory.create(Material.LIGHT_GRAY_DYE, "§cDragonBreath", "", "§7Tento efekt smi aktivovat pouze: §aVIP");
            inv.setItem(24, i);
        }
        if (p.hasPermission("craftmanager.particles.portal")) {
            if (Portal.e.containsKey(p.getName())) {
                ItemStack i = ItemFactory.create(Material.OBSIDIAN, "§ePortal", "§7Kliknutim deaktivujes!");
                i = ItemFactory.addGlow(i);
                inv.setItem(25, i);
            } else {
                ItemStack i = ItemFactory.create(Material.OBSIDIAN, "§ePortal", "§7Kliknutim aktivujes!");
                inv.setItem(25, i);
            }
        } else {
            ItemStack i = ItemFactory.create(Material.LIGHT_GRAY_DYE, "§cPortal", "", "§7Tento efekt smi aktivovat pouze: §aVIP");
            inv.setItem(25, i);
        }
        if (p.hasPermission("craftmanager.particles.lava")) {
            if (Lava.e.containsKey(p.getName())) {
                ItemStack i = ItemFactory.create(Material.LAVA_BUCKET, "§eLava", "§7Kliknutim deaktivujes!");
                i = ItemFactory.addGlow(i);
                inv.setItem(28, i);
            } else {
                ItemStack i = ItemFactory.create(Material.LAVA_BUCKET, "§eLava", "§7Kliknutim aktivujes!");
                inv.setItem(28, i);
            }
        } else {
            ItemStack i = ItemFactory.create(Material.LIGHT_GRAY_DYE, "§cLava", "", "§7Tento efekt smi aktivovat pouze: §aVIP");
            inv.setItem(28, i);
        }
        if (p.hasPermission("craftmanager.particles.smoke")) {
            if (Smoke.e.containsKey(p.getName())) {
                ItemStack i = ItemFactory.create(Material.COAL, "§eSmoke", "§7Kliknutim deaktivujes!");
                i = ItemFactory.addGlow(i);
                inv.setItem(29, i);
            } else {
                ItemStack i = ItemFactory.create(Material.COAL, "§eSmoke", "§7Kliknutim aktivujes!");
                inv.setItem(29, i);
            }
        } else {
            ItemStack i = ItemFactory.create(Material.LIGHT_GRAY_DYE, "§cSmoke", "", "§7Tento efekt smi aktivovat pouze: §aVIP");
            inv.setItem(29, i);
        }
        if (p.hasPermission("craftmanager.particles.happy")) {
            if (Happy.e.containsKey(p.getName())) {
                ItemStack i = ItemFactory.create(Material.EMERALD, "§eHappy", "§7Kliknutim deaktivujes!");
                i = ItemFactory.addGlow(i);
                inv.setItem(30, i);
            } else {
                ItemStack i = ItemFactory.create(Material.EMERALD, "§eHappy", "§7Kliknutim aktivujes!");
                inv.setItem(30, i);
            }
        } else {
            ItemStack i = ItemFactory.create(Material.LIGHT_GRAY_DYE, "§cHappy", "", "§7Tento efekt smi aktivovat pouze: §aVIP");
            inv.setItem(30, i);
        }
        if (p.hasPermission("craftmanager.particles.void")) {
            if (cz.wake.manager.perks.particles.vip.Void.e.containsKey(p.getName())) {
                ItemStack i = ItemFactory.create(Material.BEDROCK, "§eVoid", "§7Kliknutim deaktivujes!");
                i = ItemFactory.addGlow(i);
                inv.setItem(31, i);
            } else {
                ItemStack i = ItemFactory.create(Material.BEDROCK, "§eVoid", "§7Kliknutim aktivujes!");
                inv.setItem(31, i);
            }
        } else {
            ItemStack i = ItemFactory.create(Material.LIGHT_GRAY_DYE, "§cVoid", "", "§7Tento efekt smi aktivovat pouze: §aVIP");
            inv.setItem(31, i);
        }

        ItemStack deaktivace = ItemFactory.create(Material.BARRIER, "§c✖ Deaktivace ✖", "§7Kliknutim deaktivujes particles.");
        inv.setItem(49, deaktivace);

        ItemStack zpet = ItemFactory.create(Material.ARROW, "§cZpet do menu");
        inv.setItem(48, zpet);

        p.openInventory(inv);
    }

    @EventHandler
    private void onInteract(InventoryClickEvent e) {
        final Player p = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equals("VIP Particles")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (e.getCurrentItem().getType() == Material.AIR) {
                return;
            }
            if (e.getSlot() == 49) {
                deactivateParticles(p);
            }
            if (e.getSlot() == 48) {
                SmartInventory.builder().size(6, 9).title("Cosmetics Menu").provider(new CosmeticMainGUI()).build().open(p);
            }
            if (e.getSlot() == 10) {
                if (p.hasPermission("craftmanager.particles.hearts")) {
                    deactivateParticles(p);
                    this.h.activateLove(p);
                    p.closeInventory();
                } else {
                    p.sendMessage("§cK aktivaci tohoto efektu musis mit VIP!");
                    p.closeInventory();
                }
            }
            if (e.getSlot() == 11) {
                if (p.hasPermission("craftmanager.particles.angry")) {
                    deactivateParticles(p);
                    this.a.activate(p);
                    p.closeInventory();
                } else {
                    p.sendMessage("§cK aktivaci tohoto efektu musis mit VIP!");
                    p.closeInventory();
                }
            }
            if (e.getSlot() == 12) {
                if (p.hasPermission("craftmanager.particles.whitemagic")) {
                    deactivateParticles(p);
                    this.w.activate(p);
                    p.closeInventory();
                } else {
                    p.sendMessage("§cK aktivaci tohoto efektu musis mit VIP!");
                    p.closeInventory();
                }
            }
            if (e.getSlot() == 13) {
                if (p.hasPermission("craftmanager.particles.witchmagic")) {
                    deactivateParticles(p);
                    this.m.activate(p);
                    p.closeInventory();
                } else {
                    p.sendMessage("§cK aktivaci tohoto efektu musis mit VIP!");
                    p.closeInventory();
                }
            }
            if (e.getSlot() == 14) {
                if (p.hasPermission("craftmanager.particles.slime")) {
                    deactivateParticles(p);
                    this.s.activate(p);
                    p.closeInventory();
                } else {
                    p.sendMessage("§cK aktivaci tohoto efektu musis mit VIP!");
                    p.closeInventory();
                }
            }
            if (e.getSlot() == 15) {
                if (p.hasPermission("craftmanager.particles.snowball")) {
                    deactivateParticles(p);
                    this.sn.activate(p);
                    p.closeInventory();
                } else {
                    p.sendMessage("§cK aktivaci tohoto efektu musis mit VIP!");
                    p.closeInventory();
                }
            }
            if (e.getSlot() == 16) {
                if (p.hasPermission("craftmanager.particles.flame")) {
                    deactivateParticles(p);
                    f.activate(p);
                    p.closeInventory();
                } else {
                    p.sendMessage("§cK aktivaci tohoto efektu musis mit VIP!");
                    p.closeInventory();
                }
            }
            if (e.getSlot() == 19) {
                if (p.hasPermission("craftmanager.particles.redstone")) {
                    deactivateParticles(p);
                    this.r.activate(p);
                    p.closeInventory();
                } else {
                    p.sendMessage("§cK aktivaci tohoto efektu musis mit VIP!");
                    p.closeInventory();
                }
            }
            if (e.getSlot() == 20) {
                if (p.hasPermission("craftmanager.particles.cloud")) {
                    deactivateParticles(p);
                    this.l.activate(p);
                    p.closeInventory();
                } else {
                    p.sendMessage("§cK aktivaci tohoto efektu musis mit VIP!");
                    p.closeInventory();
                }
            }
            if (e.getSlot() == 21) {
                if (p.hasPermission("craftmanager.particles.enchanted")) {
                    deactivateParticles(p);
                    this.e.activate(p);
                    p.closeInventory();
                } else {
                    p.sendMessage("§cK aktivaci tohoto efektu musis mit VIP!");
                    p.closeInventory();
                }
            }
            if (e.getSlot() == 22) {
                if (p.hasPermission("craftmanager.particles.endrod")) {
                    deactivateParticles(p);
                    this.er.activate(p);
                    p.closeInventory();
                } else {
                    p.sendMessage("§cK aktivaci tohoto efektu musis mit VIP!");
                    p.closeInventory();
                }
            }
            if (e.getSlot() == 23) {
                if (p.hasPermission("craftmanager.particles.firework")) {
                    deactivateParticles(p);
                    this.k.activate(p);
                    p.closeInventory();
                } else {
                    p.sendMessage("§cK aktivaci tohoto efektu musis mit VIP!");
                    p.closeInventory();
                }
            }
            if (e.getSlot() == 24) {
                if (p.hasPermission("craftmanager.particles.dragonbreath")) {
                    deactivateParticles(p);
                    this.db.activate(p);
                    p.closeInventory();
                } else {
                    p.sendMessage("§cK aktivaci tohoto efektu musis mit VIP!");
                    p.closeInventory();
                }
            }
            if (e.getSlot() == 25) {
                if (p.hasPermission("craftmanager.particles.portal")) {
                    deactivateParticles(p);
                    this.p.activate(p);
                    p.closeInventory();
                } else {
                    p.sendMessage("§cK aktivaci tohoto efektu musis mit VIP!");
                    p.closeInventory();
                }
            }
            if (e.getSlot() == 28) {
                if (p.hasPermission("craftmanager.particles.lava")) {
                    deactivateParticles(p);
                    this.la.activate(p);
                    p.closeInventory();
                } else {
                    p.sendMessage("§cK aktivaci tohoto efektu musis mit VIP!");
                    p.closeInventory();
                }
            }
            if (e.getSlot() == 29) {
                if (p.hasPermission("craftmanager.particles.smoke")) {
                    deactivateParticles(p);
                    this.sm.activate(p);
                    p.closeInventory();
                } else {
                    p.sendMessage("§cK aktivaci tohoto efektu musis mit VIP!");
                    p.closeInventory();
                }
            }
            if (e.getSlot() == 30) {
                if (p.hasPermission("craftmanager.particles.happy")) {
                    deactivateParticles(p);
                    this.ha.activate(p);
                    p.closeInventory();
                } else {
                    p.sendMessage("§cK aktivaci tohoto efektu musis mit VIP!");
                    p.closeInventory();
                }
            }
            if (e.getSlot() == 31) {
                if (p.hasPermission("craftmanager.particles.void")) {
                    deactivateParticles(p);
                    this.vo.activate(p);
                    p.closeInventory();
                } else {
                    p.sendMessage("§cK aktivaci tohoto efektu musis mit VIP!");
                    p.closeInventory();
                }
            }
        }

    }

    public void deactivateParticles(Player p) {
        if (Hearts.e.containsKey(p.getName())) {
            Bukkit.getScheduler().cancelTask(((Integer) Hearts.e.get(p.getName())).intValue());
            Hearts.e.remove(p.getName());
            p.closeInventory();
        }
        if (Angry.e.containsKey(p.getName())) {
            Bukkit.getScheduler().cancelTask(((Integer) Angry.e.get(p.getName())).intValue());
            Angry.e.remove(p.getName());
            p.closeInventory();
        }
        if (WhiteMagic.e.containsKey(p.getName())) {
            Bukkit.getScheduler().cancelTask(((Integer) WhiteMagic.e.get(p.getName())).intValue());
            WhiteMagic.e.remove(p.getName());
            p.closeInventory();
        }
        if (WitchMagic.e.containsKey(p.getName())) {
            Bukkit.getScheduler().cancelTask(((Integer) WitchMagic.e.get(p.getName())).intValue());
            WitchMagic.e.remove(p.getName());
            p.closeInventory();
        }
        if (Slime.e.containsKey(p.getName())) {
            Bukkit.getScheduler().cancelTask(((Integer) Slime.e.get(p.getName())).intValue());
            Slime.e.remove(p.getName());
            p.closeInventory();
        }
        if (Snowball.e.containsKey(p.getName())) {
            Bukkit.getScheduler().cancelTask(((Integer) Snowball.e.get(p.getName())).intValue());
            Snowball.e.remove(p.getName());
            p.closeInventory();
        }
        if (Flame.e.containsKey(p.getName())) {
            Bukkit.getScheduler().cancelTask(((Integer) Flame.e.get(p.getName())).intValue());
            Flame.e.remove(p.getName());
            p.closeInventory();
        }
        if (Redstone.e.containsKey(p.getName())) {
            Bukkit.getScheduler().cancelTask(((Integer) Redstone.e.get(p.getName())).intValue());
            Redstone.e.remove(p.getName());
            p.closeInventory();
        }
        if (Cloud.e.containsKey(p.getName())) {
            Bukkit.getScheduler().cancelTask(((Integer) Cloud.e.get(p.getName())).intValue());
            Cloud.e.remove(p.getName());
            p.closeInventory();
        }
        if (Enchanted.e.containsKey(p.getName())) {
            Bukkit.getScheduler().cancelTask(((Integer) Enchanted.e.get(p.getName())).intValue());
            Enchanted.e.remove(p.getName());
            p.closeInventory();
        }
        if (EndRod.e.containsKey(p.getName())) {
            Bukkit.getScheduler().cancelTask(((Integer) EndRod.e.get(p.getName())).intValue());
            EndRod.e.remove(p.getName());
            p.closeInventory();
        }
        if (Firework.e.containsKey(p.getName())) {
            Bukkit.getScheduler().cancelTask(((Integer) Firework.e.get(p.getName())).intValue());
            Firework.e.remove(p.getName());
            p.closeInventory();
        }
        if (DragonBreath.e.containsKey(p.getName())) {
            Bukkit.getScheduler().cancelTask(((Integer) DragonBreath.e.get(p.getName())).intValue());
            DragonBreath.e.remove(p.getName());
            p.closeInventory();
        }
        if (Portal.e.containsKey(p.getName())) {
            Bukkit.getScheduler().cancelTask(((Integer) Portal.e.get(p.getName())).intValue());
            Portal.e.remove(p.getName());
            p.closeInventory();
        }
        if (Lava.e.containsKey(p.getName())) {
            Bukkit.getScheduler().cancelTask(((Integer) Lava.e.get(p.getName())).intValue());
            Lava.e.remove(p.getName());
            p.closeInventory();
        }
        if (Smoke.e.containsKey(p.getName())) {
            Bukkit.getScheduler().cancelTask(((Integer) Smoke.e.get(p.getName())).intValue());
            Smoke.e.remove(p.getName());
            p.closeInventory();
        }
        if (Happy.e.containsKey(p.getName())) {
            Bukkit.getScheduler().cancelTask(((Integer) Happy.e.get(p.getName())).intValue());
            Happy.e.remove(p.getName());
            p.closeInventory();
        }
        if (BlackHearts.e.containsKey(p.getName())) {
            Bukkit.getScheduler().cancelTask(((Integer) BlackHearts.e.get(p.getName())).intValue());
            BlackHearts.e.remove(p.getName());
            p.closeInventory();
        }
        if (Void.e.containsKey(p.getName())) {
            Bukkit.getScheduler().cancelTask(((Integer) cz.wake.manager.perks.particles.vip.Void.e.get(p.getName())).intValue());
            Void.e.remove(p.getName());
            p.closeInventory();
        }
        if (SantaHat.sh.containsKey(p.getName())) {
            Bukkit.getScheduler().cancelTask(SantaHat.sh.get(p.getName()));
            SantaHat.sh.remove(p.getName());
            p.closeInventory();
        }
        if (FireWalk.e.containsKey(p.getName())) {
            Bukkit.getScheduler().cancelTask(FireWalk.e.get(p.getName()));
            FireWalk.e.remove(p.getName());
            p.closeInventory();
        }
    }

    public void deaktivateCapes(Player p) {
        if (ChristmasCape.turnajCloaks.containsKey(p.getName())) {
            Bukkit.getScheduler().cancelTask((ChristmasCape.turnajCloaks.get(p.getName())));
            ChristmasCape.turnajCloaks.remove(p.getName());
            p.closeInventory();
        }
        if (SummerSplash.turnajCloaks.containsKey(p.getName())) {
            Bukkit.getScheduler().cancelTask((SummerSplash.turnajCloaks.get(p.getName())));
            SummerSplash.turnajCloaks.remove(p.getName());
            p.closeInventory();
        }
        if (SpookyCape.turnajCloaks.containsKey(p.getName())) {
            Bukkit.getScheduler().cancelTask((SpookyCape.turnajCloaks.get(p.getName())));
            SpookyCape.turnajCloaks.remove(p.getName());
            p.closeInventory();
        }
    }

}
