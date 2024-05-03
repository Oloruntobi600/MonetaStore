package com.monetastore.monetastore.contollers;

import com.monetastore.monetastore.Product;
import com.monetastore.monetastore.models.ProductDto;
import com.monetastore.monetastore.services.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductsControllerTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductsController controller;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @Test
    public void testShowProductList() {
        List<Product> productList = new ArrayList<>();
        // Populate productList with sample products

        when(repository.findAll(any())).thenReturn(products);

        String viewName = controller.showProductList(model);
        assertEquals("products/index", viewName);
        verify(model, times(1)).addAttribute(eq("products"), anyList());
    }

    @Test
    public void testShowCreatePage() {
        String viewName = controller.showCreatePage(model);
        assertEquals("products/CreateProduct", viewName);
        verify(model, times(1)).addAttribute(eq("productDto"), any(ProductDto.class));
    }

    @Test
    public void testCreateProduct() {
        ProductDto productDto = new ProductDto();
        // Set up productDto with sample data

        MultipartFile mockImageFile = mock(MultipartFile.class);
        when(mockImageFile.isEmpty()).thenReturn(true);
        productDto.setImageFile(mockImageFile);

        String viewName = controller.createProduct(productDto, bindingResult);
        assertEquals("products/CreateProduct", viewName);
        verify(bindingResult, times(1)).addError(any(FieldError.class));
    }

    // Similarly, write tests for other controller methods like showEditPage, updateProduct, and deleteProduct
}
