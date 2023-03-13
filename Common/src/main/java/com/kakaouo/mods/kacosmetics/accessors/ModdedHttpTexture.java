package com.kakaouo.mods.kacosmetics.accessors;

import com.kakaouo.mods.kacosmetics.util.Modifiers;

import java.util.function.Consumer;
import java.util.function.Supplier;

public interface ModdedHttpTexture {
    void executeAfterLoadIfModified(Consumer<Modifiers> callback);
}
