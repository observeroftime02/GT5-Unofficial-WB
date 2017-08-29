package gregtech.loaders.oreprocessing;

import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.interfaces.IOreRecipeRegistrator;
import gregtech.api.util.MatUnifier;
import gregtech.api.util.GT_Utility;
import net.minecraft.item.ItemStack;

public class ProcessingTransforming
        implements IOreRecipeRegistrator {
    public ProcessingTransforming() {
        for (OrePrefixes tPrefix : OrePrefixes.values())
            if (((tPrefix.mMaterialAmount > 0L) && (!tPrefix.mIsContainer) && (!tPrefix.mIsEnchantable)) || (tPrefix == OrePrefixes.plank))
                tPrefix.add(this);
    }

    public void registerOre(OrePrefixes aPrefix, Materials aMaterial, String aOreDictName, String aModName, ItemStack aStack) {
        if (aPrefix == OrePrefixes.plank) aPrefix = OrePrefixes.plate;
        switch (aMaterial.mName) {
            case "Wood":
                ItemStack aSealedWoodStack = MatUnifier.get(aPrefix, Materials.WoodSealed);
                GT_Values.RA.addChemicalBathRecipe(aStack, Materials.SeedOil.getFluid(GT_Utility.translateMaterialToAmount(aPrefix.mMaterialAmount, 120L, true)), aSealedWoodStack, GT_Values.NI, GT_Values.NI, null, 100, 8);
                GT_Values.RA.addChemicalBathRecipe(aStack, Materials.SeedOilLin.getFluid(GT_Utility.translateMaterialToAmount(aPrefix.mMaterialAmount, 80L, true)), aSealedWoodStack, GT_Values.NI, GT_Values.NI, null, 100, 8);
                GT_Values.RA.addChemicalBathRecipe(aStack, Materials.SeedOilHemp.getFluid(GT_Utility.translateMaterialToAmount(aPrefix.mMaterialAmount, 80L, true)), aSealedWoodStack, GT_Values.NI, GT_Values.NI, null, 100, 8);
                break;
            case "Iron":
                GT_Values.RA.addPolarizerRecipe(aStack, MatUnifier.get(aPrefix, Materials.IronMagnetic), (int) Math.max(16L, aPrefix.mMaterialAmount * 128L / 3628800L), 16);
                break;
            case "WroughtIron":
                GT_Values.RA.addPolarizerRecipe(aStack, MatUnifier.get(aPrefix, Materials.IronMagnetic), (int) Math.max(16L, aPrefix.mMaterialAmount * 128L / 3628800L), 16);
                break;
            case "Steel":
                GT_Values.RA.addPolarizerRecipe(aStack, MatUnifier.get(aPrefix, Materials.SteelMagnetic), (int) Math.max(16L, aPrefix.mMaterialAmount * 128L / 3628800L), 16);
                break;
            case "Neodymium":
                GT_Values.RA.addPolarizerRecipe(aStack, MatUnifier.get(aPrefix, Materials.NeodymiumMagnetic), (int) Math.max(16L, aPrefix.mMaterialAmount * 128L / 3628800L), 256);
        }
    }
}
