import java.util.ArrayList;
import java.util.List;

/*
제품 셋팅 ----------------------------------------------------------------
*/
interface PdInterface {
    List<Product> productList = CacheData.allProductList;

    List<Product> getList(ProductType productType);
}

// 입력받은 ProductType을 기준으로 리스트 생성
class ProductService implements PdInterface {
    @Override
    public List<Product> getList(ProductType productType) {
        List<Product> result = new ArrayList<>();

        for (Product product : productList) {
            if (product.getType().equals(productType) && product.getP_count()>product.getP_stock()) {
                result.add(product);
            }
        }

        return result;
    }
}