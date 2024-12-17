package example.store;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import example.account.AccountManager;
import example.account.AccountManagerImpl;
import example.account.Customer;
import org.junit.jupiter.api.Test;

class  StoreImplDiffblueTest {

    @Test
    void estBuy() {
        // Arrange
        StoreImpl storeImpl = new StoreImpl(new AccountManagerImpl());

        Product product = new Product();
        product.setName("Name");
        product.setPrice(1);
        product.setQuantity(1);

        Customer customer = new Customer();
        customer.setBalance(1);
        customer.setCreditAllowed(true);
        customer.setName("Name");
        customer.setVip(true);

        // Act
        storeImpl.buy(product, customer);

        // Assert
        assertEquals(0, customer.getBalance());
        assertEquals(0, product.getQuantity());
    }

    @Test
    void testBuy2() {
        // Arrange
        StoreImpl storeImpl = new StoreImpl(new AccountManagerImpl());

        Product product = new Product();
        product.setName("Name");
        product.setPrice(1);
        product.setQuantity(0);

        Customer customer = new Customer();
        customer.setBalance(1);
        customer.setCreditAllowed(true);
        customer.setName("Name");
        customer.setVip(true);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> storeImpl.buy(product, customer));
    }


    @Test
    void testBuy3() {
        // Arrange
        StoreImpl storeImpl = new StoreImpl(new AccountManagerImpl());

        Product product = new Product();
        product.setName("Name");
        product.setPrice(Integer.MIN_VALUE);
        product.setQuantity(1);

        Customer customer = new Customer();
        customer.setBalance(1);
        customer.setCreditAllowed(false);
        customer.setName("Name");
        customer.setVip(true);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> storeImpl.buy(product, customer));
    }


    @Test
    void testNewStoreImpl() {
        // Arrange, Act and Assert
        assertTrue((new StoreImpl(new AccountManagerImpl())).accountManager instanceof AccountManagerImpl);
    }
}
