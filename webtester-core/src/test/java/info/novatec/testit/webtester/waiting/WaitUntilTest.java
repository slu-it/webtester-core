package info.novatec.testit.webtester.waiting;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.base.Predicate;
import com.google.common.base.Supplier;


@RunWith(Enclosed.class)
public class WaitUntilTest {

    @RunWith(MockitoJUnitRunner.class)
    public static abstract class AbstractWaitUntilTest {

        Predicate<Object> truePredicate = new Predicate<Object>() {
            @Override
            public boolean apply(Object input) {
                return true;
            }
        };
        Predicate<Object> falsePredicate = new Predicate<Object>() {
            @Override
            public boolean apply(Object input) {
                return false;
            }
        };

        @Captor
        ArgumentCaptor<Supplier<Boolean>> supplierCaptor;

        @Mock
        Waiter waiter;
        @Mock
        WaitConfig config;
        @Mock
        Object object;

        WaitUntil<Object> cut;

        @Before
        public void init() {
            cut = new WaitUntil<>(waiter, config, object);
        }

    }

    public static class Is extends AbstractWaitUntilTest {

        @Test
        public void conditionsAreTransformedToSupplier() {

            cut.is(truePredicate);

            verify(waiter).waitUntil(same(config), supplierCaptor.capture());
            Supplier<Boolean> supplier = supplierCaptor.getValue();
            assertThat(supplier.get()).isTrue();

        }

        @Test
        public void returnsSameInstance() {
            WaitUntil actual = cut.is(truePredicate);
            assertThat(actual).isSameAs(cut);
        }

    }

    public static class Has extends AbstractWaitUntilTest {

        @Test
        public void conditionsAreTransformedToSupplier() {

            cut.has(truePredicate);

            verify(waiter).waitUntil(same(config), supplierCaptor.capture());
            Supplier<Boolean> supplier = supplierCaptor.getValue();
            assertThat(supplier.get()).isTrue();

        }

        @Test
        public void returnsSameInstance() {
            WaitUntil actual = cut.has(truePredicate);
            assertThat(actual).isSameAs(cut);
        }

    }

    public static class IsNot extends AbstractWaitUntilTest {

        @Test
        public void conditionsAreNegatedAndTransformedToSupplier() {

            cut.isNot(falsePredicate);

            verify(waiter).waitUntil(same(config), supplierCaptor.capture());
            Supplier<Boolean> supplier = supplierCaptor.getValue();
            assertThat(supplier.get()).isTrue();

        }

        @Test
        public void returnsSameInstance() {
            WaitUntil actual = cut.isNot(falsePredicate);
            assertThat(actual).isSameAs(cut);
        }

    }

    public static class HasNot extends AbstractWaitUntilTest {

        @Test
        public void conditionsAreNegatedAndTransformedToSupplier() {

            cut.hasNot(falsePredicate);

            verify(waiter).waitUntil(same(config), supplierCaptor.capture());
            Supplier<Boolean> supplier = supplierCaptor.getValue();
            assertThat(supplier.get()).isTrue();

        }

        @Test
        public void returnsSameInstance() {
            WaitUntil actual = cut.hasNot(falsePredicate);
            assertThat(actual).isSameAs(cut);
        }

    }

    public static class Not extends AbstractWaitUntilTest {

        @Test
        public void conditionsAreNegatedAndTransformedToSupplier() {

            cut.not(falsePredicate);

            verify(waiter).waitUntil(same(config), supplierCaptor.capture());
            Supplier<Boolean> supplier = supplierCaptor.getValue();
            assertThat(supplier.get()).isTrue();

        }

        @Test
        public void returnsSameInstance() {
            WaitUntil actual = cut.not(falsePredicate);
            assertThat(actual).isSameAs(cut);
        }

    }

    public static class And extends AbstractWaitUntilTest {

        @Test
        public void returnsSameInstance() {
            WaitUntil actual = cut.and();
            assertThat(actual).isSameAs(cut);
        }

        @Test
        public void doesNotChangeState() {
            cut.and();
            verifyZeroInteractions(waiter, config, object);
        }

    }

    public static class But extends AbstractWaitUntilTest {

        @Test
        public void returnsSameInstance() {
            WaitUntil actual = cut.but();
            assertThat(actual).isSameAs(cut);
        }

        @Test
        public void doesNotChangeState() {
            cut.but();
            verifyZeroInteractions(waiter, config, object);
        }

    }

}
