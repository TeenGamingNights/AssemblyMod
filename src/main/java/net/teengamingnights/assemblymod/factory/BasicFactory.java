package net.teengamingnights.assemblymod.factory;

import net.teengamingnights.assemblymod.AssemblyMod;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Container;
import org.bukkit.block.Furnace;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

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
    private BukkitRunnable fuelTimer;
    private BukkitRunnable productTimer;
    private int charcoalTime;

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

        this.currentRecipe = ft.getRecipes()[0];

        this.health = type.getStartingHealth();
        this.healthLossMultiplier = type.getHlMultiplier();
        this.requirements = type.getCreationCost();

        this.charcoalTime = 1600;
    }

    // This function is called when a user toggles a factory on or off
    @Override
    public void toggle(){
        System.out.println("On toggle");
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
        System.out.println("On enable");
        if (!consumeFuel()) return;
        enabled = true;
        resetTimers();
        furnaceOn();
        productTimer.runTaskTimer(AssemblyMod.getPlugin(AssemblyMod.class), currentRecipe.getDuration(), currentRecipe.getDuration());
        fuelTimer.runTaskTimer(AssemblyMod.getPlugin(AssemblyMod.class), charcoalTime, charcoalTime);
    }

    // This is called when a user turns off a factory
    private void onDisable(){
        System.out.println("On Disable");
        enabled = false;
        Furnace furn = (Furnace) furnace.getBlock().getState();
        furn.setBurnTime((short) 0);
        furn.update();
        this.productTimer.cancel();
        this.fuelTimer.cancel();
    }

    // Tried to consume a piece of charcoal in the furnace. returns true if successful, false if there isn't any charcoal.
    private boolean consumeFuel(){
        System.out.print("Consuming fuel ");
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
        System.out.println(fueled);
        return fueled;
    }

    private void doOutput(){
        System.out.println("DoOutput()");
        Chest c = (Chest) chest.getBlock().getState();
        for (ItemStack i : currentRecipe.getProduct()){
            c.getInventory().addItem(i);
        }
    }

    private boolean checkRecipe(){
        // Try to consume the items required for the recipe
        // TODO: Placeholder
        return true;
    }

    private void resetTimers(){
        this.fuelTimer = new BukkitRunnable() {
            @Override
            public void run() {
                if (!consumeFuel())
                    onDisable();
                else
                    furnaceOn();
            }
        };
        this.productTimer = new BukkitRunnable() {
            @Override
            public void run() {
                // Output the product, and if there's enough to start it again do that.
                doOutput();
                if (!checkRecipe())
                    onDisable();
            }
        };
    }

    private void furnaceOn(){
        Furnace furn = (Furnace) furnace.getBlock().getState();
        furn.setBurnTime((short) 1700);
        furn.update();
    }
}
