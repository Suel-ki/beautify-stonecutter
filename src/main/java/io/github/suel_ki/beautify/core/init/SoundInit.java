//? if fabric {
package io.github.suel_ki.beautify.core.init;

import io.github.suel_ki.beautify.Beautify;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.SoundType;

public class SoundInit {

	//BOOKSTACK
	public static final SoundEvent BOOKSTACK_NEXT = registerSoundEvent("block.bookstack_next");
	public static final SoundEvent BOOKSTACK_BREAK = registerSoundEvent("block.bookstack_break");
	public static final SoundEvent BOOKSTACK_STEP = registerSoundEvent("block.bookstack_step");
	public static final SoundEvent BOOKSTACK_PLACE = registerSoundEvent("block.bookstack_place");
	public static final SoundEvent BOOKSTACK_HIT = registerSoundEvent("block.bookstack_hit");
	public static final SoundEvent BOOKSTACK_FALL = registerSoundEvent("block.bookstack_fall");

	public static final SoundType BOOKSTACK_SOUNDS = new SoundType(1f, 1f, SoundInit.BOOKSTACK_BREAK,
			SoundInit.BOOKSTACK_STEP, SoundInit.BOOKSTACK_PLACE, SoundInit.BOOKSTACK_HIT, SoundInit.BOOKSTACK_FALL);

	//BLINDS
	public static final SoundEvent BLINDS_OPEN = registerSoundEvent("block.blinds_open");
	public static final SoundEvent BLINDS_CLOSE = registerSoundEvent("block.blinds_close");

	public static SoundEvent get(SoundEvent sound) {
		return sound;
	}

	private static SoundEvent registerSoundEvent(String name) {
		return Registry.register(BuiltInRegistries.SOUND_EVENT, Beautify.id(name), SoundEvent.createVariableRangeEvent(Beautify.id(name)));
	}


}
//?} else {
/*package io.github.suel_ki.beautify.core.init;

import io.github.suel_ki.beautify.Beautify;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.common.util.DeferredSoundType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class SoundInit {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister
            .create(BuiltInRegistries.SOUND_EVENT, Beautify.MODID);

    //BOOKSTACK
    public static final Supplier<SoundEvent> BOOKSTACK_NEXT = registerSoundEvent("block.bookstack_next");
    public static final Supplier<SoundEvent> BOOKSTACK_BREAK = registerSoundEvent("block.bookstack_break");
    public static final Supplier<SoundEvent> BOOKSTACK_STEP = registerSoundEvent("block.bookstack_step");
    public static final Supplier<SoundEvent> BOOKSTACK_PLACE = registerSoundEvent("block.bookstack_place");
    public static final Supplier<SoundEvent> BOOKSTACK_HIT = registerSoundEvent("block.bookstack_hit");
    public static final Supplier<SoundEvent> BOOKSTACK_FALL = registerSoundEvent("block.bookstack_fall");
    public static final DeferredSoundType BOOKSTACK_SOUNDS = new DeferredSoundType(1f, 1f, SoundInit.BOOKSTACK_BREAK,
            SoundInit.BOOKSTACK_STEP, SoundInit.BOOKSTACK_PLACE, SoundInit.BOOKSTACK_HIT, SoundInit.BOOKSTACK_FALL);

    //BLINDS
    public static final Supplier<SoundEvent> BLINDS_OPEN = registerSoundEvent("block.blinds_open");
    public static final Supplier<SoundEvent> BLINDS_CLOSE = registerSoundEvent("block.blinds_close");

	public static SoundEvent get(Supplier<SoundEvent> sound) {
		return sound.get();
	}

	private static Supplier<SoundEvent> registerSoundEvent(String name) {
		return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(Beautify.id(name)));
	}


}
*///?}
