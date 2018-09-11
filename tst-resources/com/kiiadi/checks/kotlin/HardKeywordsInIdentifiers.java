package com.kiiadi.checks.kotlin;

public class HardKeywordsInIdentifiers {
    public static final String val = "hello";

    public void in() {

    }

    public String var = "bah";

    public interface Blah {
        void as();
    }

    protected String typealias = null;

    private String object = "foo";

    private void when() {

    }

    //Should not be checked
    class PackageProtectedClass {
        public void in() { }

        public String var = "bah";
    }

    //Should be checked
    protected class ProtectedClass {
        public void in() { }

        public String var = "bah";
    }

    //Should not be checked
    private class PrivateClass {
        public void in() { }

        public String var = "bah";
    }
}