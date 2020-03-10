package tmall.comparator;

import com.chen.tmall.pojo.Product;

import java.util.Comparator;

public class ProductImageComparator implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return o2.getFirstProductImage().getId() - o1.getFirstProductImage().getId();
    }
}
