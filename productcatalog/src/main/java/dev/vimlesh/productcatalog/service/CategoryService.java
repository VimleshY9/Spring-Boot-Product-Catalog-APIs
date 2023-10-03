package dev.vimlesh.productcatalog.service;



import dev.vimlesh.productcatalog.dto.CategoryDto;
import dev.vimlesh.productcatalog.model.Category;

import java.util.List;

public interface CategoryService {
    Category getCategory(String uuid);
    List<String> getProductTitles(List<String> categoryUUIDs);

    List<CategoryDto> getAllCategories();
}
