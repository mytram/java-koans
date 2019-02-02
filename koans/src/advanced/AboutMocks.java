package advanced;

import com.sandwich.koan.Koan;

import static com.sandwich.util.Assert.fail;

public class AboutMocks {

    @FunctionalInterface
    static interface Collaborator {
        public boolean doBusinessStuff();
    }

    static class ExplosiveCollaborator implements Collaborator {
        @Override
        public boolean doBusinessStuff() {
            fail("Default collaborator's behavior is complicating testing.");
            return true;
        }
    }

    static class ClassUnderTest {
        Collaborator c;

        public ClassUnderTest() {
            // default is to pass a broken Collaborator, test should pass one
            // that doesn't throw exception
            this(new ExplosiveCollaborator());
        }

        public ClassUnderTest(Collaborator c) {
            this.c = c;
        }

        public boolean doSomething() {
            System.out.println("call here");
            c.doBusinessStuff();
            return true;
        }
    }

    @Koan
    public void simpleAnonymousMock() {
        // HINT: pass a safe Collaborator implementation to constructor
        // new ClassUnderTest(new Collaborator(){... it should not be the
        // objective of this test to test that collaborator, so replace it
        new ClassUnderTest(() -> { return false; }).doSomething();
    }
}
