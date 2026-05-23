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

    /**
     * Adds a product to the basket.
     * If the same product already exists, its quantity is increased.
     *
     * @param product the product to add
     * @return true if the product was added or updated
     */
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

    /**
     * Removes a product from the basket.
     * If entireProduct is true, the whole product is removed.
     * Otherwise, the product quantity is reduced by one.
     *
     * @param productNum the product number to remove
     * @param entireProduct true to remove the whole product
     * @return true if the product was found and removed or updated
     */
    public boolean remove(String productNum, boolean entireProduct) {

        if (productNum == null) {
            return false;
        }

        for (int i = 0; i < this.size(); i++) {

            Product product = this.get(i);

            if (product.getProductNum().equals(productNum)) {

                if (entireProduct || product.getQuantity() <= 1) {
                    this.remove(i);
                    return true;
                }

                product.setQuantity(product.getQuantity() - 1);
                return true;
            }
        }

        return false;
    }

    /**
     * Gets the total number of products in the basket.
     *
     * @return total product quantity
     */
    public int count_products() {

        int count = 0;

        for (Product product : this) {
            count += product.getQuantity();
        }

        return count;
    }

    /**
     * Gets the total cost of all products in the basket.
     *
     * @return total basket cost
     */
    public double getTotalCost() {

        double cost = 0.0;

        for (Product product : this) {
            cost += product.getPrice() * product.getQuantity();
        }

        return cost;
    }
}
