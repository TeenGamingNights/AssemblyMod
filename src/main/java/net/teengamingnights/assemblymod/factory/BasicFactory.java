package net.teengamingnights.assemblymod.factory;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Container;
import org.bukkit.block.Furnace;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.UUID;

public class BasicFactory implements Factory {
    /*So basically, when you want to make a new factory just extend this class. Most factories won't need to override anything,
      but when we do it like this it gives the option to allow factories to override methods and do fancier things.
     */

    // Private fields.
    private Location furnace;
    private Location center;
    private Location chest;
    private UUID id;
    private double health;
    private FactoryType type;
    private List<ItemStack> requirements;
    private List<Recipe> recipes;
    private double healthLossMultiplier;
    private boolean enabled;
    private Recipe currentRecipe;

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public Location getCenter() {
        return center;
    }

    @Override
    public Location getFurnace() {
        return furnace;
    }

    @Override
    public Location getChest() {
        return chest;
    }

    @Override
    public FactoryType getType() {
        return type;
    }

    @Override
    public double getHealth() {
        return health;
    }

    @Override
    public double getHealthLossMultiplier() {
        return healthLossMultiplier;
    }

    @Override
    public List<ItemStack> getRequiredMaterials() {
        return requirements;
    }

    @Override
    public boolean contains(Location loc) {
        return loc.equals(center) || loc.equals(furnace) || loc.equals(chest);
    }

    @Override
    public boolean getEnabled(){
        return enabled;
    }

    public BasicFactory(FactoryType ft, Location center, Location chest, Location furnace){
        this.center = center;
        this.furnace = furnace;
        this.chest = chest;
        this.type = ft;
        this.id = UUID.randomUUID();

        this.health = type.getStartingHealth();
        this.healthLossMultiplier = type.getHlMultiplier();
        this.requirements = type.getCreationCost();

        toggle();
    }

    // This function is called when a user toggles a factory on or off
    @Override
    public void toggle(){
        if(enabled)
            onDisable();
        else
            onEnable();
    }

    @Override
    public Recipe getCurrentRecipe(){
        return currentRecipe;
    }

    @Override
    public void setRecipe(Recipe recipe){
        this.currentRecipe = recipe;
    }

    // This is called when a user turns on the factory
    private void onEnable(){
        if (!consumeFuel()) return;
        enabled = true;
        Furnace furn = (Furnace) furnace.getBlock().getState();
        furn.setBurnTime((short) 1600);
        furn.update();
    }

    // This is called when a user turns off a factory
    private void onDisable(){
        enabled = false;
        Furnace furn = (Furnace) furnace.getBlock().getState();
        furn.setBurnTime((short) 0);
        furn.update();
    }

    // Tried to consume a piece of charcoal in the furnace. returns true if successful, false if there isn't any charcoal.
    // TODO: Put this on a loop so when one piece of fuel is done burning it checks again.
    private boolean consumeFuel(){
        Furnace furnace = (Furnace) this.furnace.getBlock().getState();
        Inventory inv = ((Container)furnace).getInventory();
        ItemStack[] items = inv.getContents();
        boolean fueled = false;
        for(int i = 0; i < items.length; i++){
            ItemStack item = items[i];
            if (item != null && item.getType() == Material.CHARCOAL){
                // Valid fuel source found
                fueled = true;
                items[i].setAmount(item.getAmount()-1);
                inv.setContents(items);
                break;
            }
        }
        return fueled;
    }
}
