package cz.wake.manager.menu;

import cz.craftmania.craftcore.builders.items.ItemBuilder;
import cz.craftmania.craftcore.inventory.builder.ClickableItem;
import cz.craftmania.craftcore.inventory.builder.SmartInventory;
import cz.craftmania.craftcore.inventory.builder.content.InventoryContents;
import cz.craftmania.craftcore.inventory.builder.content.InventoryProvider;
import cz.craftmania.craftcore.inventory.builder.content.Pagination;
import cz.craftmania.craftcore.inventory.builder.content.SlotIterator;
import cz.craftmania.craftlibs.utils.ChatInfo;
import cz.craftmania.craftpack.api.Hats;
import cz.craftmania.craftpack.api.TextureItems;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class HatsGUI implements InventoryProvider {

    public List<CosmeticItem> prepareList() {
        List<CosmeticItem> list = new ArrayList<>();

        // Admin team Hats
        list.add(new CosmeticItem().setName("§eOwner Crown")
                .setItemStack(Hats.AT_OWNER.getPureItemStack())
                .setLore("§7Speciální Admin Team čepice.", "", "§aZískáno: §fČlen AT - Owner", "§aRarita: §f" + TextureItems.RARITY_LEGENDARY.getRender(), "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.at_owner"));

        list.add(new CosmeticItem().setName("§eStaff Crown")
                .setItemStack(Hats.AT_STAFF.getPureItemStack())
                .setLore("§7Speciální Admin Team čepice.", "", "§aZískáno: §fČlen AT - Staff", "§aRarita: §f" + TextureItems.RARITY_LEGENDARY.getRender(), "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.at_staff"));

        list.add(new CosmeticItem().setName("§eEventer Crown")
                .setItemStack(Hats.AT_EVENTER.getPureItemStack())
                .setLore("§7Speciální Admin Team čepice.", "", "§aZískáno: §fČlen AT - Eventer", "§aRarita: §f" + TextureItems.RARITY_LEGENDARY.getRender(), "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.at_eventer"));

        list.add(new CosmeticItem().setName("§eDeveloper Crown")
                .setItemStack(Hats.AT_DEVELOPER.getPureItemStack())
                .setLore("§7Speciální Admin Team čepice.", "", "§aZískáno: §fČlen AT - Developer", "§aRarita: §f" + TextureItems.RARITY_LEGENDARY.getRender(), "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.at_developer"));

        list.add(new CosmeticItem().setName("§eBuilder Crown")
                .setItemStack(Hats.AT_BUILDER.getPureItemStack())
                .setLore("§7Speciální Admin Team čepice.", "", "§aZískáno: §fČlen AT - Builder", "§aRarita: §f" + TextureItems.RARITY_LEGENDARY.getRender(), "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.at_builder"));

        list.add(new CosmeticItem().setName("§eArtist Crown")
                .setItemStack(Hats.AT_ARTIST.getPureItemStack())
                .setLore("§7Speciální Admin Team čepice.", "", "§aZískáno: §fČlen AT - Artist", "§aRarita: §f" + TextureItems.RARITY_LEGENDARY.getRender(), "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.at_artist"));

        list.add(new CosmeticItem().setName("§eAdmin Crown")
                .setItemStack(Hats.AT_ADMIN.getPureItemStack())
                .setLore("§7Speciální Admin Team čepice.", "", "§aZískáno: §fČlen AT - Admin", "§aRarita: §f" + TextureItems.RARITY_LEGENDARY.getRender(), "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.at_admin"));

        list.add(new CosmeticItem().setName("§eHelper Crown")
                .setItemStack(Hats.AT_HELPER.getPureItemStack())
                .setLore("§7Speciální Admin Team čepice.", "", "§aZískáno: §fČlen AT - Helper", "§aRarita: §f" + TextureItems.RARITY_LEGENDARY.getRender(), "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.at_helper"));

        list.add(new CosmeticItem().setName("§cBanHammer")
                .setItemStack(Hats.AT_BANHAMMER.getPureItemStack())
                .setLore("§7Speciální Admin Team čepice.", "", "§aZískáno: §fČlen AT", "§aRarita: §f" + TextureItems.RARITY_EPIC.getRender(), "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.at_banhammer"));

        // Events
        list.add(new CosmeticItem().setName("§6Bronze Trophy")
                .setItemStack(Hats.BRONZE_TROPHY.getPureItemStack())
                .setLore("§7Vítězství v Eventu je ta nejlepší věc!", "§7Umístění: §f3. místo", "", "§aZískáno: §fBuildEvent (Velikonoce 2021)", "§aRarita: §f" + TextureItems.RARITY_MYTHIC.getRender(), "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.bronze_trophy_be_2021"));

        list.add(new CosmeticItem().setName("§eSilver Trophy")
                .setItemStack(Hats.SILVER_TROPHY.getPureItemStack())
                .setLore("§7Vítězství v Eventu je ta nejlepší věc!", "§7Umístění: §f2. místo", "", "§aZískáno: §fBuildEvent (Velikonoce 2021)", "§aRarita: §f" + TextureItems.RARITY_MYTHIC.getRender(), "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.silver_trophy_be_2021"));

        list.add(new CosmeticItem().setName("§eGolden Trophy")
                .setItemStack(Hats.GOLD_TROPHY.getPureItemStack())
                .setLore("§7Vítězství v Eventu je ta nejlepší věc!", "§7Umístění: §f1. místo", "", "§aZískáno: §fBuildEvent (Velikonoce 2021)", "§aRarita: §f" + TextureItems.RARITY_MYTHIC.getRender(), "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.golden_trophy_be_2021"));

        // Discord
        list.add(new CosmeticItem().setName("§9Mega Wumpus")
                .setItemStack(Hats.WUMPUS.getPureItemStack())
                .setLore("", "§aZískáno: §fBoostování CM Discordu", "§aRarita: §f" + TextureItems.RARITY_EPIC.getRender(), "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.wumpus"));

        list.add(new CosmeticItem().setName("§aWumpus Leaf")
                .setItemStack(Hats.WUMPUS_LEAF.getPureItemStack())
                .setLore("", "§aZískáno: §fPropojení profilu", "§aRarita: §f" + TextureItems.RARITY_EPIC.getRender(), "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.wumpus_leaf"));

        // Coinshop Hats
        list.add(new CosmeticItem().setName("§fKoala")
                .setItemStack(Hats.KOALA.getPureItemStack())
                .setLore("", "§aZískáno: §f/cshop", "§aRarita: §f" + TextureItems.RARITY_RARE.getRender(), "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.koala"));

        list.add(new CosmeticItem().setName("§fPanda")
                .setItemStack(Hats.PANDA.getPureItemStack())
                .setLore("", "§aZískáno: §f/cshop", "§aRarita: §f" + TextureItems.RARITY_RARE.getRender(),  "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.panda"));

        list.add(new CosmeticItem().setName("§6Koňská hlava")
                .setItemStack(Hats.HORSE.getPureItemStack())
                .setLore("", "§aZískáno: §fFree odměna za připojení", "§aRarita: §f" + TextureItems.RARITY_EPIC.getRender(),  "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.horse"));

        list.add(new CosmeticItem().setName("§dKlaun")
                .setItemStack(Hats.CLOWN.getPureItemStack())
                .setLore("", "§aZískáno: §f/cshop", "§aRarita: §f" + TextureItems.RARITY_RARE.getRender(),  "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.clown"));

        list.add(new CosmeticItem().setName("§bLast Breath")
                .setItemStack(Hats.LAST_BREATH.getPureItemStack())
                .setLore("", "§aZískáno: §fDokončení mapy od Command Builders (Limited)", "§aRarita: §f" + TextureItems.RARITY_LIMITED.getRender(),  "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.last_breath"));

        list.add(new CosmeticItem().setName("§fUnicorn")
                .setItemStack(Hats.UNICORN.getPureItemStack())
                .setLore("", "§aZískáno: §f/cshop", "§aRarita: §f" + TextureItems.RARITY_UNCOMMON.getRender(),  "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.unicorn"));

        list.add(new CosmeticItem().setName("§bBeer Hat")
                .setItemStack(Hats.BEER_HAT.getPureItemStack())
                .setLore("", "§aZískáno: §f/cshop", "§aRarita: §f" + TextureItems.RARITY_RARE.getRender(),  "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.beer_hat"));

        list.add(new CosmeticItem().setName("§6Trex")
                .setItemStack(Hats.TREX.getPureItemStack())
                .setLore("", "§aZískáno: §f/cshop", "§aRarita: §f" + TextureItems.RARITY_EPIC.getRender(),  "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.trex"));

        list.add(new CosmeticItem().setName("§eXXXTentacion")
                .setItemStack(Hats.XXXTENCATION.getPureItemStack())
                .setLore("", "§aZískáno: §f/cshop", "§aRarita: §f" + TextureItems.RARITY_RARE.getRender(),  "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.xxxtencation")); // Old název, bug wake je boomer a posral název no

        list.add(new CosmeticItem().setName("§6Bunny Ears")
                .setItemStack(Hats.BUNNY_EARS.getPureItemStack())
                .setLore("", "§aZískáno: §f/cshop", "§aRarita: §f" + TextureItems.RARITY_RARE.getRender(),  "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.bunny_ears"));
        
        list.add(new CosmeticItem().setName("§aŽabička")
                .setItemStack(Hats.ZABICKA.getPureItemStack())
                .setLore("", "§aZískáno: §f/cshop", "§aRarita: §f" + TextureItems.RARITY_RARE.getRender(),  "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.zabicka"));

        list.add(new CosmeticItem().setName("§eKachnička")
                .setItemStack(Hats.KACHNICKA.getPureItemStack())
                .setLore("", "§aZískáno: §f/cshop", "§aRarita: §f" + TextureItems.RARITY_RARE.getRender(),  "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.kachnicka"));

        list.add(new CosmeticItem().setName("§6Legendary Lion")
                .setItemStack(Hats.LION.getPureItemStack())
                .setLore("", "§aZískáno: §fVotePass 1/2021", "§aRarita: §f" + TextureItems.RARITY_LEGENDARY.getRender(),  "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.lion"));

        list.add(new CosmeticItem().setName("§aCowBoy")
                .setItemStack(Hats.COWBOY.getPureItemStack())
                .setLore("", "§aZískáno: §fFree odměna za připojení", "§aRarita: §f" + TextureItems.RARITY_EPIC.getRender(), "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.cowboy"));

        list.add(new CosmeticItem().setName("§dNaruto")
                .setItemStack(Hats.NARUTO.getPureItemStack())
                .setLore("", "§aZískáno: §fStore", "§aRarita: §f" + TextureItems.RARITY_EPIC.getRender(), "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.naruto"));

        list.add(new CosmeticItem().setName("§bDiamond On Stick")
                .setItemStack(Hats.DIAMOND_PRUT.getPureItemStack())
                .setLore("", "§aZískáno: §f/cshop", "§aRarita: §f" + TextureItems.RARITY_RARE.getRender(), "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.diamond_prut"));

        list.add(new CosmeticItem().setName("§aShrek")
                .setItemStack(Hats.SHREK.getPureItemStack())
                .setLore("", "§aZískáno: §f/cshop", "§aRarita: §f" + TextureItems.RARITY_RARE.getRender(),  "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.shrek"));

        list.add(new CosmeticItem().setName("§9Nerdy Glasses 1")
                .setItemStack(Hats.NERD_GLASSES_1.getPureItemStack())
                .setLore("", "§aZískáno: §f/cshop", "§aRarita: §f" + TextureItems.RARITY_COMMON.getRender(),  "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.nerdy_glasses"));

        list.add(new CosmeticItem().setName("§9Nerdy Glasses 2")
                .setItemStack(Hats.NERD_GLASSES_2.getPureItemStack())
                .setLore("", "§aZískáno: §f/cshop", "§aRarita: §f" + TextureItems.RARITY_COMMON.getRender(),  "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.nerdy_glasses"));

        list.add(new CosmeticItem().setName("§9Nerdy Glasses 3")
                .setItemStack(Hats.NERD_GLASSES_3.getPureItemStack())
                .setLore("", "§aZískáno: §f/cshop", "§aRarita: §f" + TextureItems.RARITY_COMMON.getRender(),  "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.nerdy_glasses"));

        list.add(new CosmeticItem().setName("§cDeer")
                .setItemStack(Hats.DEER.getPureItemStack())
                .setLore("", "§aZískáno: §f/cshop", "§aRarita: §f" + TextureItems.RARITY_RARE.getRender(),  "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.deer"));

        list.add(new CosmeticItem().setName("§6Egg Head")
                .setItemStack(Hats.EGG_HEAD.getPureItemStack())
                .setLore("", "§aZískáno: §fEvent server - Velikonoce 2021", "§aRarita: §f" + TextureItems.RARITY_LIMITED.getRender(),  "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.egg_head"));

        list.add(new CosmeticItem().setName("§aFlowers")
                .setItemStack(Hats.FLOWERS.getPureItemStack())
                .setLore("§aZískáno: §f/cshop", "§aRarita: §f" + TextureItems.RARITY_RARE.getRender(),  "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.flowers"));

        list.add(new CosmeticItem().setName("§eCat Ears")
                .setItemStack(Hats.CAT_EARS.getPureItemStack())
                .setLore("§aZískáno: §f/cshop", "§aRarita: §f" + TextureItems.RARITY_UNCOMMON.getRender(),  "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.cat_ears"));

        list.add(new CosmeticItem().setName("§6Pirate Hat")
                .setItemStack(Hats.PIRATE_HAT.getPureItemStack())
                .setLore("", "§aZískáno: §f/cshop", "§aRarita: §f" + TextureItems.RARITY_UNCOMMON.getRender(),  "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.pirate_hat"));

        list.add(new CosmeticItem().setName("§cMandalorian")
                .setItemStack(Hats.MANDALORIAN.getPureItemStack())
                .setLore("", "§aZískáno: §fVotePass 2/2021", "§aRarita: §f" + TextureItems.RARITY_LEGENDARY.getRender(),  "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.mandalorian"));

        list.add(new CosmeticItem().setName("§cYoda")
                .setItemStack(Hats.YODA.getPureItemStack())
                .setLore("", "§aZískáno: §fVotePass 2/2021", "§aRarita: §f" + TextureItems.RARITY_LEGENDARY.getRender(),  "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.old_yoda"));

        list.add(new CosmeticItem().setName("§cRádiovka")
                .setItemStack(Hats.RADIOVKA.getPureItemStack())
                .setLore("", "§aZískáno: §fStore", "§aRarita: §f" + TextureItems.RARITY_LEGENDARY.getRender(), "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.radiovka"));

        list.add(new CosmeticItem().setName("§eHalo Ring")
            .setItemStack(Hats.HALO.getPureItemStack())
            .setLore("", "§aZískáno: §f/cshop", "§aRarita: §f" + TextureItems.RARITY_RARE.getRender(),  "", "§eKlikni pro nasazení")
            .setRequiredPermission("craftmanager.hats.halo_ring"));

        list.add(new CosmeticItem().setName("§bPenguin")
                .setItemStack(Hats.PENGUIN.getPureItemStack())
                .setLore("", "§aZískáno: §f/cshop", "§aRarita: §f" + TextureItems.RARITY_RARE.getRender(),  "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.penguin"));

        list.add(new CosmeticItem().setName("§cGolem Head")
                .setItemStack(Hats.GOLEM_HEAD.getPureItemStack())
                .setLore("", "§aZískáno: §f/cshop", "§aRarita: §f" + TextureItems.RARITY_EPIC.getRender(),  "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.golem_head"));

        list.add(new CosmeticItem().setName("§3Candy Hat")
                .setItemStack(Hats.CANDY_HAT.getPureItemStack())
                .setLore("", "§aZískáno: §f/cshop", "§aRarita: §f" + TextureItems.RARITY_RARE.getRender(),  "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.candy_hat"));

        list.add(new CosmeticItem().setName("§eIron Man")
                .setItemStack(Hats.IRON_MAN.getPureItemStack())
                .setLore("", "§aZískáno: §fVotePass 3/2021", "§aRarita: §f" + TextureItems.RARITY_LEGENDARY.getRender(),  "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.iron_man"));

        list.add(new CosmeticItem().setName("§eTiger Head")
                .setItemStack(Hats.TIGER_HEAD.getPureItemStack())
                .setLore("", "§aZískáno: §fFree odměna za připojení", "§aRarita: §f" + TextureItems.RARITY_EPIC.getRender(),  "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.tiger_head"));

        list.add(new CosmeticItem().setName("§bFish Bowl")
                .setItemStack(Hats.FISH_BOWL.getPureItemStack())
                .setLore("", "§aZískáno: §fStore", "§aRarita: §f" + TextureItems.RARITY_LEGENDARY.getRender(), "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.fish_bowl"));

        list.add(new CosmeticItem().setName("§9Astronaut")
                .setItemStack(Hats.ASTRONAUT.getPureItemStack())
                .setLore("", "§aZískáno: §fTOP platící za měsíc na Storu", "§aRarita: §f" + TextureItems.RARITY_LEGENDARY.getRender(), "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.astronaut"));

        list.add(new CosmeticItem().setName("§cDevil")
                .setItemStack(Hats.DEVIL.getPureItemStack())
                .setLore("", "§aZískáno: §f/cshop", "§aRarita: §f" + TextureItems.RARITY_EPIC.getRender(), "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.devil"));

        list.add(new CosmeticItem().setName("§eTiki")
                .setItemStack(Hats.TIKI_MASK.getPureItemStack())
                .setLore("", "§aZískáno: §f/cshop", "§aRarita: §f" + TextureItems.RARITY_EPIC.getRender(), "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.tiki_mask"));

        list.add(new CosmeticItem().setName("§cDarth Vader")
                .setItemStack(Hats.DARTH_VADER.getPureItemStack())
                .setLore("", "§aZískáno: §fVotePass 4/2021", "§aRarita: §f" + TextureItems.RARITY_LEGENDARY.getRender(),  "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.darth_vader"));

        list.add(new CosmeticItem().setName("§cImpostor")
                .setItemStack(Hats.IMPOSTOR.getPureItemStack())
                .setLore("", "§aZískáno: §f/cshop", "§aRarita: §f" + TextureItems.RARITY_RARE.getRender(), "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.impostor"));

        list.add(new CosmeticItem().setName("§eWitch Hat")
                .setItemStack(Hats.WITCH.getPureItemStack())
                .setLore("", "§aZískáno: §fHalloween Minihra", "§aRarita: §f" + TextureItems.RARITY_EPIC.getRender(), "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.witch_hat"));

        list.add(new CosmeticItem().setName("§eShark")
                .setItemStack(Hats.SHARK.getPureItemStack())
                .setLore("", "§aZískáno: §f/cshop", "§aRarita: §f" + TextureItems.RARITY_EPIC.getRender(), "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.shark_hat"));

        list.add(new CosmeticItem().setName("§cReaper Hoodie")
                .setItemStack(Hats.REAPER_HOODIE.getPureItemStack())
                .setLore("", "§aZískáno: §fHalloween Minihra", "§aRarita: §f" + TextureItems.RARITY_LEGENDARY.getRender(), "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.reaper_hoodie"));

        list.add(new CosmeticItem().setName("§6Pumpkin King")
                .setItemStack(Hats.PUMPKIN_KING.getPureItemStack())
                .setLore("", "§aZískáno: §fTOP 10 v Halloween 2021", "§aRarita: §f" + TextureItems.RARITY_LEGENDARY.getRender(), "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.pumpkin_king"));

        list.add(new CosmeticItem().setName("§9Tetris Hat")
                .setItemStack(Hats.TETRIS.getPureItemStack())
                .setLore("", "§aZískáno: §fVotePass 5/2021", "§aRarita: §f" + TextureItems.RARITY_LEGENDARY.getRender(),  "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.tetris_hat"));

        list.add(new CosmeticItem().setName("§6Frontman Mask")
                .setItemStack(Hats.FRONTMAN_MASK.getPureItemStack())
                .setLore("", "§aZískáno: §fVánoční kalendář 2021", "§aRarita: §f" + TextureItems.RARITY_EPIC.getRender(),  "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.frontman_mask"));

        list.add(new CosmeticItem().setName("§cSanta Hat")
                .setItemStack(Hats.SANTA_HAT.getPureItemStack())
                .setLore("", "§aZískáno: §fVánoční kalendář 2021", "§aRarita: §f" + TextureItems.RARITY_EPIC.getRender(),  "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.santa_hat"));

        list.add(new CosmeticItem().setName("§cDear Hat")
                .setItemStack(Hats.DEAR_HAT.getPureItemStack())
                .setLore("", "§aZískáno: §fVánoční kalendář 2021", "§aRarita: §f" + TextureItems.RARITY_EPIC.getRender(),  "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.dear_hat"));

        list.add(new CosmeticItem().setName("§cSnowman Hat")
                .setItemStack(Hats.SNOWMAN_HAT.getPureItemStack())
                .setLore("", "§aZískáno: §fVánoční kalendář 2021", "§aRarita: §f" + TextureItems.RARITY_EPIC.getRender(),  "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.snowman_hat"));

        list.add(new CosmeticItem().setName("§cSilvester Glasses 1")
                .setItemStack(Hats.SILVESTER_GLASSES_1.getPureItemStack())
                .setLore("", "§aZískáno: §fVánoční kalendář 2021", "§aRarita: §f" + TextureItems.RARITY_UNCOMMON.getRender(),  "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.silvester_glasses_2021"));

        list.add(new CosmeticItem().setName("§cSilvester Glasses 2")
                .setItemStack(Hats.SILVESTER_GLASSES_2.getPureItemStack())
                .setLore("", "§aZískáno: §fVánoční kalendář 2021", "§aRarita: §f" + TextureItems.RARITY_UNCOMMON.getRender(),  "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.silvester_glasses_2021"));

        list.add(new CosmeticItem().setName("§cSilvester Glasses 3")
                .setItemStack(Hats.SILVESTER_GLASSES_3.getPureItemStack())
                .setLore("", "§aZískáno: §fVánoční kalendář 2021", "§aRarita: §f" + TextureItems.RARITY_UNCOMMON.getRender(),  "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.silvester_glasses_2021"));

        list.add(new CosmeticItem().setName("§cSilvester Party Hat 2021")
                .setItemStack(Hats.PARTY_HAT.getPureItemStack())
                .setLore("", "§aZískáno: §fVánoční kalendář 2021", "§aRarita: §f" + TextureItems.RARITY_RARE.getRender(),  "", "§eKlikni pro nasazení")
                .setRequiredPermission("craftmanager.hats.silvester_party_hat_2021"));

        return list;
    }

    @Override
    public void init(Player player, InventoryContents contents) {
        List<CosmeticItem> disguises = prepareList();

        contents.fillRow(0, ClickableItem.of(new ItemBuilder(Material.LIGHT_BLUE_STAINED_GLASS_PANE).setName("§c").build(), item -> {}));
        contents.fillRow(5, ClickableItem.of(new ItemBuilder(Material.LIGHT_BLUE_STAINED_GLASS_PANE).setName("§c").build(), item -> {}));

        final Pagination pagination = contents.pagination();
        final ArrayList<ClickableItem> items = new ArrayList<>();

        disguises.forEach((cosmeticItem -> {

            if (!player.hasPermission(cosmeticItem.getRequiredPermission())) {
                return;
            }

            items.add(ClickableItem.of(new ItemBuilder(cosmeticItem.getItemStack()).setName(cosmeticItem.getName()).setLore(cosmeticItem.getLore()).hideAllFlags().build(), item -> {
                if (player.getInventory().getHelmet() != null) {
                    ChatInfo.DANGER.send(player, "Nelze si nasadit čepici, když máš již něco na hlavě!");
                    player.closeInventory();
                    return;
                }
                ItemBuilder finalItem = new ItemBuilder(cosmeticItem.getItemStack());
                finalItem.hideAllFlags();
                finalItem.setLore("§7Nasazeno: §f" + player.getName());
                player.getInventory().setHelmet(finalItem.build());
                ChatInfo.INFO.send(player, "Nasadil jsi si na hlavu: §r" + cosmeticItem.getName());
                player.closeInventory();
            }));

        }));

        ClickableItem[] c = new ClickableItem[items.size()];
        c = items.toArray(c);
        pagination.setItems(c);
        pagination.setItemsPerPage(36);

        if (items.size() > 0 && !pagination.isLast()) {
            contents.set(5, 7, ClickableItem.of(new ItemBuilder(Material.PAPER).setName("§f§lDalší stránka").build(), e -> {
                contents.inventory().open(player, pagination.next().getPage());
            }));
        }
        if (!pagination.isFirst()) {
            contents.set(5, 2, ClickableItem.of(new ItemBuilder(Material.PAPER).setName("§f§lPředchozí stránka").build(), e -> {
                contents.inventory().open(player, pagination.previous().getPage());
            }));
        }

        contents.set(5, 1,ClickableItem.of(new ItemBuilder(Material.SPECTRAL_ARROW).setName("§eZpět do menu").hideAllFlags().build(), item -> {
            SmartInventory.builder().size(6, 9).title("Cosmetics Menu").provider(new CosmeticMainGUI()).build().open(player);
        }));

        contents.set(5, 4, ClickableItem.of(new ItemBuilder(Material.BARRIER).setName("§cDeaktivovat").build(), e -> {
            if (player.getInventory().getHelmet() != null && !player.getInventory().getHelmet().getItemMeta().hasCustomModelData()) {
                ChatInfo.DANGER.send(player, "Sundat si lze čepice pouze z Cosmetic menu.");
                return;
            }
            if (player.getInventory().getHelmet() != null && player.getInventory().getHelmet().getType() == Material.SUGAR) {
                ChatInfo.DANGER.send(player, "Máš nasazenou premium čepici, nelze ji odebrat.");
                return;
            }
            player.getInventory().setHelmet(null);
            player.getOpenInventory().close();
            ChatInfo.INFO.send(player, "Sundal jsi si čepici z hlavy.");
        }));

        SlotIterator slotIterator = contents.newIterator("hats-gui", SlotIterator.Type.HORIZONTAL, 1, 0);
        slotIterator = slotIterator.allowOverride(false);
        pagination.addToIterator(slotIterator);

    }

    @Override
    public void update(Player player, InventoryContents contents) {

    }
}
