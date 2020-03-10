package tmall.comparator;

import com.chen.tmall.pojo.Product;

import java.util.Comparator;

public class ProductAllComparator implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return o2.getSaleCount() * o2.getReviewCount() - o1.getSaleCount() * o1.getReviewCount();
    }
}
