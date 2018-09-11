package com.kiiadi.checks.kotlin;

import java.util.function.Consumer;

public class ShadowsReceiverExtensionMethods {

    public void apply(Consumer<String> block) { }

    public void apply(Consumer<String> block, String someOtherParam) { }

    public ShadowsReceiverExtensionMethods run(Runnable run) { }

    public void run(Runnable run, String someOtherParam) { }
}