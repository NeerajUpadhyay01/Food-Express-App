// package com.example.food_express_app.repositories;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
// import
// org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
// import org.springframework.test.context.junit.jupiter.SpringExtension;

// import com.example.food_express_app.entities.MenuItem;

// import org.junit.jupiter.api.extension.ExtendWith;

// import java.util.List;

// import static org.assertj.core.api.Assertions.assertThat;

// @ExtendWith(SpringExtension.class)
// @DataJpaTest
// @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// public class MenuItemRepositoryTests {

// @Autowired
// private MenuItemRepository menuItemRepository;

// @Test
// public void testFindByCategory() {
// // Arrange
// MenuItem menuItem = new MenuItem();
// menuItem.setCategory("Dessert");
// menuItemRepository.save(menuItem);

// // Act
// List<MenuItem> foundItems = menuItemRepository.findByCategory("Dessert");

// // Assert
// assertThat(foundItems).isNotEmpty();
// assertThat(foundItems.get(0).getCategory()).isEqualTo("Dessert");
// }

// @Test
// public void testFindByAvailableAndStockQuantityGreaterThan() {
// // Arrange
// MenuItem menuItem = new MenuItem();
// menuItem.setAvailable(true);
// menuItem.setStockQuantity(10);
// menuItemRepository.save(menuItem);

// // Act
// List<MenuItem> foundItems =
// menuItemRepository.findByAvailableAndStockQuantityGreaterThan(true, 5);

// // Assert
// assertThat(foundItems).isNotEmpty();
// assertThat(foundItems.get(0).isAvailable()).isTrue();
// assertThat(foundItems.get(0).getStockQuantity()).isGreaterThan(5);
// }
// }
