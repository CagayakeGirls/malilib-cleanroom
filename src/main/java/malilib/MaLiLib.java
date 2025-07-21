package malilib;

import malilib.event.dispatch.RenderEventDispatcherImpl;
import malilib.registry.Registry;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = MaLiLibReference.MOD_ID, name = MaLiLibReference.MOD_NAME, version = MaLiLibReference.MOD_VERSION, guiFactory = "malilib.compat.forge.MaLiLibGuiFactory")
public class MaLiLib
{
    public static final Logger LOGGER = LogManager.getLogger(MaLiLibReference.MOD_ID);

    public static boolean canShowCoordinates()
    {
        return MaLiLibConfigs.Generic.HIDE_ALL_COORDINATES.getBooleanValue() == false;
    }

    public static void debugLog(String str, Object... args)
    {
        if (MaLiLibConfigs.Debug.DEBUG_MESSAGES.getBooleanValue())
        {
            LOGGER.info(str, args);
        }
    }

    @SubscribeEvent
    public static void arrowNocked(GuiScreenEvent.DrawScreenEvent.Post event) {
        if (event.getGui().mc.world != null && event.getGui().mc.player != null)
        {
            ((RenderEventDispatcherImpl) Registry.RENDER_EVENT_DISPATCHER).onRenderScreenPost(event.getRenderPartialTicks());
        }
    }
}
