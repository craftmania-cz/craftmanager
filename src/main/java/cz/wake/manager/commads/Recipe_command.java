package cz.wake.manager.commads;

import cz.wake.manager.Main;
import cz.wake.manager.managers.RecipeManager;
import cz.wake.manager.utils.IntegerUtil;
import io.github.jorelali.commandapi.api.CommandAPI;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.*;
import org.bukkit.plugin.Plugin;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Recipe_command {

    public static void registerCommand() {

        CommandAPI.getInstance().register("recipe", new String[]{}, null, (sender, args) -> {

            sender.sendMessage("§c§l[!] §cTento prikaz je z duvodu kritickeho bugu docasne vypnut!");
            return;

            /*ItemStack itemType = null;
            Plugin pl = Main.getInstance();
            Player p = (Player) commandSender;
            try {
                itemType = Main.getInstance().getItemdb().get(args[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            int recipeNo = 0;

            if(args.length <= 0) {
                p.sendMessage("§cPouzij /recipe <item> nebo /recipe <id>");
                return;
            }

            final List<Recipe> recipesOfType = pl.getServer().getRecipesFor(itemType);

            if (args.length > 1) {
                if (IntegerUtil.isInt(args[1])) {
                    recipeNo = Integer.parseInt(args[1]) - 1;

                } else {
                    p.sendMessage("§cNezname cislo!");
                }
            }
            if (recipeNo < 0 || recipeNo >= recipesOfType.size() ) {
                if (IntegerUtil.isInt(args[0])) {
                    p.sendMessage("§cNeexistuje zadny recept pro toto id!");
                    return;
                } else if (recipesOfType.size() < 1) {
                    p.sendMessage("§cNeexistuje zadny recept pro tento item!");
                    return;
                }
            }


            final Recipe selectedRecipe = recipesOfType.get(recipeNo);

            if (selectedRecipe instanceof FurnaceRecipe) {
                furnaceRecipe(commandSender, (FurnaceRecipe) selectedRecipe);
            } else if (selectedRecipe instanceof ShapedRecipe) {
                shapedRecipe(commandSender, (ShapedRecipe) selectedRecipe, true);
            } else if (selectedRecipe instanceof ShapelessRecipe) {
                if (recipesOfType.size() == 1 && itemType.getType() == Material.FIREWORK_ROCKET) {
                    ShapelessRecipe shapelessRecipe = new ShapelessRecipe(itemType);
                    shapelessRecipe.addIngredient(Material.LEGACY_SULPHUR);
                    shapelessRecipe.addIngredient(Material.PAPER);
                    shapelessRecipe.addIngredient(Material.LEGACY_FIREWORK_CHARGE);
                    shapelessRecipe(commandSender, shapelessRecipe, true);
                } else {
                    shapelessRecipe(commandSender, (ShapelessRecipe) selectedRecipe, true);
                }
            }

            if (recipesOfType.size() > 1 && args.length == 1) {
            }
            return;*/
        });
    }

    public void furnaceRecipe(final CommandSender sender, final FurnaceRecipe recipe) {
    }

    public void shapedRecipe(final CommandSender sender, final ShapedRecipe recipe, final boolean showWindow) {
        final Map<Character, ItemStack> recipeMap = recipe.getIngredientMap();

        if (showWindow) {
            Player p = (Player) sender;
            p.closeInventory();
            RecipeManager.getRecipePlayer(p).setRecipe(true);
            final InventoryView view = p.openWorkbench(null, true);
            final String[] recipeShape = recipe.getShape();
            final Map<Character, ItemStack> ingredientMap = recipe.getIngredientMap();
            for (int j = 0; j < recipeShape.length; j++) {
                for (int k = 0; k < recipeShape[j].length(); k++) {
                    final ItemStack item = ingredientMap.get(recipeShape[j].toCharArray()[k]);
                    if (item == null) {
                        continue;
                    }
                    if (item.getDurability() == Short.MAX_VALUE) {
                        item.setDurability((short) 0);
                    }
                    view.getTopInventory().setItem(j * 3 + k + 1, item);
                }
            }
        } else {
            final HashMap<Material, String> colorMap = new HashMap<>();
            int i = 1;
            for (Character c : "abcdefghi".toCharArray()) {
                ItemStack item = recipeMap.get(c);
                if (!colorMap.containsKey(item == null ? null : item.getType())) {
                    colorMap.put(item == null ? null : item.getType(), String.valueOf(i++));
                }
            }
            final Material[][] materials = new Material[3][3];
            for (int j = 0; j < recipe.getShape().length; j++) {
                for (int k = 0; k < recipe.getShape()[j].length(); k++) {
                    ItemStack item = recipe.getIngredientMap().get(recipe.getShape()[j].toCharArray()[k]);
                    materials[j][k] = item == null ? null : item.getType();
                }
            }
            StringBuilder s = new StringBuilder();
            for (Material items : colorMap.keySet().toArray(new Material[colorMap.size()])) {
            }
        }
    }

    public void shapelessRecipe(final CommandSender sender, final ShapelessRecipe recipe, final boolean showWindow) {
        final List<ItemStack> ingredients = recipe.getIngredientList();
        if (showWindow) {
            final Player p = (Player) sender;
            final InventoryView view = p.openWorkbench(null, true);
            RecipeManager.getRecipePlayer(p).setRecipe(true);
            for (int i = 0; i < ingredients.size(); i++) {
                final ItemStack item = ingredients.get(i);
                if (item.getDurability() == Short.MAX_VALUE) {
                    item.setDurability((short) 0);
                }
                view.setItem(i + 1, item);
            }

        } else {
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < ingredients.size(); i++) {
                s.append(getMaterialName(ingredients.get(i)));
                if (i != ingredients.size() - 1) {
                    s.append(",");
                }
                s.append(" ");
            }
        }
    }

    public String getMaterialName(final ItemStack stack) {
        if (stack == null) {
            return "Nothing";
        }
        return getMaterialName(stack.getType());
    }

    public String getMaterialName(final Material type) {
        if (type == null) {
            return "Nothing";
        }
        return type.toString().replace("_", " ").toLowerCase(Locale.ENGLISH);
    }

}