package net.mineclave.main;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.mineclave.main.block.SteelBlockItem;
import net.mineclave.main.items.ClaveMaterial;
import net.mineclave.main.items.DebugItem;
import net.mineclave.main.items.SteelItem;
import net.mineclave.main.items.WeaponItem;

public class EnclaveMain implements ModInitializer {

	// Debug Markers
	public static boolean itemsLoaded;
	public static boolean blocksLoaded;

	// ItemGroup
	public static final ItemGroup ENCLAVEITEMS = FabricItemGroupBuilder.build(
		new Identifier("mineclave", "general"),
	() -> new ItemStack(EnclaveMain.debug));

	public static final ItemGroup ENCLAVEMATERIALS = FabricItemGroupBuilder.build(
		new Identifier("mineclave", "materials"),
	() -> new ItemStack(EnclaveMain.steel));

	public static final ItemGroup ENCLAVEBLOCKS = FabricItemGroupBuilder.build(
		new Identifier("mineclave", "blocks"),
	() -> new ItemStack(EnclaveMain.steelblock));

	public static final ItemGroup ENCLAVEWEAP = FabricItemGroupBuilder.build(
		new Identifier("mineclave", "weapons"),
	() -> new ItemStack(EnclaveMain.batonweap));

	// ToolMaterial
	private static final ClaveMaterial material = new ClaveMaterial(1, 0, 0.5F, 2.5F, 750, () -> {
		return Ingredient.ofItems(EnclaveMain.steel);
	});

	// Items
	public static final DebugItem debug = new DebugItem(new Item.Settings().group(EnclaveMain.ENCLAVEITEMS));
	public static final SteelItem steel = new SteelItem(new Item.Settings().group(EnclaveMain.ENCLAVEMATERIALS));

	// Tools
	public static final WeaponItem batonweap = new WeaponItem(material, 2, 0.5F,
			new Item.Settings().group(EnclaveMain.ENCLAVEWEAP).maxCount(1).maxDamage(450));

	// Blocks
	public static final SteelBlockItem steelblock = new SteelBlockItem(Block.Settings.of(Material.METAL));

	// Initialization
	@Override
	public void onInitialize() {
		Registry.register(Registry.BLOCK, new Identifier("mineclave", "steel_block"), steelblock);
		EnclaveMain.blocksLoaded = true;

		Registry.register(Registry.ITEM, new Identifier("mineclave", "debugitem"), debug);
		Registry.register(Registry.ITEM, new Identifier("mineclave", "wep_baton"), batonweap);
		Registry.register(Registry.ITEM, new Identifier("mineclave","steel_ingot"), steel);
		Registry.register(Registry.ITEM, new Identifier("mineclave","steel_block"), new BlockItem(steelblock, new Item.Settings().group(EnclaveMain.ENCLAVEBLOCKS)));
		EnclaveMain.itemsLoaded = true;
	}
}
