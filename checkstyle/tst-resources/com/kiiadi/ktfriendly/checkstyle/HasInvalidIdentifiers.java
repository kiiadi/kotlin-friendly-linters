package com.kiiadi.ktfriendly.checkstyle;

public class HasInvalidIdentifiers {
    public static final String invalid = "hello";

    public void invalid() {

    }

    public String invalid = "bah";

    public interface Blah {
        void invalid();
    }

    protected String invalid = null;

    private String invalid = "foo";

    private void invalid(String param) {

    }

    //Should not be checked
    class PackageProtectedClass {
        public void invalid() { }

        public String invalid = "bah";
    }

    //Should be checked
    protected class ProtectedClass {
        public void invalid() { }

        public String invalid = "bah";
    }

    //Should not be checked
    private class PrivateClass {
        public void invalid() { }

        public String invalid = "bah";
    }

    //Should be ignored, since it's overriding
    @Override
    public String invalid(String input) {
        return input;
    }
}