package org.chrisoft.jline4mcdsrv.mixin;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.chrisoft.jline4mcdsrv.JLineForMcDSrv;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = {"net.minecraft.server.dedicated.DedicatedServer$1"})
abstract class DedicatedServerConsoleThreadMixin {
    @Inject(at = @At("HEAD"), method = "run()V", cancellable = true)
    private void consoleThreadQuit(final @NonNull CallbackInfo info) {
        JLineForMcDSrv.LOGGER.info("Shutting down vanilla console thread...");
        info.cancel();
    }
}
