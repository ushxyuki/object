package catalogue;

import java.io.Serializable;

/**
 * BetterBasket extends Basket and improves product handling.
 * It combines duplicate products, removes product quantities,
 * counts products, and calculates total basket cost.
 *
 * @author  ushxyuki
 * @version 1.0
 */
public class BetterBasket extends Basket implements Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public boolean add(Product product) {

        if (product == null) {
            return false;
        }

        // Check if a similar product is already in the basket
        for (int i = 0; i < this.size(); i++) {

            Product existingProduct = this.get(i);

            if (existingProduct.getProductNum().equals(product.getProductNum())) {
                existingProduct.setQuantity(
                        existingProduct.getQuantity() + product.getQuantity()
                );
                return true;
            }
        }

        // If product does not exist, add it normally
        return super.add(product);
    }

    // Remove entire product if requested, or reduce quantity by 1
    public boolean remove(String product_num, boolean entire_product) {

        if (product_num == null) {
            return false;
        }

        for (int i = 0; i < this.size(); i++) {

            Product product = this.get(i);

            if (product.getProductNum().equals(product_num)) {

                if (entire_product || product.getQuantity() <= 1) {
                    this.remove(i);
                    return true;
                }

                product.setQuantity(product.getQuantity() - 1);
                return true;
            }
        }

        return false;
    }

    // Get the total number of products in the basket
    public int count_products() {

        int count = 0;

        for (Product product : this) {
            count += product.getQuantity();
        }

        return count;
    }

    // Get total cost for products in the basket
    public double getTotalCost() {

        double cost = 0.0;

        for (Product product : this) {
            cost += product.getPrice() * product.getQuantity();
        }

        return cost;
    }
}
