package fr.emi.invisibletab.mixin;

import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.network.packet.s2c.play.PlayerListS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerListS2CPacket.Entry.class)
public abstract class PlayerListEntryMixin {
    @Shadow @Final @Mutable private boolean listed;

    @Inject(method = "<init>(Lnet/minecraft/server/network/ServerPlayerEntity;)V", at = @At("TAIL"))
    private void invisibletab$hideInvisiblePlayers(ServerPlayerEntity player, CallbackInfo ci) {
        this.listed = !player.hasStatusEffect(StatusEffects.INVISIBILITY);
    }
}
