import java.util.ArrayList;
import java.util.List;

/*
제품 셋팅 ----------------------------------------------------------------
*/
interface PdInterface {
    List<Product> allProductList = CacheData.allProductList;
    List<MasterRc> masterRcList = CacheData.masterRcList;

    List<Product> getList(ProductType productType);
    List<MasterRc> getListMasterRc(ProductType productType);
}

// 입력받은 ProductType을 기준으로 리스트 생성
class ProductService implements PdInterface {
    @Override
    public List<Product> getList(ProductType productType) {
        List<Product> result = new ArrayList<>();

        for (Product product : allProductList) {
            if (product.getType().equals(productType)               // 해당하는 productType
                    && product.getP_count()>product.getP_stock()    // 개수가 재고보다 많을 때
                    && product.getSaleflag()                       // 판매셋팅 true
            ) {
                result.add(product);
            }
        }

        return result;
    }

    @Override
    public List<MasterRc> getListMasterRc(ProductType productType) {
        List<MasterRc> result = new ArrayList<>();

        for (MasterRc masterRc : masterRcList) {
            if (masterRc.getType().equals(productType)               // 해당하는 productType
                    && masterRc.getSaleflag()                       // 판매셋팅 true
            ) {
                result.add(masterRc);
            }
        }

        return result;
    }
}