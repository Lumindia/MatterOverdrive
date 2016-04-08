package matteroverdrive.items.food;

import matteroverdrive.MatterOverdrive;
import matteroverdrive.Reference;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

/**
 * Created by Simeon on 4/24/2015.
 */
public class EarlGrayTea extends ItemFood
{
    public EarlGrayTea(String name)
    {
        super(4, 0.8F, false);
        setUnlocalizedName(name);
        setRegistryName(new ResourceLocation(Reference.MOD_ID,name));
        setCreativeTab(MatterOverdrive.tabMatterOverdrive_food);
        setAlwaysEdible();
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving)
    {
        super.onItemUseFinish(stack,worldIn,entityLiving);

        if (!(entityLiving instanceof EntityPlayer)) return stack;
        if (!((EntityPlayer)entityLiving).capabilities.isCreativeMode)
        {
            --stack.stackSize;
        }

        if (!worldIn.isRemote)
        {
            entityLiving.curePotionEffects(stack);
        }

        if (stack.stackSize > 0)
        {
            ((EntityPlayer)entityLiving).inventory.addItemStackToInventory(new ItemStack(Items.glass_bottle));
        }

        return stack.stackSize <= 0 ? new ItemStack(Items.glass_bottle) : stack;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack itemStack)
    {
        return EnumAction.DRINK;
    }
}
