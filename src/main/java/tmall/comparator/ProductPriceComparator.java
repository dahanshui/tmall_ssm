package tmall.comparator;

import com.chen.tmall.pojo.Product;

import java.util.Comparator;

public class ProductPriceComparator implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return (int) (o2.getPromotePrice() - o1.getPromotePrice());
    }
}
