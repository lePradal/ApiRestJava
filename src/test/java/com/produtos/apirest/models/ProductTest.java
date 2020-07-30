package com.produtos.apirest.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest {

    Product product;

    @BeforeEach
    public void setup() {
        product = new Product();
        product.setName("name");
        product.setAmount(123);
        product.setValue(BigDecimal.valueOf(10.00));
    }

    @Test
    public void testMustReturnAnID() {
        assertThat(product.getId()).isNotNull().isInstanceOf(Long.class);
    }

    @Test
    public void testMustReturnASerialVersion() {
        assertThat(product.getSerialVersionUID()).isNotNull().isInstanceOf(Long.class);
    }

    @Test
    public void testMustReturnAName() {
        assertThat(product.getName()).isNotNull().isInstanceOf(String.class).isEqualTo("name");
    }

    @Test
    public void testMustReturnAnAmount() {
        assertThat(product.getAmount()).isNotNull().isInstanceOf(Integer.class).isEqualTo(123);
    }

    @Test
    public void testMustReturnAValue() {
        assertThat(product.getValue()).isNotNull().isInstanceOf(BigDecimal.class).isEqualTo(BigDecimal.valueOf(10.00));
    }

    @Test
    public void testMustSetAName() {
        String name = "name";
        product.setName(name);
        assertThat(product.getName()).isNotNull().isInstanceOf(String.class).isEqualTo(name);
    }

    @Test
    public void testMustSetAAmount() {
        int amount = 123;
        product.setAmount(amount);
        assertThat(product.getAmount()).isNotNull().isInstanceOf(Integer.class).isEqualTo(amount);
    }

    @Test
    public void testMustSetAValue() {
        BigDecimal value = BigDecimal.valueOf(10.00);
        product.setValue(value);
        assertThat(product.getValue()).isNotNull().isInstanceOf(BigDecimal.class).isEqualTo(value);
    }

}
